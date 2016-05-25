package com.alexkaz.game2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private GamePreferences gamePreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        gamePreferences = new GamePreferences(this);
    }

    public void onClickBtn(View view) {
        switch (view.getId()){
            case R.id.resumeBtn:

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
