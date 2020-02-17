package com.example.mareu.Controller;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mareu.DI.DI;
import com.example.mareu.Model.Participant;
import com.example.mareu.R;
import com.example.mareu.Service.MeetingApiService;

import java.util.ArrayList;

public class ListParticipantsActivity extends AppCompatActivity {
    private EditText mParticipant1;
    private EditText mParticipant2;
    private EditText mParticipant3;
    private ImageButton mAddParticipant;
    private ImageButton mConfirmParticipantButton;
    private ArrayList<Participant> listParticipant;
    private int participantCount = 4;
    private ArrayList<EditText> listParticipantAdditional = new ArrayList<>();
    private MeetingApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_participants);
        listParticipant = getIntent().getParcelableArrayListExtra("ListParticipant");

        participantEditText();

        addParticipant();

        mConfirmParticipantButton = findViewById(R.id.confirm_participants_button);

        mConfirmParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                participantList();

            }
        });
    }


    public void confirmParticipantButton() {
        Intent myIntent = new Intent();
        myIntent.putParcelableArrayListExtra("ListParticipant", listParticipant);
        setResult(RESULT_OK, myIntent);
        finish();

    }

    public void addParticipant() {
        mAddParticipant = findViewById(R.id.add_more_participants);
        mAddParticipant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addParticipantClick();
            }
        });

    }

    public void addParticipantClick() {

        final EditText editText = new EditText(this);
        LinearLayout li = findViewById(R.id.linear_layout_participant);
        editText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        editText.setHint("Participant" + participantCount++);
        editText.setPadding(20, 20, 20, 20);
        if (mAddParticipant.getScaleX() == 1.5 && mAddParticipant.getScaleY() == 1.5) {
            editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);
        }
        li.addView(editText);
        listParticipantAdditional.add(editText);

    }

    public void participantList() {
        Participant participant1 = new Participant(mParticipant1.getText().toString());
        Participant participant2 = new Participant(mParticipant2.getText().toString());
        Participant participant3 = new Participant(mParticipant3.getText().toString());

        listParticipant.add(participant1);
        listParticipant.add(participant2);
        listParticipant.add(participant3);

        mApiService = DI.getMeetingApiService();

        if (mApiService.verifyMeetingParticipantsIsEmpty(participant1) ||
                mApiService.verifyMeetingParticipantsIsEmpty(participant2)) {
            listParticipant.remove(participant1);
            listParticipant.remove(participant2);
            listParticipant.remove(participant3);

            Toast myToast = Toast.makeText(getApplicationContext(), "Le participant 1 ou 2 " +
                    "est vide", Toast.LENGTH_SHORT);
            myToast.show();


        } else {

            if (mParticipant3.getText().toString().isEmpty()) {
                listParticipant.remove(participant3);
            }


            for (int i = 0; i < listParticipantAdditional.size(); i++) {
                EditText editText = listParticipantAdditional.get(i);
                Participant participantAdditionnal = new Participant(editText.getText().toString());
                if (!mApiService.verifyMeetingParticipantsIsEmpty(participantAdditionnal)) {
                    listParticipant.add(participantAdditionnal);
                } else {
                    listParticipantAdditional.remove(participantAdditionnal);
                }

            }

            confirmParticipantButton();

        }
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
