package com.example.vts_decija_edukacija_lib;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.google.common.truth.Truth.assertThat;

public class ScoreBoardTest {
    private static File file;

    @Before
    public void setUp() {
            file = new File("scoreboard-test.ser");
    }

    @After
    public void tearDown() {
        if (file.exists())
            file.delete();
    }

    @Test
    public void ScoreBoard_constructFile_thenReads_ShouldReturnTrue() {
        ScoreBoard board = new ScoreBoard(file);
        assertThat(file.exists()).isTrue();
        assertThat(board.read()).isTrue();
    }

    @Test
    public void ScoreBoard_readesNonExistentFile_returnsFalse() {
        ScoreBoard board = new ScoreBoard(file);
        file.delete();
        assertThat(board.read()).isFalse();
    }

    @Test
    public void creatingScoreBoard_adding_Users_andSavingState() {
        try {
            Player player = new Player("Maja", 200);
            Player player1 = new Player("nikola", 100);
            ScoreBoard board = new ScoreBoard(file);
            board.addUser(player);
            board.addUser(player1);
            board.save();

            ScoreBoard board2 = new ScoreBoard(file);
            board2.printAll();

            assertThat(board.getTop(5)).isEqualTo(board2.getTop(5));

        } catch (InvalidUserNameException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void ScoreBoard_reades_ExistentNonDeseriazibleFile_deletesFile() {

        try {
            FileWriter out = new FileWriter(file);
            out.write("DummyText");
            out.close();
            ScoreBoard board = new ScoreBoard(file);
           assertThat(board.internal_file.exists()).isFalse();
        } catch (IOException e) {
            file.delete();
            e.printStackTrace();
        }
    }


}
