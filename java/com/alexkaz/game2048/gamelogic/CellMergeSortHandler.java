package com.alexkaz.game2048.gamelogic;


public class CellMergeSortHandler {

    private static int[][] lastID = new int[4][4];

    static {
        lastID[0] = new int[4];
        lastID[1] = new int[4];
        lastID[2] = new int[4];
        lastID[3] = new int[4];
    }

    private static void saveLastID(Cell[][] cells){
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                lastID[i][j] = cells[i][j].getId();
            }
        }
    }

    private static void searchMovingRight(Cell[][] cells){
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i >= 0; i--) {
                if(i==3){
                    if(cells[i][j].getId() == cells[i - 1][j].getMoving() && cells[i - 1][j].getMoving() != 0){
                        cells[i][j].setAnim(1);
                        if(cells[i][j].getId() == cells[i - 2][j].getMoving()){
                            cells[i][j].setAnim(cells[i][j].getAnim() + 1);
                            if(cells[i][j].getId() == cells[i - 3][j].getMoving()){
                                cells[i][j].setAnim(cells[i][j].getAnim() + 1);
                            }
                        }
                    }
                }
                if(i==2){
                    if(cells[i][j].getId() == cells[i - 1][j].getMoving() && cells[i - 1][j].getMoving() != 0){
                        cells[i][j].setAnim(1);
                        if(cells[i][j].getId() == cells[i - 2][j].getMoving()){
                            cells[i][j].setAnim(cells[i][j].getAnim() + 1);
                        }
                    }
                }
                if(i==1){
                    if(cells[i][j].getId() == cells[i - 1][j].getMoving() && cells[i - 1][j].getMoving() != 0){
                        cells[i][j].setAnim(1);
                    }
                }
            }
            if(lastID[0][j] != 0
                    && lastID[1][j] != 0
                    && lastID[2][j] == 0
                    && lastID[3][j] == 0){
                cells[3][j].setAnim(2);
            }
        }
    }

    private static void searchMovingLeft(Cell[][] cells){
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if(i==0){
                    if(cells[i][j].getId() == cells[i + 1][j].getMoving() && cells[i + 1][j].getMoving() != 0){
                        cells[i][j].setAnim(1);
                        if(cells[i][j].getId() == cells[i + 2][j].getMoving()){
                            cells[i][j].setAnim(cells[i][j].getAnim() + 1);
                            if(cells[i][j].getId() == cells[i + 3][j].getMoving()){
                                cells[i][j].setAnim(cells[i][j].getAnim() + 1);
                            }
                        }
                    }
                }
                if(i==1){
                    if(cells[i][j].getId() == cells[i + 1][j].getMoving() && cells[i + 1][j].getMoving() != 0){
                        cells[i][j].setAnim(1);
                        if(cells[i][j].getId() == cells[i + 2][j].getMoving()){
                            cells[i][j].setAnim(cells[i][j].getAnim() + 1);
                        }
                    }
                }
                if(i==2){
                    if(cells[i][j].getId() == cells[i + 1][j].getMoving() && cells[i + 1][j].getMoving() != 0){
                        cells[i][j].setAnim(1);
                    }
                }
            }
            if(lastID[0][j] == 0
                    && lastID[1][j] == 0
                    && lastID[2][j] != 0
                    && lastID[3][j] != 0){
                cells[0][j].setAnim(2);
            }
        }
    }

    private static void searchMovingDown(Cell[][] cells){
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i >= 0; i--) {
                if(i==3){
                    if(cells[j][i].getId() == cells[j][i - 1].getMoving() && cells[j][i - 1].getMoving() != 0){
                        cells[j][i].setAnim(1);
                        if(cells[j][i].getId() == cells[j][i - 2].getMoving()){
                            cells[j][i].setAnim(cells[j][i].getAnim() + 1);
                            if(cells[j][i].getId() == cells[j][i - 3].getMoving()){
                                cells[j][i].setAnim(cells[j][i].getAnim() + 1);
                            }
                        }
                    }
                }
                if(i==2){
                    if(cells[j][i].getId() == cells[j][i - 1].getMoving() && cells[j][i - 1].getMoving() != 0){
                        cells[j][i].setAnim(1);
                        if(cells[j][i].getId() == cells[j][i - 2].getMoving()){
                            cells[j][i].setAnim(cells[j][i].getAnim() + 1);
                        }
                    }
                }
                if(i==1){
                    if(cells[j][i].getId() == cells[j][i - 1].getMoving() && cells[j][i - 1].getMoving() != 0){
                        cells[j][i].setAnim(1);
                    }
                }
            }
            if(lastID[j][0] != 0
                    && lastID[j][1] != 0
                    && lastID[j][2] == 0
                    && lastID[j][3] == 0){
                cells[j][3].setAnim(2);
            }
        }
    }

    private static void searchMovingUp(Cell[][] cells){
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if(i==0){
                    if(cells[j][i].getId() == cells[j][i + 1].getMoving() && cells[j][i + 1].getMoving() != 0){
                        cells[j][i].setAnim(1);
                        if(cells[j][i].getId() == cells[j][i + 2].getMoving()){
                            cells[j][i].setAnim(cells[j][i].getAnim() + 1);
                            if(cells[j][i].getId() == cells[j][i + 3].getMoving()){
                                cells[j][i].setAnim(cells[j][i].getAnim() + 1);
                            }
                        }
                    }
                }
                if(i==1){
                    if(cells[j][i].getId() == cells[j][i + 1].getMoving() && cells[j][i + 1].getMoving() != 0){
                        cells[j][i].setAnim(1);
                        if(cells[j][i].getId() == cells[j][i + 2].getMoving()){
                            cells[j][i].setAnim(cells[j][i].getAnim() + 1);
                        }
                    }
                }
                if(i==2){
                    if(cells[j][i].getId() == cells[j][i + 1].getMoving() && cells[j][i + 1].getMoving() != 0){
                        cells[j][i].setAnim(1);
                    }
                }
            }
            if(lastID[j][0] == 0
                    && lastID[j][1] == 0
                    && lastID[j][2] != 0
                    && lastID[j][3] != 0){
                cells[j][0].setAnim(2);
            }
        }
    }


    private static void combinationFilterRight(Cell[][] cells){
        for (int j = 0; j < 4; j++) {
            int mergeCount = 0;
            int moveCount = 0;
            int mergePosition = 0;
            for (int i = 0; i < 4; i++) {
                if(cells[i][j].getMerged() != 0){
                    mergeCount = mergeCount + 1;
                }
                if(cells[i][j].getMoving() != 0){
                    moveCount = moveCount + 1;
                }
            }
            switch(mergeCount){
                case 2:
//                    System.out.println("Ця комбінація: |x|x|y|y|");
                    //////////////////////////
                    cells[2][j].setAnim(2);
                    cells[3][j].setAnim(1);

                    /////////////////////////
                    break;
                case 1:
                    for (int i = 0; i < 4; i++) {
                        if(cells[i][j].getMerged() != 0){
                            mergePosition = i;
                        }
                    }
                    switch(mergePosition){
                        case 3:
                            switch(moveCount){
                                case 3:
                                    if(cells[3][j].getMerged() == (cells[2][j].getMoving() *2)
                                            && cells[0][j].getMoving() == cells[2][j].getId()
                                            && cells[1][j].getMoving() == cells[2][j].getId()){
//                                        Log.d("animLog","[вправо]|y|x|x| |");
                                        //////////////////////////
                                        cells[3][j].setAnim(2);
                                        cells[2][j].setAnim(2);

                                        /////////////////////////
                                    } else if((lastID[0][j]*2) == cells[3][j].getMerged()
                                            && (lastID[2][j]*2) == cells[3][j].getMerged()){
//                                        Log.d("animLog","[вправо]|x| |x| |");
                                        //////////////////////////
                                        cells[3][j].setAnim(3);

                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вправо]|x|x| | |");
                                        //////////////////////////
                                        cells[3][j].setAnim(3);

                                        /////////////////////////
                                    }
                                    break;
                                case 2:
                                    if((cells[1][j].getMoving() *2) == cells[3][j].getMerged()
                                            && (cells[2][j].getMoving() *2) == cells[3][j].getMerged()){
//                                        Log.d("animLog","[вправо]| |x|x| |");
                                        //////////////////////////
                                        cells[3][j].setAnim(2);

                                        /////////////////////////
                                    } else if(cells[0][j].getMoving() != cells[1][j].getMoving()){
//                                        Log.d("animLog","[вправо]|z|y|x|x|");
                                        //////////////////////////
                                        cells[3][j].setAnim(1);
                                        cells[2][j].setAnim(1);
                                        cells[1][j].setAnim(1);
                                        /////////////////////////
                                    } else if((lastID[2][j]*2) == cells[3][j].getMerged()
                                            && (lastID[3][j]*2) == cells[3][j].getMerged()){
//                                        Log.d("animLog","[вправо]|y| |x|x|");
                                        //////////////////////////
                                        cells[3][j].setAnim(1);
                                        cells[2][j].setAnim(2);

                                        /////////////////////////
                                    } else if((lastID[1][j]*2) == cells[3][j].getMerged()
                                            && (lastID[3][j]*2) == cells[3][j].getMerged()){
//                                        Log.d("animLog","[вправо]|y|x| |x|");
                                        //////////////////////////
                                        cells[3][j].setAnim(2);
                                        cells[2][j].setAnim(2);

                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вправо]|x| | |x|");
                                        //////////////////////////
                                        cells[3][j].setAnim(3);

                                        /////////////////////////
                                    }
                                    break;
                                case 1:
                                    if((cells[1][j].getMoving() != 0)
                                            && ((cells[1][j].getMoving() *2) == cells[3][j].getMerged())
                                            && (cells[0][j].getId() == 0)
                                            && (cells[1][j].getId() == 0)
                                            && (cells[2][j].getId() == 0)){
//                                        Log.d("animLog","[вправо]| |x| |x|");
                                        //////////////////////////
                                        cells[3][j].setAnim(2);

                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вправо]| |y|x|x|");
                                        //////////////////////////
                                        cells[3][j].setAnim(1);
                                        cells[2][j].setAnim(1);

                                        /////////////////////////
                                    }
                                    break;
                                case 0:
//                                    Log.d("animLog","[вправо]| | |x|x|");
                                    //////////////////////////
                                    cells[3][j].setAnim(1);

                                    /////////////////////////
                                    break;
                            }
                            break;
                        case 2:
                            switch(moveCount){
                                case 3:
//                                    Log.d("animLog","[вправо]|x|x|y| |");
                                    //////////////////////////
                                    cells[3][j].setAnim(1);
                                    cells[2][j].setAnim(2);

                                    /////////////////////////
                                    break;
                                case 2:
//                                    Log.d("animLog","[вправо]|x|x| |y|");
                                    //////////////////////////
                                    cells[2][j].setAnim(2);

                                    /////////////////////////
                                    break;
                                case 1:
                                    if((cells[0][j].getMoving() != 0)
                                            && (cells[1][j].getMoving() == 0)
                                            && (cells[2][j].getMoving() == 0)
                                            && (cells[2][j].getMoving() == 0)
                                            && ((cells[0][j].getMoving() *2) == cells[2][j].getMerged())){
//                                        Log.d("animLog","[вправо]|x| |x|y|");
                                        //////////////////////////
                                        cells[2][j].setAnim(2);

                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вправо]|y|x|x|z|");
                                        //////////////////////////
                                        cells[1][j].setAnim(1);
                                        cells[2][j].setAnim(1);

                                        /////////////////////////
                                    }
                                    break;
                                case 0:
//                                    Log.d("animLog","[вправо]| |x|x|y|");
                                    //////////////////////////
                                    cells[2][j].setAnim(1);

                                    /////////////////////////
                                    break;
                            }
                            break;
                        case 1:
//                            Log.d("animLog","[вправо]|x|x|y|z|");
                            //////////////////////////
                            cells[1][j].setAnim(1);

                            /////////////////////////
                            break;
                    }
                    break;
                case 0:
                    if(moveCount != 0){
                        searchMovingRight( cells);
                    }
                    break;
            }
        }
    }

    private static void combinationFilterLeft(Cell[][] cells){
        for (int j = 0; j < 4; j++) {
            int mergeCount = 0;
            int moveCount = 0;
            int mergePosition = 0;
            for (int i = 0; i < 4; i++) {
                if(cells[i][j].getMerged() != 0){
                    mergeCount = mergeCount + 1;
                }
                if(cells[i][j].getMoving() != 0){
                    moveCount = moveCount + 1;
                }
            }
            switch(mergeCount){
                case 2:
//                    Log.d("animLog","[вліво]|y|y|x|x|");
                    //////////////////////////
                    cells[0][j].setAnim(1);
                    cells[1][j].setAnim(2);

                    /////////////////////////
                    break;
                case 1:
                    for (int i = 0; i < 4; i++) {
                        if(cells[i][j].getMerged() != 0){
                            mergePosition = i;
                        }
                    }
                    switch(mergePosition){
                        case 0:
                            switch(moveCount){
                                case 3:
                                    if(cells[0][j].getMerged() == (cells[1][j].getMoving() *2)
                                            && cells[3][j].getMoving() == cells[1][j].getId()
                                            && cells[2][j].getMoving() == cells[1][j].getId()){
//                                        Log.d("animLog","[вліво]| |x|x|y|");
                                        //////////////////////////
                                        cells[0][j].setAnim(2);
                                        cells[1][j].setAnim(2);

                                        /////////////////////////
                                    } else if((lastID[3][j]*2) == cells[0][j].getMerged()
                                            && (lastID[1][j]*2) == cells[0][j].getMerged()){
//                                        Log.d("animLog","[вліво]| |x| |x|");
                                        //////////////////////////
                                        cells[0][j].setAnim(3);

                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вліво]| | |x|x|");
                                        //////////////////////////
                                        cells[0][j].setAnim(3);

                                        /////////////////////////
                                    }
                                    break;
                                case 2:
                                    if((cells[2][j].getMoving() *2) == cells[0][j].getMerged()
                                            && (cells[1][j].getMoving() *2) == cells[0][j].getMerged()){
//                                        Log.d("animLog","[вліво]| |x|x| |");
                                        //////////////////////////
                                        cells[0][j].setAnim(2);

                                        /////////////////////////
                                    } else if(cells[3][j].getMoving() != cells[2][j].getMoving()){
//                                        Log.d("animLog","[вліво]|x|x|y|z|");
                                        //////////////////////////
                                        cells[0][j].setAnim(1);
                                        cells[1][j].setAnim(1);
                                        cells[2][j].setAnim(1);
                                        /////////////////////////
                                    } else if((lastID[1][j]*2) == cells[0][j].getMerged()
                                            && (lastID[0][j]*2) == cells[0][j].getMerged()){
//                                        Log.d("animLog","[вліво]|x|x| |y|");
                                        //////////////////////////
                                        cells[0][j].setAnim(1);
                                        cells[1][j].setAnim(2);
                                        /////////////////////////
                                    } else if((lastID[2][j]*2) == cells[0][j].getMerged()
                                            && (lastID[0][j]*2) == cells[0][j].getMerged()){
//                                        Log.d("animLog","[вліво]|x| |x|y|");
                                        //////////////////////////
                                        cells[0][j].setAnim(2);
                                        cells[1][j].setAnim(2);
                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вліво]|x| | |x|");
                                        //////////////////////////
                                        cells[0][j].setAnim(3);
                                        /////////////////////////
                                    }
                                    break;
                                case 1:
                                    if((cells[2][j].getMoving() != 0)
                                            && ((cells[2][j].getMoving() *2) == cells[0][j].getMerged())
                                            && (cells[3][j].getId() == 0)
                                            && (cells[2][j].getId() == 0)
                                            && (cells[1][j].getId() == 0)){
//                                        Log.d("animLog","[вліво]|x| |x| |");
                                        //////////////////////////
                                        cells[0][j].setAnim(2);

                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вліво]|x|x|y| |");
                                        //////////////////////////
                                        cells[0][j].setAnim(1);
                                        cells[1][j].setAnim(1);
                                        /////////////////////////
                                    }
                                    break;
                                case 0:
//                                    Log.d("animLog","[вліво]|x|x| | |");
                                    //////////////////////////
                                    cells[0][j].setAnim(1);

                                    /////////////////////////
                                    break;
                            }
                            break;
                        case 1:
                            switch(moveCount){
                                case 3:
//                                    Log.d("animLog","[вліво]| |y|x|x|");
                                    //////////////////////////
                                    cells[0][j].setAnim(1);
                                    cells[1][j].setAnim(2);
                                    /////////////////////////
                                    break;
                                case 2:
//                                    Log.d("animLog","[вліво]|y| |x|x|");
                                    //////////////////////////
                                    cells[1][j].setAnim(2);

                                    /////////////////////////
                                    break;
                                case 1:
                                    if((cells[3][j].getMoving() != 0)
                                            && (cells[2][j].getMoving() == 0)
                                            && (cells[1][j].getMoving() == 0)
                                            && (cells[1][j].getMoving() == 0)
                                            && ((cells[3][j].getMoving() *2) == cells[1][j].getMerged())){
//                                        Log.d("animLog","[вліво]|y|x| |x|");
                                        //////////////////////////
                                        cells[1][j].setAnim(2);

                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вліво]|z|x|x|y|");
                                        //////////////////////////
                                        cells[1][j].setAnim(1);
                                        cells[2][j].setAnim(1);
                                        /////////////////////////
                                    }
                                    break;
                                case 0:
//                                    Log.d("animLog","[вліво]|y|x|x| |");
                                    //////////////////////////
                                    cells[1][j].setAnim(1);

                                    /////////////////////////
                                    break;
                            }
                            break;
                        case 2:
//                            Log.d("animLog","[вліво]|z|y|x|x|");
                            //////////////////////////
                            cells[2][j].setAnim(1);

                            /////////////////////////
                            break;
                    }
                    break;
                case 0:
                    if(moveCount != 0){
                        searchMovingLeft(cells);
                    }
                    break;
            }
        }
    }

    private static void combinationFilterDown(Cell[][] cells){
        for (int j = 0; j < 4; j++) {
            int mergeCount = 0;
            int moveCount = 0;
            int mergePosition = 0;
            for (int i = 0; i < 4; i++) {
                if(cells[j][i].getMerged() != 0){
                    mergeCount = mergeCount + 1;
                }
                if(cells[j][i].getMoving() != 0){
                    moveCount = moveCount + 1;
                }
            }
            switch(mergeCount){
                case 2:
//                    Log.d("animLog","[вниз]|x|x|y|y|");
                    //////////////////////////
                    cells[j][3].setAnim(1);
                    cells[j][2].setAnim(2);

                    /////////////////////////
                    break;
                case 1:
                    for (int i = 0; i < 4; i++) {
                        if(cells[j][i].getMerged() != 0){
                            mergePosition = i;
                        }
                    }
                    switch(mergePosition){
                        case 3:
                            switch(moveCount){
                                case 3:
                                    if(cells[j][3].getMerged() == (cells[j][2].getMoving() *2)
                                            && cells[j][0].getMoving() == cells[j][2].getId()
                                            && cells[j][1].getMoving() == cells[j][2].getId()){
//                                        Log.d("animLog","[вниз]|y|x|x| |");
                                        //////////////////////////
                                        cells[j][3].setAnim(2);
                                        cells[j][2].setAnim(2);

                                        /////////////////////////
                                    } else if((lastID[j][0]*2) == cells[j][3].getMerged()
                                            && (lastID[j][2]*2) == cells[j][3].getMerged()){
//                                        Log.d("animLog","[вниз]|x| |x| |");
                                        //////////////////////////
                                        cells[j][3].setAnim(3);

                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вниз]|x|x| | |");
                                        //////////////////////////
                                        cells[j][3].setAnim(3);

                                        /////////////////////////
                                    }
                                    break;
                                case 2:
                                    if((cells[j][1].getMoving() *2) == cells[j][3].getMerged()
                                            && (cells[j][2].getMoving() *2) == cells[j][3].getMerged()){
//                                        Log.d("animLog","[вниз]| |x|x| |");
                                        //////////////////////////
                                        cells[j][3].setAnim(2);

                                        /////////////////////////
                                    } else if(cells[j][0].getMoving() != cells[j][1].getMoving()){
//                                        System.out.println("4. Ця комбінація: |z|y|x|x|");
//                                        Log.d("animLog","[вниз]|z|y|x|x|");
                                        //////////////////////////
                                        cells[j][3].setAnim(1);
                                        cells[j][2].setAnim(1);
                                        cells[j][1].setAnim(1);

                                        /////////////////////////
                                    } else if((lastID[j][2]*2) == cells[j][3].getMerged()
                                            && (lastID[j][3]*2) == cells[j][3].getMerged()){
//                                        Log.d("animLog","[вниз]|y| |x|x|");
                                        //////////////////////////
                                        cells[j][3].setAnim(1);
                                        cells[j][2].setAnim(2);

                                        /////////////////////////
                                    } else if((lastID[j][1]*2) == cells[j][3].getMerged()
                                            && (lastID[j][3]*2) == cells[j][3].getMerged()){
//                                        Log.d("animLog","[вниз]|y|x| |x|");
                                        //////////////////////////
                                        cells[j][3].setAnim(2);
                                        cells[j][2].setAnim(2);
                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вниз]|x| | |x|");
                                        //////////////////////////
                                        cells[j][3].setAnim(3);

                                        /////////////////////////
                                    }
                                    break;
                                case 1:
                                    if((cells[j][1].getMoving() != 0)
                                            && ((cells[j][1].getMoving() *2) == cells[j][3].getMerged())
                                            && (cells[j][0].getId() == 0)
                                            && (cells[j][1].getId() == 0)
                                            && (cells[j][2].getId() == 0)){
//                                        Log.d("animLog","[вниз]| |x| |x|");
                                        //////////////////////////
                                        cells[j][3].setAnim(2);

                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вниз]| |y|x|x|");
                                        //////////////////////////
                                        cells[j][3].setAnim(1);
                                        cells[j][2].setAnim(1);
                                        /////////////////////////
                                    }
                                    break;
                                case 0:
//                                    Log.d("animLog","[вниз]| | |x|x|");
                                    //////////////////////////
                                    cells[j][3].setAnim(1);

                                    /////////////////////////
                                    break;
                            }
                            break;
                        case 2:
                            switch(moveCount){
                                case 3:
//                                    Log.d("animLog","[вниз]|x|x|y| |");
                                    //////////////////////////
                                    cells[j][3].setAnim(1);
                                    cells[j][2].setAnim(2);
                                    /////////////////////////
                                    break;
                                case 2:
//                                    Log.d("animLog","[вниз]|x|x| |y|");
                                    //////////////////////////
                                    cells[j][2].setAnim(2);

                                    /////////////////////////
                                    break;
                                case 1:
                                    if((cells[j][0].getMoving() != 0)
                                            && (cells[j][1].getMoving() == 0)
                                            && (cells[j][2].getMoving() == 0)
                                            && (cells[j][3].getMoving() == 0)
                                            && ((cells[j][0].getMoving() *2) == cells[j][2].getMerged())){
//                                        Log.d("animLog","[вниз]|x| |x|y|");
                                        //////////////////////////
                                        cells[j][2].setAnim(2);

                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вниз]|y|x|x|z|");
                                        //////////////////////////
                                        cells[j][2].setAnim(1);
                                        cells[j][1].setAnim(1);
                                        /////////////////////////
                                    }
                                    break;
                                case 0:
//                                    Log.d("animLog","[вниз]| |x|x|y|");
                                    //////////////////////////
                                    cells[j][2].setAnim(1);

                                    /////////////////////////
                                    break;
                            }
                            break;
                        case 1:
//                            Log.d("animLog","[вниз]|x|x|y|z|");
                            //////////////////////////
                            cells[j][1].setAnim(1);

                            /////////////////////////
                            break;
                    }
                    break;
                case 0:
                    if(moveCount != 0){
                        searchMovingDown(cells);
                    }
                    break;
            }
        }
    }

    private static void combinationFilterUp(Cell[][] cells){
        for (int j = 0; j < 4; j++) {
            int mergeCount = 0;
            int moveCount = 0;
            int mergePosition = 0;
            for (int i = 0; i < 4; i++) {
                if(cells[j][i].getMerged() != 0){
                    mergeCount = mergeCount + 1;
                }
                if(cells[j][i].getMoving() != 0){
                    moveCount = moveCount + 1;
                }
            }
            switch(mergeCount){
                case 2:
//                    Log.d("animLog","[вверх]|y|y|x|x|");
                    //////////////////////////
                    cells[j][0].setAnim(1);
                    cells[j][1].setAnim(2);

                    /////////////////////////
                    break;
                case 1:
                    for (int i = 0; i < 4; i++) {
                        if(cells[i][j].getMerged() != 0){
                            mergePosition = i;
                        }
                    }
                    switch(mergePosition){
                        case 0:
                            switch(moveCount){
                                case 3:
                                    if(cells[j][0].getMerged() == (cells[j][1].getMoving() *2)
                                            && cells[j][3].getMoving() == cells[j][1].getId()
                                            && cells[j][2].getMoving() == cells[j][1].getId()){
//                                        Log.d("animLog","[вверх]| |x|x|y|");
                                        //////////////////////////
                                        cells[j][0].setAnim(2);
                                        cells[j][1].setAnim(2);

                                        /////////////////////////
                                    } else if((lastID[j][3]*2) == cells[j][0].getMerged()
                                            && (lastID[j][1]*2) == cells[j][0].getMerged()){
//                                        Log.d("animLog","[вверх]| |x| |x|");
                                        //////////////////////////
                                        cells[j][0].setAnim(3);

                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вверх]| | |x|x|");
                                        //////////////////////////
                                        cells[j][0].setAnim(3);

                                        /////////////////////////
                                    }
                                    break;
                                case 2:
                                    if((cells[j][2].getMoving() *2) == cells[j][0].getMerged()
                                            && (cells[j][1].getMoving() *2) == cells[j][0].getMerged()){
//                                        Log.d("animLog","[вверх]| |x|x| |");
                                        //////////////////////////
                                        cells[j][0].setAnim(2);

                                        /////////////////////////
                                    } else if(cells[j][3].getMoving() != cells[j][2].getMoving()){
//                                        Log.d("animLog","[вверх]|x|x|y|z|");
                                        //////////////////////////
                                        cells[j][0].setAnim(1);
                                        cells[j][1].setAnim(1);
                                        cells[j][2].setAnim(1);
                                        /////////////////////////
                                    } else if((lastID[j][1]*2) == cells[j][0].getMerged()
                                            && (lastID[j][0]*2) == cells[j][0].getMerged()){
//                                        Log.d("animLog","[вверх]|x|x| |y|");
                                        //////////////////////////
                                        cells[j][0].setAnim(1);
                                        cells[j][1].setAnim(2);
                                        /////////////////////////
                                    } else if((lastID[j][2]*2) == cells[j][0].getMerged()
                                            && (lastID[j][0]*2) == cells[j][0].getMerged()){
//                                        Log.d("animLog","[вверх]|x| |x|y|");
                                        //////////////////////////
                                        cells[j][0].setAnim(2);
                                        cells[j][1].setAnim(2);
                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вверх]|x| | |x|");
                                        //////////////////////////
                                        cells[j][0].setAnim(3);

                                        /////////////////////////
                                    }
                                    break;
                                case 1:
                                    if((cells[j][2].getMoving() != 0)
                                            && ((cells[j][2].getMoving() *2) == cells[j][0].getMerged())
                                            && (cells[j][3].getId() == 0)
                                            && (cells[j][2].getId() == 0)
                                            && (cells[j][1].getId() == 0)){
//                                        Log.d("animLog","[вверх]|x| |x| |");
                                        //////////////////////////
                                        cells[j][0].setAnim(2);

                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вверх]|x|x|y| |");
                                        //////////////////////////
                                        cells[j][0].setAnim(1);
                                        cells[j][1].setAnim(1);
                                        /////////////////////////
                                    }
                                    break;
                                case 0:
//                                    Log.d("animLog","[вверх]|x|x| | |");
                                    //////////////////////////
                                    cells[j][0].setAnim(1);

                                    /////////////////////////
                                    break;
                            }
                            break;
                        case 1:
                            switch(moveCount){
                                case 3:
//                                    Log.d("animLog","[вверх]| |y|x|x|");
                                    //////////////////////////
                                    cells[j][0].setAnim(1);
                                    cells[j][1].setAnim(2);
                                    /////////////////////////
                                    break;
                                case 2:
//                                    Log.d("animLog","[вверх]|y| |x|x|");
                                    //////////////////////////
                                    cells[j][1].setAnim(2);

                                    /////////////////////////
                                    break;
                                case 1:
                                    if((cells[j][3].getMoving() != 0)
                                            && (cells[j][2].getMoving() == 0)
                                            && (cells[j][1].getMoving() == 0)
                                            && (cells[j][0].getMoving() == 0)
                                            && ((cells[j][3].getMoving() *2) == cells[j][1].getMerged())){
//                                        Log.d("animLog","[вверх]|y|x| |x|");
                                        //////////////////////////
                                        cells[j][1].setAnim(2);

                                        /////////////////////////
                                    } else {
//                                        Log.d("animLog","[вверх]|z|x|x|y|");
                                        //////////////////////////
                                        cells[j][1].setAnim(1);
                                        cells[j][2].setAnim(1);
                                        /////////////////////////
                                    }
                                    break;
                                case 0:
//                                    Log.d("animLog","[вверх]|y|x|x| |");
                                    //////////////////////////
                                    cells[j][1].setAnim(1);

                                    /////////////////////////
                                    break;
                            }
                            break;
                        case 2:
//                            Log.d("animLog","[вверх]|z|y|x|x|");
                            //////////////////////////
                            cells[j][2].setAnim(1);

                            /////////////////////////
                            break;
                    }
                    break;
                case 0:
                    if(moveCount != 0){
                        searchMovingUp(cells);
                    }
                    break;
            }
        }
    }


    private static void resetAnimValues(Cell[][] cells){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[j][i].setMoving(0);
                cells[j][i].setMerged(0);
                cells[j][i].setAnim(0);
                lastID[j][i] = 0;
            }
        }
    }

    private static void moveCellsX(Cell[][] cells){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if(cells[j][i].getAnim() != 0){
                    cells[j][i].calculateMoveX();
                } else {
                    cells[j][i].setMoveX(0);
                }
            }
        }
    }

    private static void moveCellsY(Cell[][] cells){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cells[j][i].getAnim() != 0){
                    cells[j][i].calculateMoveY();
                } else{
                    cells[j][i].setMoveY(0);
                }
            }
        }
    }

    public static Cell[][] moveAllDown(Cell[][] cells, CellManager cellManager){
        CellManager.setSwipeDirection(Direction.DOWN);
        resetAnimValues(cells);
        saveLastID(cells);
        for (int i = 0; i <cells.length ; i++) {
            cells[i] = moveLineRight(cells[i], cellManager);
        }
        combinationFilterDown(cells);
        moveCellsY(cells);
        return cells;
    }

    public static Cell[][] moveAllUp(Cell[][] cells, CellManager cellManager){
        CellManager.setSwipeDirection(Direction.UP);
        resetAnimValues(cells);
        saveLastID(cells);
        for (int i = 0; i <cells.length ; i++) {
            cells[i] = moveLineLeft(cells[i], cellManager);
        }
        combinationFilterUp(cells);
        moveCellsY(cells);
        return cells;
    }

    public static Cell[][] moveAllLeft(Cell[][] cells, CellManager cellManager){
        CellManager.setSwipeDirection(Direction.LEFT);
        resetAnimValues(cells);
        saveLastID(cells);
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
        combinationFilterLeft(cells);
        moveCellsX(cells);
        return cells;
    }

    public static Cell[][] moveAllRight(Cell[][] cells, CellManager cellManager){
        CellManager.setSwipeDirection(Direction.RIGHT);
        resetAnimValues(cells);
        saveLastID(cells);
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
        combinationFilterRight(cells);
        moveCellsX(cells);
        return cells;
    }



    public static Cell[] moveLineRight(Cell[] line,CellManager cellManager){
        line = sortLineRight(line);
        line = mergeLineRight(line, cellManager);
        line = sortLineRight(line);
        return line;
    }

    public static Cell[] moveLineLeft(Cell[] line, CellManager cellManager){
        line = sortLineLeft(line);
        line = mergeLineLeft(line, cellManager);
        line = sortLineLeft(line);
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
                        line[j - 1].setMoving(line[j].getId());
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
                    if(line[j-1].getId() != 0){
                        line[j].setId(line[j].getId() + line[j-1].getId());
                        line[j].setMerged(line[j].getId());
                        if(line[j].getId() > 0){
                            line[j].setFresh(true);
                            line[j].setShearAnim(line[j].getShearMax());
                        }
                        cellManager.setScores(cellManager.getScores() + line[j].getId());
                        line[j-1].setId(0);
                    }
                }
            }
        }
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
                        line[j + 1].setMoving(line[j].getId());
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
                    if(line[j+1].getId() != 0){
                        line[j].setId(line[j].getId() + line[j+1].getId());
                        line[j].setMerged(line[j].getId());
                        if(line[j].getId() > 0){
                            line[j].setFresh(true);
                            line[j].setShearAnim(line[j].getShearMax());
                        }
                        cellManager.setScores(cellManager.getScores() + line[j].getId());
                        line[j+1].setId(0);
                    }
                }
            }
        }
        return line;
    }

}
