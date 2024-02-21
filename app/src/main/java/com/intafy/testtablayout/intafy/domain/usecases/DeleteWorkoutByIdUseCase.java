package com.intafy.testtablayout.intafy.domain.usecases;

import com.intafy.testtablayout.intafy.domain.WorkoutInterface;

public class DeleteWorkoutByIdUseCase {
    WorkoutInterface workoutInterface;

    public DeleteWorkoutByIdUseCase(WorkoutInterface workoutInterface){
        this.workoutInterface=workoutInterface;
    }
    public void execute(){
        workoutInterface.deleteWorkout();
    }
}
