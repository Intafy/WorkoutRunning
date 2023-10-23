package com.intafy.testtablayout.intafy.presentation;

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

import com.intafy.testtablayout.R;

public class ShortTimeDialog extends DialogFragment {
    NumberPicker numberPickerMinutes,numberPickerSeconds,numberPickerMilliSeconds;
    Button btnOk,btnCanceled;
    private ShortTimeDialog.OnShortTimeListener shortTimeListener;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.short_time_dialog, container, false);
    }
    @Override
    public void onStart() {
        super.onStart();
        View view = getView();
        if(view!=null) {
            btnOk = view.findViewById(R.id.button_ok);
            btnCanceled = view.findViewById(R.id.button_cancel);
            numberPickerMinutes = view.findViewById(R.id.numberPickerMinutes);
            numberPickerSeconds = view.findViewById(R.id.numberPickerSeconds);
            numberPickerMilliSeconds = view.findViewById(R.id.numberPickerMilliseconds);
            numberPickerMinutes.setMinValue(0);
            numberPickerMinutes.setMaxValue(59);
            numberPickerSeconds.setMinValue(0);
            numberPickerSeconds.setMaxValue(59);
            numberPickerMilliSeconds.setMaxValue(9);
            numberPickerMilliSeconds.setMinValue(0);
            btnOk.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String setTime;
                    if(numberPickerMinutes.getValue()!=0)
                        setTime = numberPickerMinutes.getValue() + " м "
                            + numberPickerSeconds.getValue() + "."
                            + numberPickerMilliSeconds.getValue() + " с";
                    else setTime = numberPickerSeconds.getValue() + "."
                            + numberPickerMilliSeconds.getValue() + " с";
                    shortTimeListener.onShortTimeListener(setTime);
                    dismiss();
                }
            });
            btnCanceled.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    numberPickerMinutes.setValue(0);
                    numberPickerSeconds.setValue(0);
                    numberPickerMilliSeconds.setValue(0);
                }
            });
        }
    }
    @Override
    public void onAttach(@NonNull Context context){
        super.onAttach(context);
        if (context instanceof ShortTimeDialog.OnShortTimeListener){
            shortTimeListener = (ShortTimeDialog.OnShortTimeListener) context;
        }else {
            throw new RuntimeException(context.toString() +
                    "must implement OnTimeListener");
        }
    }
    public interface OnShortTimeListener {
        void onShortTimeListener(String time);
    }
}
