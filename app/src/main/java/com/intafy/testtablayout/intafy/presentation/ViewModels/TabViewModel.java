package com.intafy.testtablayout.intafy.presentation.ViewModels;


import android.os.AsyncTask;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.intafy.testtablayout.intafy.domain.models.Workout;
import com.intafy.testtablayout.intafy.domain.usecases.GetWorkoutListUseCase;
import com.intafy.testtablayout.intafy.domain.usecases.SaveWorkoutUseCase;
import java.util.List;

public class TabViewModel extends ViewModel {
    Workout newWorkout;

    SaveWorkoutUseCase saveWorkoutUseCase;
    GetWorkoutListUseCase getWorkoutListUseCase;

    private final String DEFAULT_DATE ="Введите дату";
    private final String DEFAULT_TIME = "Введите время";
    private final String DEFAULT_DIST= "Введите расстояние";
    private final MutableLiveData<String>dateLiveData = new MutableLiveData<>(DEFAULT_DATE);
    private final MutableLiveData<String> timeLiveData = new MutableLiveData<>(DEFAULT_TIME);
    private final MutableLiveData<String> distLiveData = new MutableLiveData<>(DEFAULT_DIST);


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
    new MyTask().execute();
    }
    public List<Workout> getWorkoutList(){
        return getWorkoutListUseCase.execute();
    }
    public void clearAllValues(){
        dateLiveData.postValue(DEFAULT_DATE);
        timeLiveData.postValue(DEFAULT_TIME);
    }
    private class GetListTask extends AsyncTask<Void,Void,List<Workout>>{

        @Override
        protected List<Workout> doInBackground(Void... voids) {
            return null;
        }
    }
    private class MyTask extends AsyncTask<Void,Void,Boolean>{
        @Override
        protected Boolean doInBackground(Void... voids) {
            if(dateLiveData.getValue()!= DEFAULT_DATE && timeLiveData.getValue()!= DEFAULT_TIME) {
                newWorkout = new Workout(dateLiveData.getValue(), timeLiveData.getValue());
                saveWorkoutUseCase.execute(newWorkout);
            }
            return true;
        }
    }
}

