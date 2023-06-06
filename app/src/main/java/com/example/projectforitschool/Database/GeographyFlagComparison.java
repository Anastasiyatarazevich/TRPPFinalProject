package com.example.projectforitschool.Database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class GeographyFlagComparison {

    @PrimaryKey (autoGenerate = true)
    private int unit_id;

    @ColumnInfo (name = "left_name")
    private String leftCountryName;
    @ColumnInfo (name = "right_name")
    private String rightCountryName;

    @ColumnInfo (name = "right_capital")
    private String leftCountryCapital;
    @ColumnInfo (name = "left_capital")
    private String rightCountryCapital;

    @ColumnInfo (name = "left_flag")
    private int leftCountryFlag;
    @ColumnInfo (name = "right_flag")
    private int rightCountryFlag;

    public GeographyFlagComparison(String leftCountryName, String rightCountryName, String leftCountryCapital, String rightCountryCapital, int leftCountryFlag, int rightCountryFlag) {
        this.leftCountryName = leftCountryName;
        this.rightCountryName = rightCountryName;
        this.leftCountryCapital = leftCountryCapital;
        this.rightCountryCapital = rightCountryCapital;
        this.leftCountryFlag = leftCountryFlag;
        this.rightCountryFlag = rightCountryFlag;
    }

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public String getLeftCountryName() {
        return leftCountryName;
    }

    public void setLeftCountryName(String leftCountryName) {
        this.leftCountryName = leftCountryName;
    }

    public String getRightCountryName() {
        return rightCountryName;
    }

    public void setRightCountryName(String rightCountryName) {
        this.rightCountryName = rightCountryName;
    }

    public String getLeftCountryCapital() {
        return leftCountryCapital;
    }

    public void setLeftCountryCapital(String leftCountryCapital) {
        this.leftCountryCapital = leftCountryCapital;
    }

    public String getRightCountryCapital() {
        return rightCountryCapital;
    }

    public void setRightCountryCapital(String rightCountryCapital) {
        this.rightCountryCapital = rightCountryCapital;
    }

    public int getLeftCountryFlag() {
        return leftCountryFlag;
    }

    public void setLeftCountryFlag(int leftCountryFlag) {
        this.leftCountryFlag = leftCountryFlag;
    }

    public int getRightCountryFlag() {
        return rightCountryFlag;
    }

    public void setRightCountryFlag(int rightCountryFlag) {
        this.rightCountryFlag = rightCountryFlag;
    }
}
