package com.example.projectforitschool.MathMode;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projectforitschool.Database.AppDatabase;
import com.example.projectforitschool.Database.MathGameStatUnit;
import com.example.projectforitschool.R;

import java.util.ArrayList;


public class MathModeStatisticsFragment extends Fragment {


    ArrayList<MathGameStatUnit> data;
    RecyclerView recyclerView;
    ImageView person;
    TextView notFound;
    Button clearData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_math_mode_statistics, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        notFound = view.findViewById(R.id.textView_not_found);
        person = view.findViewById(R.id.imageView_confused_person);
        clearData = view.findViewById(R.id.button_clear_history);

        clearData.setOnClickListener(view1 -> {
            AppDatabase db = AppDatabase.getDatabaseInstance(getActivity().getApplicationContext());
            db.mathGameStatUnitDao().nukeTable();
            recyclerView.setVisibility(View.INVISIBLE);
            notFound.setVisibility(View.VISIBLE);
            person.setVisibility(View.VISIBLE);
            clearData.setVisibility(View.INVISIBLE);
        });
        setUpData();




        if (data.size() == 0)
        {
            person.setVisibility(View.VISIBLE);
            notFound.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
        else {
            clearData.setVisibility(View.VISIBLE);
            MathModeRecyclerViewAdapter adapter = new MathModeRecyclerViewAdapter(getActivity(), data);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    private void setUpData()
    {
        AppDatabase db = AppDatabase.getDatabaseInstance(getActivity().getApplicationContext());
        data = (ArrayList<MathGameStatUnit>) db.mathGameStatUnitDao().getAllUnits();
    }
}