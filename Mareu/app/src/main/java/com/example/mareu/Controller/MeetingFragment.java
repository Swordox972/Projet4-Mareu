package com.example.mareu.Controller;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
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

import java.util.List;

import static com.example.mareu.Service.MeetingFilterList.meetingFilterList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MeetingFragment extends Fragment implements SearchView.OnQueryTextListener {

    private RecyclerView mRecyclerView;
    private List<Meeting> mMeetingList;
    private MyMeetingRecyclerViewAdapter myAdapter;


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
        setHasOptionsMenu(true);
        initList();
        return view;
    }

    private void initList() {
        myAdapter = new MyMeetingRecyclerViewAdapter(getActivity(), mMeetingList);

        mMeetingList = Meetings.getInstance().getMeetingList();
        mRecyclerView.setAdapter(myAdapter);

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
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        List<Meeting> myFilteredList = meetingFilterList(mMeetingList, s);


        myAdapter.updateList(myFilteredList);
        return true;

    }


    //Détruit la liste à la rotation de l'écran
    @Override
    public void onDetach() {
        super.onDetach();
        myAdapter.clearList();
    }


}
