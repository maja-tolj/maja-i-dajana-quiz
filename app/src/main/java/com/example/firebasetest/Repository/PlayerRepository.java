package com.example.firebasetest.Repository;

import android.os.Build;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.firebasetest.Model.Player;
import com.example.vts_decija_edukacija_lib.InvalidUserNameException;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.core.OrderBy;

import java.util.ArrayList;
import java.util.List;

public class PlayerRepository {

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    private List<Player> topPlayersListTemp = new ArrayList<>();
    public MutableLiveData<List<Player>> topPlayersList = new MutableLiveData<List<Player>>();

    public LiveData<List<Player>> getTopPlayers(int number) {
        CollectionReference collectionReference = firebaseFirestore.collection("users");
        collectionReference
                .orderBy("score", Query.Direction.DESCENDING)
                .limit(number).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if (error != null) {
                    Log.w("Firestore", "Listen failed.", error);
                    return;
                }

                topPlayersListTemp.clear();
                for (QueryDocumentSnapshot doc : value) {
                    if (doc.get("name") != null) {
                        try {
                            Player playerToAdd = new Player(doc.getString("name"), Math.toIntExact((Long) doc.get("score")) );
                            topPlayersListTemp.add(playerToAdd);
                        } catch (InvalidUserNameException e) {
                            e.printStackTrace();
                        }
                    }
                }
                topPlayersList.setValue(topPlayersListTemp);
                Log.d("Firestore", "Highscore: " + topPlayersListTemp);
            }
        });
        return topPlayersList;
    }
}
