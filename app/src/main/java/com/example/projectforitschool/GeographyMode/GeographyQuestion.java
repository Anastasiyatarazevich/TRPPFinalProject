package com.example.projectforitschool.GeographyMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeographyQuestion {

    private Country answerArray[];
    private Country answer;
    private int answerPosition;


    public GeographyQuestion (Country answer, ArrayList<Country> countries)
    {
        Random randomNumberMaker = new Random();
        this.answerPosition = randomNumberMaker.nextInt(4);
        answerArray = new Country[4];
        this.answer = answer;


        for (int x = 0; x < 4; x++)
        {
            int index = randomNumberMaker.nextInt(countries.size());
            answerArray[x] = countries.get(index);
            countries.remove(index);
        }
        for(int x = 0; x < 4; x++)
        {
            countries.add(answerArray[x]);
        }
        this.answerArray[answerPosition] = answer;
    }

    public Country[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(Country[] answerArray) {
        this.answerArray = answerArray;
    }

    public Country getAnswer() {
        return answer;
    }

    public void setAnswer(Country answer) {
        this.answer = answer;
    }

    public int getAnswerPosition() {
        return answerPosition;
    }

    public void setAnswerPosition(int answerPosition) {
        this.answerPosition = answerPosition;
    }

}
