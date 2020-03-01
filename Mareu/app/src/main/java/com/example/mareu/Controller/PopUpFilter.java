package com.example.mareu.Controller;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.mareu.Model.Meeting;
import com.example.mareu.R;
import com.example.mareu.Service.Meetings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PopUpFilter extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String hourFilterSelected;
    private Spinner myFilterSpinner;
    private Button myTimePickerFilterButton;
    private ImageButton myConfirmFilterRoom;
    private ImageButton myConfirmFilterHour;
    private SimpleDateFormat dateFormat;
    private Date date;
    private MyMeetingRecyclerViewAdapter myAdapter;
    private List<Meeting> meetingList;
    private char roomFilter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up_filter);
        meetingList = Meetings.getInstance().getMeetingList();
        myAdapter = new MyMeetingRecyclerViewAdapter(getApplicationContext(), meetingList);

        myFilterSpinnerMethod();

        myTimePickerFilterButton = findViewById(R.id.time_picker_filter_button);
        myTimePickerFilterButton.setText("Filtrer");
        myTimePickerDialogMethod();

        myConfirmFilterRoom = findViewById(R.id.confirm_button_filter_room);
        myConfirmFilterRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmFilterWithRoom();
                finish();
            }
        });

        myConfirmFilterHour = findViewById(R.id.confirm_button_filter_hour);
        myConfirmFilterHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmFilterWithHour();
                finish();
            }
        });

    }

    private void myFilterSpinnerMethod() {
        myFilterSpinner = findViewById(R.id.spinner_search);

        ArrayAdapter<CharSequence> mArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.room_name, R.layout.support_simple_spinner_dropdown_item);
        mArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        myFilterSpinner.setAdapter(mArrayAdapter);
        myFilterSpinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void confirmFilterWithRoom() {
        Intent intent = new Intent();

        roomFilter = myFilterSpinner.getSelectedItem().toString().charAt(0);
        intent.putExtra("RoomFilter", roomFilter);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void myTimePickerDialogMethod() {


        myTimePickerFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(PopUpFilter.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                if (Build.VERSION.SDK_INT > 23) {
                                    hourFilterSelected = view.getHour() + ":" + view.getMinute();

                                    myTimePickerFilterButton.setText(hourFilterSelected);
                                } else {
                                    hourFilterSelected = view.getCurrentHour() + ":" +
                                            view.getCurrentMinute();
                                }
                            }
                        }, 0, 0, true);

                timePickerDialog.show();
            }
        });
    }

    private void confirmFilterWithHour() {
        String hourFilterFormat = returnTimeFormat(hourFilterSelected);

        Intent intent = new Intent();
        String hourFilter = hourFilterFormat;
        intent.putExtra("HourFilter", hourFilter);
        setResult(RESULT_OK, intent);
        finish();
    }


    public String returnTimeFormat(String time) {
        String finalReturn = "";
        try {
            if (time != null) {
                dateFormat = new SimpleDateFormat("H:mm");
                date = dateFormat.parse(time);
            }
        } catch (final ParseException e) {
            e.printStackTrace();
        }

        if (date != null) {
            return finalReturn = new SimpleDateFormat("H:mm").format(date);
        }

        return finalReturn;
    }
}
