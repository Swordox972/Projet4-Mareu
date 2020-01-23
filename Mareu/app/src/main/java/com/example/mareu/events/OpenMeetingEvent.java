package com.example.mareu.events;

import com.example.mareu.Model.Meeting;

public class OpenMeetingEvent {
    /**
     * Meeting to open
     */
    public Meeting meeting;

    /**
     * Constructor.
     *
     * @param meeting
     */
    public OpenMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }
}
