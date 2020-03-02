package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;

import java.io.Serializable;
import java.util.ArrayList;

public class Meetings implements Serializable {
    private static final Meetings ourInstance = new Meetings();
    private ArrayList<Meeting> mMeetingList = new ArrayList<Meeting>();
    private ArrayList<Meeting> saveMeetingList = new ArrayList<>();

    public static Meetings getInstance() {
        return ourInstance;
    }

    private Meetings() {
    }

    public ArrayList<Meeting> getMeetingList() {
        return this.mMeetingList;

    }

    public ArrayList<Meeting> getSaveMeetingList() {
        return this.saveMeetingList;
    }
}
