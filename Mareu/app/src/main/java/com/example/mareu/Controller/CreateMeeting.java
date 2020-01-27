package com.example.mareu.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Model.Participant;
import com.example.mareu.R;
import com.example.mareu.Service.DummyMeetingGenerator;
import com.example.mareu.Service.MeetingApiService;
import com.example.mareu.Service.Meetings;

import java.util.ArrayList;
import java.util.List;

public class CreateMeeting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner mSpinnerRoomName;
    private ImageButton mConfirmButton;
    private EditText mMeetingSubject;
    private Button mTimePickerButton;
    private char mRoomSelected;
    private Button mParticipantsButton;
    ArrayList<Participant> meetingParticipantList = new ArrayList<Participant>();
    int hour, minute;
    Meeting mMeeting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        spinnerRoomName();

        meetingSubject();

        confirmButtonClick();

        mTimePickerButton = findViewById(R.id.time_picker_button);
        mTimePickerButton.setText("00:00");
        mTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerIntent();
            }
        });

        mParticipantsButton = findViewById(R.id.participants_button);
        mParticipantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                participantsActivityIntent();
            }
        });

    }


    public void participantsActivityIntent() {
        Intent mParticipantsIntent = new Intent(this, ListParticipantsActivity.class);
        mParticipantsIntent.putParcelableArrayListExtra("ListParticipant", meetingParticipantList);
        startActivityForResult(mParticipantsIntent, 1);
    }


    public void meetingSubject() {
        mMeetingSubject = findViewById(R.id.meeting_subject);
        mMeetingSubject.setHint("Tapez votre sujet de réunion");

    }


    public void confirmButtonClick() {
        mConfirmButton = findViewById(R.id.confirm_button);

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRoomSelected = mSpinnerRoomName.getSelectedItem().toString().charAt(0);

                mMeeting = new Meeting(mRoomSelected, hour + ":" + minute,
                        mMeetingSubject.getText().toString(), meetingParticipantList);


                Meetings.getInstance().getMeetingList().add(mMeeting);
                finish();
            }
        });
    }

    public void spinnerRoomName() {
        mSpinnerRoomName = findViewById(R.id.my_spinner_room);

        ArrayAdapter<CharSequence> mArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.room_name, R.layout.support_simple_spinner_dropdown_item);
        mArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        mSpinnerRoomName.setAdapter(mArrayAdapter);
        mSpinnerRoomName.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void timePickerIntent() {
        Intent mTimePickerIntent = new Intent(this, TimePickerActivity.class);

        mTimePickerIntent.putExtra("MEETING_HOUR", hour);
        mTimePickerIntent.putExtra("MEETING_MINUTE", minute);

        startActivityForResult(mTimePickerIntent, 0);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                hour = data.getIntExtra("MEETING_HOUR", 2);
                minute = data.getIntExtra("MEETING_MINUTE", 2);
                mTimePickerButton.setText(hour + ":" + minute);
            } else {
            }
        } else if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                meetingParticipantList = data.getParcelableArrayListExtra("ListParticipant");
            } else {
            }
        }

    }


}


