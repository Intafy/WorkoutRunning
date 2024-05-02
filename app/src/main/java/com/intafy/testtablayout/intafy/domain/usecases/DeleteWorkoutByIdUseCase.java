package com.intafy.testtablayout.intafy.domain.usecases;

import com.intafy.testtablayout.intafy.domain.WorkoutInterface;
import com.intafy.testtablayout.intafy.domain.models.Workout;

public class DeleteWorkoutByIdUseCase {
    WorkoutInterface workoutInterface;

    public DeleteWorkoutByIdUseCase(WorkoutInterface workoutInterface){
        this.workoutInterface=workoutInterface;
    }
    public void execute(Workout workout){
        workoutInterface.deleteWorkout(workout);
    }
}
