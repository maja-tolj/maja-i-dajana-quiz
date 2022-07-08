package com.example.vts_decija_edukacija_lib;

import java.io.Serializable;
import java.util.Objects;

public class Player implements Serializable, Comparable<Player> {

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
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }


    @Override
    public int compareTo( Player player) {
        Integer temp = this.score;
        return temp.compareTo(player.getScore());
    }

    @Override
    public String toString () {
        return "Name: " + "\"" + name + "\"" + " Score: " + score;
    }

}

