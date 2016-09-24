package com.alexkaz.game2048.uicomp;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.alexkaz.game2048.GameActivity;
import com.alexkaz.game2048.R;

public class WinDialogFragment  extends DialogFragment implements View.OnClickListener {

    public static final String SCORES = "scores";
    public static final float DIM_AMOUNT = 0.8f;
    private GameActivity gameActivity;

    public static WinDialogFragment newInstance(int scores){
        WinDialogFragment fragment = new WinDialogFragment();

        Bundle args = new Bundle();
        args.putInt(SCORES,scores);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setDimAmount(DIM_AMOUNT);
        View v = inflater.inflate(R.layout.activity_win,container);

        TextView winTxtScores = (TextView) v.findViewById(R.id.winTxtScores);
        Button winOkBtn = (Button) v.findViewById(R.id.winOkBtn);
        winOkBtn.setOnClickListener(this);
        String txtScores = gameActivity.getString(R.string.dialog_text_score) + String.valueOf(getArguments().getInt(SCORES, 0));
        winTxtScores.setText(txtScores);

        winTxtScores.setTypeface(gameActivity.getTypeface());
        winOkBtn.setTypeface(gameActivity.getTypeface());
        ((TextView)v.findViewById(R.id.titleWin)).setTypeface(gameActivity.getTypeface());

        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        gameActivity = (GameActivity) activity;
    }

    @Override
    public void onClick(View v) {
        getDialog().cancel();
    }
}
