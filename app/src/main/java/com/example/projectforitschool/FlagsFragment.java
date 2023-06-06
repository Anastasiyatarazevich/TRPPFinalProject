package com.example.projectforitschool;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.projectforitschool.GeographyMode.Country;
import com.example.projectforitschool.GeographyMode.GeographyAllFlagsRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Random;

public class FlagsFragment extends Fragment {

    ArrayList<Country> countries = new ArrayList<>();
    RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_flags, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);

        retrieveData();

        GeographyAllFlagsRecyclerViewAdapter adapter = new GeographyAllFlagsRecyclerViewAdapter(getActivity() , countries);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void retrieveData()
    {
        String countryNames[] = getResources().getStringArray(R.array.CountryName);
        String countryCapitals[] = getResources().getStringArray(R.array.CountryCapital);
        TypedArray flagsId = getResources().obtainTypedArray(R.array.CountryFlag);
        for (int x = 0; x < countryNames.length; x++) {
            countries.add(new Country(countryNames[x], countryCapitals[x], flagsId.getResourceId(x, 0)));
        }
    }
}