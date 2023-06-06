package com.example.projectforitschool.GeographyMode;

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
import com.example.projectforitschool.Database.GeographyGameStatUnit;
import com.example.projectforitschool.Database.MathGameStatUnit;
import com.example.projectforitschool.MainActivity;
import com.example.projectforitschool.R;

import java.util.ArrayList;

public class GeographyGameStatisticsFragment extends Fragment {

    Button geographyMode, flagComparison;
    TextView generalInformation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_geography_game_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        geographyMode = view.findViewById(R.id.button_view_history);
        generalInformation = view.findViewById(R.id.textView_general_info);
        flagComparison = view.findViewById(R.id.button_view_mistaken_flags);

        MainActivity hostActivity = (MainActivity) getActivity();
        geographyMode.setOnClickListener(v ->
        {
            hostActivity.displayFragment(new GeographyModeStatisticsFragment());
        });
        flagComparison.setOnClickListener(v ->
        {
            hostActivity.displayFragment(new GeographyFlagComparisonFragment());
        });

        displayGeneralInfo();
    }

    private void displayGeneralInfo ()
    {
        AppDatabase db = AppDatabase.getDatabaseInstance(getActivity().getApplicationContext());
        ArrayList<GeographyGameStatUnit> data = (ArrayList<GeographyGameStatUnit>) db.geographyGameStatUnitDao().getAllUnits();
        if (db.geographyFlagComparisonDao().getAllUnits().isEmpty())
        {
            flagComparison.setVisibility(View.INVISIBLE);
        }
        int totalPlayTime = 0;
        int capitalsCounter = 0 , countriesCounter = 0;
        int totalGivenAnswers = 0;
        double counterVictory = 0;
        double averageAnswerTime = 0;

        for (int x = 0; x < data.size(); x++)
        {
            totalPlayTime += data.get(x).getPlayTime();
            totalGivenAnswers += data.get(x).getCorrectAnswersCounter();

            if (data.get(x).getGameResult().equals("Victory"))
            {
                counterVictory++;
            }

            switch(data.get(x).getMode())
            {
                case "Capitals":
                    capitalsCounter++;
                    break;
                case "Countries":
                    countriesCounter++;
                    break;
            }
        }

        if (totalGivenAnswers != 0)
        {
            averageAnswerTime = (double) (totalPlayTime / totalGivenAnswers);
        }

        String mode;
        if (data.size() == 0)
        {
            mode = "";
        }
        else {
            mode = capitalsCounter >= countriesCounter ? "Capitals" : "Countries";
        }

        int winRate = (int) (((counterVictory) / data.size()) * 100);

        generalInformation.setText("Total games played: " + data.size() + "\n\n" + "Total play time: " + totalPlayTime + " sec\n\n" +
                "Average answer time: " + averageAnswerTime + " sec\n\n" + "Win rate: " + winRate + "%\n\n" +
                "Favorite mode: " + mode);

    }
}