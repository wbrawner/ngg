package com.wbrawner.numberguess;

import java.util.List;
import java.util.ArrayList;

public class Player {

    protected List<Integer> guesses = new ArrayList<Integer>();

    public void clearGuesses() {
        this.guesses = new ArrayList<Integer>();
    }

    public void addGuess(int guess) {
        this.guesses.add(guess);
    }
    
    public int getLastGuess() {
        return this.guesses.get(this.guesses.size() - 1);
    }

    public List<Integer> getGuesses() {
        return this.guesses;
    }

}
