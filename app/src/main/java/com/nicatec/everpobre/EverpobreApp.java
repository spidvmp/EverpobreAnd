package com.nicatec.everpobre;

import android.app.Application;
import android.util.Log;

import com.nicatec.everpobre.model.db.DBHelper;

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


    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
