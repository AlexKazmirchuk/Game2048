package com.alexkaz.game2048.gamelogic;

//import javax.imageio.ImageIO;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.alexkaz.game2048.GameActivity;
import com.alexkaz.game2048.R;

public class Cell {


    //Свойства
    private int id;
    private Bitmap skinImage;
    private int[] colors = new int[3];
    public int x,y;
    private GameActivity context;

    private boolean locker = true;

    private int posX, posY;
    private int shear = 10,shearR = 0;

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
        g.drawRect(posX,posY,posX+sizeY-10,posY+sizeY-10,p);
        p.setColor(Color.CYAN);
        g.drawText(String.valueOf(this.id),posX+30,posY+30,p);
    }

    private void drawRightRomb(Canvas g){
        Paint p = new Paint();
        p.setColor(colors[1]);
        Path path = new Path();
        path.moveTo(posX + sizeX + shearR-13,posY + shearR);
        path.lineTo(posX+sizeX+shear-13, posY+shear);
        path.lineTo(posX+sizeX+shear-13, posY+sizeY+shear-10);
        path.lineTo(posX+sizeX+shearR-13, posY+sizeY+shearR-10);
        path.lineTo(posX + sizeX + shearR-13,posY + shearR);
        g.drawPath(path,p);
    }

    private void drawBottomRomb(Canvas g){
        Paint p = new Paint();
        p.setColor(colors[2]);
        Path path = new Path();
        path.moveTo(posX+shearR, posY+sizeY+shearR-10);
        path.lineTo(posX+sizeX+shearR-14, posY+sizeY+shearR-10);
        path.lineTo(posX+sizeX+shear-14, posY+sizeY+shear-10);
        path.lineTo(posX+shear, posY+sizeY+shear-10);
        path.lineTo(posX+shearR, posY+sizeY+shearR-10);
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
                colors[0] = context.getResources().getColor(R.color.colorOwnDefault);
                colors[1] = context.getResources().getColor(R.color.colorRightDefault);
                colors[2] = context.getResources().getColor(R.color.colorBottomDefault);
                break;
            case 2:
                colors[0] = context.getResources().getColor(R.color.colorOwn2);
                colors[1] = context.getResources().getColor(R.color.colorRight2);
                colors[2] = context.getResources().getColor(R.color.colorBottom2);
                break;
            case 4:
                colors[0] = context.getResources().getColor(R.color.colorOwn4);
                colors[1] = context.getResources().getColor(R.color.colorRight4);
                colors[2] = context.getResources().getColor(R.color.colorBottom4);
                break;
            case 8:
                colors[0] = context.getResources().getColor(R.color.colorOwn8);
                colors[1] = context.getResources().getColor(R.color.colorRight8);
                colors[2] = context.getResources().getColor(R.color.colorBottom8);
                break;
            case 16:
                colors[0] = context.getResources().getColor(R.color.colorOwn16);
                colors[1] = context.getResources().getColor(R.color.colorRight16);
                colors[2] = context.getResources().getColor(R.color.colorBottom16);
                break;
            case 32:
                colors[0] = context.getResources().getColor(R.color.colorOwn32);
                colors[1] = context.getResources().getColor(R.color.colorRight32);
                colors[2] = context.getResources().getColor(R.color.colorBottom32);
                break;
            case 64:
                colors[0] = context.getResources().getColor(R.color.colorOwn64);
                colors[1] = context.getResources().getColor(R.color.colorRight64);
                colors[2] = context.getResources().getColor(R.color.colorBottom64);
                break;
            case 128:
                colors[0] = context.getResources().getColor(R.color.colorOwn128);
                colors[1] = context.getResources().getColor(R.color.colorRight128);
                colors[2] = context.getResources().getColor(R.color.colorBottom128);
                break;
            case 256:
                colors[0] = context.getResources().getColor(R.color.colorOwn256);
                colors[1] = context.getResources().getColor(R.color.colorRight256);
                colors[2] = context.getResources().getColor(R.color.colorBottom256);
                break;
            case 512:
                colors[0] = context.getResources().getColor(R.color.colorOwn512);
                colors[1] = context.getResources().getColor(R.color.colorRight512);
                colors[2] = context.getResources().getColor(R.color.colorBottom512);
                break;
            case 1024:
                colors[0] = context.getResources().getColor(R.color.colorOwn1024);
                colors[1] = context.getResources().getColor(R.color.colorRight1024);
                colors[2] = context.getResources().getColor(R.color.colorBottom1024);
                break;
            case 2048:
                colors[0] = context.getResources().getColor(R.color.colorOwn2048);
                colors[1] = context.getResources().getColor(R.color.colorRight2048);
                colors[2] = context.getResources().getColor(R.color.colorBottom2048);
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

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

