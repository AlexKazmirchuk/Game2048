package com.alexkaz.game2048.gamelogic;


import android.util.Log;

public class CellMergeSortHandler {

    private static int[][] lastID = new int[4][4];
////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////
    private static void saveLastID(Cell[][] cells){
        lastID[0] = new int[4];
        lastID[1] = new int[4];
        lastID[2] = new int[4];
        lastID[3] = new int[4];

        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                lastID[i][j] = cells[i][j].getId();
            }
        }

    }



    private static void searchMovingRight(Cell[][] cells){
        System.out.println("");
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i >= 0; i--) {
                if(i==3){
                    if(cells[i][j].getId() == Integer.valueOf(cells[i-1][j].moving) && Integer.valueOf(cells[i-1][j].moving) != 0){
                        System.out.print("четверта - 1 ");
                        cells[i][j].anim = 1;
                        if(cells[i][j].getId() == Integer.valueOf(cells[i-2][j].moving)){
                            System.out.print(",2");
                            cells[i][j].anim = cells[i][j].anim + 1;
                            if(cells[i][j].getId() == Integer.valueOf(cells[i-3][j].moving)){
                                System.out.println(",3");
                                cells[i][j].anim = cells[i][j].anim + 1;
                            }
                        }
                    }
                }
                if(i==2){
                    if(cells[i][j].getId() == Integer.valueOf(cells[i-1][j].moving) && Integer.valueOf(cells[i-1][j].moving) != 0){
                        System.out.println("");
                        System.out.print("третя - 1 ");
                        cells[i][j].anim = 1;
                        if(cells[i][j].getId() == Integer.valueOf(cells[i-2][j].moving)){
                            System.out.println(",2");
                            cells[i][j].anim = cells[i][j].anim + 1;
                        }
                    }
                }
                if(i==1){
                    if(cells[i][j].getId() == Integer.valueOf(cells[i-1][j].moving) && Integer.valueOf(cells[i-1][j].moving) != 0){
                        System.out.println("");
                        System.out.println("друга - 1 ");
                        cells[i][j].anim = 1;
                    }
                }
            }
            if(lastID[0][j] != 0
                    && lastID[1][j] != 0
                    && lastID[2][j] == 0
                    && lastID[3][j] == 0){
                cells[3][j].anim = 2;

            }
        }
    }

    private static void searchMovingLeft(Cell[][] cells){
        System.out.println("");
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if(i==0){
                    if(cells[i][j].getId() == cells[i+1][j].moving && cells[i+1][j].moving != 0){
                        System.out.print("перша - 1 ");
                        cells[i][j].anim = 1;
                        if(cells[i][j].getId() == cells[i+2][j].moving){
                            System.out.print(",2");
                            cells[i][j].anim = cells[i][j].anim + 1;
                            if(cells[i][j].getId() == cells[i+3][j].moving){
                                System.out.println(",3");
                                cells[i][j].anim = cells[i][j].anim + 1;
                            }
                        }
                    }
                }
                if(i==1){
                    if(cells[i][j].getId() == cells[i+1][j].moving && cells[i+1][j].moving != 0){
                        System.out.println("");
                        System.out.print("друга - 1 ");
                        cells[i][j].anim = 1;
                        if(cells[i][j].getId() == cells[i+2][j].moving){
                            System.out.println(",2");
                            cells[i][j].anim = cells[i][j].anim + 1;
                        }
                    }
                }
                if(i==2){
                    if(cells[i][j].getId() == cells[i+1][j].moving && cells[i+1][j].moving != 0){
                        System.out.println("");
                        System.out.println("третя - 1 ");
                        cells[i][j].anim = 1;
                    }
                }
            }
            if(lastID[0][j] == 0
                    && lastID[1][j] == 0
                    && lastID[2][j] != 0
                    && lastID[3][j] != 0){
                cells[0][j].anim = 2;
            }
        }
    }

    private static void searchMovingDown(Cell[][] cells){
        System.out.println("");
        for (int j = 0; j < 4; j++) {
            for (int i = 3; i >= 0; i--) {
                if(i==3){
                    if(cells[j][i].getId() == cells[j][i-1].moving && cells[j][i-1].moving != 0){
                        System.out.print("четверта - 1 ");
                        cells[j][i].anim = 1;
                        if(cells[j][i].getId() == cells[j][i-2].moving){
                            System.out.print(",2");
                            cells[j][i].anim = cells[j][i].anim + 1;
                            if(cells[j][i].getId() == cells[j][i-3].moving){
                                System.out.println(",3");
                                cells[j][i].anim = cells[j][i].anim + 1;
                            }
                        }
                    }
                }
                if(i==2){
                    if(cells[j][i].getId() == cells[j][i-1].moving && cells[j][i-1].moving != 0){
                        System.out.println("");
                        System.out.print("третя - 1 ");
                        cells[j][i].anim = 1;
                        if(cells[j][i].getId() == cells[j][i-2].moving){
                            System.out.println(",2");
                            cells[j][i].anim = cells[j][i].anim + 1;
                        }
                    }
                }
                if(i==1){
                    if(cells[j][i].getId() == cells[j][i-1].moving && cells[j][i-1].moving != 0){
                        System.out.println("");
                        System.out.println("друга - 1 ");
                        cells[j][i].anim = 1;
                    }
                }
            }
            if(lastID[j][0] != 0
                    && lastID[j][1] != 0
                    && lastID[j][2] == 0
                    && lastID[j][3] == 0){
                cells[j][3].anim = 2;

            }
        }
    }

    private static void searchMovingUp(Cell[][] cells){
        System.out.println("");
        for (int j = 0; j < 4; j++) {
            for (int i = 0; i < 4; i++) {
                if(i==0){
                    if(cells[j][i].getId() == cells[j][i+1].moving && cells[j][i+1].moving != 0){
                        System.out.print("перша - 1 ");
                        cells[j][i].anim = 1;
                        if(cells[j][i].getId() == cells[j][i+2].moving){
                            System.out.print(",2");
                            cells[j][i].anim = cells[j][i].anim + 1;
                            if(cells[j][i].getId() == cells[j][i+3].moving){
                                System.out.println(",3");
                                cells[j][i].anim = cells[j][i].anim + 1;
                            }
                        }
                    }
                }
                if(i==1){
                    if(cells[j][i].getId() == cells[j][i+1].moving && cells[j][i+1].moving != 0){
                        System.out.println("");
                        System.out.print("друга - 1 ");
                        cells[j][i].anim = 1;
                        if(cells[j][i].getId() == cells[j][i+2].moving){
                            System.out.println(",2");
                            cells[j][i].anim = cells[j][i].anim + 1;
                        }
                    }
                }
                if(i==2){
                    if(cells[j][i].getId() == cells[j][i+1].moving && cells[j][i+1].moving != 0){
                        System.out.println("");
                        System.out.println("третя - 1 ");
                        cells[j][i].anim = 1;
                    }
                }
            }
            if(lastID[j][0] == 0
                    && lastID[j][1] == 0
                    && lastID[j][2] != 0
                    && lastID[j][3] != 0){
                cells[j][0].anim = 2;
            }
        }
    }


    private static void combinationFilterRight(Cell[][] cells){
        for (int j = 0; j < 4; j++) {
            System.out.println("===============================");
            System.out.println("");
            System.out.println("///////////////////////////////");
            System.out.println("///////////////////////////////");
            System.out.println("////////// " + j + " рядок /////");
            // поки що працює для першого рядка
            int mergeCount = 0;
            int moveCount = 0;
            int mergePosition = 0;
            for (int i = 0; i < 4; i++) {
                if(cells[i][j].merged != 0){
                    mergeCount = mergeCount + 1;
                }
                if(cells[i][j].moving != 0){
                    moveCount = moveCount + 1;
                }
            }

            //        System.out.println("Об'єднань: " + mergeCount);
            //        System.out.println("Здвигів: " + moveCount);

            ///////////////////////////////////////////////////
            /////////Перший Фільтр по кількості об'єднань /////
            switch(mergeCount){
                case 2:
                    System.out.println("1. Два мерджі значить буде одна окрема комбінація");
                    System.out.println("                    ||");
                    System.out.println("                    VV");
                    System.out.println("2. Ця комбінація: |x|x|y|y|");
                    ///////// Написати код для одної комбінації
                    Log.d("animLog","[вправо]|x|x|y|y|");
                    //////////////////////////


                    /////////////////////////
                    break;
                case 1:
                    System.out.println("1. Один мердж, значить тут буде 17 комбінацій");
                    System.out.println("                    ||");
                    System.out.println("                    VV");
                    for (int i = 0; i < 4; i++) {
                        if(cells[i][j].merged != 0){
                            mergePosition = i;
                        }
                    }
                    //                System.out.println("Позиція merge: " + mergePosition);
                    ///////////////////////////////////////////////////
                    /////////Другий Фільтр по позиції об'єднання //////
                    switch(mergePosition){
                        case 3:
                            System.out.println("2. Третя позиція, значить відфільтрувалось 11 комбінацій");
                            System.out.println("                    ||");
                            System.out.println("                    VV");
                            ///////////////////////////////////////////////////
                            /////////Третій Фільтр по кількості здвигів ///////
                            switch(moveCount){
                                case 3:
                                    System.out.println("3. Три здвиги, відфільтрувались 3 комбінації");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if(cells[3][j].merged == (cells[2][j].moving*2)
                                            && cells[0][j].moving == cells[2][j].getId()
                                            && cells[1][j].moving == cells[2][j].getId()){
                                        System.out.println("4. Ця комбінація: |y|x|x| |");
                                        Log.d("animLog","[вправо]|y|x|x| |");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if((lastID[0][j]*2) == cells[3][j].merged
                                            && (lastID[2][j]*2) == cells[3][j].merged){
                                        System.out.println("4. Ця комбінація: |x| |x| |");
                                        Log.d("animLog","[вправо]|x| |x| |");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: |x|x| | |");
                                        Log.d("animLog","[вправо]|x|x| | |");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 2:
                                    System.out.println("3. Два здвиги, відфільтрувались 5 комбінацій");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if((cells[1][j].moving*2) == cells[3][j].merged
                                            && (cells[2][j].moving*2) == cells[3][j].merged){
                                        System.out.println("4. Ця комбінація: | |x|x| |");
                                        Log.d("animLog","[вправо]| |x|x| |");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if(cells[0][j].moving != cells[1][j].moving){
                                        System.out.println("4. Ця комбінація: |z|y|x|x|");
                                        Log.d("animLog","[вправо]|z|y|x|x|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if((lastID[2][j]*2) == cells[3][j].merged
                                            && (lastID[3][j]*2) == cells[3][j].merged){
                                        System.out.println("4. Ця комбінація: |y| |x|x|");
                                        Log.d("animLog","[вправо]|y| |x|x|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if((lastID[1][j]*2) == cells[3][j].merged
                                            && (lastID[3][j]*2) == cells[3][j].merged){
                                        System.out.println("4. Ця комбінація: |y|x| |x|");
                                        Log.d("animLog","[вправо]|y|x| |x|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: |x| | |x|");
                                        Log.d("animLog","[вправо]|x| | |x|");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 1:
                                    System.out.println("3. Один здвиг, відфільтрувались 2 комбінації");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if((cells[1][j].moving != 0)
                                            && ((cells[1][j].moving*2) == cells[3][j].merged)
                                            && (cells[0][j].getId() == 0)
                                            && (cells[1][j].getId() == 0)
                                            && (cells[2][j].getId() == 0)){
                                        System.out.println("4. Ця комбінація: | |x| |x|");
                                        Log.d("animLog","[вправо]| |x| |x|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: | |y|x|x|");
                                        Log.d("animLog","[вправо]| |y|x|x|");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 0:
                                    System.out.println("3. Немає здвигів, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: | | |x|x|");
                                    Log.d("animLog","[вправо]| | |x|x|");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                            }
                            ///////////////////////////////////////////////////
                            break;
                        case 2:
                            System.out.println("2. Друга позиція, значить відфільтрувалось 5 комбінацій");
                            System.out.println("                    ||");
                            System.out.println("                    VV");
                            ///////////////////////////////////////////////////
                            /////////Третій Фільтр по кількості здвигів ///////
                            switch(moveCount){
                                case 3:
                                    System.out.println("3. Три здвиги, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: |x|x|y| |");
                                    Log.d("animLog","[вправо]|x|x|y| |");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                                case 2:
                                    System.out.println("3. Два здвиги, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: |x|x| |y|");
                                    Log.d("animLog","[вправо]|x|x| |y|");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                                case 1:
                                    System.out.println("3. Один здвиг, відфільтрувались 2 комбінації");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if((cells[0][j].moving != 0)
                                            && (cells[1][j].moving == 0)
                                            && (cells[2][j].moving == 0)
                                            && (cells[2][j].moving == 0)
                                            && ((cells[0][j].moving*2) == cells[2][j].merged)){
                                        System.out.println("4. Ця комбінація: |x| |x|y|");
                                        Log.d("animLog","[вправо]|x| |x|y|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: |y|x|x|z|");
                                        Log.d("animLog","[вправо]|y|x|x|z|");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 0:
                                    System.out.println("3. Немає здвигів, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: | |x|x|y|");
                                    Log.d("animLog","[вправо]| |x|x|y|");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                            }
                            ///////////////////////////////////////////////////
                            break;
                        case 1:
                            System.out.println("2. Перша позиція, значить відфільтрувалась 1 комбінація");
                            System.out.println("                    ||");
                            System.out.println("                    VV");
                            System.out.println("3. Ця комбінація: |x|x|y|z|");
                            Log.d("animLog","[вправо]|x|x|y|z|");
                            //////////////////////////


                            /////////////////////////
                            break;
                    }
                    ///////////////////////////////////////////////////
                    break;
                case 0:
                    if(moveCount != 0){
                        System.out.println("1. Немає мерджів,  но є здвиги");
                        searchMovingRight( cells);
                        Log.d("animLog","[вправо]|здвиги|");
                        //////////////////////////


                        /////////////////////////
                    } else{
                        System.out.println("В рядку " + j + " не відбулося ніяких змін");
                        Log.d("animLog","[вправо]|нічого|");
                        //////////////////////////


                        /////////////////////////
                    }

                    break;
            }

            System.out.println("");
            System.out.println("///////////////////////////////");
            System.out.println("///////////////////////////////");
            System.out.println("");
            /////////////////////////////
        }
    }

    private static void combinationFilterLeft(Cell[][] cells){
        for (int j = 0; j < 4; j++) {
            System.out.println("===============================");
            System.out.println("");
            System.out.println("///////////////////////////////");
            System.out.println("///////////////////////////////");
            System.out.println("////////// " + j + " рядок /////");
            // поки що працює для першого рядка
            int mergeCount = 0;
            int moveCount = 0;
            int mergePosition = 0;
            for (int i = 0; i < 4; i++) {
                if(cells[i][j].merged != 0){
                    mergeCount = mergeCount + 1;
                }
                if(cells[i][j].moving != 0){
                    moveCount = moveCount + 1;
                }
            }

            //        System.out.println("Об'єднань: " + mergeCount);
            //        System.out.println("Здвигів: " + moveCount);

            ///////////////////////////////////////////////////
            /////////Перший Фільтр по кількості об'єднань /////
            switch(mergeCount){
                case 2:
                    System.out.println("1. Два мерджі значить буде одна окрема комбінація");
                    System.out.println("                    ||");
                    System.out.println("                    VV");
                    System.out.println("2. Ця комбінація: |y|y|x|x|");
                    ///////// Написати код для одної комбінації вліво
                    Log.d("animLog","[вліво]|y|y|x|x|");
                    //////////////////////////


                    /////////////////////////
                    break;
                case 1:
                    System.out.println("1. Один мердж, значить тут буде 17 комбінацій");
                    System.out.println("                    ||");
                    System.out.println("                    VV");
                    for (int i = 0; i < 4; i++) {
                        if(cells[i][j].merged != 0){
                            mergePosition = i;
                        }
                    }
                    //                System.out.println("Позиція merge: " + mergePosition);
                    ///////////////////////////////////////////////////
                    /////////Другий Фільтр по позиції об'єднання //////
                    switch(mergePosition){
                        case 0:
                            System.out.println("2. Перша позиція, значить відфільтрувалось 11 комбінацій");
                            System.out.println("                    ||");
                            System.out.println("                    VV");
                            ///////////////////////////////////////////////////
                            /////////Третій Фільтр по кількості здвигів ///////
                            switch(moveCount){
                                case 3:
                                    System.out.println("3. Три здвиги, відфільтрувались 3 комбінації");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if(cells[0][j].merged == (cells[1][j].moving*2)
                                            && cells[3][j].moving == cells[1][j].getId()
                                            && cells[2][j].moving == cells[1][j].getId()){
                                        System.out.println("4. Ця комбінація: | |x|x|y|");
                                        Log.d("animLog","[вліво]| |x|x|y|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if((lastID[3][j]*2) == cells[0][j].merged
                                            && (lastID[1][j]*2) == cells[0][j].merged){
                                        System.out.println("4. Ця комбінація: | |x| |x|");
                                        Log.d("animLog","[вліво]| |x| |x|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: | | |x|x|");
                                        Log.d("animLog","[вліво]| | |x|x|");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 2:
                                    System.out.println("3. Два здвиги, відфільтрувались 5 комбінацій");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if((cells[2][j].moving*2) == cells[0][j].merged
                                            && (cells[1][j].moving*2) == cells[0][j].merged){
                                        System.out.println("4. Ця комбінація: | |x|x| |");
                                        Log.d("animLog","[вліво]| |x|x| |");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if(cells[3][j].moving != cells[2][j].moving){
                                        System.out.println("4. Ця комбінація: |x|x|y|z|");
                                        Log.d("animLog","[вліво]|x|x|y|z|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if((lastID[1][j]*2) == cells[0][j].merged
                                            && (lastID[0][j]*2) == cells[0][j].merged){
                                        System.out.println("4. Ця комбінація: |x|x| |y|");
                                        Log.d("animLog","[вліво]|x|x| |y|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if((lastID[2][j]*2) == cells[0][j].merged
                                            && (lastID[0][j]*2) == cells[0][j].merged){
                                        System.out.println("4. Ця комбінація: |x| |x|y|");
                                        Log.d("animLog","[вліво]|x| |x|y|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: |x| | |x|");
                                        Log.d("animLog","[вліво]|x| | |x|");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 1:
                                    System.out.println("3. Один здвиг, відфільтрувались 2 комбінації");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if((cells[2][j].moving != 0)
                                            && ((cells[2][j].moving*2) == cells[0][j].merged)
                                            && (cells[3][j].getId() == 0)
                                            && (cells[2][j].getId() == 0)
                                            && (cells[1][j].getId() == 0)){
                                        System.out.println("4. Ця комбінація: |x| |x| |");
                                        Log.d("animLog","[вліво]|x| |x| |");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: |x|x|y| |");
                                        Log.d("animLog","[вліво]|x|x|y| |");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 0:
                                    System.out.println("3. Немає здвигів, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: |x|x| | |");
                                    Log.d("animLog","[вліво]|x|x| | |");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                            }
                            ///////////////////////////////////////////////////
                            break;
                        case 1:
                            System.out.println("2. Друга позиція, значить відфільтрувалось 5 комбінацій");
                            System.out.println("                    ||");
                            System.out.println("                    VV");
                            ///////////////////////////////////////////////////
                            /////////Третій Фільтр по кількості здвигів ///////
                            switch(moveCount){
                                case 3:
                                    System.out.println("3. Три здвиги, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: | |y|x|x|");
                                    Log.d("animLog","[вліво]| |y|x|x|");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                                case 2:
                                    System.out.println("3. Два здвиги, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: |y| |x|x|");
                                    Log.d("animLog","[вліво]|y| |x|x|");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                                case 1:
                                    System.out.println("3. Один здвиг, відфільтрувались 2 комбінації");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if((cells[3][j].moving != 0)
                                            && (cells[2][j].moving == 0)
                                            && (cells[1][j].moving == 0)
                                            && (cells[1][j].moving == 0)
                                            && ((cells[3][j].moving*2) == cells[1][j].merged)){
                                        System.out.println("4. Ця комбінація: |y|x| |x|");
                                        Log.d("animLog","[вліво]|y|x| |x|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: |z|x|x|y|");
                                        Log.d("animLog","[вліво]|z|x|x|y|");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 0:
                                    System.out.println("3. Немає здвигів, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: |y|x|x| |");
                                    Log.d("animLog","[вліво]|y|x|x| |");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                            }
                            ///////////////////////////////////////////////////
                            break;
                        case 2:
                            System.out.println("2. Третя позиція, значить відфільтрувалась 1 комбінація");
                            System.out.println("                    ||");
                            System.out.println("                    VV");
                            System.out.println("3. Ця комбінація: |z|y|x|x|");
                            Log.d("animLog","[вліво]|z|y|x|x|");
                            //////////////////////////


                            /////////////////////////
                            break;
                    }
                    ///////////////////////////////////////////////////
                    break;
                case 0:
                    if(moveCount != 0){
                        System.out.println("1. Немає мерджів,  но є здвиги");
                        searchMovingLeft(cells);
                        Log.d("animLog","[вліво]|здвиги|");
                        //////////////////////////


                        /////////////////////////
                    } else{
                        System.out.println("В рядку " + j + " не відбулося ніяких змін");
                        Log.d("animLog","[вліво]|нічого|");
                        //////////////////////////


                        /////////////////////////
                    }

                    break;
            }

            System.out.println("");
            System.out.println("///////////////////////////////");
            System.out.println("///////////////////////////////");
            System.out.println("");
            /////////////////////////////
        }
    }

    private static void combinationFilterDown(Cell[][] cells){
        for (int j = 0; j < 4; j++) {
            System.out.println("===============================");
            System.out.println("");
            System.out.println("///////////////////////////////");
            System.out.println("///////////////////////////////");
            System.out.println("////////// " + j + " рядок /////");
            int mergeCount = 0;
            int moveCount = 0;
            int mergePosition = 0;
            for (int i = 0; i < 4; i++) {
                if(cells[j][i].merged != 0){
                    mergeCount = mergeCount + 1;
                }
                if(cells[j][i].moving != 0){
                    moveCount = moveCount + 1;
                }
            }

            //        System.out.println("Об'єднань: " + mergeCount);
            //        System.out.println("Здвигів: " + moveCount);

            ///////////////////////////////////////////////////
            /////////Перший Фільтр по кількості об'єднань /////
            switch(mergeCount){
                case 2:
                    System.out.println("1. Два мерджі значить буде одна окрема комбінація");
                    System.out.println("                    ||");
                    System.out.println("                    VV");
                    System.out.println("2. Ця комбінація: |x|x|y|y|");
                    ///////// Написати код для одної комбінації
                    Log.d("animLog","[вниз]|x|x|y|y|");
                    //////////////////////////


                    /////////////////////////
                    break;
                case 1:
                    System.out.println("1. Один мердж, значить тут буде 17 комбінацій");
                    System.out.println("                    ||");
                    System.out.println("                    VV");
                    for (int i = 0; i < 4; i++) {
                        if(cells[j][i].merged != 0){
                            mergePosition = i;
                        }
                    }
//                    System.out.println("Позиція merge: " + mergePosition);
                    ///////////////////////////////////////////////////
                    /////////Другий Фільтр по позиції об'єднання //////
                    switch(mergePosition){
                        case 3:
                            System.out.println("2. Третя позиція, значить відфільтрувалось 11 комбінацій");
                            System.out.println("                    ||");
                            System.out.println("                    VV");
                            ///////////////////////////////////////////////////
                            /////////Третій Фільтр по кількості здвигів ///////
                            switch(moveCount){
                                case 3:
                                    System.out.println("3. Три здвиги, відфільтрувались 3 комбінації");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if(cells[j][3].merged == (cells[j][2].moving*2)
                                            && cells[j][0].moving == cells[j][2].getId()
                                            && cells[j][1].moving == cells[j][2].getId()){
                                        System.out.println("4. Ця комбінація: |y|x|x| |");
                                        Log.d("animLog","[вниз]|y|x|x| |");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if((lastID[j][0]*2) == cells[j][3].merged
                                            && (lastID[j][2]*2) == cells[j][3].merged){
                                        System.out.println("4. Ця комбінація: |x| |x| |");
                                        Log.d("animLog","[вниз]|x| |x| |");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: |x|x| | |");
                                        Log.d("animLog","[вниз]|x|x| | |");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 2:
                                    System.out.println("3. Два здвиги, відфільтрувались 5 комбінацій");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if((cells[j][1].moving*2) == cells[j][3].merged
                                            && (cells[j][2].moving*2) == cells[j][3].merged){
                                        System.out.println("4. Ця комбінація: | |x|x| |");
                                        Log.d("animLog","[вниз]| |x|x| |");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if(cells[j][0].moving != cells[j][1].moving){
                                        System.out.println("4. Ця комбінація: |z|y|x|x|");
                                        Log.d("animLog","[вниз]|z|y|x|x|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if((lastID[j][2]*2) == cells[j][3].merged
                                            && (lastID[j][3]*2) == cells[j][3].merged){
                                        System.out.println("4. Ця комбінація: |y| |x|x|");
                                        Log.d("animLog","[вниз]|y| |x|x|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if((lastID[j][1]*2) == cells[j][3].merged
                                            && (lastID[j][3]*2) == cells[j][3].merged){
                                        System.out.println("4. Ця комбінація: |y|x| |x|");
                                        Log.d("animLog","[вниз]|y|x| |x|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: |x| | |x|");
                                        Log.d("animLog","[вниз]|x| | |x|");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 1:
                                    System.out.println("3. Один здвиг, відфільтрувались 2 комбінації");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if((cells[j][1].moving != 0)
                                            && ((cells[j][1].moving*2) == cells[j][3].merged)
                                            && (cells[j][0].getId() == 0)
                                            && (cells[j][1].getId() == 0)
                                            && (cells[j][2].getId() == 0)){
                                        System.out.println("4. Ця комбінація: | |x| |x|");
                                        Log.d("animLog","[вниз]| |x| |x|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: | |y|x|x|");
                                        Log.d("animLog","[вниз]| |y|x|x|");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 0:
                                    System.out.println("3. Немає здвигів, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: | | |x|x|");
                                    Log.d("animLog","[вниз]| | |x|x|");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                            }
                            ///////////////////////////////////////////////////
                            break;
                        case 2:
                            System.out.println("2. Друга позиція, значить відфільтрувалось 5 комбінацій");
                            System.out.println("                    ||");
                            System.out.println("                    VV");
                            ///////////////////////////////////////////////////
                            /////////Третій Фільтр по кількості здвигів ///////
                            switch(moveCount){
                                case 3:
                                    System.out.println("3. Три здвиги, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: |x|x|y| |");
                                    Log.d("animLog","[вниз]|x|x|y| |");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                                case 2:
                                    System.out.println("3. Два здвиги, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: |x|x| |y|");
                                    Log.d("animLog","[вниз]|x|x| |y|");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                                case 1:
                                    System.out.println("3. Один здвиг, відфільтрувались 2 комбінації");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if((cells[j][0].moving != 0)
                                            && (cells[j][1].moving == 0)
                                            && (cells[j][2].moving == 0)
                                            && (cells[j][3].moving == 0)
                                            && ((cells[j][0].moving*2) == cells[j][2].merged)){
                                        System.out.println("4. Ця комбінація: |x| |x|y|");
                                        Log.d("animLog","[вниз]|x| |x|y|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: |y|x|x|z|");
                                        Log.d("animLog","[вниз]|y|x|x|z|");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 0:
                                    System.out.println("3. Немає здвигів, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: | |x|x|y|");
                                    Log.d("animLog","[вниз]| |x|x|y|");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                            }
                            ///////////////////////////////////////////////////
                            break;
                        case 1:
                            System.out.println("2. Перша позиція, значить відфільтрувалась 1 комбінація");
                            System.out.println("                    ||");
                            System.out.println("                    VV");
                            System.out.println("3. Ця комбінація: |x|x|y|z|");
                            Log.d("animLog","[вниз]|x|x|y|z|");
                            //////////////////////////


                            /////////////////////////
                            break;
                    }
                    ///////////////////////////////////////////////////
                    break;
                case 0:
                    if(moveCount != 0){
                        System.out.println("1. Немає мерджів,  но є здвиги");
                        searchMovingDown(cells);
                        Log.d("animLog","[вниз]|здвиги|");
                        //////////////////////////


                        /////////////////////////
                    } else{
                        System.out.println("В рядку " + j + " не відбулося ніяких змін");
                        Log.d("animLog","[вниз]|нічого|");
                        //////////////////////////


                        /////////////////////////
                    }

                    break;
            }

            System.out.println("");
            System.out.println("///////////////////////////////");
            System.out.println("///////////////////////////////");
            System.out.println("");
            /////////////////////////////
        }
    }

    private static void combinationFilterUp(Cell[][] cells){
        for (int j = 0; j < 4; j++) {
            System.out.println("===============================");
            System.out.println("");
            System.out.println("///////////////////////////////");
            System.out.println("///////////////////////////////");
            System.out.println("////////// " + j + " рядок /////");
            // поки що працює для першого рядка
            int mergeCount = 0;
            int moveCount = 0;
            int mergePosition = 0;
            for (int i = 0; i < 4; i++) {
                if(cells[j][i].merged != 0){
                    mergeCount = mergeCount + 1;
                }
                if(cells[j][i].moving != 0){
                    moveCount = moveCount + 1;
                }
            }

            //        System.out.println("Об'єднань: " + mergeCount);
            //        System.out.println("Здвигів: " + moveCount);

            ///////////////////////////////////////////////////
            /////////Перший Фільтр по кількості об'єднань /////
            switch(mergeCount){
                case 2:
                    System.out.println("1. Два мерджі значить буде одна окрема комбінація");
                    System.out.println("                    ||");
                    System.out.println("                    VV");
                    System.out.println("2. Ця комбінація: |y|y|x|x|");
                    ///////// Написати код для одної комбінації вліво
                    Log.d("animLog","[вверх]|y|y|x|x|");
                    //////////////////////////


                    /////////////////////////
                    break;
                case 1:
                    System.out.println("1. Один мердж, значить тут буде 17 комбінацій");
                    System.out.println("                    ||");
                    System.out.println("                    VV");
                    for (int i = 0; i < 4; i++) {
                        if(cells[i][j].merged != 0){  //////////////
                            mergePosition = i; //////////////
                        }
                    }
                    //                System.out.println("Позиція merge: " + mergePosition);
                    ///////////////////////////////////////////////////
                    /////////Другий Фільтр по позиції об'єднання //////
                    switch(mergePosition){
                        case 0:
                            System.out.println("2. Перша позиція, значить відфільтрувалось 11 комбінацій");
                            System.out.println("                    ||");
                            System.out.println("                    VV");
                            ///////////////////////////////////////////////////
                            /////////Третій Фільтр по кількості здвигів ///////
                            switch(moveCount){
                                case 3:
                                    System.out.println("3. Три здвиги, відфільтрувались 3 комбінації");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if(cells[j][0].merged == (cells[j][1].moving*2)
                                            && cells[j][3].moving == cells[j][1].getId()
                                            && cells[j][2].moving == cells[j][1].getId()){
                                        System.out.println("4. Ця комбінація: | |x|x|y|");
                                        Log.d("animLog","[вверх]| |x|x|y|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if((lastID[j][3]*2) == cells[j][0].merged
                                            && (lastID[j][1]*2) == cells[j][0].merged){
                                        System.out.println("4. Ця комбінація: | |x| |x|");
                                        Log.d("animLog","[вверх]| |x| |x|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: | | |x|x|");
                                        Log.d("animLog","[вверх]| | |x|x|");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 2:
                                    System.out.println("3. Два здвиги, відфільтрувались 5 комбінацій");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if((cells[j][2].moving*2) == cells[j][0].merged
                                            && (cells[j][1].moving*2) == cells[j][0].merged){
                                        System.out.println("4. Ця комбінація: | |x|x| |");
                                        Log.d("animLog","[вверх]| |x|x| |");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if(cells[j][3].moving != cells[j][2].moving){
                                        System.out.println("4. Ця комбінація: |x|x|y|z|");
                                        Log.d("animLog","[вверх]|x|x|y|z|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if((lastID[j][1]*2) == cells[j][0].merged
                                            && (lastID[j][0]*2) == cells[j][0].merged){
                                        System.out.println("4. Ця комбінація: |x|x| |y|");
                                        Log.d("animLog","[вверх]|x|x| |y|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else if((lastID[j][2]*2) == cells[j][0].merged
                                            && (lastID[j][0]*2) == cells[j][0].merged){
                                        System.out.println("4. Ця комбінація: |x| |x|y|");
                                        Log.d("animLog","[вверх]|x| |x|y|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: |x| | |x|");
                                        Log.d("animLog","[вверх]|x| | |x|");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 1:
                                    System.out.println("3. Один здвиг, відфільтрувались 2 комбінації");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if((cells[j][2].moving != 0)
                                            && ((cells[j][2].moving*2) == cells[j][0].merged)
                                            && (cells[j][3].getId() == 0)
                                            && (cells[j][2].getId() == 0)
                                            && (cells[j][1].getId() == 0)){
                                        System.out.println("4. Ця комбінація: |x| |x| |");
                                        Log.d("animLog","[вверх]|x| |x| |");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: |x|x|y| |");
                                        Log.d("animLog","[вверх]|x|x|y| |");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 0:
                                    System.out.println("3. Немає здвигів, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: |x|x| | |");
                                    Log.d("animLog","[вверх]|x|x| | |");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                            }
                            ///////////////////////////////////////////////////
                            break;
                        case 1:
                            System.out.println("2. Друга позиція, значить відфільтрувалось 5 комбінацій");
                            System.out.println("                    ||");
                            System.out.println("                    VV");
                            ///////////////////////////////////////////////////
                            /////////Третій Фільтр по кількості здвигів ///////
                            switch(moveCount){
                                case 3:
                                    System.out.println("3. Три здвиги, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: | |y|x|x|");
                                    Log.d("animLog","[вверх]| |y|x|x|");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                                case 2:
                                    System.out.println("3. Два здвиги, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: |y| |x|x|");
                                    Log.d("animLog","[вверх]|y| |x|x|");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                                case 1:
                                    System.out.println("3. Один здвиг, відфільтрувались 2 комбінації");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    if((cells[j][3].moving != 0)
                                            && (cells[j][2].moving == 0)
                                            && (cells[j][1].moving == 0)
                                            && (cells[j][0].moving == 0)
                                            && ((cells[j][3].moving*2) == cells[j][1].merged)){
                                        System.out.println("4. Ця комбінація: |y|x| |x|");
                                        Log.d("animLog","[вверх]|y|x| |x|");
                                        //////////////////////////


                                        /////////////////////////
                                    } else {
                                        System.out.println("4. Ця комбінація: |z|x|x|y|");
                                        Log.d("animLog","[вверх]|z|x|x|y|");
                                        //////////////////////////


                                        /////////////////////////
                                    }
                                    break;
                                case 0:
                                    System.out.println("3. Немає здвигів, відфільтрувалась 1 комбінація");
                                    System.out.println("                    ||");
                                    System.out.println("                    VV");
                                    System.out.println("4. Ця комбінація: |y|x|x| |");
                                    Log.d("animLog","[вверх]|y|x|x| |");
                                    //////////////////////////


                                    /////////////////////////
                                    break;
                            }
                            ///////////////////////////////////////////////////
                            break;
                        case 2:
                            System.out.println("2. Третя позиція, значить відфільтрувалась 1 комбінація");
                            System.out.println("                    ||");
                            System.out.println("                    VV");
                            System.out.println("3. Ця комбінація: |z|y|x|x|");
                            Log.d("animLog","[вверх]|z|y|x|x|");
                            //////////////////////////


                            /////////////////////////
                            break;
                    }
                    ///////////////////////////////////////////////////
                    break;
                case 0:
                    if(moveCount != 0){
                        System.out.println("1. Немає мерджів,  но є здвиги");
                        searchMovingUp(cells);
                        Log.d("animLog","[вверх]|здвиги|");
                        //////////////////////////


                        /////////////////////////
                    } else{
                        System.out.println("В рядку " + j + " не відбулося ніяких змін");
                        Log.d("animLog","[вверх]|нічого|");
                        //////////////////////////


                        /////////////////////////
                    }

                    break;
            }

            System.out.println("");
            System.out.println("///////////////////////////////");
            System.out.println("///////////////////////////////");
            System.out.println("");
            /////////////////////////////
        }
    }


    private static void resetAnimValues(Cell[][] cells){
        //TODO
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[j][i].moving = 0;
                cells[j][i].merged = 0;
                cells[j][i].anim = 0;
                lastID[j][i] = 0;
            }
        }
    }

    private static void moveCells(Cell[][] cells){
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[j][i].calculateMoveX();
            }
        }
    }

    private static void showMoving(Cell[][] cells){
        String lineID = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                lineID = lineID + cells[j][i].moving + " ";
            }
            Log.d("animLog", lineID);
            lineID = "";
        }
        Log.d("animLog", "============");
    }

    private static void showMerging(Cell[][] cells){
        String lineID = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                lineID = lineID + cells[j][i].merged + " ";
            }
            Log.d("animLog", lineID);
            lineID = "";
        }
        Log.d("animLog", "============");
        Log.d("animLog", "============");
        Log.d("animLog", "============");
    }

    private static void showInfo(Cell[][] cells){
        showMoving(cells);
        showMerging(cells);
        showPosXPosY(cells);
    }

    private static void showPosXPosY(Cell[][] cells){
        String line = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                line = line + " [" + cells[j][i].getPosX() + "," + cells[j][i].getPosY() + "]";
            }
            Log.d("positionLog",line);
            line = "";
        }
    }



////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////
    public static Cell[][] moveAllDown(Cell[][] cells, CellManager cellManager){
        cellManager.swipeDirection = Direction.DOWN;
        resetAnimValues(cells);
        saveLastID(cells);
        for (int i = 0; i <cells.length ; i++) {
            cells[i] = moveLineRight(cells[i], cellManager);
        }
        combinationFilterDown(cells);
        showInfo(cells);
        return cells;
    }

    public static Cell[][] moveAllUp(Cell[][] cells, CellManager cellManager){
        cellManager.swipeDirection = Direction.UP;
        resetAnimValues(cells);
        saveLastID(cells);
        for (int i = 0; i <cells.length ; i++) {
            cells[i] = moveLineLeft(cells[i], cellManager);
        }
        combinationFilterUp(cells);
        showInfo(cells);
        return cells;
    }

    public static Cell[][] moveAllLeft(Cell[][] cells, CellManager cellManager){
        cellManager.swipeDirection = Direction.LEFT;
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
        moveCells(cells);
        showInfo(cells);
        return cells;
    }

    public static Cell[][] moveAllRight(Cell[][] cells, CellManager cellManager){
        cellManager.swipeDirection = Direction.RIGHT;
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
        moveCells(cells);
        showInfo(cells);
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
                        line[j - 1].moving = line[j].getId();
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
                        line[j].merged = line[j].getId();
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
                        line[j + 1].moving = line[j].getId();
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
                        line[j].merged = line[j].getId();
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
