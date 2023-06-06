package com.example.projectforitschool.MathMode;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectforitschool.Database.MathGameStatUnit;
import com.example.projectforitschool.R;

import java.util.ArrayList;

public class MathModeRecyclerViewAdapter extends RecyclerView.Adapter<MathModeRecyclerViewAdapter.MyViewHolder> {

    Context context;
    ArrayList<MathGameStatUnit> data;
    public MathModeRecyclerViewAdapter(Context context , ArrayList<MathGameStatUnit> data)
    {
        this.context = context;
        this.data = data;

    }
    @NonNull
    @Override
    public MathModeRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_math_mode_row , parent , false);
        return new MathModeRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MathModeRecyclerViewAdapter.MyViewHolder holder, int position) {
        // assign values here

        holder.date.setText(data.get(position).getDateString());
        holder.gameDuration.setText(data.get(position).getGameDurationString());
        holder.averageAnswers.setText(data.get(position).getAverageAnswerTimeString());
        holder.correctAnswers.setText(data.get(position).getCorrectAnswersString());
        holder.modeIcon.setImageResource(data.get(position).getModeIcon());
    }

    @Override
    public int getItemCount() {
        // total items , so it updates the view
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        // assign view to variables

        ImageView modeIcon;
        TextView date , correctAnswers , averageAnswers , gameDuration;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            modeIcon = itemView.findViewById(R.id.imageView_game_result_logo);
            date = itemView.findViewById(R.id.textView_date_in_row);
            correctAnswers = itemView.findViewById(R.id.textView_correct_answers_counter_in_row);
            averageAnswers = itemView.findViewById(R.id.textView_average_answers_counter_in_row);
            gameDuration = itemView.findViewById(R.id.textView_game_duration_in_row);
        }
    }
}
