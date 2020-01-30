package com.example.mareu.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Model.Participant;
import com.example.mareu.R;
import com.example.mareu.Service.Meetings;

import java.util.List;

public class DetailMeetingActivity extends AppCompatActivity {
    private TextView reunionDetail;
    private List<Meeting> mMeetingList;
    Meeting mMeeting;
    ArrayAdapter<Participant> mArrayAdapter;
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_meeting);

        reunionDetail = findViewById(R.id.meeting_detail);

        mMeeting = (Meeting) getIntent().getSerializableExtra("Meeting");

        reunionDetail.setText("Reunion salle " + mMeeting.getMeetingRoom() + " à " + mMeeting.getMeetingHour()
                + " au sujet de " + mMeeting.getMeetingTopic() + " avec: ");

        mListView = findViewById(R.id.listview_participant);

        mArrayAdapter = new ArrayAdapter<Participant>(this, R.layout.activity_detail_listparticipant,
                R.id.detail_participant_textview, returnparticipantWithEmail(
                mMeeting.getMeetingParticipants()));

        mListView.setAdapter(mArrayAdapter);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private List<Participant> returnparticipantWithEmail(List<Participant> mParticipantList) {
        for (int i = 0; i < mParticipantList.size(); i++) {
            String monParticipant = mParticipantList.get(i).getNomParticipant() + "@lamzone.com";
            mParticipantList.get(i).setNomParticipant(monParticipant);
        }
        return mParticipantList;
    }
}
