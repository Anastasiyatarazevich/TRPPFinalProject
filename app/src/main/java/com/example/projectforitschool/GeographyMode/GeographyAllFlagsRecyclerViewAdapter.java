package com.example.projectforitschool.GeographyMode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectforitschool.R;

import java.util.ArrayList;

public class GeographyAllFlagsRecyclerViewAdapter extends RecyclerView.Adapter<GeographyAllFlagsRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<Country> countries;

    public GeographyAllFlagsRecyclerViewAdapter(Context context, ArrayList<Country> countries) {
        this.context = context;
        this.countries = countries;
    }

    @NonNull
    @Override
    public GeographyAllFlagsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_display_all_flags_row, parent, false);
        return new GeographyAllFlagsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeographyAllFlagsRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.flag.setImageResource(countries.get(position).getImageResource());
        holder.name.setText("Country: " + countries.get(position).getName());
        holder.capital.setText("Capital: " + countries.get(position).getCapital());
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView flag;
        TextView name , capital;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            flag = itemView.findViewById(R.id.imageView_flag);
            name = itemView.findViewById(R.id.textView_country_name);
            capital = itemView.findViewById(R.id.textView_country_capital);

        }
    }
}
