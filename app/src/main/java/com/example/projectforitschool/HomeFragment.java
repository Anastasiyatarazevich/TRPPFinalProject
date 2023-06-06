package com.example.projectforitschool;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class HomeFragment extends Fragment {

    Button mathButton, geographyButton, historyButton;
    TextView factView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        OnBackPressedCallback callback = new OnBackPressedCallback(true ) {
            @Override
            public void handleOnBackPressed() {
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mathButton = view.findViewById(R.id.button_math);
        geographyButton = view.findViewById(R.id.button_geography);
        historyButton = view.findViewById(R.id.button_history);
        factView = view.findViewById(R.id.textView_fact);

        Animation firstButtonAnim = AnimationUtils.loadAnimation(getActivity() , R.anim.slide_up);
        Animation secondButtonAnim = AnimationUtils.loadAnimation(getActivity() , R.anim.slide_up);
        Animation thirdButtonAnim = AnimationUtils.loadAnimation(getActivity() , R.anim.slide_up);
        secondButtonAnim.setStartOffset(100);
        thirdButtonAnim.setStartOffset(200);

        mathButton.setAnimation(firstButtonAnim);
        historyButton.setAnimation(secondButtonAnim);
        geographyButton.setAnimation(thirdButtonAnim);

        setFact();

        mathButton.setOnClickListener(v ->
        {
            Intent intent = new Intent(getActivity(), MathModeActivity.class);
            startActivity(intent);
        });

        geographyButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity() , GeographyModeActivity.class);
            startActivity(intent);
        });
    }

    private void setFact()
    {
        Random randomNumber = new Random();
        String [] facts = getResources().getStringArray(R.array.facts);
        factView.setText(facts[randomNumber.nextInt(facts.length)]);
    }

}