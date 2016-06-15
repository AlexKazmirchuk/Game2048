package com.alexkaz.game2048.uicomp;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.alexkaz.game2048.R;
import com.alexkaz.game2048.gamelogic.GameFieldView;

public class LoseDialogFragment extends DialogFragment implements View.OnClickListener {

    private TextView loseTxtScores;
    private Button loseRestartBtn;
    private String txtScores;

    public static LoseDialogFragment newInstance(int scores){
        LoseDialogFragment fragment = new LoseDialogFragment();

        Bundle args = new Bundle();
        args.putInt("scores",scores);
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        View v = inflater.inflate(R.layout.activity_lose,null);

        loseTxtScores = (TextView) v.findViewById(R.id.loseTxtScores);
        loseRestartBtn = (Button) v.findViewById(R.id.loseRestartBtn);
        loseRestartBtn.setOnClickListener(this);
        txtScores = String.valueOf(getArguments().getInt("scores",0));
        loseTxtScores.setText("YOUR SCORE:"+txtScores);

        return v;
    }

    @Override
    public void onClick(View v) {
        getDialog().cancel();
    }
}
