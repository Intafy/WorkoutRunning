package com.intafy.testtablayout.intafy.data.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.intafy.testtablayout.intafy.domain.models.Workout;
import com.intafy.testtablayout.intafy.presentation.TabLayoutActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SqliteStorage implements WorkoutStorageInterface {
    Context context;
    private WorkoutSqliteHelper workoutSqliteHelper;

    private Cursor cursor;

    public SqliteStorage(Context context) {
        this.context = context;
    }

    //Здесь пишем код сохранения в БД
    @Override
    public void saveWorkout(Workout workout) {

    workoutSqliteHelper = new WorkoutSqliteHelper(context);
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

    @Override
    public List<Workout> getWorkoutList() {




        //Пробник для заполнения RecyclerView
//        Workout []workout = {
//                new Workout("121212","151515"),
//                new Workout("1566","131"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515"),
//                new Workout("121212","151515")
//        };
//        workouts.addAll(Arrays.asList(workout));

        //Добавление в лист записей из БД с помощью cursor
        List<Workout>workouts = new ArrayList<>();
        workoutSqliteHelper = new WorkoutSqliteHelper(context);
        try{
            SQLiteDatabase workoutDb = workoutSqliteHelper.getReadableDatabase();

            cursor = workoutDb.query("WORKOUT",
                    new String [] {"DATE","DESCRIPTION"},
                    null,null,null,null,null);
            if(cursor.moveToFirst()){
                while(cursor.moveToNext()){
                String date = cursor.getString(0);
                String description = cursor.getString(1);
                Workout workout = new Workout(date,description);
                workouts.add(workout);
                cursor.moveToNext();
                }
            }
        } catch (SQLException e){
            Log.d("MyLog","dataBase unavailable");
        }

        return workouts;
    }
}
