package com.atars.mvpexample;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String PREF_NOTES = "PREF_NOTES";
    private static final String KEY_NOTES_JSON_STRING = "KEY_NOTES_JSON_STRING";

    private static DataManager instance;
    private final SharedPreferences notesSharedPref;

    public static DataManager getInstance(Context applicationContext) {
        if (instance == null) {
            instance = new DataManager(applicationContext);
        }
        return instance;
    }

    private DataManager(Context applicationContext) {
        notesSharedPref = applicationContext.getSharedPreferences(PREF_NOTES, Context.MODE_PRIVATE);
    }

    public List<String> getNoteList() {
        return getSavedNoteList();
    }

    public void addNoteToFirstIndexOfList(String note) {
        List<String> savedNoteList = getSavedNoteList();
        savedNoteList.add(0, note);
        String noteJsonString = new Gson().toJson(savedNoteList);

        notesSharedPref
                .edit()
                .putString(KEY_NOTES_JSON_STRING, noteJsonString)
                .apply();
    }

    public void removeNoteFromList(int position) {
        List<String> savedNoteList = getSavedNoteList();
        savedNoteList.remove(position);
        String noteJsonString = new Gson().toJson(savedNoteList);

        notesSharedPref
                .edit()
                .putString(KEY_NOTES_JSON_STRING, noteJsonString)
                .apply();
    }

    private List<String> getSavedNoteList() {
        String notesJsonString = notesSharedPref.getString(KEY_NOTES_JSON_STRING, "");
        if (notesJsonString.isEmpty()) {
            return new ArrayList<>();
        }
        Type collectionType = new TypeToken<List<String>>() {
        }.getType();
        return new Gson().fromJson(notesJsonString, collectionType);
    }

}
