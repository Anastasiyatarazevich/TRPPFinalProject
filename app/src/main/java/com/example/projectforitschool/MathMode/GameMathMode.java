package com.example.projectforitschool.MathMode;

import java.util.ArrayList;
import java.util.List;

public class GameMathMode {
    private List<MathQuestion> questions;
    private int numberCorrect;
    private int numberIncorrect;
    private int totalQuestions;
    private int lives;
    private char mode;
    private MathQuestion currentQuestion;

    public GameMathMode(char mode)
    {
        numberCorrect = 0;
        numberIncorrect = 0;
        totalQuestions = 0;
        lives = 3;
        this.mode = mode;
        currentQuestion = new MathQuestion(20 , mode);
        questions = new ArrayList<>();
    }

    public void makeNewQuestion()
    {
        currentQuestion = new MathQuestion(totalQuestions * 2 + 5 , mode);
        totalQuestions++;
        questions.add(currentQuestion);
    }

    public boolean checkAnswer(int submittedAnswer) {
        boolean isCorrect;
        if (currentQuestion.getAnswer() == submittedAnswer) {
            numberCorrect++;
            isCorrect = true;
        } else {
            numberIncorrect++;
            lives--;
            isCorrect = false;
        }

        return isCorrect;
    }

    public List<MathQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<MathQuestion> questions) {
        this.questions = questions;
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

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    public MathQuestion getCurrentQuestion() {
        return currentQuestion;
    }

    public void setCurrentQuestion(MathQuestion currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public char getMode() {
        return mode;
    }

    public void setMode(char mode) {
        this.mode = mode;
    }
}
