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

import com.alexkaz.game2048.GameActivity;
import com.alexkaz.game2048.R;
import com.alexkaz.game2048.controllers.GamePreferences;

public class MenuDialogFragment extends DialogFragment implements View.OnClickListener {

    private GameActivity gameActivity;
    private GamePreferences gamePreferences;
    private Button resumeBtn, restartBtn, modeBtn, musicBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setDimAmount(0.8f);
        View v = inflater.inflate(R.layout.activity_menu,null);

        initComps(v);
        readPrefs();

        return v;
    }

    private void initComps(View v) {
        resumeBtn = (Button)v.findViewById(R.id.resumeBtn);
        restartBtn = (Button)v.findViewById(R.id.restartBtn);
        modeBtn = (Button)v.findViewById(R.id.modeBtn);
        musicBtn = (Button)v.findViewById(R.id.musicBtn);

        resumeBtn.setOnClickListener(this);
        restartBtn.setOnClickListener(this);
        modeBtn.setOnClickListener(this);
        musicBtn.setOnClickListener(this);

        gamePreferences = new GamePreferences(getActivity());
    }

    private void readPrefs() {
        if (gamePreferences.getMusicPrefs()){
            musicBtn.setText("MUSIC:ON");
        }else {
            musicBtn.setText("MUSIC:OFF");
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        gameActivity = (GameActivity) activity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.resumeBtn:
                getDialog().cancel();
                break;
            case R.id.restartBtn:
                gameActivity.restartGame();
                getDialog().cancel();
                break;
            case R.id.modeBtn:

                break;
            case R.id.musicBtn:
                if (gamePreferences.getMusicPrefs()){
                    ((Button)v).setText("MUSIC:OFF");
                    gamePreferences.setMusicPrefs(false);
                    gameActivity.setMusicEnabled(false);
                    gameActivity.stopSound();
                }else {
                    ((Button)v).setText("MUSIC:ON");
                    gamePreferences.setMusicPrefs(true);
                    gameActivity.setMusicEnabled(true);
                }
                break;
        }
    }
}
