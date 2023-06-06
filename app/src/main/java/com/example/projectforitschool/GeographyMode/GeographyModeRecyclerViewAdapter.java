package com.example.projectforitschool.GeographyMode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectforitschool.Database.GeographyGameStatUnit;
import com.example.projectforitschool.R;

import java.util.ArrayList;

public class GeographyModeRecyclerViewAdapter extends RecyclerView.Adapter<GeographyModeRecyclerViewAdapter.MyViewHolder> {


    Context context;
    ArrayList<GeographyGameStatUnit> data;
    public GeographyModeRecyclerViewAdapter(Context context , ArrayList<GeographyGameStatUnit> data)
    {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public GeographyModeRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_geography_mode_games_row, parent , false);
        return new GeographyModeRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GeographyModeRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.gameResult.setImageResource(data.get(position).getGameResultIcon());
        holder.modeLogo.setImageResource(data.get(position).getModeIcon());
        holder.date.setText(data.get(position).getDateString());
        holder.correctAnswers.setText(data.get(position).getCorrectAnswersString());
        holder.averageAnswers.setText(data.get(position).getAverageAnswerTimeString());
        holder.gameDuration.setText(data.get(position).getGameDurationString());
        holder.gameSize.setText(data.get(position).getGameSizeString());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView gameResult , modeLogo;
        TextView date , correctAnswers , averageAnswers , gameDuration , gameSize;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            gameResult = itemView.findViewById(R.id.imageView_game_result_logo);
            modeLogo = itemView.findViewById(R.id.imageView_mode_logo);

            date = itemView.findViewById(R.id.textView_date_in_row);
            correctAnswers = itemView.findViewById(R.id.textView_correct_answers_counter_in_row);
            averageAnswers = itemView.findViewById(R.id.textView_average_answers_counter_in_row);
            gameDuration = itemView.findViewById(R.id.textView_game_duration_in_row);
            gameSize = itemView.findViewById(R.id.textView_game_size_in_row);
        }
    }
}
