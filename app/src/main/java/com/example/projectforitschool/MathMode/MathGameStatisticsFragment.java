package com.example.projectforitschool.MathMode;

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
import com.example.projectforitschool.GeographyMode.GeographyGameStatisticsFragment;
import com.example.projectforitschool.MainActivity;
import com.example.projectforitschool.MathMode.MathModeStatisticsFragment;
import com.example.projectforitschool.R;

import java.util.ArrayList;


public class MathGameStatisticsFragment extends Fragment {


    Button mathMode;
    TextView generalInformation;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_math_game_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mathMode = view.findViewById(R.id.button_view_history);
        generalInformation = view.findViewById(R.id.textView_general_info);

        MainActivity hostActivity = (MainActivity) getActivity();

        mathMode.setOnClickListener(v -> {
            hostActivity.displayFragment(new MathModeStatisticsFragment());
        });

        displayGeneralInfo();

//
//        historyMode.setOnClickListener(v ->
//        {
//
//        });
//
//        geographyMode.setOnClickListener(v ->{
//
//        });


    }

    private void displayGeneralInfo ()
    {
        AppDatabase db = AppDatabase.getDatabaseInstance(getActivity().getApplicationContext());
        ArrayList<MathGameStatUnit> data = (ArrayList<MathGameStatUnit>) db.mathGameStatUnitDao().getAllUnits();

        int totalPlayTime = 0;
        int additionCounter = 0 , substactionCounter = 0 , multiplyCounter = 0;
        int totalGivenAnswers = 0;
        double averageAnswerTime = 0;

        for (int x = 0; x < data.size(); x++)
        {
            totalPlayTime += data.get(x).getPlayTime();
            totalGivenAnswers += data.get(x).getCorrectAnswersCounter();
            switch(data.get(x).getMode())
            {
                case '+':
                    additionCounter++;
                    break;
                case '-':
                    substactionCounter++;
                    break;
                case '*':
                    multiplyCounter++;
                    break;
            }
        }

        if (totalGivenAnswers != 0)
        {
            averageAnswerTime = (double) (totalPlayTime / totalGivenAnswers);
        }
        String mode = "";

        if (data.size() != 0) {
            if (additionCounter > substactionCounter && additionCounter > multiplyCounter) {
                mode = "Addition";
            } else if (substactionCounter > additionCounter && substactionCounter > multiplyCounter) {
                mode = "Subtraction";
            } else if (multiplyCounter > additionCounter && multiplyCounter > substactionCounter) {
                mode = "Multiplication";
            }
        }

        generalInformation.setText("Total play time: " + totalPlayTime + " sec\n\n" +
                "Average answer time: " + averageAnswerTime + " sec\n\n" +
                "Favorite mode: " + mode);

    }
}