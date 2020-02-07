package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Model.Participant;

import java.util.List;

public interface MeetingApiService {
    List<Meeting> getMeeting();

    void deleteMeeting(Meeting meeting);


    List<Participant> getParticipants();

    void deleteParticipant(Participant participant);
}
