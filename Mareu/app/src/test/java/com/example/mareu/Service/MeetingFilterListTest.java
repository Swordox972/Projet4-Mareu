package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Model.Participant;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.example.mareu.Service.MeetingFilterList.meetingFilterList;
import static com.example.mareu.Service.MeetingRefreshList.refreshMyMeeting;

public class MeetingFilterListTest {

    @Test
    public void myMeetingFilterShouldWorkWithRoom() {
        List<Meeting> meetingList = new ArrayList<>();

        Meeting meeting = new Meeting('A', "12:02", 45,
                "Lol", new ArrayList<Participant>());

        Meeting meeting1 = new Meeting('B', "05:42", 50,
                "Lol", new ArrayList<Participant>());

        meetingList.add(meeting);
        meetingList.add(meeting1);

        String meetingRoom = "A";

        List<Meeting> meetingListFiltered = meetingFilterList(meetingList, meetingRoom);

        assert meetingListFiltered.contains(meeting);
        assert meetingListFiltered.size() == 1;
        assert !meetingListFiltered.contains(meeting1);
    }

    @Test
    public void myMeetingFilterShouldNotWorkWithRoom() {
        List<Meeting> meetingList = new ArrayList<>();

        Meeting meeting = new Meeting('A', "12:02", 45,
                "Lol", new ArrayList<Participant>());

        Meeting meeting1 = new Meeting('B', "05:42", 50,
                "Lol", new ArrayList<Participant>());

        meetingList.add(meeting);
        meetingList.add(meeting1);

        String meetingRoom = "C";

        List<Meeting> meetingListFiltered = meetingFilterList(meetingList, meetingRoom);

        assert !meetingListFiltered.contains(meeting);
        assert !meetingListFiltered.contains(meeting1);
        assert meetingListFiltered.size() == 0;

    }

    @Test
    public void myMeetingFilterShouldWorkWithHour() {
        List<Meeting> meetingList = new ArrayList<>();

        Meeting meeting = new Meeting('A', "12:02", 45,
                "Lol", new ArrayList<Participant>());

        Meeting meeting1 = new Meeting('B', "05:42", 50,
                "Lol", new ArrayList<Participant>());

        meetingList.add(meeting);
        meetingList.add(meeting1);

        String meetingRoom = "12";

        List<Meeting> meetingListFiltered = meetingFilterList(meetingList, meetingRoom);

        assert meetingListFiltered.contains(meeting);
        assert !meetingListFiltered.contains(meeting1);
        assert meetingListFiltered.size() == 1;

    }

    @Test
    public void myMeetingFilterShouldNotWorkWithHour() {
        List<Meeting> meetingList = new ArrayList<>();

        Meeting meeting = new Meeting('A', "12:02", 45,
                "Lol", new ArrayList<Participant>());

        Meeting meeting1 = new Meeting('B', "05:42", 50,
                "Lol", new ArrayList<Participant>());

        meetingList.add(meeting);
        meetingList.add(meeting1);

        String meetingRoom = "3";

        //Method to filter
        List<Meeting> meetingListFiltered = meetingFilterList(meetingList, meetingRoom);

        assert !meetingListFiltered.contains(meeting);
        assert !meetingListFiltered.contains(meeting1);
        assert meetingListFiltered.size() == 0;

    }

    @Test
    public void myRefreshMeetingButton() {
        List<Meeting> meetingList = Meetings.getInstance().getMeetingList();

        Meeting meeting = new Meeting('A', "12:02", 45,
                "Lol", new ArrayList<Participant>());

        Meeting meeting1 = new Meeting('B', "05:42", 50,
                "Lol", new ArrayList<Participant>());

        meetingList.add(meeting);
        meetingList.add(meeting1);

        List<Meeting> saveMyMeetingList = Meetings.getInstance().getSaveMeetingList();
        saveMyMeetingList.addAll(meetingList);

        String meetingRoom = "12";

        List<Meeting> meetingListFiltered = meetingFilterList(meetingList, meetingRoom);

        assert meetingListFiltered.contains(meeting);
        assert meetingListFiltered.size() == 1;

        List<Meeting> refreshedMeetingList = refreshMyMeeting(meetingListFiltered);

        assert refreshedMeetingList.contains(meeting);
        assert refreshedMeetingList.contains(meeting1);
        assert refreshedMeetingList.size() == 2;

        meetingList.clear();

    }
}
