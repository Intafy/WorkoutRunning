package com.intafy.testtablayout.intafy.data.repositories;

import com.intafy.testtablayout.intafy.data.storage.WorkoutStorageInterface;
import com.intafy.testtablayout.intafy.domain.WorkoutInterface;
import com.intafy.testtablayout.intafy.domain.models.Workout;

import java.util.List;

public class WorkoutRepository implements WorkoutInterface {
    private WorkoutStorageInterface workoutStorageInterface;
    public WorkoutRepository(WorkoutStorageInterface workoutStorageInterface) {
        this.workoutStorageInterface = workoutStorageInterface;
    }
    @Override
    public void saveWorkout(Workout workout) {
        workoutStorageInterface.saveWorkout(workout);
    }

    @Override
    public List<Workout> getWorkoutList() {
        return workoutStorageInterface.getWorkoutList();
    }
    public  void deleteWorkout(Workout workout){
        workoutStorageInterface.deleteWorkout(workout);
    }
//    @Override
//    public  void deleteWorkout(String date,String time, String dist){
//        workoutStorageInterface.deleteWorkout(date,time,dist);
//
//    }
}
