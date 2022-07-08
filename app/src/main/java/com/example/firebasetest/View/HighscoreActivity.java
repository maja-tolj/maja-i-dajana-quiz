package com.example.firebasetest.View;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.firebasetest.Model.Player;
import com.example.firebasetest.R;
import com.example.firebasetest.ViewModel.HighscoreViewModel;
import com.example.vts_decija_edukacija_lib.InvalidUserNameException;


public class HighscoreActivity extends AppCompatActivity {

    HighscoreViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);
        ListView myListView = findViewById(R.id.list);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        model = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
                .get(HighscoreViewModel.class);



        model.getTopPlayersLiveData().observe(this, topPlayers -> {
            ArrayAdapter<Player> myAdapter = new ArrayAdapter<Player>(HighscoreActivity.this, android.R.layout.simple_list_item_1, topPlayers);
            myListView.setAdapter(myAdapter);
            progressBar.setVisibility(View.GONE);
        });

    }
}