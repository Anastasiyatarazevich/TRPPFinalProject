package com.example.projectforitschool.MathMode;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectforitschool.Database.AppDatabase;
import com.example.projectforitschool.Database.MathGameStatUnit;
import com.example.projectforitschool.MainActivity;
import com.example.projectforitschool.MathModeActivity;
import com.example.projectforitschool.R;


public class MathGameResultFragment extends Fragment {

    TextView date , correctAnswersCounter , playTime , averageAnswerTime , mode;
    Button buttonHome;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_math_game_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date = view.findViewById(R.id.textView_date);
        correctAnswersCounter = view.findViewById(R.id.textView_correct_answer_counter);
        playTime = view.findViewById(R.id.textView_playTime);
        averageAnswerTime = view.findViewById(R.id.textView_average_answer_time);
        mode = view.findViewById(R.id.textView_gameSize);
        buttonHome = view.findViewById(R.id.button_home);

        buttonHome.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity() , MainActivity.class);
            startActivity(intent);
        });

        displayData();



    }

    private void displayData()
    {
        AppDatabase db = AppDatabase.getDatabaseInstance(getActivity().getApplicationContext());
        MathGameStatUnit results = db.mathGameStatUnitDao().getUnitById(((MathModeActivity)getActivity()).gameUnit_id);


        date.setText(results.getDateString());
        correctAnswersCounter.setText(results.getCorrectAnswersString());
        playTime.setText(results.getGameDurationString());
        averageAnswerTime.setText(results.getAverageAnswerTimeString());

        String modeString = "Mode: ";
        switch (results.getMode())
        {
            case '+':
                modeString += "Addition";
                break;
            case '-':
                modeString += "Subtraction";
                break;
            case '*':
                modeString += "Multiplication";
                break;
        }
        mode.setText(modeString);
    }
}