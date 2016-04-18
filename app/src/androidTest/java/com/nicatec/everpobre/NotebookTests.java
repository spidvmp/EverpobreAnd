package com.nicatec.everpobre;

import android.test.AndroidTestCase;

import com.nicatec.everpobre.model.Notebook;

/**
 * Created by vtx on 18/4/16.
 */
public class NotebookTests extends AndroidTestCase {

    public static final String TITULO_NOTEBOOK = "Titulo Notebook";

    public void testCanCreateNotebook() {
        Notebook sut = new Notebook(1, TITULO_NOTEBOOK);
        assertNotNull(sut);
        assertEquals(1, sut.getId());
        assertEquals(TITULO_NOTEBOOK, sut.getName());
    }

    public void test_Creating_A_Notebook_With_Empty_Or_Null_Name_Sets_Default_Name(){
        final Notebook sut = new Notebook(1, null);
        assertEquals(Notebook.DEFAULT_NAME, sut.getName());

        final Notebook sut2 = new Notebook(1, "");
        assertEquals(Notebook.DEFAULT_NAME, sut2.getName());

    }
}
