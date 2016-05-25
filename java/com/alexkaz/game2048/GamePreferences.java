package com.alexkaz.game2048;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class GamePreferences {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static final String MY_PREFS = "MyPreferences";

    public static final String MUSIC_SWICH = "musicSwitch";
    public static final String MODE_SWICH = "modeSwitch";
    public static final String BEST_SCORES = "bestScores";

    public GamePreferences(Context context){
        preferences = context.getSharedPreferences(MY_PREFS, Activity.MODE_PRIVATE);
        createPrefs();
    }

    private void createPrefs() {
        editor = preferences.edit();
        if (!preferences.contains(MUSIC_SWICH)){
            editor.putBoolean(MUSIC_SWICH,true);
            editor.putInt(MODE_SWICH,0);
            editor.putInt(BEST_SCORES,0);
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

}
