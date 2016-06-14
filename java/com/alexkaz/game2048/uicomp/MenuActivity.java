package com.alexkaz.game2048.uicomp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alexkaz.game2048.controllers.GamePreferences;
import com.alexkaz.game2048.R;

public class MenuActivity extends AppCompatActivity {

    private GamePreferences gamePreferences;
    private Button resumeBtn, restartBtn, modeBtn, musicBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initComps();
        gamePreferences = new GamePreferences(this);
        readPrefs();
    }

    private void initComps() {
        resumeBtn = (Button)findViewById(R.id.resumeBtn);
        restartBtn = (Button)findViewById(R.id.restartBtn);
        modeBtn = (Button)findViewById(R.id.modeBtn);
        musicBtn = (Button)findViewById(R.id.musicBtn);
    }

    private void readPrefs() {
        if (gamePreferences.getMusicPrefs()){
            musicBtn.setText("Music:on");
        }else {
            musicBtn.setText("Music:off");
        }
    }

    public void onClickBtn(View view) {
        switch (view.getId()){
            case R.id.resumeBtn:
                finish();
                break;
            case R.id.restartBtn:

                break;
            case R.id.modeBtn:

                break;
            case R.id.musicBtn:
                if (gamePreferences.getMusicPrefs()){
                    ((Button)view).setText("Music:off");
                    gamePreferences.setMusicPrefs(false);
                }else {
                    ((Button)view).setText("Music:on");
                    gamePreferences.setMusicPrefs(true);
                }
                break;
        }
    }
}
