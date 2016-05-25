package com.alexkaz.game2048;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alexkaz.game2048.gamelogic.Direction;

public class GameActivity extends AppCompatActivity {

    private GestureDetector gestureDetector;
    private FrameLayout gameFieldWrapper;
    private GameFieldView gameFieldView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initComp();

    }

    private void initComp(){
        gestureDetector = initGestureDetector();

        gameFieldWrapper = (FrameLayout) findViewById(R.id.gameFieldWrapper);
        gameFieldView = new GameFieldView(this);
        gameFieldWrapper.addView(gameFieldView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);


        gameFieldView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });

        gameFieldView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
            }
        });
    }

    private GestureDetector initGestureDetector() {
        return new GestureDetector(new GestureDetector.SimpleOnGestureListener() {

            private SwipeDetector detector = new SwipeDetector();

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                try {
                    if (detector.isSwipeDown(e1, e2, velocityY)) {
                        gameFieldView.moveCells(Direction.DOWN);
                    } else if (detector.isSwipeUp(e1, e2, velocityY)) {
                        gameFieldView.moveCells(Direction.UP);
                    }else if (detector.isSwipeLeft(e1, e2, velocityX)) {
                        gameFieldView.moveCells(Direction.LEFT);
                    } else if (detector.isSwipeRight(e1, e2, velocityX)) {
                        gameFieldView.moveCells(Direction.RIGHT);
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
}
