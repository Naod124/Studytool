package com.company.studytool.View;

import android.content.Context;
import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.company.studytool.R;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class LogInTest {
    @Rule

    public ActivityTestRule<loginActivity> loginActivityActivityTestRule = new ActivityTestRule<loginActivity>(loginActivity.class);
    private loginActivity activity = null;

    @Before
    public void setUp() throws Exception {
        activity = loginActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLunch() {
        // Context of the app under test.
        View testView = activity.findViewById(R.id.username);
        View testView1 = activity.findViewById(R.id.password);
        View testView2 = activity.findViewById(R.id.loginButton);
        View testView3 = activity.findViewById(R.id.sign_up_textView);
        Assert.assertNotNull(testView);
        Assert.assertNotNull(testView1);
        Assert.assertNotNull(testView2);
        Assert.assertNotNull(testView3);
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }
}