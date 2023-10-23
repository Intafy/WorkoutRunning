package com.intafy.testtablayout.intafy.domain.usecases;

import android.util.Log;
import android.widget.Toast;

import com.intafy.testtablayout.intafy.data.WorkoutRepository;
import com.intafy.testtablayout.intafy.domain.WorkoutInterface;
import com.intafy.testtablayout.intafy.domain.models.Workout;
public class SaveWorkoutUseCase {
    private WorkoutInterface workoutInterface;
    public void execute (Workout workout){
//        workoutInterface.saveWorkout(workout);
            Log.d("MyLog","All has saved");
    }
}
