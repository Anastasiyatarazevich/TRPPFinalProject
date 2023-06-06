package com.example.projectforitschool.MathMode;

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

import com.example.projectforitschool.Database.AppDatabase;
import com.example.projectforitschool.Database.MathGameStatUnit;
import com.example.projectforitschool.MainActivity;
import com.example.projectforitschool.MathModeActivity;
import com.example.projectforitschool.R;

import java.util.Calendar;
import java.util.Random;


public class MathGameFragment extends Fragment {


    Button buttonFirst, buttonSecond, buttonThird, buttonFourth;
    TextView textViewProblem, textViewTimeLeft;
    ProgressBar progressBar;
    ImageView heartFirst, heartSecond, heartThird, thinkingPerson, homeButton;

    GameMathMode game;
    long startTime;

    int secondsRemaining = 10;
    int lastImage = 0;
    int gameDuration = 0;

    boolean homeButtonPressed = false;
    boolean timerIsRunning = false;
    CountDownTimer timer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_math_game, container, false);
    }

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        heartFirst = view.findViewById(R.id.heart_third);
        heartSecond = view.findViewById(R.id.heart_second);
        heartThird = view.findViewById(R.id.heart_first);
        buttonFirst = view.findViewById(R.id.button_first);
        buttonSecond = view.findViewById(R.id.button_second);
        buttonThird = view.findViewById(R.id.button_third);
        buttonFourth = view.findViewById(R.id.button_fourth);
        textViewProblem = view.findViewById(R.id.textView_problem);
        textViewTimeLeft = view.findViewById(R.id.textView_time_left);
        progressBar = view.findViewById(R.id.progressBar);
        thinkingPerson = view.findViewById(R.id.imageView_thinking_person);
        homeButton = view.findViewById(R.id.button_home);
        progressBar.setMax(secondsRemaining);


        game = new GameMathMode(((MathModeActivity) getActivity()).mode);

        homeButton.setOnClickListener(v ->{
            Intent intent = new Intent(getActivity() , MainActivity.class);
            homeButtonPressed = true;
            startActivity(intent);
        });

        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button buttonClicked = (Button) view;
                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());
                game.checkAnswer(answerSelected);

                setLivesDisplay(game.getLives());

                if (game.getLives() == 0) {
                    homeButtonPressed = true;
                    endGame();
                } else {
                    changePersonIcon();
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

    private void changePersonIcon()
    {
        Random random = new Random();
        int selection = random.nextInt(4);
        while (selection == lastImage)
        {
            selection = random.nextInt(4);
        }
        lastImage = selection;
        switch(selection)
        {
            case 0:
                thinkingPerson.setImageResource(R.drawable.ic_thinking_boy);
                break;
            case 1:
                thinkingPerson.setImageResource(R.drawable.ic_thinking_girl);
                break;
            case 2:
                thinkingPerson.setImageResource(R.drawable.ic_thinking_man);
                break;
            case 3:
                thinkingPerson.setImageResource(R.drawable.ic_thinking_woman);
                break;
        }
    }

    private void nextTurn()
    {
        game.makeNewQuestion();
        int [] answers = game.getCurrentQuestion().getAnswerArray();
        buttonFirst.setText(Integer.toString(answers[0]));
        buttonSecond.setText(Integer.toString(answers[1]));
        buttonThird.setText(Integer.toString(answers[2]));
        buttonFourth.setText(Integer.toString(answers[3]));
        textViewProblem.setText(game.getCurrentQuestion().getQuestionPhrase());
        if (timerIsRunning)
        {
            timer.cancel();
        }
        secondsRemaining = 10;
        timer = new CountDownTimer(10000 , 1000) {
            @Override
            public void onTick(long l) {
                textViewTimeLeft.setText(Integer.toString(secondsRemaining) + " sec");
                progressBar.setProgress(10 - secondsRemaining);
                secondsRemaining--;
            }

            @Override
            public void onFinish() {
                textViewTimeLeft.setText("0 sec");
                homeButtonPressed = true;
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
        MathModeActivity hostActivity = (MathModeActivity) getActivity();
        Calendar rightNow = Calendar.getInstance();
        gameDuration = (int) ((System.currentTimeMillis() - startTime) / 1000);
        MathGameStatUnit gameUnit= new MathGameStatUnit(rightNow.get(Calendar.DATE),rightNow.get(Calendar.MONTH) , rightNow.get(Calendar.YEAR) , game.getNumberCorrect() , gameDuration , game.getMode());

        AppDatabase database = AppDatabase.getDatabaseInstance(hostActivity.getApplicationContext());
        hostActivity.gameUnit_id = database.mathGameStatUnitDao().insertUnit(gameUnit);
        hostActivity.startResultFragment();
    }
}