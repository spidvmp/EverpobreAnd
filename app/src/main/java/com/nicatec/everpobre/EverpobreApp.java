package com.nicatec.everpobre;

import android.app.Application;
import android.util.Log;

import com.nicatec.everpobre.model.Notebook;
import com.nicatec.everpobre.model.db.DBHelper;
import com.nicatec.everpobre.model.db.dao.NotebookDAO;

/**
 * Created by vtx on 18/4/16.
 */
public class EverpobreApp extends Application {

    public static final String TAG = EverpobreApp.class.getName();


    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "Hello world");

        //pongo nombre a la base de datos
        DBHelper.configure(DBHelper.DBNAME, getApplicationContext());

        NotebookDAO notebookDAO = new NotebookDAO();
        for (int i=1; i<=20; i++){
            Notebook notebook = new Notebook(i, "Notebook " + i);
            notebookDAO.insert(notebook);

        }


    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
