package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;

import java.util.ArrayList;
import java.util.List;

public class MeetingFilterList {

    public static List<Meeting> meetingFilterList(List<Meeting> meetingList, String query) {
        String userInput = query.toLowerCase();

        List<Meeting> newList = new ArrayList<>();
        for (Meeting meeting : meetingList) {
            String meetingRoom = Character.toString(meeting.getMeetingRoom());
            String meetingHour = String.valueOf(meeting.getMeetingHour());
            if (meetingRoom.toLowerCase().contains(userInput) ||
                    meetingHour.contains(userInput)) {
                newList.add(meeting);
            }
        }

        return newList;
    }
}
