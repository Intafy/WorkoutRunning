package com.intafy.testtablayout.intafy.domain;

import com.intafy.testtablayout.intafy.domain.models.Workout;

import java.util.List;

public interface WorkoutInterface {
    void saveWorkout(Workout workout);
    List<Workout> getWorkoutList();
    void deleteWorkout();
}
