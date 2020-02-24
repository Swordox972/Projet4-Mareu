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

        mApiService.verifyMeetingHourDisponibility(meetingList, meeting);

    }

    @Test

    public void verifyMeetingHourDisponibility() {

        List<Meeting> meetingList = new ArrayList<>();


        Meeting oldMeeting = new Meeting('A', "15:45", 45,
                "Lol", new ArrayList<Participant>());


        Meeting newMeeting = new Meeting('B', "16:30", 45,
                "Lol", new ArrayList<Participant>());

        Meeting newMeeting1 = new Meeting('A', "15:01", 45,
                "Lol", new ArrayList<Participant>());


        Meeting newMeeting2 = new Meeting('A', "16:29", 45,
                "Lol", new ArrayList<Participant>());
        meetingList.add(oldMeeting);

        assert mApiService.verifyMeetingHourDisponibility(meetingList, newMeeting);
        assert !mApiService.verifyMeetingHourDisponibility(meetingList, newMeeting1);
        assert !mApiService.verifyMeetingHourDisponibility(meetingList, newMeeting2);

    }


    @Test
    public void verifyMeetingDurationHasValue() {
        Meeting meeting = new Meeting('E', "00:00", 0,
                "lol", new ArrayList<Participant>());

        Meeting meeting1 = new Meeting('E', "00:00", 45,
                "lol", new ArrayList<Participant>());


        assert !mApiService.verifyMeetingDurationHasValue(meeting) &&
                mApiService.verifyMeetingDurationHasValue(meeting1);


    }

    @Test
    public void verifyMeetingTopicIsEmpty() {
        Meeting meeting = new Meeting('F', "15:00", 30,
                "Lol", new ArrayList<Participant>());

        Meeting meeting1 = new Meeting('F', "15:00", 30,
                "", new ArrayList<Participant>());

        assert !mApiService.verifyMeetingTopicIsEmpty(meeting) &&
                mApiService.verifyMeetingTopicIsEmpty(meeting1);
    }

    @Test
    public void verifyMeetingParticipantsIsNull() {
        Participant participant = new Participant("");
        Participant participant1 = new Participant("François");

        assert mApiService.verifyMeetingParticipantsIsEmpty(participant) &&
                !mApiService.verifyMeetingParticipantsIsEmpty(participant1);
    }
}