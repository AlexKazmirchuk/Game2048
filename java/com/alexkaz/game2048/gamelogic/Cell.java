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

class Cell {

    //for cell
    private static final int DEFAULT_ID = 0;
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
    private static final int SHEAR_MAX_DIVIDER = 7;
    private static final int SHEAR_DIVIDER = 7;
    private static final int FIVE_SYMBOLS = 5;

    private int sideSpaceX; //resizable
    private int sideSpaceY; //resizable
    private int shearMax;   //resizable
    private int shear;      //resizable
    private int shearAnim = 0;

    //for text
    private static final int START_TEXT_POSITION = 0;
    private static final String TEXT_SHADOW_COLOR = "#741111";
    private static final String TEXT_COLOR = "#fa1515";
    private static final int THREE_SYMBOLS = 3;
    private static final int FOUR_SYMBOLS = 4;
    private static final String RESOURCE_TYPE = "color";
    private static final float TEXT_SHEAR_DIVIDER = 0.7143f;
    private static final float SHEAR_FOR_TEXT_DIVIDER = 0.4286f;
    private static final float NORMAL_TEXT_SIZE_DIVIDER = 0.5289f;
    private static final float SMALL_TEXT_SIZE_DIVIDER = 0.4223f;
    private static final float VERY_SMALL_TEXT_SIZE_DIVIDER = 0.3223f;
    private static final float LARGE_TEXT_SIZE_DIVIDER = 0.7512f;

    private int textShear = 5;      //resizable
    private int shearForText = 3;   //resizable
    private float normalTextSize = 35.0f; //resizable    35.0f
    private float smallTextSize = 29.0f;  //resizable    29.0f
    private float verySmallTextSize = 29.0f;  //resizable    29.0f
    private float largeTextSize = 55.0f;  //resizable    55.0f

    //Свойства
    private GameActivity context;
    private int id;
    private int[] colors = new int[3];
    private boolean locker = true;
    private boolean isFresh = false;

    private int x,y;
    private int posX, posY;  //resizable
    private int sizeX ;      //resizable
    private int sizeY ;      //resizable
    private int borderX ;      //resizable
    private int borderY ;      //resizable
    private int cellPosX;
    private int cellPosY;

    //for anim
    private int moving = 0;
    private int merged = 0;
    private int anim = 0;
    private int moveX = 0;
    private int moveY = 0;

    //for paint
    private Paint rectPaint;
    private Paint rightRectPaint;
    private Paint bottomRectPaint;
    private Paint numberPaint;

    //Конструктор
    Cell(GameActivity context, int x, int y){
        this.context = context;
        this.x = x;
        this.y = y;
        this.id = DEFAULT_ID;
        initComp(context);
    }

    private void initComp(GameActivity context) {
        Typeface typeFace = context.getTypeface();
        rectPaint = new Paint();
        rightRectPaint = new Paint();
        bottomRectPaint = new Paint();
        numberPaint = new Paint();
        numberPaint.setTypeface(typeFace);
        numberPaint.setStyle(Paint.Style.FILL);
        numberPaint.setAntiAlias(true);
    }

    // Методи
    void draw(Canvas g){
        if (locker){
            getSizes(g.getHeight(),g.getWidth());
            locker = false;
        }

        this.posX = cellPosX;
        this.posY = cellPosY;

        if (CellManager.getSwipeDirection() == Direction.RIGHT){
            this.posX = cellPosX - getMoveX();
        } else if(CellManager.getSwipeDirection() == Direction.LEFT){
            this.posX = cellPosX + getMoveX();
        } else if(CellManager.getSwipeDirection() == Direction.DOWN){
            this.posY = cellPosY - getMoveY();
        } else if(CellManager.getSwipeDirection() == Direction.UP){
            this.posY = cellPosY + getMoveY();
        }

        if(this.id != 0){
            drawMyCell(g);
        }
    }

    private void drawMyCell(Canvas g) {
        setColorFromRes(this.id);
        drawRect(g);
        drawRightRect(g);
        drawBottomRect(g);
    }

    private void drawRect(Canvas g){
        rectPaint.setColor(colors[COLOR_OWN]);

        int left = posX + shearAnim;
        int top = posY + shearAnim;
        int right = posX + sizeX - sideSpaceX + shearAnim;
        int bottom = posY+sizeY - sideSpaceY + shearAnim;

        g.drawRect(left,top,right,bottom,rectPaint);

        drawNumbers(g);
    }

    private void drawRightRect(Canvas g){
        rightRectPaint.setColor(colors[COLOR_RIGHT]);
        Path path = new Path();

        path.moveTo(posX + sizeX + shearAnim - sideSpaceX, posY + shearAnim);                        // left  top
        path.lineTo(posX+sizeX+shear - sideSpaceX, posY+shear);                                      // right top
        path.lineTo(posX+sizeX+shear - sideSpaceX, posY+sizeY + shear - sideSpaceY);               // right bottom
        path.lineTo(posX+sizeX+ shearAnim - sideSpaceX, posY+sizeY + shearAnim - sideSpaceY);      // left  bottom
        path.lineTo(posX + sizeX + shearAnim - sideSpaceX, posY + shearAnim);                        // left  top

        g.drawPath(path, rightRectPaint);
    }

    private void drawBottomRect(Canvas g){
        bottomRectPaint.setColor(colors[COLOR_BOTTOM]);
        Path path = new Path();

        path.moveTo(posX + shearAnim, posY+sizeY + shearAnim - sideSpaceY);                           // left  top
        path.lineTo(posX+sizeX+ shearAnim - sideSpaceX, posY+sizeY+ shearAnim - sideSpaceY);       // right top
        path.lineTo(posX+sizeX+shear- sideSpaceX, posY+sizeY+shear- sideSpaceY);                    // right bottom
        path.lineTo(posX+shear, posY+sizeY+shear- sideSpaceY);                                        // left  bottom
        path.lineTo(posX + shearAnim, posY+sizeY + shearAnim - sideSpaceY);                           // left  top

        g.drawPath(path, bottomRectPaint);
    }

    private void drawNumbers(Canvas g){

        float mTextWidth, mTextHeight;
        String text = String.valueOf(this.id);
        Rect mTextBoundRect = new Rect();
        float width, height,centerX, centerY;

        width = sizeX;
        height = sizeY;
        centerX = posX + width/2;
        centerY = posY + height/2;

        if(this.id > 0){
            numberPaint.setTextSize(resizeText(text.length()));
            numberPaint.getTextBounds(text, START_TEXT_POSITION, text.length(), mTextBoundRect);
            mTextWidth = numberPaint.measureText(text);
            mTextHeight = mTextBoundRect.height();
            numberPaint.setColor(Color.parseColor(TEXT_SHADOW_COLOR));
            if (shearAnim <= textShear){
                int paddingFix = 0;
                for (int i = 1; i < (textShear - shearAnim); i++) {
                    g.drawText(text,(centerX - (mTextWidth / 2f)) - sideSpaceX + i,(centerY + (mTextHeight /2f)) - sideSpaceY + i, numberPaint);
                    paddingFix = i;
                }
                numberPaint.setColor(Color.parseColor(TEXT_COLOR));
                g.drawText(text,(centerX - (mTextWidth/2f)) - sideSpaceX -paddingFix+ shearForText,(centerY + (mTextHeight/2f))- sideSpaceY -paddingFix+ shearForText,numberPaint);
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
        int pivotX = (int) fxPivot;

        sizeY = (int) fySize;
        borderY = (int) fyBorder;
        int pivotY = (int) fyPivot;

        cellPosX = (sizeX*x) + borderX*x + pivotX;
        cellPosY = (sizeY*y) + borderY*y + pivotY;

        this.posX = cellPosX;
        this.posY = cellPosY;

        normalTextSize = new BigDecimal(sizeX * NORMAL_TEXT_SIZE_DIVIDER).setScale(0, RoundingMode.DOWN).floatValue();
        smallTextSize = new BigDecimal(sizeX * SMALL_TEXT_SIZE_DIVIDER).setScale(0, RoundingMode.DOWN).floatValue();
        verySmallTextSize = new BigDecimal(sizeX * VERY_SMALL_TEXT_SIZE_DIVIDER).setScale(0, RoundingMode.DOWN).floatValue();
        largeTextSize = new BigDecimal(sizeX * LARGE_TEXT_SIZE_DIVIDER).setScale(0, RoundingMode.DOWN).floatValue();
    }

    private float resizeText(int textLength){
        if (textLength > 0){
            if(textLength == THREE_SYMBOLS){
                return normalTextSize;
            } else if (textLength == FOUR_SYMBOLS){
                return smallTextSize;
            } else if (textLength == FIVE_SYMBOLS){
                return verySmallTextSize;
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

    int getShearAnim() {
        return shearAnim;
    }

    void setShearAnim(int shearAnim) {
        this.shearAnim = shearAnim;
    }

    boolean isFresh() {
        return isFresh;
    }

    void setFresh(boolean fresh) {
        isFresh = fresh;
    }

    int getShearMax() {
        return shearMax;
    }

    void calculateMoveX(){
        this.setMoveX(sizeX* getAnim() + borderX* getAnim());
    }

    void calculateMoveY(){
        this.setMoveY(sizeY* getAnim() + borderY* getAnim());
    }

    int getMoving() {
        return moving;
    }

    void setMoving(int moving) {
        this.moving = moving;
    }

    int getMerged() {
        return merged;
    }

    void setMerged(int merged) {
        this.merged = merged;
    }

    public int getAnim() {
        return anim;
    }

    public void setAnim(int anim) {
        this.anim = anim;
    }

    int getMoveX() {
        return moveX;
    }

    void setMoveX(int moveX) {
        this.moveX = moveX;
    }

    int getMoveY() {
        return moveY;
    }

    void setMoveY(int moveY) {
        this.moveY = moveY;
    }
}

