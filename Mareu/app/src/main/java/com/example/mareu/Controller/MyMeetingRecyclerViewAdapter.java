package com.example.mareu.Controller;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Model.Participant;
import com.example.mareu.R;
import com.example.mareu.events.DeleteMeetingEvent;
import com.example.mareu.events.OpenMeetingEvent;

import org.greenrobot.eventbus.EventBus;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyMeetingRecyclerViewAdapter extends
        RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    Context context;
    private List<Meeting> mMeetingList;
    private SimpleDateFormat dateFormat;
    private Date date;



    public MyMeetingRecyclerViewAdapter(Context context, List<Meeting> items) {
        this.context = context;
        this.mMeetingList = items;
    }

    private void returnColor() {

    }

    public String returnTimeFormat(String time) {
        try {
            dateFormat = new SimpleDateFormat("H:mm");
            date = dateFormat.parse(time);
        } catch (final ParseException e) {
            e.printStackTrace();
        }

        return new SimpleDateFormat("H:mm").format(date);
    }

    public String participantToString(List<Participant> meetingParticipants) {
        String container = "";

        for (int i = 0; i < meetingParticipants.size(); i++) {
            if (i == meetingParticipants.size() - 1) {
                String container1 = meetingParticipants.get(i).getNomParticipant();
                container += container1 + "@lamzone.com";
            } else {
                String container1 = meetingParticipants.get(i).getNomParticipant();

                container += container1 + "@lamzone.com, ";
            }
        }

        return container;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_meeting, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Meeting meeting = mMeetingList.get(position);
        Random r = new Random();
        int i1 = r.nextInt(10- 0) + 0;

        String[] myColors= context.getResources().getStringArray(R.array.room_color);
         final String finalColor=myColors[i1];
                GradientDrawable gradientDrawable= new GradientDrawable();
                gradientDrawable.mutate();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(Color.parseColor(finalColor));

        holder.mRoomColor.setBackground(gradientDrawable);


        holder.mMeetingDescriptive.setText("Réunion " + meeting.getMeetingRoom() + " - "
                + returnTimeFormat(meeting.getMeetingHour()) + " - " + meeting.getMeetingTopic());

        holder.mMeetingParticipants.setText(participantToString(meeting.getMeetingParticipants()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new OpenMeetingEvent(meeting));

            }
        });

        holder.mDeleteMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));
            }
        });

    }

    @Override
    public int getItemCount() {
        return mMeetingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.room_color)
        public ImageView mRoomColor;
        @BindView(R.id.meeting_descriptive)
        public TextView mMeetingDescriptive;
        @BindView(R.id.meeting_participants)
        public TextView mMeetingParticipants;
        @BindView(R.id.delete_meeting_button)
        public ImageButton mDeleteMeetingButton;

        public ViewHolder(@NonNull View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void updateList(List<Meeting> newMeetingList){
        mMeetingList = newMeetingList;
        notifyDataSetChanged();

    }

    }








