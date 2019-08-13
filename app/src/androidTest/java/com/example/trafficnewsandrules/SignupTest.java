package com.example.trafficnewsandrules;

import androidx.test.rule.ActivityTestRule;

import com.example.trafficnewsandrules.ui.main.LoginActivity;
import com.example.trafficnewsandrules.ui.main.RegisterActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class SignupTest {
    @Rule
    public ActivityTestRule<RegisterActivity> activityTestRule =
            new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void checkSignup() throws Exception
    {
        onView(withId(R.id.regfullname))
                .perform(typeText("mm"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.regemail))
                .perform(typeText("mm"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.regusername))
                .perform(typeText("mmmmm"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.regcontact))
                .perform(typeText("123456"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.regcitizenship))
                .perform(typeText("12345"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.regpassword))
                .perform(typeText("m"))
                .perform(closeSoftKeyboard());


        onView(withId(R.id.btnregister))
                .perform(click());

    }
}
