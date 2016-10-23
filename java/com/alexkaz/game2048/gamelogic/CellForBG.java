package com.alexkaz.game2048.gamelogic;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import com.alexkaz.game2048.GameActivity;

class CellForBG {

    //for cell
    private static final int COLOR_OWN = 0;
    private static final int COLOR_RIGHT = 1;
    private static final int COLOR_BOTTOM = 2;
    private static final int DEFAULT_WIDTH = 400;
    private static final int CELL_SIDE_X_DIVIDER = 88;
    private static final int CELL_BORDER_X_DIVIDER = 10;
    private static final int CELL_PIVOT_X_DIVIDER = 10;
    private static final int CELL_SIDE_Y_DIVIDER = 88;
    private static final int CELL_BORDER_Y_DIVIDER = 11;
    private static final int CELL_PIVOT_Y_DIVIDER = 10;
    private static final int SIDE_SPACE_X_DIVIDER = 5;
    private static final int SIDE_SPACE_Y_DIVIDER = 5;
    private static final int SHEAR_DIVIDER = 7;
    private static final String RESOURCE_TYPE = "color";

    private int sideSpaceX; //resizable
    private int sideSpaceY; //resizable
    private int shear;      //resizable

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

    private Paint rectPaint;
    private Paint rightRectPaint;
    private Paint bottomRectPaint;

    private Path rectPath;
    private Path rightRectPath;
    private Path bottomRectPath;
    private Path buff1;
    private Path buff2;

    //Конструктор
    CellForBG(GameActivity context){
        this.context = context;
        initComp();
    }

    private void initComp() {
        rectPaint = new Paint();
        rightRectPaint = new Paint();
        bottomRectPaint = new Paint();

        rectPath = new Path();
        rightRectPath = new Path();
        bottomRectPath = new Path();

        buff1 = new Path();
        buff2 = new Path();
    }

    // Методи
    void draw(Canvas g){
        if (locker){
            getSizes(g.getHeight(),g.getWidth());

            setColorFromRes();
            rectPaint.setColor(colors[COLOR_OWN]);
            rightRectPaint.setColor(colors[COLOR_RIGHT]);
            bottomRectPaint.setColor(colors[COLOR_BOTTOM]);

            initPaths();
            locker = false;
        }
        drawMyCell(g);
    }

    private void drawMyCell(Canvas g) {
        g.drawPath(rectPath,rectPaint);
        g.drawPath(rightRectPath, rightRectPaint);
        g.drawPath(bottomRectPath, bottomRectPaint);
    }

    private void initPaths(){
        int posX,posY;
        for (int x = 0; x < 4; x++) {
            for (int y = 0; y < 4; y++) {
                posX = sizeX*x + borderX*x + pivotX;
                posY = sizeY*y + borderY*y + pivotY;

                int shearAnim = 0;
                int left = posX + shearAnim;
                int top = posY + shearAnim;
                int right = posX + sizeX - sideSpaceX + shearAnim;
                int bottom = posY + sizeY - sideSpaceY + shearAnim;
                rectPath.addRect(left, top, right, bottom, Path.Direction.CCW);

                buff1.reset();
                buff1.moveTo(posX + sizeX + shearAnim - sideSpaceX, posY + shearAnim);                        // left  top
                buff1.lineTo(posX+sizeX+shear - sideSpaceX, posY+shear);                                      // right top
                buff1.lineTo(posX+sizeX+shear - sideSpaceX, posY+sizeY + shear - sideSpaceY);               // right bottom
                buff1.lineTo(posX+sizeX+ shearAnim - sideSpaceX, posY+sizeY + shearAnim - sideSpaceY);      // left  bottom
                buff1.lineTo(posX + sizeX + shearAnim - sideSpaceX, posY + shearAnim);
                rightRectPath.addPath(buff1);

                buff2.reset();
                buff2.moveTo(posX + shearAnim, posY+sizeY + shearAnim - sideSpaceY);                           // left  top
                buff2.lineTo(posX+sizeX+ shearAnim - sideSpaceX, posY+sizeY+ shearAnim - sideSpaceY);       // right top
                buff2.lineTo(posX+sizeX+shear- sideSpaceX, posY+sizeY+shear- sideSpaceY);                    // right bottom
                buff2.lineTo(posX+shear, posY+sizeY+shear- sideSpaceY);                                        // left  bottom
                buff2.lineTo(posX + shearAnim, posY+sizeY + shearAnim - sideSpaceY);
                bottomRectPath.addPath(buff2);
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
        float fShear = ((float) width / DEFAULT_WIDTH) * SHEAR_DIVIDER;        //resize 7

        sideSpaceX = (int)fSideSpaceX;
        sideSpaceY = (int)fSideSpaceY;
        shear = (int)fShear;

        sizeX = (int) fxSize;
        borderX = (int) fxBorder;
        pivotX = (int) fxPivot;

        sizeY = (int) fySize;
        borderY = (int) fyBorder;
        pivotY = (int) fyPivot;
    }
}
