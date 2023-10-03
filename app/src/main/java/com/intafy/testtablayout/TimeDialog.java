package com.intafy.testtablayout;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class TimeDialog extends DialogFragment {
    NumberPicker numberPickerHours,numberPickerMinutes,numberPickerSeconds;
    Button btnOk,btnCanceled;
    String time;
    private OnTimeListener timeListener;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.time_dialog, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view!=null) {
            btnOk = view.findViewById(R.id.button_ok);
            btnCanceled = view.findViewById(R.id.button_cancel);
            numberPickerHours = view.findViewById(R.id.numberPickerHours);
            numberPickerMinutes = view.findViewById(R.id.numberPickerMinutes);
            numberPickerSeconds = view.findViewById(R.id.numberPickerHoursSeconds);
            numberPickerHours.setMinValue(0);
            numberPickerHours.setMaxValue(24);
            numberPickerMinutes.setMinValue(0);
            numberPickerMinutes.setMaxValue(60);
            numberPickerSeconds.setMinValue(0);
            numberPickerSeconds.setMaxValue(60);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String setTime = numberPickerHours.getValue() + " ч "
                            + numberPickerMinutes.getValue() + " м "
                            + numberPickerSeconds.getValue() + " с.";
                    timeListener.onTimeListener(setTime);
                    Log.d("MyLog","timeListener.onTimeListener");
                    dismiss();
                }
            });
            btnCanceled.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberPickerHours.setValue(0);
                    numberPickerMinutes.setValue(0);
                    numberPickerSeconds.setValue(0);
                }
            });
        }
    }
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if (context instanceof OnTimeListener){
            timeListener = (OnTimeListener) context;
        }else {
            throw new RuntimeException(context.toString() +
                    "must implement OnTimeListener");
        }
    }
    public interface OnTimeListener {
        void onTimeListener(String time);
    }
}