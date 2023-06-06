package com.example.projectforitschool.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.projectforitschool.R;

@Entity
public class GeographyGameStatUnit {

    @PrimaryKey (autoGenerate = true)
    private int unit_id;

    @ColumnInfo (name = "day")
    private int day;
    @ColumnInfo(name = "month")
    private int month;
    @ColumnInfo(name = "year")
    private int year;

    @ColumnInfo(name = "correct_answers_counter")
    private int correctAnswersCounter;
    @ColumnInfo (name = "play_time")
    private int playTime;
    @ColumnInfo (name = "mode")
    private String mode;

    @ColumnInfo(name = "game_result")
    private String gameResult;

    @ColumnInfo(name = "game_size")
    private int gameSize;

    public GeographyGameStatUnit(int day, int month, int year, int correctAnswersCounter, int playTime, String mode, String gameResult, int gameSize) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.correctAnswersCounter = correctAnswersCounter;
        this.playTime = playTime;
        this.mode = mode;
        this.gameResult = gameResult;
        this.gameSize = gameSize;
    }

    public String getCorrectAnswersString()
    {
        return "Correct answers: " + correctAnswersCounter;
    }

    public String getDateString()
    {
        return "Date: " + day + "/" + (month) + "/" + year;
    }

    public String getGameDurationString()
    {
        return "Game duration: " + playTime;
    }

    public String getAverageAnswerTimeString()
    {
        if (correctAnswersCounter != 0)
        {
            double average = playTime / correctAnswersCounter;
            return "Average answer time: " + average;
        }
        return "Average answer time: 0";
    }

    public String getGameSizeString()
    {
        return "Size of game: " + gameSize;
    }

    public int getModeIcon()
    {
        if (mode.equals("Capitals"))
        {
            return R.drawable.ic_location_pin;
        }
        else
        {
            return R.drawable.ic_flag;
        }
    }

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getCorrectAnswersCounter() {
        return correctAnswersCounter;
    }

    public void setCorrectAnswersCounter(int correctAnswersCounter) {
        this.correctAnswersCounter = correctAnswersCounter;
    }

    public int getPlayTime() {
        return playTime;
    }

    public void setPlayTime(int playTime) {
        this.playTime = playTime;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getGameResult() {
        return gameResult;
    }

    public void setGameResult(String gameResult) {
        this.gameResult = gameResult;
    }

    public int getGameSize() {
        return gameSize;
    }

    public void setGameSize(int gameSize) {
        this.gameSize = gameSize;
    }

    public int getGameResultIcon() {
        if (gameResult.equals("Victory"))
        {
            return R.drawable.ic_trophy_logo;
        }
        else
        {
            return R.drawable.ic_cancel_logo;
        }
    }
}
