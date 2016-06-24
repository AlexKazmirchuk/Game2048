package com.alexkaz.game2048.gamelogic;

import android.graphics.Canvas;
import android.util.Log;

import com.alexkaz.game2048.GameActivity;

import java.util.Random;

public class CellManager {


    //Свойства
    private Cell[][] cells = new Cell[4][4];
    private int[][] cellsId = new int[4][4];
    public int scores = 0;
//    private Graphics g;
    private Random rand = new Random();
    private int[] isFull = new int[]{0,0,0,0};
    private boolean key = false;
    public boolean lose = false;
    private GameActivity context;

    //Конструктор
    public CellManager(GameActivity context){
        this.context = context;
        initComp();
    }


    //Методи
    public void initComp(){
        for (int i = 0; i < cells.length; i++) {
            cells[i] = new Cell[4];
            for (int j = 0; j <cells[i].length ; j++) {
                cells[i][j] = new Cell(context,i,j);
            }
        }

        cells[rand.nextInt(4)][rand.nextInt(4)].setId(2);
        cellsId[0] = new int[4];
        cellsId[1] = new int[4];
        cellsId[2] = new int[4];
        cellsId[3] = new int[4];

    }


    public int getScores() {
        return scores;
    }

    public void setScores(int scores) {
        this.scores = scores;
    }

    public void draw(Canvas g){
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j <cells[i].length ; j++) {
                cells[i][j].draw(g);
            }
        }
    }

    public boolean verifyCells(){
        for (int i = 0; i <cells.length ; i++) {
            for (int j = 0; j <cells[i].length ; j++) {
                if(cells[i][j].getId() == 0){
                    return true;
                }
            }
        }
        return false;
    }

    public void moveCells(Direction direction){

        saveCellId();

        switch (direction){
            case UP:{
//                moveAllUp(cells);
                CellMergeSortHandler.moveAllUp(cells,this);
                System.out.println(scores);
                if (key){
                    isFull[0] = 1;
                }
                break;
            }
            case DOWN:{
//                moveAllDown(cells);
                CellMergeSortHandler.moveAllDown(cells,this);
                System.out.println(scores);
                if (key){
                    isFull[1] = 1;
                }
                break;
            }
            case LEFT:{
//                moveAllLeft(cells);
                CellMergeSortHandler.moveAllLeft(cells,this);
                System.out.println(scores);
                if (key){
                    isFull[2] = 1;
                }
                break;
            }
            case RIGHT:{
//                moveAllRight(cells);
                CellMergeSortHandler.moveAllRight(cells,this);
                System.out.println(scores);
                if (key){
                    isFull[3] = 1;
                }
                break;
            }
        }
        if (verifyCells()){

            if (!isCellsIdChanged()){
                spownCell();
            }

            key = false;
            isFull[0] = 0;
            isFull[1] = 0;
            isFull[2] = 0;
            isFull[3] = 0;
            context.setScores(scores);
        }
        else {
            key = true;
            int tmp;
            tmp = isFull[0] + isFull[1]+ isFull[2]+ isFull[3];
            if (tmp == 4){
                //////////////////////////////
                /////////////////////////////   Зробити нормально
                ////////////////////////////
                lose = true;
            }
        }

    }

    public void spownCell(){
        int x = rand.nextInt(4);
        int y = rand.nextInt(4);

        if (cells[x][y].getId() == 0){
            cells[x][y].setId(2);
            cells[x][y].setFresh(true);
            cells[x][y].setShearR(Cell.SHEAR_MAX);
            return;
        }
        else{
            spownCell();
        }
    }

    public void startNewGame(){
        this.lose = false;
        this.key = false;
        initComp();
        this.scores = 0;
        this.isFull = new int[]{0,0,0,0};
    }

    public int getTheLargestNumber(){
        int largestNumber = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (largestNumber<cells[i][j].getId()){
                    largestNumber = cells[i][j].getId();
                }
            }
        }
        Log.d("largestNumber",String.valueOf(largestNumber));
        return largestNumber;
    }

    public static void spawnCellAnimation(Cell[][] cell){

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (!(cell[i][j].getId() == 0)){
                    if (cell[i][j].isFresh()){
                        if (cell[i][j].getShearR() > 0){
                            cell[i][j].setShearR(cell[i][j].getShearR()-1);
                        } else {
                            cell[i][j].setFresh(false);
                        }
                    }
                } else {
                    cell[i][j].setShearR(0);
                    cell[i][j].setFresh(false);
                }
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    private void saveCellId(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cellsId[j][i] = cells[j][i].getId();
            }
        }
    }

    private boolean isCellsIdChanged(){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cellsId[j][i] != cells[j][i].getId()){
                    return false;
                }
            }
        }
        return true;
    }
}
