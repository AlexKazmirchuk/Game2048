package com.alexkaz.game2048.gamelogic;


import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

import com.alexkaz.game2048.GameActivity;
import com.alexkaz.game2048.uicomp.LoseDialogFragment;
import com.alexkaz.game2048.uicomp.WinDialogFragment;

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

    private void showLoseDialog(int scores) {
        LoseDialogFragment loseDialogFragment = LoseDialogFragment.newInstance(scores);
        loseDialogFragment.show(context.getFragmentManager(),"loseDialog");
    }

    private void showWinDialog(int scores){
        WinDialogFragment winDialogFragment = WinDialogFragment.newInstance(scores);
        winDialogFragment.show(context.getFragmentManager(),"loseDialog");
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
            if (elapsedTime > 40){
                prevTime = now;
                canvas = null;
                CellManager.spawnCellAnimation(cellManager.getCells());
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
                showWinDialog(cellManager.getScores());
                isWinActivityShowed = false;
            }
        }
        else {
            showLoseDialog(cellManager.getScores());
            cellManager.startNewGame();
            cellManager.draw(canvas);
        }
    }
}
