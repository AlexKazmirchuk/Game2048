package com.alexkaz.game2048;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import com.alexkaz.game2048.gamelogic.CellManager;

import java.io.File;
import java.io.IOException;

public class GameFieldView extends View {

    private GameActivity context;
    private CellManager cellManager;

    public GameFieldView(GameActivity context) {
        super(context);
        this.context = context;

        setOwnParam();
        initComp();
    }

    //Методи
    private void setOwnParam(){

    }

    private void initComp(){
        cellManager = new CellManager(context);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //TODO
        if (!getCellManager().lose){
            cellManager.draw(canvas);
        }
        else {
            System.out.println("Ви програли!!!!!!!!!!!!");
/*            try {
                Image lose = ImageIO.read(new File("src/res/YouLose.png"));
                g.drawImage(lose, 0, 0, null);
            } catch (IOException ex) {
                System.out.println("не знайдено");
            }*/
        }



    }

    public CellManager getCellManager() {
        return cellManager;
    }

    public void setCellManager(CellManager cellManager) {
        this.cellManager = cellManager;
    }
}
