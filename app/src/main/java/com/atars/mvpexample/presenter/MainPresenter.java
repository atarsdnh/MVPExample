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

public class MainPresenter {
    private final MainView mView;
    private final MainModel mModel;

    public MainPresenter(MainView view, MainModel model) {
        mView = view;
        mModel = model;
    }

    public void onCreate() {
        mView.setContentView();
        mView.initNoteRecyclerView();
    }

    public void onSaveButtonClick(String note) {
        if (note.isEmpty()) {
            return;
        }
        mModel.addNoteToList(note);
        mView.clearNoteEditText();
        mView.updateRecyclerView(mModel.getNoteList());
    }

    public void onDeleteButtonClick(int position) {
        mModel.removeNoteFromList(position);
        mView.updateRecyclerView(mModel.getNoteList());
    }
}
