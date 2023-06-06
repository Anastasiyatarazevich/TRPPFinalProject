package com.example.projectforitschool.GeographyMode;

public class Country {

    private String name;
    private String capital;
    private int imageResource;

    public Country(String name, String capital, int imageResource) {
        this.name = name;
        this.capital = capital;
        this.imageResource = imageResource;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
