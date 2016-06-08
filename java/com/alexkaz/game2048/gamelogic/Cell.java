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

    //Свойства
    private int id;
    private Bitmap skinImage;
    private int[] colors = new int[3];
    public int x,y;
    private GameActivity context;

    private boolean locker = true;

    private int posX, posY;
    private int shear = 7,shearR = 0;

    private int sizeX ;
    private int borderX ;
    private int pivotX ;

    private int sizeY ;
    private int borderY ;
    private int pivotY ;

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
        g.drawRect(posX,posY,posX+sizeX - SIDE_SPACE_X,posY+sizeY - SIDE_SPACE_Y,p);

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
            p.getTextBounds(text, 0, text.length(), mTextBoundRect);
            mTextWidth = p.measureText(text);
            mTextHeight = mTextBoundRect.height();

            p.setColor(Color.parseColor("#741111"));
            g.drawText(text,(centerX - (mTextWidth / 2f)) - SIDE_SPACE_X + 1,(centerY + (mTextHeight /2f)) - SIDE_SPACE_Y + 1, p);
            g.drawText(text,(centerX - (mTextWidth / 2f)) - SIDE_SPACE_X + 2,(centerY + (mTextHeight /2f)) - SIDE_SPACE_Y + 2, p);
            g.drawText(text,(centerX - (mTextWidth / 2f)) - SIDE_SPACE_X + 3,(centerY + (mTextHeight /2f)) - SIDE_SPACE_Y + 3, p);
            g.drawText(text,(centerX - (mTextWidth / 2f)) - SIDE_SPACE_X + 4,(centerY + (mTextHeight /2f)) - SIDE_SPACE_Y + 4, p);
            p.setColor(Color.parseColor("#fa1515"));
            g.drawText(text,(centerX - (mTextWidth/2f)) - SIDE_SPACE_X,(centerY + (mTextHeight/2f))-SIDE_SPACE_Y,p);
        }
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

    private void bitmapDraw(Canvas g){
        selectSkinFromRes(this.id);
        skinImage = Bitmap.createScaledBitmap(skinImage,sizeX,sizeY,false);
        g.drawBitmap(skinImage, this.posX, this.posY,new Paint());
    }

    private void setColorFromRes(int id){
        switch (id){
            case 0:
//                int i = context.getResources().getIdentifier("colorOwnDefault", "color", context.getPackageName());
//                colors[0] = ContextCompat.getColor(context,i);
                colors[0] = ContextCompat.getColor(context,R.color.colorOwnDefault);
                colors[1] = ContextCompat.getColor(context,R.color.colorRightDefault);
                colors[2] = ContextCompat.getColor(context,R.color.colorBottomDefault);
                break;
            case 2:
                colors[0] = ContextCompat.getColor(context,R.color.colorOwn2);
                colors[1] = ContextCompat.getColor(context,R.color.colorRight2);
                colors[2] = ContextCompat.getColor(context,R.color.colorBottom2);
                break;
            case 4:
                colors[0] = ContextCompat.getColor(context,R.color.colorOwn4);
                colors[1] = ContextCompat.getColor(context,R.color.colorRight4);
                colors[2] = ContextCompat.getColor(context,R.color.colorBottom4);
                break;
            case 8:
                colors[0] = ContextCompat.getColor(context,R.color.colorOwn8);
                colors[1] = ContextCompat.getColor(context,R.color.colorRight8);
                colors[2] = ContextCompat.getColor(context,R.color.colorBottom8);
                break;
            case 16:
                colors[0] = ContextCompat.getColor(context,R.color.colorOwn16);
                colors[1] = ContextCompat.getColor(context,R.color.colorRight16);
                colors[2] = ContextCompat.getColor(context,R.color.colorBottom16);
                break;
            case 32:
                colors[0] = ContextCompat.getColor(context,R.color.colorOwn32);
                colors[1] = ContextCompat.getColor(context,R.color.colorRight32);
                colors[2] = ContextCompat.getColor(context,R.color.colorBottom32);
                break;
            case 64:
                colors[0] = ContextCompat.getColor(context,R.color.colorOwn64);
                colors[1] = ContextCompat.getColor(context,R.color.colorRight64);
                colors[2] = ContextCompat.getColor(context,R.color.colorBottom64);
                break;
            case 128:
                colors[0] = ContextCompat.getColor(context,R.color.colorOwn128);
                colors[1] = ContextCompat.getColor(context,R.color.colorRight128);
                colors[2] = ContextCompat.getColor(context,R.color.colorBottom128);
                break;
            case 256:
                colors[0] = ContextCompat.getColor(context,R.color.colorOwn256);
                colors[1] = ContextCompat.getColor(context,R.color.colorRight256);
                colors[2] = ContextCompat.getColor(context,R.color.colorBottom256);
                break;
            case 512:
                colors[0] = ContextCompat.getColor(context,R.color.colorOwn512);
                colors[1] = ContextCompat.getColor(context,R.color.colorRight512);
                colors[2] = ContextCompat.getColor(context,R.color.colorBottom512);
                break;
            case 1024:
                colors[0] = ContextCompat.getColor(context,R.color.colorOwn1024);
                colors[1] = ContextCompat.getColor(context,R.color.colorRight1024);
                colors[2] = ContextCompat.getColor(context,R.color.colorBottom1024);
                break;
            case 2048:
                colors[0] = ContextCompat.getColor(context,R.color.colorOwn2048);
                colors[1] = ContextCompat.getColor(context,R.color.colorRight2048);
                colors[2] = ContextCompat.getColor(context,R.color.colorBottom2048);
                break;
        }
    }

    private void getSizes(int height, int width) {

        float fHeight = height;
        float fWidth = width;

        float fxSize = (fWidth/400)*88;
        float fxBorder = (fWidth/400)*7;
        float fxPivot = (fWidth/400)*10;

        float fySize = (fHeight/400)*88;
        float fyBorder = (fHeight/400)*7;
        float fyPivot = (fHeight/400)*10;

        sizeX = (int) fxSize;
        borderX = (int) fxBorder;
        pivotX = (int) fxPivot;

        sizeY = (int) fySize;
        borderY = (int) fyBorder;
        pivotY = (int) fyPivot;

        this.posX = (sizeX*x) + borderX*x + pivotX;
        this.posY = (sizeY*y) + borderY*y + pivotY;
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
}

