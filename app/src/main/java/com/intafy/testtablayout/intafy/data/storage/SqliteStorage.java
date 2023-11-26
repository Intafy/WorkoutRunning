package com.intafy.testtablayout.intafy.data.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.intafy.testtablayout.intafy.domain.models.Workout;
import com.intafy.testtablayout.intafy.presentation.TabLayoutActivity;


public class SqliteStorage implements WorkoutStorageInterface {
    Context context;

    public SqliteStorage(Context context) {
        this.context = context;
    }

    //Здесь пишем код сохранения в БД
    @Override
    public void saveWorkout(Workout workout) {

    WorkoutSqliteHelper workoutSqliteHelper = new WorkoutSqliteHelper(context);
    String date = workout.date;
    String time = workout.time;
        Log.d("MyLog","All in storage");
        try {
            ContentValues runValues = new ContentValues();
            runValues.put("DATE", date);
            runValues.put("DESCRIPTION", "Вы пробежали "  + "м за " + time + " (ч:м:с)");
            SQLiteDatabase workoutDb = workoutSqliteHelper.getWritableDatabase();
            Log.d("MyLog","Db has created");
            workoutDb.insert("WORKOUT", null, runValues);
            workoutDb.close();
            Log.d ("MyLog","All in Db");
//              Toast.makeText(context, "Запись сохранена", Toast.LENGTH_SHORT).show();
        }
        catch (SQLException e) {
//              Toast.makeText(context, "Database unavaible", Toast.LENGTH_SHORT).show();
        }
    }
}
