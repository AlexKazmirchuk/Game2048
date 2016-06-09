package com.alexkaz.game2048.gamelogic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.alexkaz.game2048.GameActivity;

public class GameSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private DrawThreat drawThreat;
    private GameActivity context;

    public GameSurfaceView(GameActivity context) {
        super(context);
        this.context = context;
        getHolder().addCallback(this);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawThreat = new DrawThreat(context, getHolder());
        drawThreat.setRunning(true);
        drawThreat.start();
    }

    public void moveCells(Direction direction){
        drawThreat.moveCells(direction);
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        drawThreat.setRunning(false);
        while (retry){
            try {
                drawThreat.join();
                retry = false;
            } catch (InterruptedException e){

            }
        }
    }
}
