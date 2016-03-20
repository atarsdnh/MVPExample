package com.atars.mvpexample.presenter;

import com.atars.mvpexample.model.MainModel;
import com.atars.mvpexample.view.MainView;

public class MainPresenter {
    private final MainView view;
    private final MainModel model;

    public MainPresenter(MainView view, MainModel model) {
        this.view = view;
        this.model = model;
    }

    public void onCreate() {
        view.setContentView();
        view.initRecyclerView();
    }

    public void onSaveButtonClick(String note) {
        if (note.isEmpty()) {
            return;
        }
        model.addNoteToList(note);
        view.clearNoteEditText();
        view.updateRecyclerView(model.getNoteList());
    }

    public void onDeleteButtonClick(int position) {
        model.removeNoteFromList(position);
        view.updateRecyclerView(model.getNoteList());
    }
}
