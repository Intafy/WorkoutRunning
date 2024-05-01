package com.intafy.testtablayout.intafy.presentation;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.intafy.testtablayout.R;
import com.intafy.testtablayout.intafy.domain.models.Workout;

import java.util.List;

public class WorkoutAdapter extends RecyclerView.Adapter <WorkoutAdapter.WorkoutHolder> {
    private final List<Workout> workoutList;
    private Listener listener;
    public WorkoutAdapter(List<Workout> workoutList){
        this.workoutList=workoutList;
    }
    interface Listener{
        void onLongClick(Workout workout);
    }
    public void setListener(Listener listener){
        this.listener = listener;
    }
    public static class WorkoutHolder extends RecyclerView.ViewHolder{
        private final CardView cardView;
        public WorkoutHolder (CardView cv) {
            super(cv);
            cardView = cv;
        }
    }
    @NonNull
    public WorkoutHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).
                inflate(R.layout.workout_cardview,parent,false);
        return new WorkoutHolder(cv);
    }
    @Override
    public void onBindViewHolder(@NonNull WorkoutHolder holder, int position) {

        CardView cardView = holder.cardView;
        TextView dateText = cardView.findViewById(R.id.date_text);
        TextView descText = cardView.findViewById(R.id.desc_text);
        dateText.setText(workoutList.get(holder.getAdapterPosition()).date);
        descText.setText(workoutList.get(holder.getAdapterPosition()).time);
        cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                listener.onLongClick(workoutList.get(holder.getAdapterPosition()));
                workoutList.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
                return true;
            }
        });
    }
    @Override
    public int getItemCount() {
        return workoutList.size();
    }
}
