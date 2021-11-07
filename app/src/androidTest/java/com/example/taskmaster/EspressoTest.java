package com.example.taskmaster;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;
import static androidx.test.espresso.action.ViewActions.typeText;

import androidx.test.espresso.contrib.RecyclerViewActions;


import static androidx.test.espresso.action.ViewActions.click;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;

@RunWith(AndroidJUnit4.class)
public class EspressoTest {


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);
    @Test
    public void taskViewerButtonTest(){
        onView(withId(R.id.TaskViewer)).perform(click());
        onView(withId(R.id.EnteredName)).perform(typeText("Name"), closeSoftKeyboard());
        onView(withId(R.id.SaveButton)).perform(click());
        onView(isRoot()).perform(ViewActions.pressBack());

        onView(withId(R.id.Message)).check(matches(withText("Name's Tasks")));


    }

    @Test
    public void addTaskTest(){


        onView(withId(R.id.Button3)).perform(click());
        onView(withId(R.id.Field1ID)).perform(typeText("title"), closeSoftKeyboard());
        onView(withId(R.id.Field2ID)).perform(typeText("body"), closeSoftKeyboard());
        onView(withId(R.id.Field3ID)).perform(typeText("state"), closeSoftKeyboard());
        onView(withId(R.id.AddToDB)).perform(click());


        onView(isRoot()).perform(ViewActions.pressBack());



        onView(withId(R.id.TaskID)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        onView(withId(R.id.TaskTitle)).check(matches(withText("title")));
        onView(withId(R.id.textView8)).check(matches(withText("body")));
        onView(withId(R.id.textView7)).check(matches(withText("state")));


    }

    @Test
    public void tasksViewerTest() {

        onView(withId(R.id.TaskID)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

    }


}
