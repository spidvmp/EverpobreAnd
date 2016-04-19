package com.nicatec.everpobre.model.db.dao;

import android.test.AndroidTestCase;

import com.nicatec.everpobre.model.Notebook;

/**
 * Created by vtx on 20/4/16.
 */
public class NotebookDAOTests extends AndroidTestCase {
    public void testCanInsertNotebook() {
        Notebook notebook = new Notebook(1,"test");

        NotebookDAO notebookDAO = new NotebookDAO(getContext());
        long id = notebookDAO.insert(notebook);
        assertTrue(id > 0);

    }
}
