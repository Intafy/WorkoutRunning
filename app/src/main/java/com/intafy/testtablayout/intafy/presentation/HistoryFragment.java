package com.intafy.testtablayout.intafy.presentation;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.intafy.testtablayout.R;
import com.intafy.testtablayout.intafy.domain.models.Workout;
import com.intafy.testtablayout.intafy.presentation.ViewModels.TabViewModel;
public class HistoryFragment extends Fragment {
    private TabViewModel tabViewModel;
    private RecyclerView workoutRecycler;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history,container,false);
    }
    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if (view != null) {
            workoutRecycler = view.findViewById(R.id.workout_recycler);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            workoutRecycler.setLayoutManager(layoutManager);
            tabViewModel = new ViewModelProvider(requireActivity()).get(TabViewModel.class);
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        Log.d("MyLog","History Frag onResume");
        WorkoutAdapter workoutAdapter = new WorkoutAdapter(tabViewModel.getWorkoutList());
        workoutRecycler.setAdapter(workoutAdapter);
            workoutAdapter.setListener(new WorkoutAdapter.Listener() {
                @Override
                public void onLongClick(Workout workout) {
                    tabViewModel.deleteWorkout(workout);
                    tabViewModel.getWorkoutList();
                }
            });
    }

}