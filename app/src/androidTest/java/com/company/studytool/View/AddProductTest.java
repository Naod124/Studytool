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
public class AddProductTest {
    @Rule

    public ActivityTestRule<AddProductActivity> addProductActivityActivityTestRule = new ActivityTestRule<AddProductActivity>(AddProductActivity.class);
    private AddProductActivity activity = null;

    @Before
    public void setUp() throws Exception {
        activity = addProductActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLunch() {
        // Context of the app under test.
        View testView = activity.findViewById(R.id.ivImg);
        View testView1 = activity.findViewById(R.id.title);
        View testView2 = activity.findViewById(R.id.etTitle);
        View testView3 = activity.findViewById(R.id.email);
        View testView4 = activity.findViewById(R.id.phone);
        Assert.assertNotNull(testView);
        Assert.assertNotNull(testView1);
        Assert.assertNotNull(testView2);
        Assert.assertNotNull(testView3);
        Assert.assertNotNull(testView4);
    }

    @After
    public void tearDown() throws Exception {
        activity = null;
    }
}
