package com.wbrawner.numberguess;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class GameOverDialogFragment extends DialogFragment {

    protected String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public interface GameOverListener {
        public void onDialogPositiveClick(DialogFragment dialog);
    }

    GameOverListener mListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (GameOverListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() +
            "must implement GameOverListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(this.message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                mListener.onDialogPositiveClick(GameOverDialogFragment.this);
            }
        });
        return builder.create();
    }
}
