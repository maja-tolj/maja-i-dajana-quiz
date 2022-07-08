package com.example.firebasetest.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.firebasetest.Model.Player;
import com.example.firebasetest.Repository.PlayerRepository;

import java.util.List;

public class HighscoreViewModel extends ViewModel {

    private MutableLiveData<List<Player>> topPlayers = new MutableLiveData<List<Player>>();
    private PlayerRepository repo = new PlayerRepository();
    LiveData liveData = repo.getTopPlayers(15);

    public LiveData<List<Player>> getTopPlayersLiveData() {
        return liveData;
    }

}
