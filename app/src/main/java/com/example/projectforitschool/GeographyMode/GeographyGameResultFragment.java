package com.example.projectforitschool.GeographyMode;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectforitschool.Database.AppDatabase;
import com.example.projectforitschool.Database.GeographyGameStatUnit;
import com.example.projectforitschool.GeographyModeActivity;
import com.example.projectforitschool.MainActivity;
import com.example.projectforitschool.R;


public class GeographyGameResultFragment extends Fragment {

    TextView date , correctAnswersCounter , playTime , averageAnswerTime , gameSize, background, gameResult;
    Button buttonHome , buttonRetry;
    ImageView image;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_geography_game_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        date = view.findViewById(R.id.textView_date);
        correctAnswersCounter = view.findViewById(R.id.textView_correct_answer_counter);
        playTime = view.findViewById(R.id.textView_playTime);
        averageAnswerTime = view.findViewById(R.id.textView_average_answer_time);
        gameSize = view.findViewById(R.id.textView_gameSize);
        buttonHome = view.findViewById(R.id.button_home);
        background = view.findViewById(R.id.textView_end_reason);
        gameResult = view.findViewById(R.id.textView_game_result);
        buttonRetry = view.findViewById(R.id.button_retry);
        image = view.findViewById(R.id.imageView_trophy);

        buttonHome.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity() , MainActivity.class);
            startActivity(intent);
        });

        displayData();
    }

    private void displayData()
    {
        AppDatabase db = AppDatabase.getDatabaseInstance(getActivity().getApplicationContext());
        GeographyGameStatUnit results = db.geographyGameStatUnitDao().getUnitById(((GeographyModeActivity)getActivity()).gameUnit_id);
        date.setText(results.getDateString());
        correctAnswersCounter.setText(results.getCorrectAnswersString() + " / " + results.getGameSize());
        playTime.setText(results.getGameDurationString());
        averageAnswerTime.setText(results.getAverageAnswerTimeString());
        gameSize.setText(results.getGameSizeString());

        if (results.getGameResult().equals("Defeat"))
        {

            image.setImageResource(R.drawable.ic_cancel_logo);
            gameResult.setText("Defeat!");

            buttonRetry.setOnClickListener(v ->
            {
                GeographyModeActivity hostActivity = (GeographyModeActivity) getActivity();
                if (results.getMode().equals("Capitals"))
                {
                    hostActivity.startCapitalGameFragment();
                }
                else
                {
                    hostActivity.startCountryGameFragment();
                }
            });
            buttonRetry.setVisibility(View.VISIBLE);


        }
    }
}