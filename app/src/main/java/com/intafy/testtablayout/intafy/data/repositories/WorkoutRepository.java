package com.intafy.testtablayout.intafy.data.repositories;

import android.util.Log;

import com.intafy.testtablayout.intafy.data.storage.WorkoutStorageInterface;
import com.intafy.testtablayout.intafy.domain.WorkoutInterface;
import com.intafy.testtablayout.intafy.domain.models.Workout;

public class WorkoutRepository implements WorkoutInterface {
    WorkoutStorageInterface workoutStorageInterface;
    public WorkoutRepository(WorkoutStorageInterface workoutStorageInterface) {
        this.workoutStorageInterface = workoutStorageInterface;
    }
    @Override
    public void saveWorkout(Workout workout) {
        workoutStorageInterface.saveWorkout(workout);
    }
}
