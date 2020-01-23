package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;

import java.util.List;

public class DummyMeetingApiService implements MeetingApiService {
    private List<Meeting> meeting = DummyMeetingGenerator.DUMMY_MEETING;


    @Override
    public List<Meeting> getMeeting() {
        return meeting;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        this.meeting.remove(meeting);
    }
}
