package com.intafy.testtablayout.intafy.data.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.intafy.testtablayout.intafy.domain.models.Workout;
import java.util.ArrayList;
import java.util.List;


public class SqliteStorage implements WorkoutStorageInterface {
    List<Workout> workouts;
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
        String dist = workout.dist;
        Log.d("MyLog","All in storage");
        try {
            ContentValues runValues = new ContentValues();
            runValues.put("DATE", date);
            runValues.put("DESCRIPTION", "Вы пробежали "  + dist + " метров за " + time);
            SQLiteDatabase workoutDb = workoutSqliteHelper.getWritableDatabase();
            Log.d("MyLog","Db has created");
            workoutDb.insert("WORKOUT", null, runValues);
            //            workoutDb.close();
            Log.d ("MyLog","All in Db");
            Toast.makeText(context,"Workout has saved",Toast.LENGTH_SHORT).show();
        }
        catch (SQLException e) {
            Toast.makeText(context, "Database unavaible", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public List<Workout> getWorkoutList() {

        //Добавление в лист записей из БД с помощью cursor
        workouts = new ArrayList<>();
        workoutSqliteHelper = new WorkoutSqliteHelper(context);
        try{
            SQLiteDatabase workoutDb = workoutSqliteHelper.getReadableDatabase();
            cursor = workoutDb.query("WORKOUT",
                    new String [] {"DATE","DESCRIPTION"},
                    null,null,null,null,null);

            cursor.moveToFirst();
                while(!cursor.isAfterLast()){

                    String date = cursor.getString(0);
                    String description = cursor.getString(1);
                    String dist = cursor.getString(1);
                    Workout workout = new Workout(date,description,dist);
                    workouts.add(workout);
                    cursor.moveToNext();
            }
            cursor.close();
//            workoutDb.close();
        } catch (SQLException e){
            Log.d("MyLog","dataBase unavailable");
        }
        return workouts;
    }
    @Override
    public void deleteWorkout(Workout workout) {
        //Переделать передачу description в методе getWorkoutList(), так как
        //в поле workout.time там сохраняется вся информация из description
        //и, соответственно, в методе deleteWorkout() неоходимо ее "вытаскивать"
        // из поля workout.time, приравнивая переменной descr, которую мы передаем
        //в workoutDb.delete(descr,date)
        String date = workout.date;
        String descr = workout.time;
//        String sqliteDeleteRequest = "DELETE FROM WORKOUT WHERE DATE="+date+" AND DESCRIPTION="+descr;
        Toast.makeText(context,"Запись удалена",Toast.LENGTH_SHORT).show();
        workoutSqliteHelper = new WorkoutSqliteHelper(context);
        try {
           SQLiteDatabase workoutDb = workoutSqliteHelper.getWritableDatabase();
//           workoutDb.delete("WORKOUT","DATE=?",new String[]{date});
//           workoutDb.execSQL("DELETE FROM WORKOUT WHERE DESCRIPTION = descr");
           workoutDb.delete("WORKOUT","DESCRIPTION=? AND DATE=?",new String[]{descr,date});


        }
        catch (SQLException e){
            Log.d("MyLog","dataBase unavailable");
        }
    }
}
