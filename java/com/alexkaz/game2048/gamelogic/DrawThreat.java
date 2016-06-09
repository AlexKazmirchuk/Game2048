package com.alexkaz.game2048.gamelogic;


import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

import com.alexkaz.game2048.GameActivity;
import com.alexkaz.game2048.uicomp.LoseActivity;
import com.alexkaz.game2048.uicomp.WinActivity;

public class DrawThreat extends Thread {

    private GameActivity context;
    private CellManager cellManager;
    public static final String SCORES_VALUE = "scoresValue";
    private boolean isWinActivityShowed  = true;

    private boolean runFlag = false;
    private SurfaceHolder surfaceHolder;
    private long prevTime;

    public DrawThreat(GameActivity context, SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        this.context = context;
        prevTime = System.currentTimeMillis();
        cellManager = new CellManager(context);
    }

    private void showLoseActivity(int scores) {
        Intent intent = new Intent(context,LoseActivity.class);
        intent.putExtra(SCORES_VALUE,scores);
        context.startActivity(intent);
    }

    private void showWinActivity(int scores){
        Intent intent = new Intent(context,WinActivity.class);
        intent.putExtra(SCORES_VALUE,scores);
        context.startActivity(intent);
    }

    public CellManager getCellManager() {
        return cellManager;
    }

    public void moveCells(Direction direction){
        cellManager.moveCells(direction);
    }

    @Override
    public void run() {
        Canvas canvas;
        while(runFlag){
            long now = System.currentTimeMillis();
            long elapsedTime  = now - prevTime;
            if (elapsedTime > 30){
                prevTime = now;
                canvas = null;
                try{
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder){
                        drawField(canvas);
                    }
                }finally {
                    if (canvas!= null){
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }
            }
        }
    }

    public void setRunning(boolean run){
        runFlag = run;
    }

    //TODO WRITE YOUR CODE HERE
    private void drawField(Canvas canvas){
        canvas.drawColor(Color.parseColor("#137b82"));
        if (!getCellManager().lose){
            cellManager.draw(canvas);
            if(2048 == cellManager.getTheLargestNumber() && isWinActivityShowed){
                showWinActivity(cellManager.getScores());
                isWinActivityShowed = false;
            }
        }
        else {
            showLoseActivity(cellManager.getScores());
            cellManager.startNewGame();
            cellManager.draw(canvas);
        }
    }
}
