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
    private Button musicBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setDimAmount(0.8f);
        View v = inflater.inflate(R.layout.activity_menu,container);

        initComps(v);
        readPrefs();

        return v;
    }

    private void initComps(View v) {
        Button resumeBtn = (Button) v.findViewById(R.id.resumeBtn);
        Button restartBtn = (Button) v.findViewById(R.id.restartBtn);
        Button clearResultsBtn = (Button) v.findViewById(R.id.clearResultsBtn);
        musicBtn = (Button)v.findViewById(R.id.musicBtn);

        resumeBtn.setOnClickListener(this);
        restartBtn.setOnClickListener(this);
        clearResultsBtn.setOnClickListener(this);
        musicBtn.setOnClickListener(this);

        resumeBtn.setTypeface(gameActivity.getTypeface());
        restartBtn.setTypeface(gameActivity.getTypeface());
        clearResultsBtn.setTypeface(gameActivity.getTypeface());
        musicBtn.setTypeface(gameActivity.getTypeface());

        gamePreferences = new GamePreferences(getActivity());
    }

    private void readPrefs() {
        if (gamePreferences.getMusicPrefs()){
            musicBtn.setText(R.string.music_btn_text_on);
        }else {
            musicBtn.setText(R.string.music_btn_text_off);
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
                restartGame();
                break;
            case R.id.clearResultsBtn:
                clearResults();
                break;
            case R.id.musicBtn:
                musicBtnHandler(v);
                break;
        }
    }

    private void restartGame(){
        gameActivity.restartGame();
        getDialog().cancel();
    }

    private void clearResults(){
        gamePreferences.setGameScores(0);
        gamePreferences.setBestScores(0);
        gameActivity.setScores(0);
        gameActivity.setTxtBestScores(0);
        gameActivity.restartGame();
        getDialog().cancel();
    }

    private void musicBtnHandler(View v){
        if (gamePreferences.getMusicPrefs()){
            ((Button)v).setText(R.string.music_btn_text_off);
            gamePreferences.setMusicPrefs(false);
            gameActivity.setMusicEnabled(false);
            gameActivity.getSoundController().stopSound();
        }else {
            ((Button)v).setText(R.string.music_btn_text_on);
            gamePreferences.setMusicPrefs(true);
            gameActivity.setMusicEnabled(true);
        }
    }
}
