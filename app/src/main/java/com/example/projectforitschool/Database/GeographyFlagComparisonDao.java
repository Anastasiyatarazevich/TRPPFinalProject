package com.example.projectforitschool.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GeographyFlagComparisonDao {

    @Insert
    long insertUnit (GeographyFlagComparison unit);

    @Query("SELECT * FROM geographyflagcomparison")
    List<GeographyFlagComparison> getAllUnits();

    @Query("SELECT * FROM geographyflagcomparison WHERE unit_id=:id ")
    GeographyFlagComparison getUnitById(long id);

    @Query("DELETE FROM geographyflagcomparison")
    void nukeTable();

    @Query("SELECT COUNT() FROM geographyflagcomparison WHERE left_name = :leftName AND right_name = :rightName")
    int countUnits(String leftName , String rightName);
}
