package com.example.projectforitschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Bundle;

import com.example.projectforitschool.GeographyMode.Country;
import com.example.projectforitschool.GeographyMode.GeographyCapitalGameFragment;
import com.example.projectforitschool.GeographyMode.GeographyCountryGameFragment;
import com.example.projectforitschool.GeographyMode.GeographyGameModeSelectionFragment;
import com.example.projectforitschool.GeographyMode.GeographyGameResultFragment;

import java.util.ArrayList;
import java.util.Random;



public class GeographyModeActivity extends AppCompatActivity {

    public ArrayList<Country> countries = new ArrayList<>();
    public ArrayList<Country> gameQuestions = new ArrayList<>();
    public int gameSize;
    public long gameUnit_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geography_mode);

        SharedPreferences sharedPreferences = getSharedPreferences("AppPreference" , Context.MODE_PRIVATE);
        gameSize = sharedPreferences.getInt("gameSize" , 10);

        GeographyGameModeSelectionFragment fragment = new GeographyGameModeSelectionFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout_geography , fragment).commit();
    }

    public void startCountryGameFragment()
    {
        initializeGameArrays();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        GeographyCountryGameFragment fragment = new GeographyCountryGameFragment();
        ft.replace(R.id.frame_layout_geography , fragment);
        ft.commit();
    }

    public void startCapitalGameFragment()
    {
        initializeGameArrays();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        GeographyCapitalGameFragment fragment = new GeographyCapitalGameFragment();
        ft.replace(R.id.frame_layout_geography , fragment);
        ft.commit();
    }

    public void startResultFragment()
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        GeographyGameResultFragment fragment = new GeographyGameResultFragment();
        ft.replace(R.id.frame_layout_geography , fragment);
        ft.commit();
    }


    private void initializeGameArrays()
    {
        if (countries.isEmpty() && gameQuestions.isEmpty()) {
            String countryNames[] = getResources().getStringArray(R.array.CountryName);
            String countryCapitals[] = getResources().getStringArray(R.array.CountryCapital);
            TypedArray flagsId = getResources().obtainTypedArray(R.array.CountryFlag);

            for (int x = 0; x < countryNames.length; x++) {
                countries.add(new Country(countryNames[x], countryCapitals[x], flagsId.getResourceId(x, 0)));
            }

            Random random = new Random();

            for (int x = 0; x < gameSize; x++) {
                int index = random.nextInt(countries.size());
                gameQuestions.add(countries.get(index));
                countries.remove(index);
            }
        }
    }

    @Override
    public void onBackPressed() {
    }
}