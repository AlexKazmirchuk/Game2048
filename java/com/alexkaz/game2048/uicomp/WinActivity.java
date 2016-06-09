package com.alexkaz.game2048.uicomp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alexkaz.game2048.gamelogic.GameFieldView;
import com.alexkaz.game2048.R;

public class WinActivity extends AppCompatActivity {

    private TextView winTxtScores;
    private Button winOkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);

        winTxtScores = (TextView) findViewById(R.id.winTxtScores);
        winOkBtn = (Button) findViewById(R.id.winOkBtn);
        int scores = getIntent().getIntExtra(GameFieldView.SCORES_VALUE,0);
        winTxtScores.setText("YOUR SCORE:"+scores);
    }

    public void onOk(View view) {
        finish();
    }
}
