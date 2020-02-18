package com.example.mareu.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Model.Participant;
import com.example.mareu.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DetailMeetingActivity extends AppCompatActivity {
    private TextView reunionDetail;
    Meeting mMeeting;
    ArrayAdapter<Participant> mArrayAdapter;
    private ListView mListView;
    private SimpleDateFormat dateFormat;
    private Date date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_meeting);

        reunionDetail = findViewById(R.id.meeting_detail);

        mMeeting = (Meeting) getIntent().getSerializableExtra("Meeting");

        reunionDetail.setText("Réunion salle " + mMeeting.getMeetingRoom() + " à " +
                returnTimeFormat(mMeeting.getMeetingHour()) + " d'une durée de "+ mMeeting.getMeetingDuration()+
                " minutes "+"au sujet de " + mMeeting.getMeetingTopic() + " avec:");

        mListView = findViewById(R.id.listview_participant);

        mArrayAdapter = new ArrayAdapter<Participant>(this, R.layout.activity_detail_listparticipant,
                R.id.detail_participant_textview, returnParticipantWithEmail(
                mMeeting.getMeetingParticipants()));

        mListView.setAdapter(mArrayAdapter);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private List<Participant> returnParticipantWithEmail(List<Participant> mParticipantList) {
        for (int i = 0; i < mParticipantList.size(); i++) {
            String monParticipant = mParticipantList.get(i).getNomParticipant() + "@lamzone.com";
            mParticipantList.get(i).setNomParticipant(monParticipant);
        }
        return mParticipantList;
    }

    public String returnTimeFormat(String time) {
        try {
            dateFormat = new SimpleDateFormat("H:mm");
            date = dateFormat.parse(time);
        } catch (final ParseException e) {
            e.printStackTrace();
        }

        return new SimpleDateFormat("H:mm").format(date);
    }
}
