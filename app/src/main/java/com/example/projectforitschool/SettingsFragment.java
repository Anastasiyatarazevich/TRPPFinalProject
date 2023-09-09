package com.example.projectforitschool;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class SettingsFragment extends Fragment {


    ImageView minus , plus;
    TextView counter;

    int currentGameSize;
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        minus = view.findViewById(R.id.imageView_minus);
        plus = view.findViewById(R.id.imageView_plus);
        counter = view.findViewById(R.id.textView_game_size);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AppPreference" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        currentGameSize = sharedPreferences.getInt("gameSize" , 10);

        counter.setText(String.valueOf(currentGameSize));

        minus.setOnClickListener(v -> {
            currentGameSize--;
            if (currentGameSize > 30)
            {
                currentGameSize = 30;
            }
//            else if (currentGameSize < 10)
//            {
//                currentGameSize = 10;
//            }
            editor.putInt("gameSize", currentGameSize);
            editor.commit();
            counter.setText(String.valueOf(currentGameSize));
//            currentGameSize=10;
        });
        plus.setOnClickListener(v -> {
            currentGameSize++;
            if (currentGameSize > 30)
            {
                currentGameSize = 30;
            }
//            else if (currentGameSize < 10)
//            {
//                currentGameSize = 10;
//            }
            editor.putInt("gameSize", currentGameSize);
            editor.commit();
            counter.setText(String.valueOf(currentGameSize));
//            currentGameSize=10;
        });

    }
}