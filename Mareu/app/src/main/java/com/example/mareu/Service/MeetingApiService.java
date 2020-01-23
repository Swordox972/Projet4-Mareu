package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;

import java.util.List;

public interface MeetingApiService {
    List<Meeting> getMeeting();

    void deleteMeeting(Meeting meeting);
}
