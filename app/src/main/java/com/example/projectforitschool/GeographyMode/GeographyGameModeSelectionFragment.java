package com.example.projectforitschool.GeographyMode;

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

import com.example.projectforitschool.GeographyModeActivity;
import com.example.projectforitschool.R;


public class GeographyGameModeSelectionFragment extends Fragment {

    Button guessFlags, guessCapitals;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_geography_game_mode_selection, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        guessFlags = view.findViewById(R.id.button_selection_guess_country);
        guessCapitals = view.findViewById(R.id.button_selection_guess_capitals);

        Animation firstButtonAnim = AnimationUtils.loadAnimation(getActivity() , R.anim.slide_from_left);
        Animation secondButtonAnim = AnimationUtils.loadAnimation(getActivity() , R.anim.slide_from_left);
        secondButtonAnim.setStartOffset(100);

        guessFlags.setAnimation(firstButtonAnim);
        guessCapitals.setAnimation(secondButtonAnim);


        GeographyModeActivity hostActivity = (GeographyModeActivity) getActivity();

        guessFlags.setOnClickListener(v ->
        {
            hostActivity.startCountryGameFragment();
        });
        guessCapitals.setOnClickListener(v ->{
            hostActivity.startCapitalGameFragment();
        });
    }
}