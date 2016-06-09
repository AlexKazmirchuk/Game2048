package com.alexkaz.game2048;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alexkaz.game2048.controllers.GamePreferences;
import com.alexkaz.game2048.controllers.SwipeDetector;
import com.alexkaz.game2048.gamelogic.Direction;
import com.alexkaz.game2048.gamelogic.GameFieldView;
import com.alexkaz.game2048.gamelogic.GameSurfaceView;
import com.alexkaz.game2048.uicomp.MenuActivity;

public class GameActivity extends AppCompatActivity {

    private GestureDetector gestureDetector;
    private GamePreferences gamePreferences;
    private FrameLayout gameFieldWrapper;
    private GameSurfaceView gameSurfaceView;
    private TextView txtScores, txtBestScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initComp();
        readPrefs();
    }

    private void initComp(){
        gestureDetector = initGestureDetector();
        gamePreferences = new GamePreferences(this);

        gameFieldWrapper = (FrameLayout) findViewById(R.id.gameFieldWrapper);
        txtScores = (TextView) findViewById(R.id.txtScores);
        txtBestScores = (TextView) findViewById(R.id.txtBestScores);

        gameSurfaceView = new GameSurfaceView(this);
        gameFieldWrapper.addView(gameSurfaceView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);

        gameSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        gameSurfaceView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
            }
        });
    }

    private void readPrefs() {
        int bestScores = gamePreferences.getBestScores();
        txtScores.setText("SCORES:0");
        txtBestScores.setText("BEST SCORES:" + bestScores);
    }

    private GestureDetector initGestureDetector() {
        return new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

            private SwipeDetector detector = new SwipeDetector();

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                try {
                    if (detector.isSwipeDown(e1, e2, velocityY)) {
                        gameSurfaceView.moveCells(Direction.DOWN);
                    } else if (detector.isSwipeUp(e1, e2, velocityY)) {
                        gameSurfaceView.moveCells(Direction.UP);
                    }else if (detector.isSwipeLeft(e1, e2, velocityX)) {
                        gameSurfaceView.moveCells(Direction.LEFT);
                    } else if (detector.isSwipeRight(e1, e2, velocityX)) {
                        gameSurfaceView.moveCells(Direction.RIGHT);
                    }
                } catch (Exception e) {} //for now, ignore
                return false;
            }

            private void showToast(String phrase){
                Toast.makeText(getApplicationContext(), phrase, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickMenuBtn(View view) {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
    }

    public void setScores(int points){
        txtScores.setText("SCORES:" + points);
        setBestScores(points);
    }

    private void setBestScores(int points){
        int bestScores = gamePreferences.getBestScores();

        if(points>bestScores){
            txtBestScores.setText("BEST SCORES:" + points);
            gamePreferences.setBestScores(points);
        }
    }
}
