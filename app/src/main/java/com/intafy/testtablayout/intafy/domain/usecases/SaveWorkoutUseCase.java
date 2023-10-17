package com.intafy.testtablayout.intafy.domain.usecases;

import android.util.Log;
import android.widget.Toast;

import com.intafy.testtablayout.intafy.domain.models.Workout;
public class SaveWorkoutUseCase {
    public void execute (Workout workout){
        if(workout.date.isEmpty()||workout.time.isEmpty()) {

        }
        else{
            Log.d("MyLog","All has saved");
        }

    }
}
