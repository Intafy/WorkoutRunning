package com.intafy.testtablayout.intafy.presentation.ViewModels;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.intafy.testtablayout.intafy.data.repositories.WorkoutRepository;
import com.intafy.testtablayout.intafy.data.storage.SqliteStorage;
import com.intafy.testtablayout.intafy.domain.models.Workout;
import com.intafy.testtablayout.intafy.domain.usecases.SaveWorkoutUseCase;
import com.intafy.testtablayout.intafy.presentation.TabLayoutActivity;

public class TabViewModel extends ViewModel {
    Workout newWorkout;

    SaveWorkoutUseCase saveWorkoutUseCase;

    private final String DEFAULT_DATE ="Введите дату";
    private final String DEFAULT_TIME = "Введите время";
    private final String DEFAULT_DIST= "Введите расстояние";
    private MutableLiveData<String>dateLiveData = new MutableLiveData<>(DEFAULT_DATE);
    private MutableLiveData<String> timeLiveData = new MutableLiveData<>(DEFAULT_TIME);
    private MutableLiveData<String> distLiveData = new MutableLiveData<>(DEFAULT_DIST);

    {
        Log.d("MyLog","VM created");
    }

    public TabViewModel(SaveWorkoutUseCase saveWorkoutUseCase) {
        this.saveWorkoutUseCase = saveWorkoutUseCase;
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
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("MyLog","VM cleared");
    }
    public void setWorkout() {
        if(dateLiveData.getValue()!= DEFAULT_DATE && timeLiveData.getValue()!= DEFAULT_TIME) {
            newWorkout = new Workout(dateLiveData.getValue(), timeLiveData.getValue());
            saveWorkoutUseCase.execute(newWorkout);
        }
    }
    public void clearAllValues(){
        dateLiveData.postValue(DEFAULT_DATE);
        timeLiveData.postValue(DEFAULT_TIME);
    }
}

