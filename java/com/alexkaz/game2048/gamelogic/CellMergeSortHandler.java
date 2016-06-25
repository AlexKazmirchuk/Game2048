package com.alexkaz.game2048.gamelogic;


public class CellMergeSortHandler {

    public static Cell[][] moveAllDown(Cell[][] cells, CellManager cellManager){
        for (int i = 0; i <cells.length ; i++) {
            cells[i] = moveLineRight(cells[i], cellManager);
        }
        return cells;
    }

    public static Cell[][] moveAllUp(Cell[][] cells, CellManager cellManager){
        for (int i = 0; i <cells.length ; i++) {
            cells[i] = moveLineLeft(cells[i], cellManager);
        }
        return cells;
    }

    public static Cell[][] moveAllLeft(Cell[][] cells, CellManager cellManager){
        Cell[] line = new Cell[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                line[j] = cells[j][i];
            }
            line = moveLineLeft(line, cellManager);
            for (int j = 0; j < 4; j++) {
                cells[j][i]=line[j];
            }
        }
        return cells;
    }

    public static Cell[][] moveAllRight(Cell[][] cells, CellManager cellManager){
        Cell[] line = new Cell[4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                line[j] = cells[j][i];
            }
            line = moveLineRight(line, cellManager);
            for (int j = 0; j < 4; j++) {
                cells[j][i]=line[j];
            }
        }
        return cells;
    }


    public static Cell[] moveLineRight(Cell[] line,CellManager cellManager){
        line = sortLineRight(line);
        line = mergeLineRight(line, cellManager);
        line = sortLineRight(line);
        return line;
    }

    public static Cell[] sortLineRight(Cell[] line){
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

    public static Cell[] mergeLineRight(Cell[] line, CellManager cellManager){
        for (int i = 1; i > 0; i--) {
            for (int j = 3; j > 0; j--) {
                if (line[j].getId() == line[j-1].getId()){
                    line[j].setId(line[j].getId() + line[j-1].getId());
                    if(line[j].getId() > 0){
                        line[j].setFresh(true);
                        line[j].setShearAnim(line[j].getShearMax());
                    }
                    cellManager.setScores(cellManager.getScores() + line[j].getId());
//                    scores = scores + line[j].getId();
                    line[j-1].setId(0);
                }
            }
        }
        return line;
    }

    public static Cell[] moveLineLeft(Cell[] line, CellManager cellManager){
        line = sortLineLeft(line);
        line = mergeLineLeft(line, cellManager);
        line = sortLineLeft(line);
        return line;
    }

    public static Cell[] sortLineLeft(Cell[] line){
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

    public static Cell[] mergeLineLeft(Cell[] line, CellManager cellManager){
        for (int j = 0; j < 4; j++) {
            if(j != 3) {
                if (line[j].getId() == line[j+1].getId()){
                    line[j].setId(line[j].getId() + line[j+1].getId());
                    if(line[j].getId() > 0){
                        line[j].setFresh(true);
                        line[j].setShearAnim(line[j].getShearMax());
                    }
                    cellManager.setScores(cellManager.getScores() + line[j].getId());
//                    scores = scores + line[j].getId();
                    line[j+1].setId(0);
                }
            }
        }
        return line;
    }

}
