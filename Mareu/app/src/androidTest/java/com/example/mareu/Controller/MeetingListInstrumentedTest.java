package com.example.mareu.Controller;

import android.os.SystemClock;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.widget.TimePicker;

import com.example.mareu.Model.Meeting;
import com.example.mareu.Model.Participant;
import com.example.mareu.R;
import com.example.mareu.Service.Meetings;
import com.example.mareu.utils.DeleteViewAction;
import com.example.mareu.utils.RecyclerViewMatcher;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.mareu.utils.AtPosition.atPosition;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.core.IsNull.notNullValue;

public class MeetingListInstrumentedTest {

    private MainActivity mainActivity;

    private static int ITEM_COUNT = 0;
    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(
            MainActivity.class);

    @Before
    public void setUp() {
        mainActivity = mainActivityActivityTestRule.getActivity();
        assertThat(mainActivity, notNullValue());
    }


    @Test
    public void myFakeMeetingCreationTest() {
        myMeetingListShouldBeEmpty();


        onView(ViewMatchers.withId(R.id.meeting_fab)).perform(click());

        onView(withId(R.id.durée_edit_text)).perform(typeText("45"));

        closeSoftKeyboard();
        onView(withId(R.id.meeting_subject)).perform(typeText("l"));

        closeSoftKeyboard();

        onView(withId(R.id.participants_button)).perform(click());
        onView(withId(R.id.participant_1)).perform(typeText("P"));
        SystemClock.sleep(500);
        onView(withId(R.id.participant_2)).perform(typeText("PP"));

        closeSoftKeyboard();

        onView(withId(R.id.confirm_participants_button)).perform(click());
        SystemClock.sleep(1000);
        onView(withId(R.id.confirm_button)).perform(click());

        onView(atPosition(0, withId(R.id.meeting_fragment))).check(matches(
                hasChildCount(ITEM_COUNT + 1)));
    }

    @Test
    public void myDeleteMeetingAction() {
        myFakeMeetingCreationTest();

        onView(atPosition(0, withId(R.id.meeting_fragment))).perform(RecyclerViewActions.
                actionOnItemAtPosition(0, new DeleteViewAction()));

        myMeetingListShouldBeEmpty();

    }

    @Test
    public void myMeetingDetailOpenAction() {
        myFakeMeetingCreationTest();

        Intents.init();
        SystemClock.sleep(1000);
        onView(atPosition(0, withId(R.id.meeting_fragment))).perform(RecyclerViewActions.
                actionOnItemAtPosition(0, click()));

        intended(hasComponent(DetailMeetingActivity.class.getName()));


        onView(withId(R.id.meeting_detail)).check(matches(withText("Réunion salle A à 0:00" +
                " d'une durée de 45 minutes au sujet de l avec:")));

    }

    @Test
    public void myMeetingListShouldBeEmpty() {
        onView(atPosition(0, withId(R.id.meeting_fragment))).check(matches(
                hasChildCount(ITEM_COUNT)));

    }

    @Test
    public void myMeetingListFilterWithRoom() {
        List<Meeting> meetingList = Meetings.getInstance().getMeetingList();
        Participant participant = new Participant("Carole");
        Participant participant1 = new Participant("k");

        List<Participant> participantList = new ArrayList<>();
        participantList.add(participant);
        participantList.add(participant1);

        Meeting meeting = new Meeting('D', "12:01", 45,
                "Princesse Peach", participantList);


        Meeting meeting1 = new Meeting('A', "16:10", 20,
                "Lol", participantList);


        meetingList.add(meeting);
        meetingList.add(meeting1);


        onView(withId(R.id.app_bar_search)).perform(click());

        onView(withId(R.id.spinner_search)).perform(click());
        onData(anything()).atPosition(0).perform(click());

        onView(withId(R.id.spinner_search)).check(matches(withSpinnerText("A")));

        onView(withId(R.id.confirm_button_filter_room)).perform(click());

        onView(new RecyclerViewMatcher(R.id.meeting_fragment).atPositionOnView(0,
                R.id.meeting_descriptive)).check(matches(withText
                ("Réunion A - 16:10 - Lol")));

        Meetings.getInstance().getMeetingList().clear();

    }


    @Test
    public void myMeetingListFilterWithHour() {
        Participant participant = new Participant("Carole");
        Participant participant1 = new Participant("k");

        List<Participant> participantList = new ArrayList<>();
        participantList.add(participant);
        participantList.add(participant1);

        Meeting meeting = new Meeting('D', "12:01", 45,
                "Princesse Peach", participantList);

        Meeting meeting1 = new Meeting('A', "16:10", 20,
                "Lol", participantList);

        List<Meeting> meetingList = Meetings.getInstance().getMeetingList();

        meetingList.add(meeting);
        meetingList.add(meeting1);

        assert meetingList.size() == 2;


        onView(withId(R.id.app_bar_search)).perform(click());


        onView(withId(R.id.time_picker_filter_button)).perform(click());


        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(
                PickerActions.setTime(12, 1));
        onView(withText("OK")).perform(click());


        onView(withId(R.id.confirm_button_filter_hour)).perform(click());

        onView(new RecyclerViewMatcher(R.id.meeting_fragment).atPositionOnView(0,
                R.id.meeting_descriptive)).check(matches(withText
                ("Réunion D - 12:01 - Princesse Peach")));

        Meetings.getInstance().getMeetingList().clear();

    }

    @Test
    public void myListShouldRefresh() {
        List<Meeting> meetingList = Meetings.getInstance().getMeetingList();

        Participant participant = new Participant("Carole");
        Participant participant1 = new Participant("k");

        List<Participant> participantList = new ArrayList<>();
        participantList.add(participant);
        participantList.add(participant1);

        Meeting meeting = new Meeting('D', "12:01", 45,
                "Princesse Peach", participantList);

        Meeting meeting1 = new Meeting('J', "04:05", 45,
                "lol", participantList);


        meetingList.add(meeting);
        meetingList.add(meeting1);

        onView(withId(R.id.app_bar_search)).perform(click());
        onView(withId(R.id.spinner_search)).perform(click());
        onData(anything()).atPosition(9).perform(click());

        onView(withId(R.id.spinner_search)).check(matches(withSpinnerText("J")));
        onView(withId(R.id.confirm_button_filter_room)).perform(click());

        onView(new RecyclerViewMatcher(R.id.meeting_fragment).atPositionOnView(0,
                R.id.meeting_descriptive)).check(matches(withText
                ("Réunion J - 4:05 - lol")));

        SystemClock.sleep(1000);
        onView(withId(R.id.refresh_list)).perform(click());

        onView(withId(R.id.refresh_list)).check(doesNotExist());

        onView(new RecyclerViewMatcher(R.id.meeting_fragment).atPositionOnView(0,
                R.id.meeting_descriptive)).check(matches(withText
                ("Réunion D - 12:01 - Princesse Peach")));

        onView(new RecyclerViewMatcher(R.id.meeting_fragment).atPositionOnView(1,
                R.id.meeting_descriptive)).check(matches(withText
                ("Réunion J - 4:05 - lol")));

        meetingList.clear();
    }


}
