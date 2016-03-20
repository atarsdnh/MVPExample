package com.atars.mvpexample.view;

import java.util.ArrayList;

public interface MainView {
    void setContentView();

    void initRecyclerView();

    void clearNoteEditText();

    void updateRecyclerView(ArrayList<String> note);
}
