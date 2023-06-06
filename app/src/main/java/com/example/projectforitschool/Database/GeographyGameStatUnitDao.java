package com.example.projectforitschool.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GeographyGameStatUnitDao {

    @Insert
    long insertUnit (GeographyGameStatUnit unit);

    @Query("SELECT * FROM geographygamestatunit")
    List<GeographyGameStatUnit> getAllUnits();

    @Query("SELECT * FROM geographygamestatunit WHERE unit_id=:id ")
    GeographyGameStatUnit getUnitById(long id);

    @Query("DELETE FROM geographygamestatunit")
    void nukeTable();
}
