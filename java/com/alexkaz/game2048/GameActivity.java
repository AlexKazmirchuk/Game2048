package com.alexkaz.game2048;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.alexkaz.game2048.controllers.GamePreferences;
import com.alexkaz.game2048.controllers.SoundController;
import com.alexkaz.game2048.controllers.SwipeDetector;
import com.alexkaz.game2048.gamelogic.Direction;
import com.alexkaz.game2048.gamelogic.GameSurfaceView;
import com.alexkaz.game2048.uicomp.MenuDialogFragment;

public class GameActivity extends AppCompatActivity {

    public static final String MENU_TAG = "menu";

    private GestureDetector gestureDetector;
    private GamePreferences gamePreferences;
    private GameSurfaceView gameSurfaceView;
    private Typeface typeface;
    private TextView txtScores;
    private TextView txtBestScores;
    private MenuDialogFragment menuDialogFragment;
    private SoundController soundController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initComp();
        readPrefs();
        startAnim();
    }

    private void initComp(){
        menuDialogFragment = new MenuDialogFragment();
        gestureDetector = initGestureDetector();
        setGamePreferences(new GamePreferences(this));
        typeface = Typeface.createFromAsset(getAssets(),"impact.ttf");
        txtScores = (TextView) findViewById(R.id.txtScores);
        txtScores.setTypeface(typeface);
        txtBestScores = (TextView) findViewById(R.id.txtBestScores);
        txtBestScores.setTypeface(typeface);
        initGameSurface();
        setSoundController(new SoundController(this));
    }

    private void initGameSurface(){
        gameSurfaceView = new GameSurfaceView(this);
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
        getSoundController().setMusicEnabled(getGamePreferences().getMusicPrefs());
        int bestScores = getGamePreferences().getBestScores();
        String scoresValue = getString(R.string.txt_scores_default_text) + 0;
        txtScores.setText(scoresValue);
        String bestScoresValue = getString(R.string.txt_best_scores_default_text) + bestScores;
        txtBestScores.setText(bestScoresValue);
    }

    private void startAnim() {
        FrameLayout gameTitle = (FrameLayout) findViewById(R.id.titleGame);
        Animation gameTitleAnim = AnimationUtils.loadAnimation(this,R.anim.game_title_anim);
        gameTitle.startAnimation(gameTitleAnim);

        FrameLayout infoPanel = (FrameLayout) findViewById(R.id.infoPanel);
        Animation infoPanelAnim = AnimationUtils.loadAnimation(this, R.anim.info_panel_anim);
        infoPanel.startAnimation(infoPanelAnim);

        FrameLayout txtScoresWrapper = (FrameLayout) findViewById(R.id.txtScoresWrapper);
        Animation txtScoresAnim = AnimationUtils.loadAnimation(this, R.anim.txt_scores_anim);
        txtScoresWrapper.startAnimation(txtScoresAnim);

        FrameLayout menuBtnWrapper = (FrameLayout) findViewById(R.id.menuBtnWrapper);
        Animation menuBtnAnim = AnimationUtils.loadAnimation(this, R.anim.menu_btn_anim);
        menuBtnWrapper.startAnimation(menuBtnAnim);

        FrameLayout txtBestScoresWrapper = (FrameLayout) findViewById(R.id.txtBestScoresWrapper);
        Animation txtBestScoresAnim = AnimationUtils.loadAnimation(this, R.anim.txt_best_scores_anim);
        txtBestScoresWrapper.startAnimation(txtBestScoresAnim);
    }

    @Override
    protected void onPause() {
        super.onPause();
        getGamePreferences().saveCellsID(gameSurfaceView.getDrawThreat().getCellManager().getStringCellsID());
        getGamePreferences().setGameScores(gameSurfaceView.getDrawThreat().getCellManager().getScores());
        getSoundController().resetSoundPool();
    }

    private GestureDetector initGestureDetector() {
        return new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            private SwipeDetector detector = new SwipeDetector();
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                try {
                    if (detector.isSwipeDown(e1, e2, velocityY)) {
                        getSoundController().playSwipe();
                        gameSurfaceView.moveCells(Direction.DOWN);
                    } else if (detector.isSwipeUp(e1, e2, velocityY)) {
                        getSoundController().playSwipe();
                        gameSurfaceView.moveCells(Direction.UP);
                    }else if (detector.isSwipeLeft(e1, e2, velocityX)) {
                        getSoundController().playSwipe();
                        gameSurfaceView.moveCells(Direction.LEFT);
                    } else if (detector.isSwipeRight(e1, e2, velocityX)) {
                        getSoundController().playSwipe();
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
        int bestScores = getGamePreferences().getBestScores();
        if(points>bestScores){
            String bestScoresValue = getString(R.string.txt_best_scores_default_text) + points;
            txtBestScores.setText(bestScoresValue);
            getGamePreferences().setBestScores(points);
        }
    }

    public void setTxtBestScores(int points) {
        String bestScoresValue = getString(R.string.txt_best_scores_default_text) + points;
        this.txtBestScores.setText(bestScoresValue);
    }

    public  void restartGame(){
        gameSurfaceView.getDrawThreat().getCellManager().startNewGame();
        gameSurfaceView.getDrawThreat().setWinActivityShowed(true);
        getGamePreferences().setWinDialogShowed(true);
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
        getSoundController().initSound(this);
    }

    public void setMusicEnabled(boolean musicEnabled) {
        getSoundController().setMusicEnabled(musicEnabled);
    }

    public Typeface getTypeface() {
        return typeface;
    }

    public GamePreferences getGamePreferences() {
        return gamePreferences;
    }

    public void setGamePreferences(GamePreferences gamePreferences) {
        this.gamePreferences = gamePreferences;
    }

    public SoundController getSoundController() {
        return soundController;
    }

    public void setSoundController(SoundController soundController) {
        this.soundController = soundController;
    }
}
