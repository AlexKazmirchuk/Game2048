package com.alexkaz.game2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoseActivity extends AppCompatActivity {

    private TextView loseTxtScores;
    private Button loseRestartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lose);

        loseTxtScores = (TextView) findViewById(R.id.loseTxtScores);
        loseRestartBtn = (Button) findViewById(R.id.winOkBtn);
        int scores = getIntent().getIntExtra(GameFieldView.SCORES_VALUE,0);
        loseTxtScores.setText("YOUR SCORE:"+scores);
    }

    public void onRestartBtn(View view) {
        finish();
    }
}
