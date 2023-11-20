package com.intafy.testtablayout.intafy.data.storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WorkoutSqliteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "workoutDb";
    private static final int DB_NUMBER = 1;

    public WorkoutSqliteHelper (Context context) {
        super (context,DB_NAME,null,DB_NUMBER);
    }
    @Override
    public void onCreate (SQLiteDatabase workoutDb){
        updateMyBd(workoutDb,0,DB_NUMBER);
    }
    public void onUpgrade(SQLiteDatabase workoutDb,int oldVersion,int newVersion){
        updateMyBd(workoutDb,oldVersion,newVersion);
    }
    public void onDowngrade (SQLiteDatabase workoutDb,int oldVersion,int newVersion){
    }
    private void updateMyBd(SQLiteDatabase workoutDb,int oldVersion,int newVersion){
        createTable(workoutDb);
        }


    private void createTable(SQLiteDatabase workoutDb){
        workoutDb.execSQL("CREATE TABLE WORKOUT ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "DATE TEXT,"
                +"DESCRIPTION TEXT);");
    }
}
