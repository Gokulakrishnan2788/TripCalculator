package com.gokulPramati.tripcalculator.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class CommonUtils {
    public static final String TRIP_DATA="trip_data";
    public static final String REPORT_DATA="report_data";
    public static final String MEMBER_DATA="member_data";
    public static final String INFO="INFO:";
    public static final String ERROR="ERROR:";

    /**
     * Show Long Toast
     * @param msg
     * @param context
     */
    public static void showLongToast(String msg, Context context){
        Toast.makeText(context,msg,Toast.LENGTH_LONG).show();

    }

    public static void printInfo(String msg, String title){
        Log.i(title,msg);

    }
    public static void printInfo(String msg){
        Log.i(INFO,msg);

    }
    public static void printError(String msg, String title){
        Log.e(title,msg);

    }
    public static void printError(String msg){
        Log.e(ERROR,msg);

    }

    /**
     * Hide keyboard
     * @param activity
     */
    public static void hideKeyboard(Activity activity) {
        View v = activity.getWindow().getCurrentFocus();
        if (v != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


}
