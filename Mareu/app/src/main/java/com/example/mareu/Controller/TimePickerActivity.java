package com.example.mareu.Controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mareu.R;

public class TimePickerActivity extends AppCompatActivity {

    private android.widget.TimePicker mTimePicker;
    private Button mSaveButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);
        mTimePicker = findViewById(R.id.time_picker);
        mSaveButton = findViewById(R.id.save_button);
        mTimePicker.setIs24HourView(true);


        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ReturnHour();

                finish();
            }
        });
    }

    public void ReturnHour() {
        Intent intent = new Intent();

        int hour, minute;

        if (Build.VERSION.SDK_INT > 23) {
            hour = mTimePicker.getHour();
            minute = mTimePicker.getMinute();

        } else {
            hour = mTimePicker.getCurrentHour();
            minute = mTimePicker.getCurrentMinute();
        }

        intent.putExtra("MEETING_HOUR", hour);
        intent.putExtra("MEETING_MINUTE", minute);
        setResult(RESULT_OK, intent);
    }


}
