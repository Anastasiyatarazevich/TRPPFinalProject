package com.example.projectforitschool.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MathGameStatUnitDao {

    @Insert
    long insertUnit (MathGameStatUnit unit);

    @Query("SELECT * FROM mathgamestatunit")
    List<MathGameStatUnit> getAllUnits();

    @Query("SELECT * FROM mathgamestatunit WHERE unit_id=:id ")
    MathGameStatUnit getUnitById(long id);

    @Query("DELETE FROM mathgamestatunit")
    void nukeTable();

}





