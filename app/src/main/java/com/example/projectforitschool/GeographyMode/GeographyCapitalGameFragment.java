package com.example.projectforitschool.GeographyMode;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectforitschool.Database.AppDatabase;
import com.example.projectforitschool.Database.GeographyFlagComparison;
import com.example.projectforitschool.Database.GeographyGameStatUnit;
import com.example.projectforitschool.GeographyModeActivity;
import com.example.projectforitschool.MainActivity;
import com.example.projectforitschool.R;

import java.util.Calendar;


public class GeographyCapitalGameFragment extends Fragment {

    Button buttonFirst, buttonSecond, buttonThird, buttonFourth;
    TextView textViewTimeLeft , textViewQuestionsLeft , textViewCountry;
    ProgressBar progressBar;
    ImageView heartFirst, heartSecond, heartThird, homeButton, flag;

    Country [] answers;

    boolean homeButtonPressed = false;

    AppDatabase database;

    GameGeographyMode game;
    long startTime;
    boolean victory = false;

    int secondsRemaining = 15;

    boolean timerIsRunning = false;
    CountDownTimer timer;

    @Override
    public void onPause() {
        super.onPause();
        if (timerIsRunning) {
            timer.cancel();
        }
        if (!homeButtonPressed)
        {
            endGame();
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_geography_capital_game, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        heartFirst = view.findViewById(R.id.heart_third);
        heartSecond = view.findViewById(R.id.heart_second);
        heartThird = view.findViewById(R.id.heart_first);
        buttonFirst = view.findViewById(R.id.button_first);
        buttonSecond = view.findViewById(R.id.button_second);
        buttonThird = view.findViewById(R.id.button_third);
        buttonFourth = view.findViewById(R.id.button_fourth);
        flag = view.findViewById(R.id.imageView_flag_question);
        textViewTimeLeft = view.findViewById(R.id.textView_time_left);
        progressBar = view.findViewById(R.id.progressBar);
        homeButton = view.findViewById(R.id.button_home);
        textViewQuestionsLeft = view.findViewById(R.id.textView_questions_left);
        textViewCountry = view.findViewById(R.id.textView_country_guessing);
        database = AppDatabase.getDatabaseInstance(getActivity().getApplicationContext());

        progressBar.setMax(secondsRemaining);

        GeographyModeActivity hostActivity = (GeographyModeActivity) getActivity();
        game = new GameGeographyMode(GameGeographyMode.Mode.CAPITAL , hostActivity.gameSize , hostActivity.gameQuestions , hostActivity.countries);

        homeButton.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity() , MainActivity.class);
            homeButtonPressed = true;
            startActivity(intent);
        });

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button buttonClicked = (Button) view;
                String answerSelected = buttonClicked.getText().toString();
                boolean correct = game.checkAnswer(answerSelected);

                if(!correct)
                {
                    Country leftCountry = game.getCurrentQuestion().getAnswer();
                    Country rightCountry = null;
                    for (int x = 0; x < answers.length; x++)
                    {
                        if (buttonClicked.getText().equals(answers[x].getCapital()))
                        {
                            rightCountry = answers[x];
                            break;
                        }
                    }
                    if (database.geographyFlagComparisonDao().countUnits(leftCountry.getName() , rightCountry.getName()) == 0)
                    {
                        database.geographyFlagComparisonDao().insertUnit(new GeographyFlagComparison(leftCountry.getName() , rightCountry.getName() , leftCountry.getCapital() , rightCountry.getCapital() , leftCountry.getImageResource() , rightCountry.getImageResource()));
                    }
                }

                setLivesDisplay(game.getLives());

                if (game.getLives() == 0)
                {
                    if (game.getGameSize() == game.getTurn())
                    {
                        victory = true;
                    }
                    homeButtonPressed = true;
                    endGame();
                }
                else
                {
                    nextTurn();
                }

            }
        };

        buttonFirst.setOnClickListener(answerButtonClickListener);
        buttonSecond.setOnClickListener(answerButtonClickListener);
        buttonThird.setOnClickListener(answerButtonClickListener);
        buttonFourth.setOnClickListener(answerButtonClickListener);
        startTime = System.currentTimeMillis();
        nextTurn();
    }

    private void nextTurn()
    {
        game.makeNewQuestion();
        textViewQuestionsLeft.setText((game.getTurn()) + " / " + game.getGameSize());
        answers = game.getCurrentQuestion().getAnswerArray();
        buttonFirst.setText(answers[0].getCapital());
        buttonSecond.setText(answers[1].getCapital());
        buttonThird.setText(answers[2].getCapital());
        buttonFourth.setText(answers[3].getCapital());
        flag.setImageResource(game.getCurrentQuestion().getAnswer().getImageResource());
        textViewCountry.setText(game.getCurrentQuestion().getAnswer().getName());
        if (timerIsRunning)
        {
            timer.cancel();
        }
        secondsRemaining = 15;
        timer = new CountDownTimer(secondsRemaining * 1000 , 1000) {
            @Override
            public void onTick(long l) {
                textViewTimeLeft.setText(Integer.toString(secondsRemaining) + " sec");
                progressBar.setProgress(15 - secondsRemaining);
                secondsRemaining--;
            }

            @Override
            public void onFinish() {
                textViewTimeLeft.setText("0 sec");
                endGame();

            }
        };
        timer.start();
        timerIsRunning = true;
    }

    private void setLivesDisplay(int livesLeft)
    {
        switch (livesLeft)
        {
            case 0:
                heartFirst.setVisibility(View.INVISIBLE);
                heartSecond.setVisibility(View.INVISIBLE);
                heartThird.setVisibility(View.INVISIBLE);
                break;
            case 1:
                if (heartSecond.getVisibility() == View.VISIBLE || heartThird.getVisibility() == View.VISIBLE)
                {
                    heartSecond.setVisibility(View.INVISIBLE);
                    heartThird.setVisibility(View.INVISIBLE);
                }
                break;
            case 2:
                if (heartThird.getVisibility() == View.VISIBLE)
                {
                    heartThird.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }

    private void endGame()
    {
        GeographyModeActivity hostActivity = (GeographyModeActivity) getActivity();
        Calendar rightNow = Calendar.getInstance();
        int gameDuration = (int) ((System.currentTimeMillis() - startTime) / 1000);
        String gameResult = victory ? "Victory" : "Defeat";
        GeographyGameStatUnit gameUnit = new GeographyGameStatUnit(rightNow.get(Calendar.DATE), rightNow.get(Calendar.MONTH) + 1, rightNow.get(Calendar.YEAR), game.getNumberCorrect() , gameDuration , "Capitals" , gameResult , game.getGameSize());
        hostActivity.gameUnit_id = database.geographyGameStatUnitDao().insertUnit(gameUnit);
        hostActivity.startResultFragment();
    }

}