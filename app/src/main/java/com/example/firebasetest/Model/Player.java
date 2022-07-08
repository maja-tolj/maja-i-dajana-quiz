package com.example.firebasetest.Model;

import com.example.vts_decija_edukacija_lib.InvalidUserNameException;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Comparable<Player> {

    private final String name;
    private final int score;

    public Player(String name, int score) throws InvalidUserNameException {
        if (validUserName(name)) {
            this.name = name;
            this.score = score;
        } else {
            throw new InvalidUserNameException(name + " is not a valid user name! regex[a-zA-z]+");
        }
    }

    protected static boolean validUserName (String user_name) {
        return user_name.matches("[a-zA-Z]+");
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    @Override
    public String toString () {
        return "Name: " + "\"" + name + "\"" + " Score: " + score;
    }

    @Override
    public int compareTo(Player o) {
        Integer temp = this.score;
        return temp.compareTo(o.getScore());
    }
}


