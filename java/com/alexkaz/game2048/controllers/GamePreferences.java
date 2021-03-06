package com.alexkaz.game2048.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class GamePreferences {

    private static final String MY_PREFS = "MyPreferences";
    private static final String MUSIC_SWITCH = "musicSwitch";
    private static final String MODE_SWITCH = "modeSwitch";
    private static final String BEST_SCORES = "bestScores";
    private static final String GAME_SCORES = "gameScores";
    private static final String CELLS_ID = "cellsID";
    private static final String CELLS_ID_DEFAULT = "0 0 0 0 0 0 0 0 2 0 0 0 0 0 0 0";
    private static final String WIN_DIALOG_SHOWED = "isWinDialogShowed";

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public GamePreferences(Context context){
        preferences = context.getSharedPreferences(MY_PREFS, Activity.MODE_PRIVATE);
        createPrefs();
    }

    private void createPrefs() {
        editor = preferences.edit();
        if (!preferences.contains(MUSIC_SWITCH)){
            editor.putBoolean(MUSIC_SWITCH,true);
            editor.putInt(MODE_SWITCH,0);
            editor.putInt(GAME_SCORES,0);
            editor.putInt(BEST_SCORES,0);
            editor.putBoolean(WIN_DIALOG_SHOWED,true);
            editor.putString(CELLS_ID,CELLS_ID_DEFAULT);
            editor.apply();
        }

    }

    public boolean getMusicPrefs(){
        return preferences.getBoolean(MUSIC_SWITCH,true);
    }

    public void setMusicPrefs(boolean bool){
        editor.putBoolean(MUSIC_SWITCH,bool);
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

    public void saveCellsID(String cellsID){
        editor.putString(CELLS_ID,cellsID);
        editor.commit();
    }

    public String recoverCellsID(){
        String cellsID;
        cellsID = preferences.getString(CELLS_ID,CELLS_ID_DEFAULT);
        return cellsID;
    }

    public void setWinDialogShowed(boolean value){
        editor.putBoolean(WIN_DIALOG_SHOWED,value);
        editor.commit();
    }

    public boolean getWinDialogShowed(){
        return preferences.getBoolean(WIN_DIALOG_SHOWED,true);
    }
}
