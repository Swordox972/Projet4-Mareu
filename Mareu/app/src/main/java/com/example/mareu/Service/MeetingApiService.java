package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Model.Participant;

import java.util.List;

public interface MeetingApiService {

    boolean verifyMeetingHourDisponibility(List<Meeting> meetingList, Meeting meeting);

    boolean verifyMeetingDurationHasValue(Meeting meeting);

    boolean verifyMeetingTopicIsEmpty(Meeting meeting);

    boolean verifyMeetingParticipantsIsEmpty(Participant participant);


}
