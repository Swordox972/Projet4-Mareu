package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Model.Participant;

import java.util.ArrayList;
import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {
    private List<Meeting> meeting = DummyMeetingGenerator.generateMeetingList();
    private List<Participant> participants= DummyMeetingGenerator.generateParticipantList();

    @Override
    public List<Meeting> getMeeting() {
        return meeting;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        this.meeting.remove(meeting);
    }


    @Override
    public List<Participant> getParticipants() {
        return participants;
    }

    @Override
    public void deleteParticipant(Participant participant) {
    participants.remove(participant);
    }
}