package com.intafy.testtablayout.ViewModels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TabViewModel extends ViewModel {
    private MutableLiveData<String> timeLiveData = new MutableLiveData<>();
    {
        Log.d("MyLog","VM created");
    }
    public void save(String time) {
        timeLiveData.postValue(time);
    }
    public LiveData<String>load(){
        return timeLiveData;
    }
    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d("MyLog","VM cleared");
    }
}

