package com.example.projectforitschool.GeographyMode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectforitschool.Database.GeographyFlagComparison;
import com.example.projectforitschool.R;

import java.util.ArrayList;

public class GeographyFlagRecyclerViewAdapter extends RecyclerView.Adapter<GeographyFlagRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<GeographyFlagComparison> data;

    public GeographyFlagRecyclerViewAdapter(Context context, ArrayList<GeographyFlagComparison> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GeographyFlagRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_geography_mode_flags_row, parent , false);
        return new GeographyFlagRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeographyFlagRecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.leftCountryFlag.setImageResource(data.get(position).getLeftCountryFlag());
        holder.rightCountryFlag.setImageResource(data.get(position).getRightCountryFlag());

        holder.leftCountryName.setText("Country: " + data.get(position).getLeftCountryName());
        holder.rightCountryName.setText("Country: " + data.get(position).getRightCountryName());

        holder.leftCountryCapital.setText("Capital: " + data.get(position).getLeftCountryCapital());
        holder.rightCountryCapital.setText("Capital: " + data.get(position).getRightCountryCapital());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView leftCountryFlag, rightCountryFlag;
        TextView leftCountryName , rightCountryName , leftCountryCapital , rightCountryCapital;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            leftCountryFlag = itemView.findViewById(R.id.imageView_left_country_flag);
            rightCountryFlag = itemView.findViewById(R.id.imageView_right_country_flag);
            leftCountryName = itemView.findViewById(R.id.textView_left_country_name);
            rightCountryName = itemView.findViewById(R.id.textView_right_country_name);
            leftCountryCapital = itemView.findViewById(R.id.textView_left_country_capital);
            rightCountryCapital = itemView.findViewById(R.id.textView_right_country_capital);
        }
    }
}
