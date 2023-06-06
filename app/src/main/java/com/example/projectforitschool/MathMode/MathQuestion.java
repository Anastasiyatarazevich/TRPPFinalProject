package com.example.projectforitschool.MathMode;

import java.util.Random;

public class MathQuestion {
    private int firstNumber;
    private int secondNumber;
    private char mode;
    private int answer;
    private int answerArray [];
    private int answerPosition;
    private int upperLimit;
    private String questionPhrase;


    public MathQuestion (int upperLimit , char mode)
    {
        this.upperLimit = upperLimit;
        this.mode = mode;
        Random randomNumberMaker = new Random();

        this.firstNumber = randomNumberMaker.nextInt(upperLimit);
        this.secondNumber = randomNumberMaker.nextInt(upperLimit);
        switch (mode)
        {
            case '+':
                this.answer = this.firstNumber + this.secondNumber;
                this.questionPhrase = this.firstNumber + " + " + this.secondNumber + " = ";
                break;
            case '-':
                this.answer = this.firstNumber - this.secondNumber;
                this.questionPhrase = this.firstNumber + " - " + this.secondNumber + " = ";
                break;
            case '*':
                this.answer = this.firstNumber * this.secondNumber;
                this.questionPhrase = this.firstNumber + " * " + this.secondNumber + " = ";
                break;
        }

        this.answerPosition = randomNumberMaker.nextInt(4);
        this.answerArray = new int[4];

        this.answerArray[0] = this.answer + 12;
        this.answerArray[1] = this.answer - (randomNumberMaker.nextInt(10) + 2);
        if (this.answer == 0)
        {
            this.answerArray[2] = this.answer + (randomNumberMaker.nextInt(10) + 1);
        }
        else
        {
            this.answerArray[2] = this.answer * 2;
        }
        this.answerArray[3] = this.answer - 1;

        this.answerArray = shuffleArray(this.answerArray);

        this.answerArray[answerPosition] = answer;
    }


    private int [] shuffleArray(int array [])
    {
        int index , temp;
        Random randomNumberMaker = new Random();

        for (int i = array.length - 1; i > 0; i--)
        {
            index = randomNumberMaker.nextInt(i + 1);
            temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
        return array;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(int firstNumber) {
        this.firstNumber = firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(int secondNumber) {
        this.secondNumber = secondNumber;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(int[] answerArray) {
        this.answerArray = answerArray;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

    public int getUpperLimit() {
        return upperLimit;
    }

    public void setUpperLimit(int upperLimit) {
        this.upperLimit = upperLimit;
    }

    public String getQuestionPhrase() {
        return questionPhrase;
    }

    public void setQuestionPhrase(String questionPhrase) {
        this.questionPhrase = questionPhrase;
    }

    public char getMode() {
        return mode;
    }

    public void setMode(char mode) {
        this.mode = mode;
    }
}
