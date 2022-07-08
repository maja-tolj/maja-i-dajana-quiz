package com.example.vts_decija_edukacija_lib;

import android.os.Build;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ScoreBoard {


    private ArrayList<Player> players = new ArrayList<Player>(); // Create an ArrayList object
    protected File internal_file;
    private FirebaseFirestore db;

    public ScoreBoard (File file) {
        this.internal_file = file;
        if(!(internal_file.exists()))
            save();
        else
            read();
    }

    public ScoreBoard (FirebaseFirestore db) {
        this.db = db;
    }

    public boolean read() {
        try {
            FileInputStream fileIn = new FileInputStream(internal_file.getCanonicalPath());
            ObjectInputStream in = getObjectInputStream(fileIn);
            players = (ArrayList<Player>) in.readObject();
            in.close();
            return true;
        } catch (IOException | ClassNotFoundException | NullPointerException e) {
            e.printStackTrace();
            System.out.println("Reading file " + internal_file.getName() + " failed!");
            internal_file.delete();
            return false;
        }
    }

    public boolean save() {
        try {
            FileOutputStream fileOut = new FileOutputStream(internal_file.getCanonicalPath());
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(players);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + internal_file.getCanonicalPath());
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Saving the data to Score Board failed!");
            return false;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public static void saveOnCloud(FirebaseFirestore db, Player player) {

        db.collection("users")
                .add(player)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("Firestore: ", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Firestore:", "Error adding document.", e);
                    }
                });
    }

    public static void readFromCloud(FirebaseFirestore db, TextView tv) {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("Firestore: ", document.getId() + " => " + document.getData());
                                tv.setText((String) tv.getText() + document.getData());
                            }
                        } else {
                            Log.d("Firestore: ", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private ObjectInputStream getObjectInputStream(FileInputStream fileIn) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(fileIn);
            return in;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Opening " + internal_file.getName() + " failed! Reason: file is corrupt.");
            try {
                fileIn.close();
                in.close();
            } catch (IOException | NullPointerException exception) {
                exception.printStackTrace();
                System.out.println("Couldn't close ObjectInputStream!");
                return in;
            }
            return in;
        }
    }

    public void addUser(Player player) {
        if (!players.contains(player)) {
            players.add(player);
            System.out.println("User " + "\"" + player.getName() + "\"" + " added successfully.");
        } else {
            int index = players.indexOf(player);
            if (players.get(index).getScore() < player.getScore()){
                players.set(index, player);
                System.out.println("User " + "\"" + player.getName() + "\"" + " updated successfully.");
            }
        }
        sortUsers();
    }

    public void deleteUser(String name) {
        int index;
        Player player = null;
        try {
            player = new Player(name, 0);
        } catch (InvalidUserNameException e) {
            e.printStackTrace();
        }
        if (players.contains(player)) {
            index = players.indexOf(player);
            players.remove(index);
            System.out.println("User " + "\"" + player.getName() + "\"" + " deleted successfully.");
        } else {
            System.out.println("User doesn't exist");
        }
    }

    private void sortUsers () {
        Collections.sort(players, Collections.reverseOrder());
    }

    public void printAll() {
        for(Player player : players){
            System.out.println(player.getName() + ": " + player.getScore());
            System.out.println("-----------------------------");
        }
    }

    public ArrayList<Player> getTop(int number) {
        ArrayList<Player> top_players = new ArrayList<Player>();
        if (players.size() < number)
        {
            System.out.println("ScoreBoard: Specified number of users to be returned exceedes Score Board length!");
            return top_players;
        }

        for (int i = 0; i < number; i++) {
            top_players.add(i, players.get(i));
        }
        return top_players;
    }

}
