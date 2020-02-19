package com.example.mareu.Model;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Meeting implements Serializable {
    private char meetingRoom;
    private String meetingHour;
    private String meetingTopic;
    private int meetingDuration;
    private List<Participant> meetingParticipants;

    // Constructeur par défault
    public Meeting() {
    }

    //Constructeur avec paramètres
    public Meeting(char meetingRoom, String meetingHour, int meetingDuration, String meetingTopic, List<Participant> meetingParticipants) {
        this.meetingRoom = meetingRoom;
        this.meetingHour = meetingHour;
        this.meetingDuration = meetingDuration;
        this.meetingTopic = meetingTopic;
        this.meetingParticipants = meetingParticipants;
    }

    public char getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(char meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public String getMeetingHour() {
        return meetingHour;
    }

    public void setMeetingHour(String meetingHour) {
        this.meetingHour = meetingHour;
    }

    public int getMeetingDuration() {
        return meetingDuration;
    }

    public void setMeetingDuration(int meetingDuration) {
        this.meetingDuration = meetingDuration;
    }

    public String getMeetingTopic() {
        return meetingTopic;
    }

    public void setMeetingTopic(String meetingTopic) {
        this.meetingTopic = meetingTopic;
    }

    public List<Participant> getMeetingParticipants() {
        return meetingParticipants;
    }

    public void setMeetingParticipants(List<Participant> meetingParticipants) {
        this.meetingParticipants = meetingParticipants;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Meeting meeting = (Meeting) obj;
        return Objects.equals(meetingRoom + meetingHour + meetingTopic + meetingParticipants,
                meeting.meetingRoom + meeting.meetingHour + meeting.meetingTopic + meeting.meetingParticipants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(meetingRoom);
    }
}
