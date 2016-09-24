package com.alexkaz.game2048.gamelogic;

import android.graphics.Canvas;

import com.alexkaz.game2048.GameActivity;
import java.util.Random;

public class CellManager {

    //Свойства
    private GameActivity context;
    private static Direction swipeDirection;
    private CellForBG cellsForBG;
    private Cell[][] cells = new Cell[4][4];
    private int[][] cellsId = new int[4][4];
    private int[] isFull = new int[]{0,0,0,0};
    private int scores = 0;
    private boolean key = false;
    private boolean lose = false;
    private Random rand = new Random();

    //Конструктор
    public CellManager(GameActivity context){
        this.context = context;
        initComp();
    }

    public static Direction getSwipeDirection() {
        return swipeDirection;
    }

    public static void setSwipeDirection(Direction swipeDirection) {
        CellManager.swipeDirection = swipeDirection;
    }

    //Методи
    public void initComp(){
        cellsForBG = new CellForBG(this.context);
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
        cellsForBG.draw(g);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length ; j++) {
                if (cells[j][i] == null){
                    return;
                }
                if (getSwipeDirection() == Direction.RIGHT || getSwipeDirection() == Direction.LEFT){
                    if (cells[j][i].getMoveX() > 0){
                        cells[j][i].setMoveX(cells[j][i].getMoveX() - 40);
                    } else {
                        cells[j][i].setMoveX(0);
                    }
                } else if(getSwipeDirection() == Direction.DOWN || getSwipeDirection() == Direction.UP){
                    if (cells[j][i].getMoveY() > 0){
                        cells[j][i].setMoveY(cells[j][i].getMoveY() - 40);
                    } else {
                        cells[j][i].setMoveY(0);
                    }
                }
                if (cells[i][j] != null){
                    cells[i][j].draw(g);
                }
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
                CellMergeSortHandler.moveAllUp(cells,this);
                System.out.println(getScores());
                if (key){
                    isFull[0] = 1;
                }
                break;
            }
            case DOWN:{
                CellMergeSortHandler.moveAllDown(cells,this);
                System.out.println(getScores());
                if (key){
                    isFull[1] = 1;
                }
                break;
            }
            case LEFT:{
                CellMergeSortHandler.moveAllLeft(cells,this);
                System.out.println(getScores());
                if (key){
                    isFull[2] = 1;
                }
                break;
            }
            case RIGHT:{
                CellMergeSortHandler.moveAllRight(cells,this);
                System.out.println(getScores());
                if (key){
                    isFull[3] = 1;
                }
                break;
            }
        }
        if (verifyCells()){

            if (!isCellsIdChanged()){
                spawnCell();
            }

            key = false;
            isFull[0] = 0;
            isFull[1] = 0;
            isFull[2] = 0;
            isFull[3] = 0;
            context.setScores(getScores());
        }
        else {
            key = true;
            int tmp;
            tmp = isFull[0] + isFull[1]+ isFull[2]+ isFull[3];
            if (tmp == 4){
                setLose(true);
            }
        }

    }

    public void spawnCell(){
        int x = rand.nextInt(4);
        int y = rand.nextInt(4);
        if (cells[x][y].getId() == 0){
            cells[x][y].setId(2);
            cells[x][y].setFresh(true);
            cells[x][y].setShearAnim(cells[x][y].getShearMax());
        }
        else{
            spawnCell();
        }
    }

    public void startNewGame(){
        this.setLose(false);
        this.key = false;
        initComp();
        this.setScores(0);
        this.isFull = new int[]{0,0,0,0};
    }

    public int getTheLargestNumber(){
        int largestNumber = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cells[i][j] != null ){
                    if (largestNumber<cells[i][j].getId()){
                        largestNumber = cells[i][j].getId();
                    }
                }
            }
        }
        return largestNumber;
    }

    public static void spawnCellAnimation(Cell[][] cell){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cell[i][j] == null){
                    return;
                }
                if (!(cell[i][j].getId() == 0)){
                    if (cell[i][j].isFresh()){
                        if (cell[i][j].getShearAnim() > 0){
                            cell[i][j].setShearAnim(cell[i][j].getShearAnim()-1);
                        } else {
                            cell[i][j].setFresh(false);
                        }
                    }
                } else {
                    cell[i][j].setShearAnim(0);
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

    public String getStringCellsID(){
        String cellsID = "";

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cellsID = cellsID + cells[i][j].getId() + " ";
            }
        }
        return cellsID;
    }

    public void setCellsIDFromString(String cellsID){
        String[] strArr = cellsID.split(" ");
        if (strArr.length == 16){
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    cells[i][j].setId(Integer.valueOf(strArr[i*4+j]));
                }
            }
        }
    }

    public boolean isLose() {
        return lose;
    }

    public void setLose(boolean lose) {
        this.lose = lose;
    }
}
