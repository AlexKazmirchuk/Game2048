package com.alexkaz.game2048.gamelogic;

//import javax.imageio.ImageIO;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.alexkaz.game2048.GameActivity;
import com.alexkaz.game2048.R;

public class Cell {


    //Свойства
    private int id;
    private Bitmap skinImage;
    public int x,y;
    private GameActivity context;

    private boolean locker = true;

    private int posX, posY;

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

        selectSkinFromRes(this.id);
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
        selectSkinFromRes(this.id);

        if (locker){
            getSizes(g.getHeight(),g.getWidth());
            locker = false;
        }
        skinImage = Bitmap.createScaledBitmap(skinImage,sizeX,sizeY,false);
        g.drawBitmap(skinImage, this.posX, this.posY,new Paint());

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

        selectSkinFromRes(this.id);
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }
}

