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
    private boolean isWinActivityShowed  = true;
    private boolean isLoseDialogShow = false;

    private boolean runFlag = false;
    private final SurfaceHolder surfaceHolder;
    private long prevTime;

    public DrawThreat(GameActivity context, SurfaceHolder surfaceHolder){
        this.surfaceHolder = surfaceHolder;
        this.context = context;
        setWinActivityShowed(context.getGamePreferences().getWinDialogShowed());
        prevTime = System.currentTimeMillis();
        cellManager = new CellManager(context);
    }

    private void showLoseDialog(int scores) {
        LoseDialogFragment loseDialogFragment = LoseDialogFragment.newInstance(scores);
        loseDialogFragment.setCellManager(cellManager);
        loseDialogFragment.setDrawThreat(this);
        context.getSoundController().playGameOverSound();
        loseDialogFragment.show(context.getFragmentManager(),"loseDialog");
    }

    private void showWinDialog(int scores){
        WinDialogFragment winDialogFragment = WinDialogFragment.newInstance(scores);
        context.getSoundController().playWinSound();
        winDialogFragment.show(context.getFragmentManager(),"winDialog");
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
            if (elapsedTime > 5){
                prevTime = now;
                canvas = null;
                CellManager.spawnCellAnimation(cellManager.getCells());
                try{
                    canvas = surfaceHolder.lockCanvas();
                    synchronized (surfaceHolder){
                        if (canvas!=null){
                            drawField(canvas);
                        }
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

    private void drawField(Canvas canvas){
        canvas.drawColor(Color.parseColor("#137b82"));
        if (!getCellManager().isLose()){
            cellManager.draw(canvas);
            if(2048 == cellManager.getTheLargestNumber() && isWinActivityShowed()){
                showWinDialog(cellManager.getScores());
                setWinActivityShowed(false);
                context.getGamePreferences().setWinDialogShowed(false);
            }
        }
        else {
            if (!isLoseDialogShow()){
                showLoseDialog(cellManager.getScores());
                setLoseDialogShow(true);
            }
            cellManager.draw(canvas);
        }
    }

    public boolean isWinActivityShowed() {
        return isWinActivityShowed;
    }

    public void setWinActivityShowed(boolean winActivityShowed) {
        isWinActivityShowed = winActivityShowed;
    }

    public boolean isLoseDialogShow() {
        return isLoseDialogShow;
    }

    public void setLoseDialogShow(boolean loseDialogShow) {
        isLoseDialogShow = loseDialogShow;
    }
}
