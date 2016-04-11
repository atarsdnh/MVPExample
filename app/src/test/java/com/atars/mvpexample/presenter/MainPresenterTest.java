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

package com.atars.mvpexample.presenter;

import com.atars.mvpexample.model.MainModel;
import com.atars.mvpexample.view.MainView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    private MainView view;
    private MainModel model;
    private MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        view = mock(MainView.class);
        model = mock(MainModel.class);
        presenter = new MainPresenter(view, model);
    }

    @Test
    public void testOnCreate() throws Exception {
        presenter.onCreate();

        verify(view).setContentView();
        verify(view).initNoteRecyclerView();
    }

    @Test
    public void testOnSaveButtonClick_withTextInput() throws Exception {
        String note = "note";

        presenter.onSaveButtonClick(note);

        verify(model).addNoteToList(note);
        verify(view).clearNoteEditText();
        verify(view).updateRecyclerView(model.getNoteList());
    }

    @Test
    public void testOnSaveButtonClick_noTextInput() throws Exception {
        String note = "";

        presenter.onSaveButtonClick(note);

        verify(model, never()).addNoteToList(note);
        verify(view, never()).clearNoteEditText();
        verify(view, never()).updateRecyclerView(model.getNoteList());
    }

    @Test
    public void testOnDeleteButtonClick() throws Exception {
        int position = 0;

        presenter.onDeleteButtonClick(position);

        verify(model).removeNoteFromList(position);
        verify(view).updateRecyclerView(model.getNoteList());
    }
}