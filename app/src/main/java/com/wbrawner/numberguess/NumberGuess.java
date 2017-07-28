package com.wbrawner.numberguess;

import java.util.HashMap;
import java.util.Map;

public class NumberGuess {

    private int answer = 0;

    public void setAnswer() {
        this.answer = (int) (Math.random() * 100);
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getAnswer() {
        return this.answer;
    }

    public Map checkAnswer(int guess) {
        Map<String, String> response = new HashMap<>();
        if (guess == this.getAnswer()) {
            response.put("code", "correct");
        } else {
            if (guess > this.getAnswer()) {
                response.put("code", "too_big");
            } else {
                // If the number wasn't equal to the answer and it wasn't too
                // big, it must be too small
                response.put("code", "too_small");
            }
        }
        return response;
    }

}
