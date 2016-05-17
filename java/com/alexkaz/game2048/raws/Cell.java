package com.alexkaz.game2048.raws;

//import javax.imageio.ImageIO;
import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.alexkaz.game2048.GameActivity;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Cell {


    //Свойства
    private int id;
    private Bitmap skin;
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

    }

    // Методи
    public void draw(Canvas g){

    }

    public void setId(int id){
        this.id = id;
    }


    public int getId() {
        return id;
    }
}

