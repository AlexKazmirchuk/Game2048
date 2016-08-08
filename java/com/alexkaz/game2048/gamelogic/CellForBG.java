package com.alexkaz.game2048.gamelogic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;

import com.alexkaz.game2048.GameActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;


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
    private int id;
    private int[] colors = new int[3];

    private boolean locker = true;

    public int x,y;
    private int posX, posY;  //resizable
    private int sizeX ;      //resizable
    private int sizeY ;      //resizable
    private int borderX ;      //resizable
    private int borderY ;      //resizable
    private int pivotX ;      //resizable
    private int pivotY ;      //resizable

    //Конструктор
    public CellForBG(GameActivity context, int x, int y){
        this.context = context;
        this.x = x;
        this.y = y;
        this.id = DEFAULT_ID;
    }

    // Методи
    public void draw(Canvas g){
        if (locker){
            getSizes(g.getHeight(),g.getWidth());
            locker = false;
        }
        drawMyCell(g);
    }

    private void drawMyCell(Canvas g) {
        setColorFromRes();
        drawRect(g);
        drawRightRomb(g);
        drawBottomRomb(g);
    }

    private void drawRect(Canvas g){
        Paint p = new Paint();
        p.setColor(colors[COLOR_OWN]);

        int left = posX + shearAnim;
        int top = posY + shearAnim;
        int right = posX + sizeX - sideSpaceX + shearAnim;
        int bottom = posY+sizeY - sideSpaceY + shearAnim;

        g.drawRect(left,top,right,bottom,p);
    }

    private void drawRightRomb(Canvas g){
        Paint p = new Paint();
        p.setColor(colors[COLOR_RIGHT]);
        Path path = new Path();

        path.moveTo(posX + sizeX + shearAnim - sideSpaceX, posY + shearAnim);                        // left  top
        path.lineTo(posX+sizeX+shear - sideSpaceX, posY+shear);                                      // right top
        path.lineTo(posX+sizeX+shear - sideSpaceX, posY+sizeY + shear - sideSpaceY);               // right bottom
        path.lineTo(posX+sizeX+ shearAnim - sideSpaceX, posY+sizeY + shearAnim - sideSpaceY);      // left  bottom
        path.lineTo(posX + sizeX + shearAnim - sideSpaceX, posY + shearAnim);                        // left  top

        g.drawPath(path,p);
    }

    private void drawBottomRomb(Canvas g){
        Paint p = new Paint();
        p.setColor(colors[COLOR_BOTTOM]);
        Path path = new Path();

        path.moveTo(posX + shearAnim, posY+sizeY + shearAnim - sideSpaceY);                           // left  top
        path.lineTo(posX+sizeX+ shearAnim - sideSpaceX, posY+sizeY+ shearAnim - sideSpaceY);       // right top
        path.lineTo(posX+sizeX+shear- sideSpaceX, posY+sizeY+shear- sideSpaceY);                    // right bottom
        path.lineTo(posX+shear, posY+sizeY+shear- sideSpaceY);                                        // left  bottom
        path.lineTo(posX + shearAnim, posY+sizeY + shearAnim - sideSpaceY);                           // left  top

        g.drawPath(path,p);
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

        this.posX = (sizeX*x) + borderX*x + pivotX;
        this.posY = (sizeY*y) + borderY*y + pivotY;
    }
}
