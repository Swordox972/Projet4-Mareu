package com.example.mareu.Controller;


import android.app.ListActivity;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.mareu.Model.Participant;
import com.example.mareu.R;

import java.util.ArrayList;

public class ListParticipantsActivity extends ListActivity {
    private EditText mParticipant1;
    private EditText mParticipant2;
    private EditText mParticipant3;
    private ImageButton mAddParticipant;
    private ArrayList<Participant> listParticipant;
    private int participantCount=4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_participants);
        participantEditText();


    }

    public void addParticipant() {

        mAddParticipant= findViewById(R.id.add_more_participants);
        mAddParticipant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             addParticipantClick();
            }
        });
    }

    public void addParticipantClick() {

        EditText editText= new EditText(this);
        LinearLayout li= findViewById(R.id.linear_layout_participant);
        editText.setPadding(20,20,20,20);
        editText.setHint("Nouveau participant");
        editText.setId(Integer.parseInt("@id/participant"+participantCount+1));

    }

    public void participantList() {
        Participant participant1= new Participant(mParticipant1.toString());
        Participant participant2= new Participant(mParticipant2.toString());
        Participant participant3= new Participant(mParticipant3.toString());

        listParticipant = new ArrayList<>();
        listParticipant.add(participant1);
        listParticipant.add(participant2);
        listParticipant.add(participant3);
    }

    public void participantEditText() {
        mParticipant1 = findViewById(R.id.participant_1);
        mParticipant2 = findViewById(R.id.participant_2);
        mParticipant3 = findViewById(R.id.participant_3);

        mParticipant1.setHint("Participant 1");
        mParticipant2.setHint("Participant 2");
        mParticipant3.setHint("Participant 3");

    }


}
