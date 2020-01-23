package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;

import java.util.ArrayList;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETING = new ArrayList<>();

    static List<Meeting> generateMeetingList() {
        return new ArrayList<>(DUMMY_MEETING);
    }

}
