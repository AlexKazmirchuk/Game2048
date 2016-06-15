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


    public static final int SIDE_SPACE_X = 5;
    public static final int SIDE_SPACE_Y = 5;

    public static final int SHEAR_MAX = 7;

    //Свойства
    private int id;
    private Bitmap skinImage;
    private int[] colors = new int[3];
    public int x,y;
    private GameActivity context;
    private boolean locker = true;

    private boolean isFresh = false;
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
        this.id = 0;
    }

    // Методи

    //Метод для вибору картинки з папки drawable
    private void selectSkinFromRes(int id) {
        switch (id){
            case 0:
                skinImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_default);
                break;
            case 2:
                skinImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_two);
                break;
            case 4:
                skinImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_four);
                break;
            case 8:
                skinImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_eight);
                break;
            case 16:
                skinImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_sixteen);
                break;
            case 32:
                skinImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_thirty_two);
                break;
            case 64:
                skinImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_sixty_four);
                break;
            case 128:
                skinImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_one_hundred_twenty_eight);
                break;
            case 256:
                skinImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_two_hundred_fifty_six);
                break;
            case 512:
                skinImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_five_hundred_twelve);
                break;
            case 1024:
                skinImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_one_thousand_twenty_four);
                break;
            case 2048:
                skinImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.item_two_thousands_forty_eight);
                break;
        }
    }

    public void draw(Canvas g){
        if (locker){
            getSizes(g.getHeight(),g.getWidth());
            locker = false;
        }
//        bitmapDraw(g);
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
        p.setColor(colors[0]);
        g.drawRect(posX + shearR,posY + shearR,posX+sizeX - SIDE_SPACE_X + shearR,posY+sizeY - SIDE_SPACE_Y + shearR,p);

        drawNumbers(g);


    }

    private void drawRightRomb(Canvas g){
        Paint p = new Paint();
        p.setColor(colors[1]);
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
        p.setColor(colors[2]);
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
            p.getTextBounds(text, 0, text.length(), mTextBoundRect);
            mTextWidth = p.measureText(text);
            mTextHeight = mTextBoundRect.height();

            p.setColor(Color.parseColor("#741111"));
            if (shearR <=5){
                int a = 0;
                for (int i = 1; i < (5 - shearR); i++) {
                    g.drawText(text,(centerX - (mTextWidth / 2f)) - SIDE_SPACE_X + i,(centerY + (mTextHeight /2f)) - SIDE_SPACE_Y + i, p);
                    a = i;
                }
                p.setColor(Color.parseColor("#fa1515"));
                g.drawText(text,(centerX - (mTextWidth/2f)) - SIDE_SPACE_X-a+3,(centerY + (mTextHeight/2f))-SIDE_SPACE_Y-a+3,p);
            }
        }
    }

    private void bitmapDraw(Canvas g){
        selectSkinFromRes(this.id);
        skinImage = Bitmap.createScaledBitmap(skinImage,sizeX,sizeY,false);
        g.drawBitmap(skinImage, this.posX, this.posY,new Paint());
    }

    private void setColorFromRes(int id){
                int i = context.getResources().getIdentifier("colorOwn" + id, "color", context.getPackageName());
                colors[0] = ContextCompat.getColor(context,i);

                i = context.getResources().getIdentifier("colorRight" + id, "color", context.getPackageName());
                colors[1] = ContextCompat.getColor(context,i);

                i = context.getResources().getIdentifier("colorBottom" + id, "color", context.getPackageName());
                colors[2] = ContextCompat.getColor(context,i);
    }

    private void getSizes(int height, int width) {

        float fxSize = ((float) width /400)*88;
        float fxBorder = ((float) width /400)*10;
        float fxPivot = ((float) width /400)*10;

        float fySize = ((float) height /400)*88;
        float fyBorder = ((float) height /400)*11;
        float fyPivot = ((float) height /400)*10;

        sizeX = (int) fxSize;
        int borderX = (int) fxBorder;
        int pivotX = (int) fxPivot;

        sizeY = (int) fySize;
        int borderY = (int) fyBorder;
        int pivotY = (int) fyPivot;

        this.posX = (sizeX*x) + borderX *x + pivotX;
        this.posY = (sizeY*y) + borderY *y + pivotY;
    }

    private float resizeText(int textLength){
        if (textLength>0){
            if(textLength == 3){
                return 35.0f;
            } else if (textLength == 4){
                return 29.0f;
            } else {
                return 55.0f;
            }
        }
        return 55.0f;
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

