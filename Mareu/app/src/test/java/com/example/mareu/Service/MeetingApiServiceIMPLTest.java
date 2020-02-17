package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Model.Participant;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MeetingApiServiceIMPLTest {
    private MeetingApiServiceIMPL mApiService = new MeetingApiServiceIMPL();

    @Test
    public void verifyMeetingHourDisponibilityIfSingle() {
        List<Meeting> meetingList = new ArrayList<>();
        Meeting meeting = new Meeting('A', "15:45", 45,
                "Lol", new ArrayList<Participant>());

        assert mApiService.verifyMeetingHourDisponibility(meetingList, meeting) == true;

    }

    @Test
    public void verifyMeetingHourDisponibility() {
        List<Meeting> meetingList = new ArrayList<>();


        Meeting oldMeeting = new Meeting('A', "15:45", 45,
                "Lol", new ArrayList<Participant>());


        Meeting newMeeting = new Meeting('B', "16:31", 45,
                "Lol", new ArrayList<Participant>());

        Meeting newMeeting1 = new Meeting('B', "15:01", 45,
                "Lol", new ArrayList<Participant>());

        meetingList.add(oldMeeting);

        assert mApiService.verifyMeetingHourDisponibility(meetingList, newMeeting) == true &&
                mApiService.verifyMeetingHourDisponibility(meetingList, newMeeting1)== false;

    }


    @Test
    public void verifyMeetingDurationHasValue() {
        Meeting meeting= new Meeting('E', "00:00", 0,
                "lol", new ArrayList<Participant>());

        Meeting meeting1= new Meeting('E', "00:00", 45,
                "lol", new ArrayList<Participant>());


        assert mApiService.verifyMeetingDurationHasValue(meeting)== false &&
                mApiService.verifyMeetingDurationHasValue(meeting1) == true;


    }

    @Test
    public void verifyMeetingTopicIsEmpty()
     {Meeting meeting = new Meeting('F', "15:00", 30,
             "Lol", new ArrayList<Participant>());

         Meeting meeting1 = new Meeting('F', "15:00", 30,
                 "", new ArrayList<Participant>());

     assert mApiService.verifyMeetingTopicIsEmpty(meeting)== false &&
             mApiService.verifyMeetingTopicIsEmpty(meeting1)== true;
    }

    @Test
    public void verifyMeetingParticipantsIsNull() {
        Participant participant= new Participant("");
        Participant participant1= new Participant("François");

        assert mApiService.verifyMeetingParticipantsIsEmpty(participant)==true &&
                mApiService.verifyMeetingParticipantsIsEmpty(participant1)== false;
    }
}