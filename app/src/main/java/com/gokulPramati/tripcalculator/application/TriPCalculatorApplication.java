package com.gokulPramati.tripcalculator.application;

import android.app.Application;
import android.util.Log;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public class TriPCalculatorApplication extends Application {
    private static final String TAG = TriPCalculatorApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("Application",TAG);

    }
}
