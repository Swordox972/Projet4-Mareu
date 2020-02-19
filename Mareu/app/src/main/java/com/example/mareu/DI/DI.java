package com.example.mareu.DI;

import com.example.mareu.Service.MeetingApiService;
import com.example.mareu.Service.MeetingApiServiceIMPL;

public class DI {

    private static MeetingApiService service = new MeetingApiServiceIMPL();

    public static MeetingApiService getMeetingApiService() {
        return service;
    }

}
