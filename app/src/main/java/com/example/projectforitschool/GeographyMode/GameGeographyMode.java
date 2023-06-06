package com.example.projectforitschool.GeographyMode;

import java.time.chrono.IsoChronology;
import java.util.ArrayList;
import java.util.List;

public class GameGeographyMode {
    enum Mode
    {
        CAPITAL , COUNTRY , FLAG
    }
    private int numberCorrect;
    private int numberIncorrect;
    private int gameSize;
    private int lives;

    private int turn;
    private Mode mode;
    private GeographyQuestion currentQuestion;

    private ArrayList<Country> gameQuestions;
    private ArrayList<Country> countries;

    public GameGeographyMode(Mode mode, int gameSize , ArrayList<Country> gameQuestions , ArrayList<Country> countries)
    {
        this.mode = mode;
        this.numberCorrect = 0;
        this.numberIncorrect = 0;
        this.gameSize = gameSize;
        this.lives = 3;
        this.turn = 0;

        this.gameQuestions = gameQuestions;
        this.countries = countries;
        this.currentQuestion = null;
    }

    public void makeNewQuestion()
    {
        currentQuestion = new GeographyQuestion(gameQuestions.get(turn++) , countries);
    }

    public boolean checkAnswer(String sumbittedAnswer)
    {
        boolean isCorrect;
        String correctAnswer = null;
        switch (mode)
        {
            case CAPITAL:
                correctAnswer = currentQuestion.getAnswer().getCapital();
                break;
            case COUNTRY:
                correctAnswer = currentQuestion.getAnswer().getName();
                break;
        }

        if (correctAnswer.equals(sumbittedAnswer))
        {
            numberCorrect++;
            isCorrect = true;
        }
        else
        {
            numberIncorrect++;
            lives--;
            isCorrect = false;
        }
        if (turn == gameSize)
        {
            lives = 0;
        }
        return isCorrect;
    }

    public boolean checkAnswer(int sumbittedAnswer)
    {
        boolean isCorrect;
        if (currentQuestion.getAnswer().getImageResource() == sumbittedAnswer)
        {
            numberCorrect++;
            isCorrect = true;
        }
        else
        {
            numberIncorrect++;
            lives--;
            isCorrect = false;
        }
        return isCorrect;
    }

    public int getNumberCorrect() {
        return numberCorrect;
    }

    public void setNumberCorrect(int numberCorrect) {
        this.numberCorrect = numberCorrect;
    }

    public int getNumberIncorrect() {
        return numberIncorrect;
    }

    public void setNumberIncorrect(int numberIncorrect) {
        this.numberIncorrect = numberIncorrect;
    }

    public int getGameSize() {
        return gameSize;
    }

    public void setGameSize(int gameSize) {
        this.gameSize = gameSize;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public GeographyQuestion getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(GeographyQuestion currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public ArrayList<Country> getGameQuestions() {
        return gameQuestions;
    }

    public void setGameQuestions(ArrayList<Country> gameQuestions) {
        this.gameQuestions = gameQuestions;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }
}
