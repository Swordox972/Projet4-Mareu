package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;

import java.util.List;

public class MeetingRefreshList {

    public static List<Meeting> refreshMyMeeting(List<Meeting> myFilteredMeetingList) {
        myFilteredMeetingList.clear();
        myFilteredMeetingList.addAll(Meetings.getInstance().getSaveMeetingList());
        Meetings.getInstance().getSaveMeetingList().clear();

        return myFilteredMeetingList;
    }
}
