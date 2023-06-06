package com.example.projectforitschool;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.projectforitschool.GeographyMode.GeographyGameStatisticsFragment;
import com.example.projectforitschool.MathMode.MathGameStatisticsFragment;

import kotlinx.coroutines.MainCoroutineDispatcher;

public class StatisticsFragment extends Fragment {

     Button math , geography;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_statistics, container, false);
    }

     @Override
     public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
         super.onViewCreated(view, savedInstanceState);

         math = view.findViewById(R.id.button_math_stats);
         geography = view.findViewById(R.id.button_geography_stats);

         MainActivity hostActivity = (MainActivity) getActivity();

         Animation firstButtonAnim = AnimationUtils.loadAnimation(getActivity() , R.anim.slide_from_left);
         Animation secondButtonAnim = AnimationUtils.loadAnimation(getActivity() , R.anim.slide_from_left);
         secondButtonAnim.setStartOffset(100);

         math.setAnimation(firstButtonAnim);
         geography.setAnimation(secondButtonAnim);

         math.setOnClickListener(v ->
         {
             hostActivity.displayFragment(new MathGameStatisticsFragment());
         });

         geography.setOnClickListener(v ->
         {
            hostActivity.displayFragment(new GeographyGameStatisticsFragment());
         });
     }
 }