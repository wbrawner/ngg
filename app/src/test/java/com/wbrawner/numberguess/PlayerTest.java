package com.wbrawner.numberguess;

import android.support.annotation.NonNull;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import static org.junit.Assert.*;

/**
 * Created by billy on 7/28/2017.
 */
public class PlayerTest {
    private Player player;

    @Before
    public void setUp() {
        player = new Player(new int[] {25, 50, 75});
    }

    @Test
    public void player() {
        Player newPlayer = new Player();
        assertEquals("New player has no guesses", 0, newPlayer.getGuessCount());
    }

    @Test
    public void clearGuesses() throws Exception {
        player.clearGuesses();
        assertEquals("clearGuesses", 0, player.getGuessCount());
    }

    @Test
    public void addGuess() throws Exception {
        player.addGuess(90);
        assertEquals("Added guess 90 - should be last guess", 90, player.getLastGuess());
        assertEquals("Added guess 90 - guess count should be 4", 4, player.getGuessCount());
    }

    @Test
    public void getGuessCount() throws Exception {
        assertEquals("Guess count equals 3", 3, player.getGuessCount());
    }

    @Test
    public void getLastGuess() throws Exception {
        assertEquals("Last guess is 75", 75, player.getLastGuess());
    }

    @Test
    public void getGuesses() throws Exception {
        List<Integer> startingGuesses =
                Arrays.asList(25, 50, 75);
        assertEquals("getGuesses", startingGuesses, player.getGuesses());
    }

}