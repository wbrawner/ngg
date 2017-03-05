package com.wbrawner.numberguess;

import android.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
    implements GameOverDialogFragment.GameOverListener {

    private String GAME_FRAGMENT_TAG = "GF_TAG";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_main, new GameFragment(), GAME_FRAGMENT_TAG)
                    .commit();
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        GameFragment gameFragment = (GameFragment) getFragmentManager()
                .findFragmentByTag(GAME_FRAGMENT_TAG);
        gameFragment.resetGame();
    }
}
