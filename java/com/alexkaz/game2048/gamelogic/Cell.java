package com.alexkaz.game2048.gamelogic;

//import javax.imageio.ImageIO;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import com.alexkaz.game2048.GameActivity;
import com.alexkaz.game2048.R;

public class Cell {


    //Свойства
    private int id;
    private Bitmap skinImage;
    public int posX;
    public int posY;
    private Canvas g;
    private GameActivity context;

    //Конструктор
    public Cell(GameActivity context, int posX, int posY){
        this.context = context;
        this.posX = posX;
        this.posY = posY;
        this.id = 0;

        selectSkinFromRes(this.id);
    }

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

    // Методи
    public void draw(Canvas g){
        selectSkinFromRes(this.id);

    }

    public void setId(int id){
        this.id = id;
    }


    public int getId() {
        return id;
    }
}

