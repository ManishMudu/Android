package com.example.trafficnewsandrules;

import androidx.test.rule.ActivityTestRule;

import com.example.trafficnewsandrules.ui.main.LoginActivity;

import org.junit.Rule;
import org.junit.Test;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class Logintest {
    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void checkLogin() throws Exception
    {
        onView(withId(R.id.logusername))
                .perform(typeText("m"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.logpassword))
                .perform(typeText("m"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.btnlog))
                .perform(click());

    }

}
