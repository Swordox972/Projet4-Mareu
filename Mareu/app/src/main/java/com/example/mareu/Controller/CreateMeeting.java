package com.example.mareu.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mareu.DI.DI;
import com.example.mareu.Model.Meeting;
import com.example.mareu.Model.Participant;
import com.example.mareu.R;
import com.example.mareu.Service.MeetingApiService;
import com.example.mareu.Service.Meetings;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//Implements for spinner
public class CreateMeeting extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner mSpinnerRoomName;
    private ImageButton mConfirmButton;
    private EditText mMeetingDuration;
    private EditText mMeetingSubject;
    private Button mTimePickerButton;
    private char mRoomSelected;
    private Button mParticipantsButton;
    private MeetingApiService mApiService;
    boolean empty;
    ArrayList<Participant> meetingParticipantList = new ArrayList<>();
    SimpleDateFormat dateFormat;
    Date dateObj;
    String time = "00:00";
    Meeting mMeeting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        spinnerRoomName();

        myMeetingDuration();

        meetingSubject();


        mTimePickerButton = findViewById(R.id.time_picker_button);
        returnTimeFormat(time);
        mTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerIntent();
            }
        });

        mParticipantsButton = findViewById(R.id.participants_button);
        mParticipantsButton.setText("Vide");
        mParticipantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                participantsActivityIntent();
            }
        });

        confirmButtonClick();

    }

    public void spinnerRoomName() {
        mSpinnerRoomName = findViewById(R.id.my_spinner_room);

        ArrayAdapter<CharSequence> mArrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.room_name, R.layout.support_simple_spinner_dropdown_item);
        mArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        mSpinnerRoomName.setAdapter(mArrayAdapter);
        mSpinnerRoomName.setOnItemSelectedListener(this);

    }


    //For spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        parent.getItemAtPosition(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void timePickerIntent() {
        Intent mTimePickerIntent = new Intent(this, TimePickerActivity.class);

        mTimePickerIntent.putExtra("MEETING_TIME", time);
        startActivityForResult(mTimePickerIntent, 0);

    }


    public void myMeetingDuration() {
        mMeetingDuration = findViewById(R.id.durée_edit_text);
        mMeetingDuration.setHint("Tapez la durée estimée de la réunion");

    }

    public void meetingSubject() {
        mMeetingSubject = findViewById(R.id.meeting_subject);
        mMeetingSubject.setHint("Tapez votre sujet de réunion");

    }

    public void participantsActivityIntent() {
        Intent mParticipantsIntent = new Intent(this, ListParticipantsActivity.class);
        mParticipantsIntent.putParcelableArrayListExtra
                ("ListParticipant", meetingParticipantList);
        startActivityForResult(mParticipantsIntent, 1);
    }


    public void confirmButtonClick() {
        mConfirmButton = findViewById(R.id.confirm_button);

        mApiService = DI.getMeetingApiService();

        mConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mRoomSelected = mSpinnerRoomName.getSelectedItem().toString().charAt(0);

                try {
                    mMeeting = new Meeting(mRoomSelected, time,
                            Integer.parseInt(mMeetingDuration.getText().toString()),
                            mMeetingSubject.getText().toString(), meetingParticipantList);
                } catch (NumberFormatException e) {
                    empty = true;
                    Log.e("Log_durée_empty", "Durée vide");
                }

                if (empty && mMeetingDuration.getText().toString().isEmpty()) {
                    Toast myToast = Toast.makeText(getApplicationContext(), "durée invalide"
                            , Toast.LENGTH_SHORT);

                    myToast.show();
                }

                try {

                    if (mApiService.verifyMeetingHourDisponibility(Meetings.getInstance()
                            .getMeetingList(), mMeeting) && mApiService
                            .verifyMeetingDurationHasValue(mMeeting) &&
                            !mApiService.verifyMeetingTopicIsEmpty(mMeeting)
                            && !meetingParticipantList.isEmpty()) {
                        Meetings.getInstance().getMeetingList().add(mMeeting);
                        finish();
                    } else {
                        Toast myToast = Toast.makeText(getApplicationContext(),
                                "Reunion incorrecte, vérifiez que vous avez rempli les " +
                                        "champs correctement ou que l'heure de début et " +
                                        "l'heure de fin d'une autre réunion ne se confronte pas" +
                                        " à votre réunion en fonction de la salle"
                                , Toast.LENGTH_LONG);
                        myToast.show();
                    }
                } catch (NullPointerException npe) {
                }


            }
        });

    }

    public void returnTimeFormat(String time) {

        try {
            dateFormat = new SimpleDateFormat("H:mm");
            dateObj = dateFormat.parse(time);
            mTimePickerButton.setText(new SimpleDateFormat("H:mm").format(dateObj));
        } catch (final ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                time = data.getStringExtra("MEETING_TIME");
                returnTimeFormat(time);
            } else {
            }
        } else if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                meetingParticipantList = data.getParcelableArrayListExtra("ListParticipant");
                mParticipantsButton.setText("Enregistré");
            } else {
            }
        }

    }


}


