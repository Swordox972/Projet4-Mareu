package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Model.Participant;

import java.util.List;

public class MeetingApiServiceIMPL implements MeetingApiService {


    private int convertHourToMinute(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));

        int convertHourToMinute = hour * 60;

        int minute = Integer.parseInt(time.substring(3));

        int finalTimeInMinute = convertHourToMinute + minute;

        return finalTimeInMinute;
    }


    @Override
    public boolean verifyMeetingHourDisponibility(List<Meeting> meetingList, Meeting newMeeting) {

        boolean disponibility = true;

        char newMeetingRoom = newMeeting.getMeetingRoom();

        String meetingHour = newMeeting.getMeetingHour();

        int newMeetingStartTime = convertHourToMinute(meetingHour);

        int meetingDuration = newMeeting.getMeetingDuration();

        int newMeetingEndTime = newMeetingStartTime + meetingDuration;


        for (Meeting oldMeeting : meetingList) {
            char oldMeetingRoom = oldMeeting.getMeetingRoom();

            String oldMeetingHour = oldMeeting.getMeetingHour();

            int oldMeetingStartTime = convertHourToMinute(oldMeetingHour);
            int oldMeetingEndTime = oldMeetingStartTime + oldMeeting.getMeetingDuration();

            if ((oldMeetingRoom == newMeetingRoom && oldMeetingStartTime <= newMeetingStartTime &&
                    newMeetingStartTime <= oldMeetingEndTime) ||
                    (oldMeetingRoom == newMeetingRoom && newMeetingEndTime >= oldMeetingStartTime
                            && newMeetingEndTime <= oldMeetingEndTime)) {
                disponibility = false;
                break;
            }
        }
        return disponibility;
    }

    @Override
    public boolean verifyMeetingDurationHasValue(Meeting meeting) {
        return meeting.getMeetingDuration() > 0;
    }

    @Override
    public boolean verifyMeetingTopicIsEmpty(Meeting meeting) {
        return meeting.getMeetingTopic().isEmpty();

    }

    @Override
    public boolean verifyMeetingParticipantsIsEmpty(Participant participant) {
        return participant.getNomParticipant().isEmpty();

    }
}