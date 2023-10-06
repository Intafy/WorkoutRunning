package com.intafy.testtablayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import com.intafy.testtablayout.ViewModels.TabViewModel;

import java.util.Calendar;
public class TabLayoutActivity extends AppCompatActivity implements TimeDialog.OnTimeListener{
    TabViewModel tabViewModel;
    Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        Log.d("MyLog", "Act created");
        tabViewModel = new ViewModelProvider(this).get(TabViewModel.class);
        ViewPager2 viewpager = findViewById(R.id.view_pager);
        PagerAdapter pagerAdapter = new PagerAdapter(this);
        viewpager.setAdapter(pagerAdapter);
    }
    @Override
    public void onTimeListener(String time) {
        tabViewModel.save(time);
    }
    private class PagerAdapter extends FragmentStateAdapter{
        public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:  fragment = new MainFragment();
                return fragment;
                case 1:  fragment = new WorkoutFragment();
                return fragment;
                default: fragment = new HistoryFragment();
                return fragment;
            }
        }
        @Override
        public int getItemCount() {
            return 3;
        }
    }
}