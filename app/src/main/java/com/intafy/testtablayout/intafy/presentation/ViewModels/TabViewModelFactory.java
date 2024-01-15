package com.intafy.testtablayout.intafy.presentation.ViewModels;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.intafy.testtablayout.intafy.data.repositories.WorkoutRepository;
import com.intafy.testtablayout.intafy.data.storage.SqliteStorage;
import com.intafy.testtablayout.intafy.domain.usecases.GetWorkoutListUseCase;
import com.intafy.testtablayout.intafy.domain.usecases.SaveWorkoutUseCase;

public class TabViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private SaveWorkoutUseCase saveWorkoutUseCase;
    private GetWorkoutListUseCase getWorkoutListUseCase;
    public TabViewModelFactory (Context context){
        SqliteStorage sqliteStorage = new SqliteStorage(context);
        WorkoutRepository workoutRepository = new WorkoutRepository(sqliteStorage);
        saveWorkoutUseCase = new SaveWorkoutUseCase(workoutRepository);
        getWorkoutListUseCase = new GetWorkoutListUseCase(workoutRepository);
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create (@NonNull Class<T> modelClass){
        return (T) new TabViewModel(saveWorkoutUseCase,getWorkoutListUseCase);
    }

}
