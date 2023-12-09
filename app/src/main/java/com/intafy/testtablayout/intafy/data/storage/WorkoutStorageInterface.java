package com.intafy.testtablayout.intafy.data.storage;

import com.intafy.testtablayout.intafy.domain.models.Workout;

import java.util.List;

public interface WorkoutStorageInterface {
    void saveWorkout(Workout workout);
    List<Workout>getWorkoutList();
}
