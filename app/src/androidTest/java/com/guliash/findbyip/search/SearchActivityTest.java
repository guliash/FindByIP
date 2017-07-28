package com.guliash.findbyip.search;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.widget.TextView;

import com.guliash.findbyip.R;
import com.guliash.findbyip.Stub;
import com.guliash.findbyip.core.test.ConditionWatcher;
import com.guliash.findbyip.core.test.Instruction;
import com.guliash.findbyip.search.ip.IpSearchFragment;
import com.guliash.findbyip.search.location.model.Location;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class SearchActivityTest {

    @Rule
    public ActivityTestRule<SearchActivity> activityRule = new ActivityTestRule<>(
            SearchActivity.class
    );

    @Test
    public void ipInputHasCorrectHint() {
        onView(withId(R.id.ip)).check(matches(withHint(R.string.ip_input_hint)));
    }

    @Test
    public void ipSearchFragmentShowLocation_showsCorrectlyLocation() throws Throwable {
        final IpSearchFragment fragment = (IpSearchFragment) activityRule.getActivity()
                .getSupportFragmentManager()
                .findFragmentByTag(IpSearchFragment.TAG);

        final Location location = Stub.IP_INFO.location();

        activityRule.runOnUiThread(() -> fragment.showLocation(Stub.IP_INFO.location()));

        onView(withId(R.id.location)).check(
                matches(withText(location.latitude() + "," + location.longitude()))
        );
    }

    @Test
    public void findClick_showsFoundLocation() throws Exception {
        onView(withId(R.id.ip)).perform(typeText("8.8.8.8"));
        onView(withId(R.id.find)).perform(click());

        ConditionWatcher.waitForCondition(new Instruction() {
            @Override
            public String getDescription() {
                return null;
            }

            @Override
            public boolean checkCondition() {
                return !TextUtils.isEmpty((
                        (TextView) activityRule.getActivity().findViewById(R.id.location)).getText()
                );
            }
        });

        onView(withId(R.id.location)).check(matches(withText("37.751,-97.822")));
    }

}
