package com.example.mareu.Controller;

import android.os.SystemClock;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.example.mareu.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNull.notNullValue;

public class MeetingListInstrumentedTest {

    private MainActivity mainActivity;

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
        onView(ViewMatchers.withId(R.id.meeting_fab)).perform(click());
        onView(withId(R.id.durée_edit_text)).perform(typeText("45"));

        closeSoftKeyboard();
        onView(withId(R.id.meeting_subject)).perform(typeText("l"));

        closeSoftKeyboard();

        onView(withId(R.id.participants_button)).perform(click());
        onView(withId(R.id.participant_1)).perform(typeText("P"));
        onView(withId(R.id.participant_2)).perform(typeText("PP"));

        closeSoftKeyboard();

        onView(withId(R.id.confirm_participants_button)).perform(click());
        SystemClock.sleep(2000);
        onView(withId(R.id.confirm_button)).perform(click());

    }

    @Test
    public void myDeleteMeetingAction() {
        myFakeMeetingCreationTest();


        onView(withId(R.id.meeting_fab)).check(matches(isDisplayed()));


    }

    @Test
    public void myMeetingDetailOpenAction() {

    }

    @Test
    public void name() {
    }
}
