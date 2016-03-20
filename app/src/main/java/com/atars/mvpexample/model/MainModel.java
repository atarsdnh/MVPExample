package com.atars.mvpexample.model;

import java.util.ArrayList;

public class MainModel {
    private ArrayList<String> noteList = new ArrayList<>();

    public void addNoteToList(String note) {
        noteList.add(0, note);
    }

    public ArrayList<String> getNoteList() {
        return noteList;
    }

    public void removeNoteFromList(int position) {
        noteList.remove(position);
    }
}
