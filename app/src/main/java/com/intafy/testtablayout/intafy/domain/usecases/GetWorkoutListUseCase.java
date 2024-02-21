package com.intafy.testtablayout.intafy.domain.usecases;

import com.intafy.testtablayout.intafy.domain.WorkoutInterface;
import com.intafy.testtablayout.intafy.domain.models.Workout;

import java.util.List;

public class GetWorkoutListUseCase {
    WorkoutInterface workoutInterface;
    public GetWorkoutListUseCase(WorkoutInterface workoutInterface){
        this.workoutInterface=workoutInterface;
    }
    public List<Workout> execute(){
        return workoutInterface.getWorkoutList();
    }
}
