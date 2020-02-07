package com.example.mareu.Service;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Model.Participant;

import java.util.ArrayList;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETING = new ArrayList<>();

    static List<Meeting> generateMeetingList() {
        return new ArrayList<>(DUMMY_MEETING);
    }

    public static ArrayList<Participant> DUMMY_PARTICIPANT = new ArrayList<>();

    static List<Participant> generateParticipantList() {
        return new ArrayList<>(DUMMY_PARTICIPANT);
    }

}
