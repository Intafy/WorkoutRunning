package com.intafy.testtablayout.intafy.domain.usecases;

import android.util.Log;
import com.intafy.testtablayout.intafy.domain.WorkoutInterface;
import com.intafy.testtablayout.intafy.domain.models.Workout;
public class SaveWorkoutUseCase {
    WorkoutInterface workoutInterface;
    public SaveWorkoutUseCase(WorkoutInterface workoutInterface){
        this.workoutInterface=workoutInterface;
    }
    public void execute (Workout workout){
        
        Log.d("MyLog",workout.date);
        Log.d("MyLog",workout.time);

        Log.d("MyLog","All has saved");
        workoutInterface.saveWorkout(workout);

    }

}
