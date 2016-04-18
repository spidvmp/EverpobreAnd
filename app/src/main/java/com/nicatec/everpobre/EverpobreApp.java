package com.nicatec.everpobre;

import android.app.Application;
import android.util.Log;

/**
 * Created by vtx on 18/4/16.
 */
public class EverpobreApp extends Application {

    public static final String TAG = EverpobreApp.class.getName();

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "Hello world");


    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
