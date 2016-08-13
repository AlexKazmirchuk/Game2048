package com.alexkaz.game2048.gamelogic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;

import com.alexkaz.game2048.GameActivity;


public class CellForBG {

    //for cell
    public static final int DEFAULT_ID = 0;
    public static final int COLOR_OWN = 0;
    public static final int COLOR_RIGHT = 1;
    public static final int COLOR_BOTTOM = 2;
    public static final int DEFAULT_WIDTH = 400;
    public static final int CELL_SIDE_X_DIVIDER = 88;
    public static final int CELL_BORDER_X_DIVIDER = 10;
    public static final int CELL_PIVOT_X_DIVIDER = 10;
    public static final int CELL_SIDE_Y_DIVIDER = 88;
    public static final int CELL_BORDER_Y_DIVIDER = 11;
    public static final int CELL_PIVOT_Y_DIVIDER = 10;
    public static final int SIDE_SPACE_X_DIVIDER = 5;
    public static final int SIDE_SPACE_Y_DIVIDER = 5;
    public static final int SHEAR_MAX_DIVIDER = 7;
    public static final int SHEAR_DIVIDER = 7;

    private int sideSpaceX; //resizable
    private int sideSpaceY; //resizable
    private int shearMax;   //resizable
    private int shear;      //resizable
    private int shearAnim = 0;

    //for text
    public static final String RESOURCE_TYPE = "color";

    //Свойства
    private GameActivity context;
    private int[] colors = new int[3];

    private boolean locker = true;

    private int sizeX ;      //resizable
    private int sizeY ;      //resizable
    private int borderX ;      //resizable
    private int borderY ;      //resizable
    private int pivotX ;      //resizable
    private int pivotY ;      //resizable

    int left,top,right,bottom;

    Paint rectPaint;
    Paint rightRombPaint;
    Paint bottomRombPaint;

    Path rectPath;
    Path rightRombPath ;
    Path bottomRombPath;

    Path buff1;
    Path buff2;

    //Конструктор
    public CellForBG(GameActivity context){
        this.context = context;
        initComp();
    }

    private void initComp() {
        rectPaint = new Paint();
        rightRombPaint = new Paint();
        bottomRombPaint = new Paint();

        rectPath = new Path();
        rightRombPath = new Path();
        bottomRombPath = new Path();

        buff1 = new Path();
        buff2 = new Path();
    }

    // Методи
    public void draw(Canvas g){
        if (locker){
            getSizes(g.getHeight(),g.getWidth());

            setColorFromRes();
            rectPaint.setColor(colors[COLOR_OWN]);
            rightRombPaint.setColor(colors[COLOR_RIGHT]);
            bottomRombPaint.setColor(colors[COLOR_BOTTOM]);

            initPaths();
            locker = false;
        }
        drawMyCell(g);
    }

    private void drawMyCell(Canvas g) {
        g.drawPath(rectPath,rectPaint);
        g.drawPath(rightRombPath,rightRombPaint);
        g.drawPath(bottomRombPath,bottomRombPaint);
    }

    private void initPaths(){
        int posX,posY;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                posX = sizeX*x + borderX*x + pivotX;
                posY = sizeY*y + borderY*y + pivotY;
                /////////////////////////////////////////
                left = posX + shearAnim;
                top = posY + shearAnim;
                right = posX + sizeX - sideSpaceX + shearAnim;
                bottom = posY+sizeY - sideSpaceY + shearAnim;
                rectPath.addRect(left,top,right,bottom, Path.Direction.CCW);
                ////////////////////////////////////////
                buff1.reset();
                buff1.moveTo(posX + sizeX + shearAnim - sideSpaceX, posY + shearAnim);                        // left  top
                buff1.lineTo(posX+sizeX+shear - sideSpaceX, posY+shear);                                      // right top
                buff1.lineTo(posX+sizeX+shear - sideSpaceX, posY+sizeY + shear - sideSpaceY);               // right bottom
                buff1.lineTo(posX+sizeX+ shearAnim - sideSpaceX, posY+sizeY + shearAnim - sideSpaceY);      // left  bottom
                buff1.lineTo(posX + sizeX + shearAnim - sideSpaceX, posY + shearAnim);
                rightRombPath.addPath(buff1);
                ////////////////////////////////////////
                buff2.reset();
                buff2.moveTo(posX + shearAnim, posY+sizeY + shearAnim - sideSpaceY);                           // left  top
                buff2.lineTo(posX+sizeX+ shearAnim - sideSpaceX, posY+sizeY+ shearAnim - sideSpaceY);       // right top
                buff2.lineTo(posX+sizeX+shear- sideSpaceX, posY+sizeY+shear- sideSpaceY);                    // right bottom
                buff2.lineTo(posX+shear, posY+sizeY+shear- sideSpaceY);                                        // left  bottom
                buff2.lineTo(posX + shearAnim, posY+sizeY + shearAnim - sideSpaceY);
                bottomRombPath.addPath(buff2);
            }
        }
    }

    private void setColorFromRes(){
        int i = context.getResources().getIdentifier("colorOwn" + 0, RESOURCE_TYPE, context.getPackageName());
        colors[COLOR_OWN] = ContextCompat.getColor(context,i);

        i = context.getResources().getIdentifier("colorRight" + 0, RESOURCE_TYPE, context.getPackageName());
        colors[COLOR_RIGHT] = ContextCompat.getColor(context,i);

        i = context.getResources().getIdentifier("colorBottom" + 0, RESOURCE_TYPE, context.getPackageName());
        colors[COLOR_BOTTOM] = ContextCompat.getColor(context,i);
    }

    private void getSizes(int height, int width) {

        float fxSize = ((float) width / DEFAULT_WIDTH)* CELL_SIDE_X_DIVIDER;
        float fxBorder = ((float) width / DEFAULT_WIDTH)* CELL_BORDER_X_DIVIDER;
        float fxPivot = ((float) width / DEFAULT_WIDTH)* CELL_PIVOT_X_DIVIDER;

        float fySize = ((float) height / DEFAULT_WIDTH)* CELL_SIDE_Y_DIVIDER;
        float fyBorder = ((float) height / DEFAULT_WIDTH)* CELL_BORDER_Y_DIVIDER;
        float fyPivot = ((float) height / DEFAULT_WIDTH)* CELL_PIVOT_Y_DIVIDER;


        float fSideSpaceX = ((float) width / DEFAULT_WIDTH) * SIDE_SPACE_X_DIVIDER;    //resize 5
        float fSideSpaceY = ((float) width / DEFAULT_WIDTH) * SIDE_SPACE_Y_DIVIDER;   //resize 5
        float fShearMax = ((float) width / DEFAULT_WIDTH) * SHEAR_MAX_DIVIDER;     //resize 7
        float fShear = ((float) width / DEFAULT_WIDTH) * SHEAR_DIVIDER;        //resize 7

        sideSpaceX = (int)fSideSpaceX;
        sideSpaceY = (int)fSideSpaceY;
        shearMax = (int)fShearMax;
        shear = (int)fShear;

        sizeX = (int) fxSize;
        borderX = (int) fxBorder;
        pivotX = (int) fxPivot;

        sizeY = (int) fySize;
        borderY = (int) fyBorder;
        pivotY = (int) fyPivot;
    }
}
