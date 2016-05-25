package com.alexkaz.game2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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

                break;
        }
    }
}
