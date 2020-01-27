package com.example.mareu.Controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.mareu.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimePickerActivity extends AppCompatActivity {

    private android.widget.TimePicker mTimePicker;
    private Button mSaveButton;
    SimpleDateFormat dateFormat;
    Date dateObj;


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
        try {
            Intent intent = new Intent();

            int hour, minute;
            String time="";
            String time1;


            if (Build.VERSION.SDK_INT > 23) {
                hour = mTimePicker.getHour();
                minute = mTimePicker.getMinute();
                time = Integer.toString(hour);
                time1 = Integer.toString(minute);


            } else {
                hour = mTimePicker.getCurrentHour();
                minute = mTimePicker.getCurrentMinute();
                time = Integer.toString(hour);
                time1 = Integer.toString(minute);
            }

            time += ":" + time1;
            dateFormat = new SimpleDateFormat("k:mm");
            dateObj= dateFormat.parse(time);
            intent.putExtra("MEETING_TIME",time);
            setResult(RESULT_OK, intent);
        } catch (final ParseException e) {
            e.printStackTrace();
        }


    }
}
