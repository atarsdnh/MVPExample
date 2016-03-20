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
        verify(view).initRecyclerView();
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