package com.example.mareu.Controller;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputFilter;
import android.text.Layout;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mareu.Model.Meeting;
import com.example.mareu.R;
import com.example.mareu.Service.MeetingApiService;
import com.example.mareu.Service.Meetings;
import com.example.mareu.events.DeleteMeetingEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Meeting> mMeetingList;

    public MeetingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meeting_list, container, false);
        Context context = view.getContext();
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        initList();
        return view;
    }

    private void initList() {
        mMeetingList = Meetings.getInstance().getMeetingList();
        mRecyclerView.setAdapter(new MyMeetingRecyclerViewAdapter(mMeetingList));
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }

    @Override
    public void onResume() {
        super.onResume();
        initList();
    }

    @Subscribe
    public void onDeleteMeeting(DeleteMeetingEvent event) {
        Meetings.getInstance().getMeetingList().remove(event.meeting);
        initList();

    }


}
