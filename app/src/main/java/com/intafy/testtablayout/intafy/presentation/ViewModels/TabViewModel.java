package com.intafy.testtablayout.intafy.presentation.ViewModels;


import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.intafy.testtablayout.intafy.domain.models.Workout;
import com.intafy.testtablayout.intafy.domain.usecases.DeleteWorkoutByIdUseCase;
import com.intafy.testtablayout.intafy.domain.usecases.GetWorkoutListUseCase;
import com.intafy.testtablayout.intafy.domain.usecases.SaveWorkoutUseCase;
import java.util.List;

public class TabViewModel extends ViewModel {
    Workout newWorkout;
    SaveWorkoutUseCase saveWorkoutUseCase;
    GetWorkoutListUseCase getWorkoutListUseCase;
    DeleteWorkoutByIdUseCase deleteWorkoutByIdUseCase;
    private final String DEFAULT_DATE ="Введите дату";
    private final String DEFAULT_TIME = "Введите время";
    private final String DEFAULT_DIST= "Введите расстояние";
    private String dist;
    private final MutableLiveData<String>dateLiveData = new MutableLiveData<>(DEFAULT_DATE);
    private final MutableLiveData<String> timeLiveData = new MutableLiveData<>(DEFAULT_TIME);
    public TabViewModel(SaveWorkoutUseCase saveWorkoutUseCase,GetWorkoutListUseCase getWorkoutListUseCase,DeleteWorkoutByIdUseCase deleteWorkoutByIdUseCase) {
        this.saveWorkoutUseCase = saveWorkoutUseCase;
        this.getWorkoutListUseCase=getWorkoutListUseCase;
        this.deleteWorkoutByIdUseCase=deleteWorkoutByIdUseCase;
    }
    public void saveDate(String date){
        dateLiveData.postValue(date);
    }
    public LiveData<String>loadDate(){
        return dateLiveData;
    }
    public void saveTime(String time) {
        timeLiveData.postValue(time);
    }
    public LiveData<String> loadTime(){
        return timeLiveData;
    }
    public void saveDist(String dist){
        this.dist=dist;
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("MyLog","VM cleared");
    }
    public void setWorkout() {
    new MyTask().doInBackground();
    }
    public List<Workout> getWorkoutList(){
        return new GetListTask().doInBackground();}
    public void deleteWorkout(Workout workout){
        new DeleteTask().doInBackground(workout);
    }

    public void clearAllValues(){
        dateLiveData.postValue(DEFAULT_DATE);
        timeLiveData.postValue(DEFAULT_TIME);
    }
    private class GetListTask extends AsyncTask<Void,Void,List<Workout>>{

        @Override
        protected List<Workout> doInBackground(Void... voids) {
            return getWorkoutListUseCase.execute();
        }
    }
    private class MyTask extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected Boolean doInBackground(Void... voids) {
            if(dateLiveData.getValue()!= DEFAULT_DATE
               && timeLiveData.getValue()!= DEFAULT_TIME
               && dist!=DEFAULT_DIST
               && dist!=null) {
                    newWorkout = new Workout(
                            dateLiveData.getValue(),
                            timeLiveData.getValue(),
                            dist);
                    saveWorkoutUseCase.execute(newWorkout);
            }
            return true;
        }
    }
    private class DeleteTask extends AsyncTask<Workout,Void,Boolean>{
        @Override
        protected Boolean doInBackground(Workout... workouts) {

            String date = workouts[0].date;
            String time = workouts[0].time;
            String dist = workouts[0].dist;
            Workout workout = new Workout(date,time,dist);
            deleteWorkoutByIdUseCase.execute(workout);
            return true;
        }
    }
}

