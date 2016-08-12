package com.alexkaz.game2048.gamelogic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.alexkaz.game2048.GameActivity;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Cell {

    //for anim
    public int moving = 0;
    public int merged = 0;
    public int anim = 0;
    public int moveX = 0;
    public int moveY = 0;


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
    public static final int START_TEXT_POSITION = 0;
    public static final String TEXT_SHADOW_COLOR = "#741111";
    public static final String TEXT_COLOR = "#fa1515";
    public static final int THREE_SYMBOLS = 3;
    public static final int FOUR_SYMBOLS = 4;
    public static final String RESOURCE_TYPE = "color";
    public static final float TEXT_SHEAR_DIVIDER = 0.7143f;
    public static final float SHEAR_FOR_TEXT_DIVIDER = 0.4286f;
    public static final float NORMAL_TEXT_SIZE_DIVIDER = 0.3889f;
    public static final float SMALL_TEXT_SIZE_DIVIDER = 0.3223f;
    public static final float LARGE_TEXT_SIZE_DIVIDER = 0.6112f;

    private int textShear = 5;      //resizable
    private int shearForText = 3;   //resizable
    private float normalTextSize = 35.0f; //resizable    35.0f
    private float smallTextSize = 29.0f;  //resizable    29.0f
    private float largeTextSize = 55.0f;  //resizable    55.0f

    //Свойства
    private GameActivity context;
    private int id;
    private int[] colors = new int[3];

    private boolean locker = true;
    private boolean isFresh = false;

    public int x,y;
    private int posX, posY;  //resizable
    private int sizeX ;      //resizable
    private int sizeY ;      //resizable
    private int borderX ;      //resizable
    private int borderY ;      //resizable
    private int pivotX ;      //resizable
    private int pivotY ;      //resizable
    private int cellPosX;
    private int cellPosY;

    //Конструктор
    public Cell(GameActivity context, int x, int y){
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

        this.posX = cellPosX;
        this.posY = cellPosY;

        if (CellManager.swipeDirection == Direction.RIGHT){
            this.posX = cellPosX - moveX;
        } else if(CellManager.swipeDirection == Direction.LEFT){
            this.posX = cellPosX + moveX;
        } else if(CellManager.swipeDirection == Direction.DOWN){
            this.posY = cellPosY - moveY;
        } else if(CellManager.swipeDirection == Direction.UP){
            this.posY = cellPosY + moveY;
        }

        if(this.id != 0){
            drawMyCell(g);
        }
    }

    private void drawMyCell(Canvas g) {
        setColorFromRes(this.id);
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

        drawNumbers(g);
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

    private void drawNumbers(Canvas g){
        Paint p = new Paint();

        float mTextWidth, mTextHeight;
        String text = String.valueOf(this.id);
        Rect mTextBoundRect = new Rect();
        float width, height,centerX, centerY;

        width = sizeX;
        height = sizeY;
        centerX = posX + width/2;
        centerY = posY + height/2;

        if(this.id > 0){
            p.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            p.setTextSize(resizeText(text.length()));
            p.setStyle(Paint.Style.FILL);
            p.setAntiAlias(true);
            p.getTextBounds(text, START_TEXT_POSITION, text.length(), mTextBoundRect);
            mTextWidth = p.measureText(text);
            mTextHeight = mTextBoundRect.height();

            p.setColor(Color.parseColor(TEXT_SHADOW_COLOR));
            if (shearAnim <= textShear){
                int paddingFix = 0;
                for (int i = 1; i < (textShear - shearAnim); i++) {
                    g.drawText(text,(centerX - (mTextWidth / 2f)) - sideSpaceX + i,(centerY + (mTextHeight /2f)) - sideSpaceY + i, p);
                    paddingFix = i;
                }
                p.setColor(Color.parseColor(TEXT_COLOR));
                g.drawText(text,(centerX - (mTextWidth/2f)) - sideSpaceX -paddingFix+ shearForText,(centerY + (mTextHeight/2f))- sideSpaceY -paddingFix+ shearForText,p);
            }
        }
    }

    private void setColorFromRes(int id){
                int i = context.getResources().getIdentifier("colorOwn" + id, RESOURCE_TYPE, context.getPackageName());
                colors[COLOR_OWN] = ContextCompat.getColor(context,i);

                i = context.getResources().getIdentifier("colorRight" + id, RESOURCE_TYPE, context.getPackageName());
                colors[COLOR_RIGHT] = ContextCompat.getColor(context,i);

                i = context.getResources().getIdentifier("colorBottom" + id, RESOURCE_TYPE, context.getPackageName());
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

        float fTextShear = shearMax * TEXT_SHEAR_DIVIDER;
        float fShearForText = shearMax * SHEAR_FOR_TEXT_DIVIDER;

        textShear = (int)fTextShear;
        shearForText = (int) fShearForText;

        sizeX = (int) fxSize;
        borderX = (int) fxBorder;
        pivotX = (int) fxPivot;

        sizeY = (int) fySize;
        borderY = (int) fyBorder;
        pivotY = (int) fyPivot;

        cellPosX = (sizeX*x) + borderX*x + pivotX;
        cellPosY = (sizeY*y) + borderY*y + pivotY;

        this.posX = cellPosX;
        this.posY = cellPosY;

        normalTextSize = new BigDecimal(sizeX * NORMAL_TEXT_SIZE_DIVIDER).setScale(0, RoundingMode.DOWN).floatValue();
        smallTextSize = new BigDecimal(sizeX * SMALL_TEXT_SIZE_DIVIDER).setScale(0, RoundingMode.DOWN).floatValue();
        largeTextSize = new BigDecimal(sizeX * LARGE_TEXT_SIZE_DIVIDER).setScale(0, RoundingMode.DOWN).floatValue();
    }

    private float resizeText(int textLength){
        if (textLength > 0){
            if(textLength == THREE_SYMBOLS){
                return normalTextSize;
            } else if (textLength == FOUR_SYMBOLS){
                return smallTextSize;
            } else {
                return largeTextSize;
            }
        }
        return largeTextSize;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getShearAnim() {
        return shearAnim;
    }

    public void setShearAnim(int shearAnim) {
        this.shearAnim = shearAnim;
    }

    public boolean isFresh() {
        return isFresh;
    }

    public void setFresh(boolean fresh) {
        isFresh = fresh;
    }

    public int getShearMax() {
        return shearMax;
    }

    public void setShearMax(int shearMax) {
        this.shearMax = shearMax;
    }

    public void calculateMoveX(){
        this.moveX = sizeX*anim + borderX*anim;
    }

    public void calculateMoveY(){
        this.moveY = sizeY*anim + borderY*anim;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
}

