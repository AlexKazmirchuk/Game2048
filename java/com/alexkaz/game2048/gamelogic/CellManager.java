package com.alexkaz.game2048.gamelogic;

import android.graphics.Canvas;
import android.util.Log;

import com.alexkaz.game2048.GameActivity;

import java.util.Random;

public class CellManager {


    //Свойства
    private Cell[][] cells = new Cell[4][4];
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
        setParam();
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
    }

    private void setParam(){

    }

    public int getScores() {
        return scores;
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

        switch (direction){
            case UP:{
                moveAllUp(cells);
                System.out.println(scores);
                if (key){
                    isFull[0] = 1;
                }
                break;
            }
            case DOWN:{
//                System.out.println("всі донизу");
                moveAllDown(cells);
                System.out.println(scores);
                if (key){
                    isFull[1] = 1;
                }
                break;
            }
            case LEFT:{
//                System.out.println("всі вліво");
                moveAllLeft(cells);
                System.out.println(scores);
                if (key){
                    isFull[2] = 1;
                }
                break;
            }
            case RIGHT:{
//                System.out.println("всі вправо");
                moveAllRight(cells);
                System.out.println(scores);
                if (key){
                    isFull[3] = 1;
                }
                break;
            }
        }
        if (verifyCells()){
            spownCell();
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
//                scores = 0;
                lose = true;


//                System.out.println("Ви програли");

//                initComp();
            }
        }

    }

    public void spownCell(){
        int x = rand.nextInt(4);
        int y = rand.nextInt(4);

        if (cells[x][y].getId() == 0){
            cells[x][y].setId(2);
            return;
        }
        else{
            spownCell();
        }
    }

    private Cell[][] moveAllDown(Cell[][] cells){
        for (int i = 0; i <cells.length ; i++) {
            cells[i] = moveLineRight(cells[i]);
        }
        return cells;
    }

    private Cell[][] moveAllUp(Cell[][] cells){
        for (int i = 0; i <cells.length ; i++) {
            cells[i] = moveLineLeft(cells[i]);
        }
        return cells;
    }

    private Cell[][] moveAllLeft(Cell[][] cells){
        Cell[] line = new Cell[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                line[j] = cells[j][i];
            }
            line = moveLineLeft(line);
            for (int j = 0; j < 4; j++) {
                cells[j][i]=line[j];
            }
        }
        return cells;
    }

    private Cell[][] moveAllRight(Cell[][] cells){
        Cell[] line = new Cell[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                line[j] = cells[j][i];
            }
//            System.out.println();
            line = moveLineRight(line);
            for (int j = 0; j < 4; j++) {
                cells[j][i]=line[j];
            }
        }
        return cells;
    }

    private Cell[] moveLineRight(Cell[] line){

        line = sortLineRight(line);
        line = mergeLineRight(line);
        line = sortLineRight(line);

        return line;
    }

    private Cell[] sortLineRight(Cell[] line){

        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
                if(j != 0) {
                    if (line[j].getId() == 0 && line[j - 1].getId() != 0) {
                        int buf = line[j].getId();
                        line[j].setId(line[j - 1].getId()) ;
                        line[j - 1].setId(buf);
                    }
                }
            }
        }
        return line;
    }

    private Cell[] mergeLineRight(Cell[] line){
        for (int i = 1; i > 0; i--) {
            for (int j = 3; j > 0; j--) {
                if(j != 0) {
                    if (line[j].getId() == line[j-1].getId()){
                        line[j].setId(line[j].getId() + line[j-1].getId());
                        if(line[j].getId() > 0){
                            line[j].setFresh(true);
                            line[j].setShearR(Cell.SHEAR_MAX);
                        }
                        scores = scores + line[j].getId();
                        line[j-1].setId(0);
                    }
                }
            }
        }
        return line;
    }

    private Cell[] moveLineLeft(Cell[] line){

        line = sortLineLeft(line);
        line = mergeLineLeft(line);
        line = sortLineLeft(line);

        return line;
    }

    private Cell[] sortLineLeft(Cell[] line){

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(j != 3) {
                    if (line[j].getId() == 0 && line[j + 1].getId() != 0) {
                        int buf = line[j].getId();
                        line[j].setId(line[j + 1].getId()) ;
                        line[j + 1].setId(buf);
                    }
                }
            }
        }
        return line;
    }

    private Cell[] mergeLineLeft(Cell[] line){
        for (int j = 0; j < 4; j++) {
            if(j != 3) {
                if (line[j].getId() == line[j+1].getId()){
                    line[j].setId(line[j].getId() + line[j+1].getId());
                    if(line[j].getId() > 0){
                        line[j].setFresh(true);
                        line[j].setShearR(Cell.SHEAR_MAX);
                    }
                    scores = scores + line[j].getId();
                    line[j+1].setId(0);
                }
            }
        }
        return line;
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
                if (cell[i][j].isFresh()){
                    if (cell[i][j].getShearR() > 0){
                        cell[i][j].setShearR(cell[i][j].getShearR()-1);
                    } else {
                        cell[i][j].setFresh(false);
                    }
                }
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }
}
