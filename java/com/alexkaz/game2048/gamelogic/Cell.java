package com.alexkaz.game2048.gamelogic;

//import javax.imageio.ImageIO;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;

import com.alexkaz.game2048.GameActivity;
import com.alexkaz.game2048.R;

public class Cell {


    //for cell
    public static final int SIDE_SPACE_X = 5;
    public static final int SIDE_SPACE_Y = 5;
    public static final int DEFAULT_ID = 0;
    public static final int SHEAR_MAX = 7;
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

    //for text
    public static final int START_TEXT_POSITION = 0;
    public static final String TEXT_SHADOW_COLOR = "#741111";
    public static final String TEXT_COLOR = "#fa1515";
    public static final int TEXT_SHEAR = 5;
    public static final float NORMAL_TEXT_SIZE = 35.0f;
    public static final float SMALL_TEXT_SIZE = 29.0f;
    public static final float LARGE_TEXT_SIZE = 55.0f;
    public static final int THREE_SYMBOLS = 3;
    public static final int FOUR_SYMBOLS = 4;
    public static final String RESOURCE_TYPE = "color";
    public static final int SHEAR_FOR_TEXT = 3; //rename

    //Свойства
    private GameActivity context;
    private int id;
    private int[] colors = new int[3];

    private boolean locker = true;
    private boolean isFresh = false;

    public int x,y;
    private int posX, posY;

    private int shear = 7;
    private int shearR = 0;

    private int sizeX ;
    private int sizeY ;

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
        drawMyCell(g);
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
        g.drawRect(posX + shearR,posY + shearR,posX+sizeX - SIDE_SPACE_X + shearR,posY+sizeY - SIDE_SPACE_Y + shearR,p);

        drawNumbers(g);
    }

    private void drawRightRomb(Canvas g){
        Paint p = new Paint();
        p.setColor(colors[COLOR_RIGHT]);
        Path path = new Path();
        path.moveTo(posX + sizeX + shearR - SIDE_SPACE_X, posY + shearR);
        path.lineTo(posX+sizeX+shear - SIDE_SPACE_X, posY+shear);
        path.lineTo(posX+sizeX+shear - SIDE_SPACE_X, posY+sizeY + shear - SIDE_SPACE_Y);
        path.lineTo(posX+sizeX+shearR - SIDE_SPACE_X, posY+sizeY + shearR - SIDE_SPACE_Y);
        path.lineTo(posX + sizeX + shearR - SIDE_SPACE_X, posY + shearR);
        g.drawPath(path,p);
    }

    private void drawBottomRomb(Canvas g){
        Paint p = new Paint();
        p.setColor(colors[COLOR_BOTTOM]);
        Path path = new Path();
        path.moveTo(posX+shearR, posY+sizeY+shearR-SIDE_SPACE_Y);
        path.lineTo(posX+sizeX+shearR- SIDE_SPACE_X, posY+sizeY+shearR-SIDE_SPACE_Y);
        path.lineTo(posX+sizeX+shear- SIDE_SPACE_X, posY+sizeY+shear-SIDE_SPACE_Y);
        path.lineTo(posX+shear, posY+sizeY+shear-SIDE_SPACE_Y);
        path.lineTo(posX+shearR, posY+sizeY+shearR-SIDE_SPACE_Y);
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
            if (shearR <= TEXT_SHEAR){
                int paddingFix = 0;
                for (int i = 1; i < (TEXT_SHEAR - shearR); i++) {
                    g.drawText(text,(centerX - (mTextWidth / 2f)) - SIDE_SPACE_X + i,(centerY + (mTextHeight /2f)) - SIDE_SPACE_Y + i, p);
                    paddingFix = i;
                }
                p.setColor(Color.parseColor(TEXT_COLOR));
                g.drawText(text,(centerX - (mTextWidth/2f)) - SIDE_SPACE_X-paddingFix+ SHEAR_FOR_TEXT,(centerY + (mTextHeight/2f))-SIDE_SPACE_Y-paddingFix+SHEAR_FOR_TEXT,p);
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

        sizeX = (int) fxSize;
        int borderX = (int) fxBorder;
        int pivotX = (int) fxPivot;

        sizeY = (int) fySize;
        int borderY = (int) fyBorder;
        int pivotY = (int) fyPivot;

        this.posX = (sizeX*x) + borderX*x + pivotX;
        this.posY = (sizeY*y) + borderY*y + pivotY;
    }

    private float resizeText(int textLength){
        if (textLength > 0){
            if(textLength == THREE_SYMBOLS){
                return NORMAL_TEXT_SIZE;
            } else if (textLength == FOUR_SYMBOLS){
                return SMALL_TEXT_SIZE;
            } else {
                return LARGE_TEXT_SIZE;
            }
        }
        return LARGE_TEXT_SIZE;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getShearR() {
        return shearR;
    }

    public void setShearR(int shearR) {
        this.shearR = shearR;
    }

    public boolean isFresh() {
        return isFresh;
    }

    public void setFresh(boolean fresh) {
        isFresh = fresh;
    }
}

