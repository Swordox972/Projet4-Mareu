package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;

import java.util.ArrayList;

public class Meetings {
    private static final Meetings ourInstance = new Meetings();
    private ArrayList<Meeting> mMeetingList = new ArrayList<Meeting>();

    public static Meetings getInstance() {
        return ourInstance;
    }

    private Meetings() {
    }

    public ArrayList<Meeting> getMeetingList() {
        return this.mMeetingList;

    }
}
