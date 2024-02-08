package com.intafy.testtablayout.intafy.presentation;
import android.app.DatePickerDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.intafy.testtablayout.R;
import com.intafy.testtablayout.intafy.presentation.ViewModels.TabViewModel;
import java.util.Calendar;

public class WorkoutFragment extends Fragment {

    Button btnTime,btnDate,btnSave,btnClear;
    TextView tvDate,tvTime;
    EditText edDist;
    Calendar date = Calendar.getInstance();
    TabViewModel tabViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("MyLog","Frag_onCreateView");
        return inflater.inflate(R.layout.fragment_workout,container,false);
    }
    @Override
    public void onStart() {
        Log.d("MyLog","Frag_onStart");
        super.onStart();
    View view = getView();
        if(view!=null){
            btnDate = view.findViewById(R.id.btnDate);
            btnTime = view.findViewById(R.id.btnTime);
            btnSave = view.findViewById(R.id.btnSave);
            btnClear = view.findViewById(R.id.bntClear);
            tvDate = view.findViewById(R.id.tvDate);
            tvTime = view.findViewById(R.id.tvTime);
            edDist = view.findViewById(R.id.edDist);

            tabViewModel=new ViewModelProvider(requireActivity()).get(TabViewModel.class);
            tabViewModel.loadTime().observe(this,new Observer<String>(){
                @Override
                public void onChanged(String s) {
                    tvTime.setText(s);
                }
            });
            tabViewModel.loadDate().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    tvDate.setText(s);
                }
            });
            tabViewModel.loadDist().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    edDist.setText(s);
                }
            });
        }
    btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dist = edDist.getText().toString();
                if (dist.equals("")) tvTime.setText(R.string.enter_Dist);
                else {
                    if (Integer.parseInt(dist)>=1000) {
                        new TimeDialog().show(getChildFragmentManager(), "timeDialog");
                        Log.d("MyLog", "TimeDialog.show");
                    }
                    else new ShortTimeDialog().show(getChildFragmentManager(),"shortTimeDialog");
                }
            }
    });
    btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(getContext(), d,
                        date.get(Calendar.YEAR),
                        date.get(Calendar.MONTH),
                        date.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(edDist.getText()!=null) {
                        tabViewModel.saveDist(edDist.getText().toString());
                        tabViewModel.setWorkout();
                    }
                }
            });
    btnClear.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            edDist.setText("");
            tabViewModel.clearAllValues();
        }
    });
        }

    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            date.set(Calendar.YEAR, year);
            date.set(Calendar.MONTH, month);
            date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String dateOfDay = DateUtils.formatDateTime(getActivity(),
                    date.getTimeInMillis(),
                    DateUtils.FORMAT_NUMERIC_DATE | DateUtils.FORMAT_SHOW_YEAR);
            tabViewModel.saveDate(dateOfDay);
        }
    };
    }
