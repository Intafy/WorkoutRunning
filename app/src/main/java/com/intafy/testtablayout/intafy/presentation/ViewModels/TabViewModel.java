package com.intafy.testtablayout.intafy.presentation.ViewModels;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.intafy.testtablayout.R;
import com.intafy.testtablayout.intafy.domain.models.Workout;
import com.intafy.testtablayout.intafy.domain.usecases.SaveWorkoutUseCase;

public class TabViewModel extends ViewModel {
    private final String DEFAULT_DATE ="Вы не ввели дату";
    private final String DEFAULT_TIME = "Вы не ввели время";
    private MutableLiveData<String>dateLiveData = new MutableLiveData<>(DEFAULT_DATE);
    private MutableLiveData<String> timeLiveData = new MutableLiveData<>(DEFAULT_TIME);

    {
        Log.d("MyLog","VM created");
    }
    private SaveWorkoutUseCase saveWorkoutUseCase = new SaveWorkoutUseCase();
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
    public void saveWorkout() {
        if(dateLiveData.getValue()!= DEFAULT_DATE && timeLiveData.getValue()!= DEFAULT_TIME) {
            Workout newWorkout = new Workout(dateLiveData.toString(), timeLiveData.toString());
            saveWorkoutUseCase.execute(newWorkout);
        }
    }
    public void clearAllValues(){
        dateLiveData.postValue(DEFAULT_DATE);
        timeLiveData.postValue(DEFAULT_TIME);
    }
}

