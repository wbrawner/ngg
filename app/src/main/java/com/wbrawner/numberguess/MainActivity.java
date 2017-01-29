package com.wbrawner.numberguess;

import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements GameOverDialogFragment.GameOverListener {

    public EditText guessInput;
    public TextView guessResponse;
    public Player user;
    public NumberGuess ng;

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        this.resetGame();
    }

    public void gameOver(String message) {
        GameOverDialogFragment gameOverPrompt = new GameOverDialogFragment();
        gameOverPrompt.setMessage(message);
        gameOverPrompt.show(getFragmentManager(), "gameOver");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.guessInput = (EditText) findViewById(R.id.guessInput);
        resetGame();
    }

    public void resetGame() {
        this.guessInput.setText("50");
        this.user = null;
        this.ng = null;

        this.user = new Player();
        this.user.clearGuesses();

        this.ng = new NumberGuess();
        this.ng.setAnswer();
    }

    public void setResponse(Map response) {
        CharSequence text;
        if (response.get("code").equals("correct")) {
            String popup = String.format(getString(R.string.correct_answer), response.get("count"));
            text = getString(R.string.game_over);
            gameOver(popup);
        } else if (response.get("code").equals("too_big")) {
            text = getString(R.string.too_big);
        } else {
            text = getString(R.string.too_small);
        }
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void setResponse(String response) {
        this.guessResponse.setText(response);
    }

    public void onClickGuessButton(View view) {
        if (!guessInput.getText().toString().equals("")) {
            this.user.addGuess(
                    Integer.parseInt(
                            guessInput.getText().toString()
                    )
            );
        }
        this.setResponse(
                this.ng.checkAnswer(this.user)
        );
        this.guessInput.setText(null);
    }
}
