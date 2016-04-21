package com.nicatec.everpobre.model.db.dao;

import android.test.AndroidTestCase;

import com.nicatec.everpobre.model.Notebook;
import com.nicatec.everpobre.model.db.DBHelper;

/**
 * Created by vtx on 20/4/16.
 */
public class NotebookDAOTests extends AndroidTestCase {
    public void testCanInsertNotebook() {
        Notebook notebook = new Notebook(1,"test");
        DBHelper.configure("TestDB.sqlite",getContext());

        NotebookDAO notebookDAO = new NotebookDAO();
        long id = notebookDAO.insert(notebook);
        assertTrue(id > 0);

    }
}
