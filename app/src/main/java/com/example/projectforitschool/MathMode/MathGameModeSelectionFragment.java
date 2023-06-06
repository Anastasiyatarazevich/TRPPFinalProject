package com.example.projectforitschool.MathMode;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.projectforitschool.MathModeActivity;
import com.example.projectforitschool.R;


public class MathGameModeSelectionFragment extends Fragment {


    Button plus , minus , multi;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_math_game_mode_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plus = view.findViewById(R.id.button_plus);
        minus = view.findViewById(R.id.button_minus);
        multi = view.findViewById(R.id.button_multi);

        Animation firstButtonAnim = AnimationUtils.loadAnimation(getActivity() , R.anim.slide_from_left);
        Animation secondButtonAnim = AnimationUtils.loadAnimation(getActivity() , R.anim.slide_from_left);
        Animation thirdButtonAnim = AnimationUtils.loadAnimation(getActivity() , R.anim.slide_from_left);
        secondButtonAnim.setStartOffset(100);
        thirdButtonAnim.setStartOffset(200);

        plus.setAnimation(firstButtonAnim);
        multi.setAnimation(secondButtonAnim);
        minus.setAnimation(thirdButtonAnim);


        MathModeActivity hostActivity = (MathModeActivity) getActivity();
        plus.setOnClickListener(v -> {
            hostActivity.mode = '+';
            hostActivity.startGameFragment();
        });
        minus.setOnClickListener(v -> {
            hostActivity.mode = '-';
            hostActivity.startGameFragment();
        });
        multi.setOnClickListener(v -> {
            hostActivity.mode = '*';
            hostActivity.startGameFragment();
        });
    }
}