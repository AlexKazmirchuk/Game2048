package com.alexkaz.game2048.controllers;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import java.io.IOException;

public class SoundController {

    private static final String GAME_OVER_SOUND = "game_over_sound.mp3";
    private static final String SWIPE_SOUND = "swipe_sound.mp3";
    private static final String WIN_SOUND = "win_sound.mp3";

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;
    private int gameOverSound, swipeSound, winSound;
    private int streamID;
    private boolean isMusicEnabled = false;

    public SoundController(Context context){
        initSound(context);
    }

    public void initSound(Context context){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            createOldSoundPool();
        } else {
            createNewSoundPool();
        }
        mAssetManager = context.getAssets();
        gameOverSound = loadSound(GAME_OVER_SOUND);
        swipeSound = loadSound(SWIPE_SOUND);
        winSound = loadSound(WIN_SOUND);
    }

    @SuppressWarnings("deprecation")
    private void createOldSoundPool() {
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createNewSoundPool() {
        AudioAttributes attributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(attributes)
                .build();
    }

    private int loadSound(String fileName){
        AssetFileDescriptor afd;
        try{
            afd = mAssetManager.openFd(fileName);
        } catch (IOException e){
            e.printStackTrace();
            return -1;
        }
        return mSoundPool.load(afd, 1);
    }

    private int playSound(int sound) {
        if (isMusicEnabled){
            if (sound > 0) {
                streamID = mSoundPool.play(sound, 1, 1, 1, 0, 1);
            }
            return streamID;
        }
        return -1;
    }

    public void playWinSound(){
        playSound(winSound);
    }

    public void playGameOverSound(){
        playSound(gameOverSound);
    }

    public void stopSound(){
        mSoundPool.stop(streamID);
    }

    public void resetSoundPool(){
        if (mSoundPool != null){
            mSoundPool.release();
            mSoundPool = null;
        }
    }

    public void playSwipe(){
        playSound(swipeSound);
    }

    public void setMusicEnabled(boolean musicEnabled) {
        isMusicEnabled = musicEnabled;
    }
}
