package com.intafy.testtablayout.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TabViewModel extends ViewModel {
    private MutableLiveData<String>dateLiveData = new MutableLiveData<>();
    private MutableLiveData<String> timeLiveData = new MutableLiveData<>();
    {
        Log.d("MyLog","VM created");
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
}

