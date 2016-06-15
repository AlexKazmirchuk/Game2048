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

    private TextView winTxtScores;
    private Button winOkBtn;
    private String txtScores;

    public static WinDialogFragment newInstance(int scores){
        WinDialogFragment fragment = new WinDialogFragment();

        Bundle args = new Bundle();
        args.putInt("scores",scores);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.activity_win,null);

        winTxtScores = (TextView) v.findViewById(R.id.winTxtScores);
        winOkBtn = (Button) v.findViewById(R.id.winOkBtn);
        winOkBtn.setOnClickListener(this);
        txtScores = String.valueOf(getArguments().getInt("scores",0));
        winTxtScores.setText("YOUR SCORE:"+txtScores);

        return v;
    }

    @Override
    public void onClick(View v) {
        getDialog().cancel();
    }
}
