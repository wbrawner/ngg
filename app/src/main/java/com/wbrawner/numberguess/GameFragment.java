package com.wbrawner.numberguess;

import android.app.DialogFragment;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;


public class GameFragment extends Fragment  {

    private TextView guessInput;
    private Button guessButton;
    private Player user;
    private NumberGuess ng;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_game, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_reset_game) {
            resetGame();
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_game, container, false);
        guessInput = (TextView) rootView.findViewById(R.id.guessInput);
        for (int i = 0; i <= 9; i++) {
            int buttonId = getActivity().getResources().getIdentifier(
                    "button_" + i,
                    "id",
                    getActivity().getPackageName()
            );
            final Button inputBtn = (Button) rootView.findViewById(buttonId);
            inputBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (guessInput.length() < 3)
                        guessInput.append(inputBtn.getText());
                }
            });
        }
        ((Button) rootView.findViewById(R.id.button_clear)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guessInput.setText("");
            }
        });
        ((Button) rootView.findViewById(R.id.button_delete)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guessInput.getText().length() > 0)
                    guessInput.setText(
                            guessInput.getText().toString().substring(
                                    0,
                                    guessInput.getText().length() - 1
                            )
                    );
            }
        });
        guessButton = (Button) rootView.findViewById(R.id.guessButton);
        guessButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!guessInput.getText().toString().equals("")) {
                    user.addGuess(
                            Integer.parseInt(
                                    guessInput.getText().toString()
                            )
                    );
                }
                setResponse(
                        ng.checkAnswer(user.getLastGuess())
                );
                guessInput.setText(null);
            }
        });
        resetGame();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public void gameOver(String message) {
        GameOverDialogFragment gameOverPrompt = new GameOverDialogFragment();
        gameOverPrompt.setMessage(message);
        gameOverPrompt.show(getActivity().getFragmentManager(), "gameOver");
    }

    public void resetGame() {
        guessInput.setText("50");
        user = null;
        ng = null;

        user = new Player();
        user.clearGuesses();

        ng = new NumberGuess();
        ng.setAnswer();
        // Log the number for quicker debugging
        Log.d("DEBUG", "Number: " + ng.getAnswer());
    }

    public void setResponse(Map response) {
        CharSequence text;
        Context context = getActivity();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, null, duration);
        if (response.get("code").equals("correct")) {
            String popup = getActivity().getString(R.string.correct_answer, user.getGuessCount());
            text = getString(R.string.game_over);
            gameOver(popup);
        } else {
            toast.setGravity(Gravity.CENTER, 0, 0);
            if (response.get("code").equals("too_big")) {
                text = getString(R.string.too_big);
            } else {
                text = getString(R.string.too_small);
            }
        }
        toast.setText(text);
        toast.show();
    }

}
