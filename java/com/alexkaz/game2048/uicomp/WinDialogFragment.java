package com.alexkaz.game2048.uicomp;

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
import com.alexkaz.game2048.R;

public class WinDialogFragment  extends DialogFragment implements View.OnClickListener {

    public static final String SCORES = "scores";
    public static final float DIM_AMOUNT = 0.8f;

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
        View v = inflater.inflate(R.layout.activity_win,null);

        TextView winTxtScores = (TextView) v.findViewById(R.id.winTxtScores);
        Button winOkBtn = (Button) v.findViewById(R.id.winOkBtn);
        winOkBtn.setOnClickListener(this);
        String txtScores = "YOUR SCORE:" + String.valueOf(getArguments().getInt(SCORES, 0));
        winTxtScores.setText(txtScores);

        return v;
    }

    @Override
    public void onClick(View v) {
        getDialog().cancel();
    }
}
