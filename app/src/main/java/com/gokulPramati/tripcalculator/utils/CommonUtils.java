package com.gokulPramati.tripcalculator.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Gokulakrishnan Mani on 2020-01-18.
 */
public class CommonUtils {
    public static final String TRIP_DATA="trip_data";
    public static final String REPORT_DATA="report_data";
    public static final String INFO="INFO:";
    public static final String ERROR="ERROR:";


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
}
