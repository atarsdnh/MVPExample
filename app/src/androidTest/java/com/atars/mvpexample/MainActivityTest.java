/*
 * Copyright 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.atars.mvpexample;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void testOnSaveButtonClick_withTextInput() throws Exception {
        enterText("note");
        clickSaveButton();

        onView(withId(R.id.add_note_edit_text))
                .check(matches(withText("")));
        onView(withId(R.id.notes_recycler_view))
                .check(hasItemsCount(1));
    }

    @Test
    public void testOnSaveButtonClick_emptyInput() throws Exception {
        enterText("");
        clickSaveButton();

        onView(withId(R.id.notes_recycler_view))
                .check(hasItemsCount(0));
    }

    @Test
    public void testOnDeleteButtonClick() throws Exception {
        enterText("note");
        clickSaveButton();
        onView(withId(R.id.notes_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(
                        0, MyViewAction.clickChildViewWithId(R.id.item_note_delete_button)));

        onView(withId(R.id.notes_recycler_view))
                .check(hasItemsCount(0));
    }

    private static ViewAssertion hasItemsCount(final int count) {
        return new ViewAssertion() {
            @Override
            public void check(View view, NoMatchingViewException e) {
                if (!(view instanceof RecyclerView)) {
                    throw e;
                }
                RecyclerView rv = (RecyclerView) view;
                Assert.assertEquals(count, rv.getAdapter().getItemCount());
            }
        };
    }

    private void enterText(String text) {
        onView(withId(R.id.add_note_edit_text))
                .perform(typeText(text), closeSoftKeyboard());
    }

    private void clickSaveButton() {
        onView(withId(R.id.save_button)).perform(click());
    }

    public static class MyViewAction {

        public static ViewAction clickChildViewWithId(final int id) {
            return new ViewAction() {
                @Override
                public Matcher<View> getConstraints() {
                    return null;
                }

                @Override
                public String getDescription() {
                    return "Click on a child view with specified id.";
                }

                @Override
                public void perform(UiController uiController, View view) {
                    View v = view.findViewById(id);
                    if (v != null) {
                        v.performClick();
                    }
                }
            };
        }

    }

}
