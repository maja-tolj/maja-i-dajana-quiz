package com.example.vts_decija_edukacija_lib;


import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class PlayerTest {

    @Test
    public void validUserName_returnsTrue() {
        assertThat(Player.validUserName("Maja")).isTrue();
    }

    @Test(expected = InvalidUserNameException.class)
    public void userConstructor_throwsExceptionForInvalidName() throws InvalidUserNameException {
        new Player("maja ", 200);
    }

}
