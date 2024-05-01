package com.intafy.testtablayout.intafy.data.storage;

import com.intafy.testtablayout.intafy.domain.models.Workout;

import java.util.List;

public interface WorkoutStorageInterface {


    //Здесь пишем код сохранения в БД
    void saveWorkout(Workout workout);

    List<Workout>getWorkoutList();
//    void deleteWorkout(String date, String time, String dist);

    void deleteWorkout(Workout workout);
}
