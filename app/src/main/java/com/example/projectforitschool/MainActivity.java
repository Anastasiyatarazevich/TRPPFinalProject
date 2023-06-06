package com.example.projectforitschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.projectforitschool.MathMode.MathGameStatisticsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        navigationBar = findViewById(R.id.bottom_navigation_bar);

        navigationBar.setSelectedItemId(R.id.bottom_home);
        displayFragment(new HomeFragment());

        navigationBar.setOnItemSelectedListener(item ->
        {
            switch (item.getItemId()){
                case R.id.bottom_home:
                    if (navigationBar.getSelectedItemId() != R.id.bottom_home) {
                        displayFragment(new HomeFragment());
                    }
                    return true;
                case R.id.bottom_settings:
                    if (navigationBar.getSelectedItemId() != R.id.bottom_settings) {
                        displayFragment(new SettingsFragment());
                    }
                    return true;
                case R.id.bottom_statistics:
                    if (navigationBar.getSelectedItemId() != R.id.bottom_statistics){
                        displayFragment(new StatisticsFragment());
                    }
                    return true;
                case R.id.bottom_help:
                    if (navigationBar.getSelectedItemId() != R.id.bottom_help){
                        displayFragment(new FlagsFragment());
                    }
                    return true;
            }
            return false;
        });

    }

    public void displayFragment(Fragment fragment)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frame_layout , fragment);
        ft.addToBackStack(null);
        ft.commit();
    }


}