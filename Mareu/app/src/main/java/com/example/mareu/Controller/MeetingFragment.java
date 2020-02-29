package com.example.mareu.Controller;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.mareu.Model.Meeting;
import com.example.mareu.R;
import com.example.mareu.Service.Meetings;
import com.example.mareu.events.DeleteMeetingEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.example.mareu.Service.MeetingFilterList.meetingFilterList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Meeting> mMeetingList;
    private MyMeetingRecyclerViewAdapter myAdapter;
    private char roomFilter;
    private String hourFilter;
    private List<Meeting> meetingFilterList;
    private boolean notFiltered = true;

    public MeetingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        hourFilter = "";
        meetingFilterList = new ArrayList<>();
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
        setHasOptionsMenu(true);
        initList();
        return view;
    }

    private void initList() {
        if (notFiltered) {

            myAdapter = new MyMeetingRecyclerViewAdapter(getActivity(), mMeetingList);

            mMeetingList = Meetings.getInstance().getMeetingList();
            mRecyclerView.setAdapter(myAdapter);
        } else {
            myAdapter = new MyMeetingRecyclerViewAdapter(getActivity(), meetingFilterList);
            mRecyclerView.setAdapter(myAdapter);
        }

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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_filter, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.app_bar_search:
                Intent intent = new Intent(getContext(), PopUpFilter.class);
                intent.putExtra("RoomFilter", roomFilter);
                intent.putExtra("HourFilter", hourFilter);
                startActivityForResult(intent, 0);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //Détruit la liste à la rotation de l'écran
    @Override
    public void onDetach() {
        super.onDetach();
        myAdapter.clearList();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                roomFilter = data.getCharExtra("RoomFilter", 'M');
                String roomFilterString = Character.toString(roomFilter);


                if (roomFilter != 'M') {
                    meetingFilterList = meetingFilterList(mMeetingList, roomFilterString);

                    myAdapter.updateList(meetingFilterList);

                }

                hourFilter = data.getStringExtra("HourFilter");
                if (hourFilter != null) {
                    meetingFilterList = meetingFilterList(mMeetingList, hourFilter);

                    myAdapter.updateList(meetingFilterList);
                }

                notFiltered = false;
            } else {
            }
            notFiltered = true;
        } else {
        }
    }
}
