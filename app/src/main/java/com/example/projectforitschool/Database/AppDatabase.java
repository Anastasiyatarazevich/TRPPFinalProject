package com.example.projectforitschool.Database;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MathGameStatUnit.class , GeographyGameStatUnit.class, GeographyFlagComparison.class} , version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract MathGameStatUnitDao mathGameStatUnitDao();
    public abstract GeographyGameStatUnitDao geographyGameStatUnitDao();
    public abstract  GeographyFlagComparisonDao geographyFlagComparisonDao();

    private static AppDatabase instance;

    public static AppDatabase getDatabaseInstance(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class , "MainDataBase").allowMainThreadQueries().build();
        }
        return instance;
    }
}



