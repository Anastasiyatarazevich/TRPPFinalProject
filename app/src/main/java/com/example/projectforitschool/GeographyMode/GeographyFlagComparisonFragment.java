package com.example.projectforitschool.GeographyMode;

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
import com.example.projectforitschool.Database.GeographyFlagComparison;
import com.example.projectforitschool.Database.GeographyGameStatUnit;
import com.example.projectforitschool.MainActivity;
import com.example.projectforitschool.R;

import java.util.ArrayList;


public class GeographyFlagComparisonFragment extends Fragment {


    ArrayList<GeographyFlagComparison> data;
    RecyclerView recyclerView;
    ImageView person;
    TextView notFound;
    Button goBack;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_geography_flag_comparison, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        notFound = view.findViewById(R.id.textView_not_found);
        person = view.findViewById(R.id.imageView_confused_person);
        goBack = view.findViewById(R.id.button_clear_history);
        goBack.setVisibility(View.VISIBLE);

        goBack.setOnClickListener(view1 -> {
            ((MainActivity)getActivity()).displayFragment(new GeographyGameStatisticsFragment());
        });

        setUpData();

        if (data.size() == 0)
        {
            notFound.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.INVISIBLE);
        }
        else {
            GeographyFlagRecyclerViewAdapter adapter = new GeographyFlagRecyclerViewAdapter(getActivity(), data);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
    }

    private void setUpData()
    {
        AppDatabase db = AppDatabase.getDatabaseInstance(getActivity().getApplicationContext());
        data = (ArrayList<GeographyFlagComparison>) db.geographyFlagComparisonDao().getAllUnits();
    }

}