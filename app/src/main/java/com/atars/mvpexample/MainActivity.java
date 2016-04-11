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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.atars.mvpexample.model.MainModel;
import com.atars.mvpexample.presenter.MainPresenter;
import com.atars.mvpexample.view.MainView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainView, NotesAdapter.DeleteButtonListener {

    private MainPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainPresenter(this, new MainModel());
        mPresenter.onCreate();
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initNoteRecyclerView() {
        NotesAdapter adapter = new NotesAdapter(new ArrayList<String>(), this);

        RecyclerView recyclerView = getRecyclerView();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void clearNoteEditText() {
        getNoteEditText().setText("");
    }

    @Override
    public void updateRecyclerView(ArrayList<String> newNoteList) {
        NotesAdapter adapter = (NotesAdapter) (getRecyclerView().getAdapter());
        adapter.updateNotes(newNoteList);
    }

    public void onSaveButtonClick(View view) {
        mPresenter.onSaveButtonClick(getNoteEditText().getText().toString());
    }

    @Override
    public void onDeleteButtonClick(int position) {
        mPresenter.onDeleteButtonClick(position);
    }

    private RecyclerView getRecyclerView() {
        return (RecyclerView) findViewById(R.id.notes_recycler_view);
    }

    private EditText getNoteEditText() {
        return (EditText) findViewById(R.id.add_note_edit_text);
    }
}
