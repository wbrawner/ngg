package com.wbrawner.numberguess;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by billy on 7/28/2017.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class GameTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void testDefaultGuess() {
        onView(withId(R.id.guessInput)).check(matches(withText("50")));
    }

    @Test
    public void testNumberInput() {
        onView(withId(R.id.guessInput)).check(matches(withText("50")));
        onView(withId(R.id.button_clear)).perform(click());
        onView(withId(R.id.guessInput)).check(matches(withText("")));
        onView(withId(R.id.button_1)).perform(click());
        onView(withId(R.id.button_2)).perform(click());
        onView(withId(R.id.button_3)).perform(click());
        // This shouldn't work, as the input should only allow for 3 numbers max
        onView(withId(R.id.button_4)).perform(click());
        onView(withId(R.id.guessInput)).check(matches(withText("123")));
        onView(withId(R.id.button_clear)).perform(click());
        onView(withId(R.id.guessInput)).check(matches(withText("")));
        onView(withId(R.id.button_4)).perform(click());
        onView(withId(R.id.button_5)).perform(click());
        onView(withId(R.id.button_6)).perform(click());
        onView(withId(R.id.guessInput)).check(matches(withText("456")));
        onView(withId(R.id.button_clear)).perform(click());
        onView(withId(R.id.guessInput)).check(matches(withText("")));
        onView(withId(R.id.button_7)).perform(click());
        onView(withId(R.id.button_8)).perform(click());
        onView(withId(R.id.button_9)).perform(click());
        onView(withId(R.id.guessInput)).check(matches(withText("789")));
        onView(withId(R.id.button_clear)).perform(click());
        onView(withId(R.id.guessInput)).check(matches(withText("")));
        onView(withId(R.id.button_0)).perform(click());
        onView(withId(R.id.guessInput)).check(matches(withText("0")));
        onView(withId(R.id.button_delete)).perform(click());
        onView(withId(R.id.guessInput)).check(matches(withText("")));
    }

    @Test
    public void testReset() {
        onView(withId(R.id.guessInput)).check(matches(withText("50")));
        onView(withId(R.id.guessButton)).perform(click());
        onView(withId(R.id.guessInput)).check(matches(withText("")));
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText(R.string.reset)).perform(click());
        onView(withId(R.id.guessInput)).check(matches(withText("50")));
    }
}
