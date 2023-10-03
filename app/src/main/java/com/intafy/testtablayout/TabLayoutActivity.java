package com.intafy.testtablayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;
public class TabLayoutActivity extends AppCompatActivity implements TimeDialog.OnTimeListener{
    String time;
    TextView tvTimeAct;
    private final Fragment [] fragments = {new MainFragment(),
            new WorkoutFragment(), new HistoryFragment()};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);
        tvTimeAct=findViewById(R.id.tvTimeAct);
        if(savedInstanceState != null){
            time=savedInstanceState.getString("time");
            tvTimeAct.setText(time);
        }
        ViewPager2 viewpager = findViewById(R.id.view_pager);
        PagerAdapter pagerAdapter = new PagerAdapter(this);
        viewpager.setAdapter(pagerAdapter);

    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("time", time);
    }
    @Override
    public void onTimeListener(String time) {
        this.time=time;
        tvTimeAct.setText(time);
    }
    private class PagerAdapter extends FragmentStateAdapter{
        public PagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragments[position];
        }
        @Override
        public int getItemCount() {
            return 3;
        }
    }
}