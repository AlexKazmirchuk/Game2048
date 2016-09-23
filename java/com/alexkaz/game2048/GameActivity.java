package com.alexkaz.game2048;

import android.annotation.TargetApi;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.alexkaz.game2048.controllers.GamePreferences;
import com.alexkaz.game2048.controllers.SwipeDetector;
import com.alexkaz.game2048.gamelogic.Direction;
import com.alexkaz.game2048.gamelogic.GameSurfaceView;
import com.alexkaz.game2048.uicomp.MenuDialogFragment;

import java.io.IOException;

public class GameActivity extends AppCompatActivity {

    public static final String GAME_OVER_SOUND = "game_over_sound.mp3";
    public static final String SWIPE_SOUND = "swipe_sound.mp3";
    public static final String WIN_SOUND = "win_sound.mp3";
    public static final String MENU_TAG = "menu";

    private GestureDetector gestureDetector;
    public  GamePreferences gamePreferences;
    private GameSurfaceView gameSurfaceView;
    private TextView txtScores;
    private TextView txtBestScores;
    private MenuDialogFragment menuDialogFragment;

    private SoundPool mSoundPool;
    private AssetManager mAssetManager;
    private int gameOverSound, swipeSound, winSound;
    private int streamID;
    private boolean isMusicEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComp();
        initSound();
        readPrefs();
    }

    private void initComp(){
        menuDialogFragment = new MenuDialogFragment();
        gestureDetector = initGestureDetector();
        gamePreferences = new GamePreferences(this);
        txtScores = (TextView) findViewById(R.id.txtScores);
        txtBestScores = (TextView) findViewById(R.id.txtBestScores);
        gameSurfaceView = new GameSurfaceView(this);
        initGameSurface(gameSurfaceView);
    }

    private void initGameSurface(GameSurfaceView gameSurfaceView){
        FrameLayout gameFieldWrapper = (FrameLayout) findViewById(R.id.gameFieldWrapper);
        gameFieldWrapper.addView(gameSurfaceView, FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
        gameSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        });
        gameSurfaceView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {}
        });
    }

    private void readPrefs() {
        isMusicEnabled = gamePreferences.getMusicPrefs();
        int bestScores = gamePreferences.getBestScores();
        String scoresValue = getString(R.string.txt_scores_default_text) + 0;
        txtScores.setText(scoresValue);
        String bestScoresValue = getString(R.string.txt_best_scores_default_text) + bestScores;
        txtBestScores.setText(bestScoresValue);
    }

    private void initSound(){
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            createOldSoundPool();
        } else {
            createNewSoundPool();
        }
        mAssetManager = getAssets();
        gameOverSound = loadSound(GAME_OVER_SOUND);
        swipeSound = loadSound(SWIPE_SOUND);
        winSound = loadSound(WIN_SOUND);
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

    @SuppressWarnings("deprecation")
    private void createOldSoundPool() {
        mSoundPool = new SoundPool(3, AudioManager.STREAM_MUSIC, 0);
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

    @Override
    protected void onPause() {
        super.onPause();
        gamePreferences.saveCellsID(gameSurfaceView.getDrawThreat().getCellManager().getStringCellsID());
        gamePreferences.setGameScores(gameSurfaceView.getDrawThreat().getCellManager().getScores());
        if (mSoundPool != null){
            mSoundPool.release();
            mSoundPool = null;
        }
    }

    private GestureDetector initGestureDetector() {
        return new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            private SwipeDetector detector = new SwipeDetector();
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                try {
                    if (detector.isSwipeDown(e1, e2, velocityY)) {
                        playSound(swipeSound);
                        gameSurfaceView.moveCells(Direction.DOWN);
                    } else if (detector.isSwipeUp(e1, e2, velocityY)) {
                        playSound(swipeSound);
                        gameSurfaceView.moveCells(Direction.UP);
                    }else if (detector.isSwipeLeft(e1, e2, velocityX)) {
                        playSound(swipeSound);
                        gameSurfaceView.moveCells(Direction.LEFT);
                    } else if (detector.isSwipeRight(e1, e2, velocityX)) {
                        playSound(swipeSound);
                        gameSurfaceView.moveCells(Direction.RIGHT);
                    }
                } catch (Exception ignored) {}
                return false;
            }
        });
    }

    public void onClickMenuBtn(View view) {
        menuDialogFragment.show(getFragmentManager(), MENU_TAG);
    }

    public void setScores(int points){
        String scoresValue = getString(R.string.txt_scores_default_text) + points;
        txtScores.setText(scoresValue);
        setBestScores(points);
    }

    private void setBestScores(int points){
        int bestScores = gamePreferences.getBestScores();
        if(points>bestScores){
            String bestScoresValue = getString(R.string.txt_best_scores_default_text) + points;
            txtBestScores.setText(bestScoresValue);
            gamePreferences.setBestScores(points);
        }
    }

    public void setTxtBestScores(int points) {
        String bestScoresValue = getString(R.string.txt_best_scores_default_text) + points;
        this.txtBestScores.setText(bestScoresValue);
    }

    public  void restartGame(){
        gameSurfaceView.getDrawThreat().getCellManager().startNewGame();
        setScores(0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU){
            menuDialogFragment.show(getFragmentManager(),MENU_TAG);
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initSound();
    }

    public void setMusicEnabled(boolean musicEnabled) {
        isMusicEnabled = musicEnabled;
    }
}
