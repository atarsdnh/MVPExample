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

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new MainPresenter(this, new MainModel());
        presenter.onCreate();
    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initRecyclerView() {
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
        RecyclerView recyclerView = getRecyclerView();
        NotesAdapter adapter = (NotesAdapter) (recyclerView.getAdapter());
        adapter.updateNotes(newNoteList);
    }

    public void onSaveButtonClick(View view) {
        presenter.onSaveButtonClick(getNoteEditText().getText().toString());
    }

    @Override
    public void onDeleteButtonClick(int position) {
        presenter.onDeleteButtonClick(position);
    }

    private RecyclerView getRecyclerView() {
        return (RecyclerView) findViewById(R.id.notes_recycler_view);
    }

    private EditText getNoteEditText() {
        return (EditText) findViewById(R.id.add_note_edit_text);
    }
}
