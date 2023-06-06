package com.example.projectforitschool;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.projectforitschool.MathMode.MathGameFragment;
import com.example.projectforitschool.MathMode.MathGameModeSelectionFragment;
import com.example.projectforitschool.MathMode.MathGameResultFragment;

public class MathModeActivity extends AppCompatActivity {

    public long gameUnit_id;
    public char mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_game);

        MathGameModeSelectionFragment fragment = new MathGameModeSelectionFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout , fragment).commit();
    }

    public void startGameFragment()
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MathGameFragment fragment = new MathGameFragment();
        ft.replace(R.id.frame_layout , fragment);
        ft.commit();
    }

    public void startResultFragment()
    {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MathGameResultFragment fragment = new MathGameResultFragment();
        ft.replace(R.id.frame_layout , fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {

    }
}