package com.example.mareu.DI;

import com.example.mareu.Service.MeetingApiServiceIMPL;
import com.example.mareu.Service.MeetingApiService;

public class DI {

    private static MeetingApiService service = new MeetingApiServiceIMPL();

    public static MeetingApiService getMeetingApiService() {
        return service;
    }

}
