package com.alexkaz.game2048;

import android.content.Intent;
import android.graphics.Canvas;
import android.view.View;

import com.alexkaz.game2048.gamelogic.CellManager;
import com.alexkaz.game2048.gamelogic.Direction;


public class GameFieldView extends View {

    private GameActivity context;
    private CellManager cellManager;
    public static final String SCORES_VALUE = "scoresValue";

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
////            System.out.println("Ви програли!!!!!!!!!!!!");
//            try {
//                Image lose = ImageIO.read(new File("src/res/YouLose.png"));
//                g.drawImage(lose, 0, 0, null);
//            } catch (IOException ex) {
//                System.out.println("не знайдено");
//            }
            showLoseActivity(cellManager.getScores());
            cellManager.startNewGame();
        }



    }

    private void showLoseActivity(int scores) {
        Intent intent = new Intent(context,LoseActivity.class);
        intent.putExtra(SCORES_VALUE,scores);
        context.startActivity(intent);
    }

    public CellManager getCellManager() {
        return cellManager;
    }


    public void moveCells(Direction direction){
        cellManager.moveCells(direction);
        invalidate();
    }
}
