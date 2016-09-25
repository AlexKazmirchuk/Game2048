package com.alexkaz.game2048.uicomp;

import android.app.Activity;
import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.alexkaz.game2048.GameActivity;
import com.alexkaz.game2048.R;
import com.alexkaz.game2048.gamelogic.CellManager;
import com.alexkaz.game2048.gamelogic.DrawThreat;

public class LoseDialogFragment extends DialogFragment implements View.OnClickListener {

    public static final String SCORES = "scores";
    public static final float DIM_AMOUNT = 0.8f;
    private GameActivity gameActivity;
    private CellManager cellManager;
    private DrawThreat drawThreat;

    public static LoseDialogFragment newInstance(int scores){
        LoseDialogFragment fragment = new LoseDialogFragment();

        Bundle args = new Bundle();
        args.putInt(SCORES,scores);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setDimAmount(DIM_AMOUNT);
        View v = inflater.inflate(R.layout.activity_lose,container);

        TextView loseTxtScores = (TextView) v.findViewById(R.id.loseTxtScores);
        Button loseRestartBtn = (Button) v.findViewById(R.id.loseRestartBtn);
        loseRestartBtn.setOnClickListener(this);
        String txtScores = gameActivity.getString(R.string.dialog_text_score) + String.valueOf(getArguments().getInt(SCORES, 0));
        loseTxtScores.setText(txtScores);

        loseTxtScores.setTypeface(gameActivity.getTypeface());
        loseRestartBtn.setTypeface(gameActivity.getTypeface());
        ((TextView)v.findViewById(R.id.titleLose)).setTypeface(gameActivity.getTypeface());

        return v;
    }

    @Override
    public void onClick(View v) {
        getDialog().cancel();
//        if (cellManager!=null){
//            cellManager.startNewGame();
//        }
//        cellManager = null;
//        if (drawThreat!=null){
//            drawThreat.setLoseDialogShow(false);
//        }
//        drawThreat = null;
    }

    public void setCellManager(CellManager cellManager) {
        this.cellManager = cellManager;
    }

    public void setDrawThreat(DrawThreat drawThreat) {
        this.drawThreat = drawThreat;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.gameActivity = (GameActivity) activity;
    }

    @Override
    public void onDetach() {
        if (cellManager!=null){
            cellManager.startNewGame();
        }
        cellManager = null;
        if (drawThreat!=null){
            drawThreat.setLoseDialogShow(false);
        }
        drawThreat = null;
        super.onDetach();
    }
}
