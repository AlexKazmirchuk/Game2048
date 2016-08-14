package com.alexkaz.game2048.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.alexkaz.game2048.gamelogic.Cell;

public class GamePreferences {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static final String MY_PREFS = "MyPreferences";

    public static final String MUSIC_SWICH = "musicSwitch";
    public static final String MODE_SWICH = "modeSwitch";
    public static final String BEST_SCORES = "bestScores";
    public static final String GAME_SCORES = "gameScores";
    public static final String CELLS_ID = "cellsID";
    public static final String CELLS_ID_DEFAULT = "0 0 0 0 0 0 0 0 2 0 0 0 0 0 0 0";

    public GamePreferences(Context context){
        preferences = context.getSharedPreferences(MY_PREFS, Activity.MODE_PRIVATE);
        createPrefs();
    }

    private void createPrefs() {
        editor = preferences.edit();
        if (!preferences.contains(MUSIC_SWICH)){
            editor.putBoolean(MUSIC_SWICH,true);
            editor.putInt(MODE_SWICH,0);
            editor.putInt(GAME_SCORES,0);
            editor.putInt(BEST_SCORES,0);
            editor.putString(CELLS_ID,CELLS_ID_DEFAULT);
            editor.commit();
        }

    }

    public boolean getMusicPrefs(){
        Log.d("prefs","зчитано : " + String.valueOf(preferences.getBoolean(MUSIC_SWICH,true)));
        return preferences.getBoolean(MUSIC_SWICH,true);
    }

    public void setMusicPrefs(boolean bool){
        editor.putBoolean(MUSIC_SWICH,bool);
        Log.d("prefs","збережено" + bool);
        editor.commit();
    }

    public int getGameScores(){
        return preferences.getInt(GAME_SCORES,0);
    }

    public void setGameScores(int scores){
        editor.putInt(GAME_SCORES,scores);
        editor.commit();
    }

    public int getBestScores(){
        return preferences.getInt(BEST_SCORES,0);
    }

    public void setBestScores(int scores){
        editor.putInt(BEST_SCORES,scores);
        editor.commit();
    }

    public int getMode(){
        return preferences.getInt(MODE_SWICH,0);
    }

    public void setMode(int mode){
        editor.putInt(MODE_SWICH,mode);
        editor.commit();
    }

    public void saveCellsID(String cellsID){
        editor.putString(CELLS_ID,cellsID);
        editor.commit();
    }

    public String recoverCellsID(){
        String cellsID = "";
        cellsID = preferences.getString(CELLS_ID,CELLS_ID_DEFAULT);
        return cellsID;
    }
}
