package com.example.mareu.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;

import com.example.mareu.Model.Meeting;
import com.example.mareu.R;
import com.example.mareu.events.OpenMeetingEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton mFab;
    private Toolbar mToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolBar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolBar);

        mFab = findViewById(R.id.meeting_fab);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateMeetingIntent();

            }
        });

    }

    protected void CreateMeetingIntent() {
        Intent mIntent = new Intent(this, CreateMeeting.class);
        startActivity(mIntent);
    }

    @Override
    protected void onStart() {
        EventBus.getDefault().register(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe
    public void onOpenMeeting(OpenMeetingEvent event) {
        Meeting meeting = event.meeting;
        if (meeting != null) {
            startActivity(new Intent(this, DetailMeetingActivity.class)
                    .putExtra("Meeting", meeting));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}






