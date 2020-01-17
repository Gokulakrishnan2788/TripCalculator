package com.gokulPramati.tripcalculator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gokulPramati.tripcalculator.model.Trip;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.CREATE_TABLE_MEMBER_EXPENDITURE;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.CREATE_TABLE_TRIP;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.CREATE_TABLE_TRIP_MEMBER;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.DATABASE_NAME;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.DATABASE_VERSION;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.DATE_TIME_PATTERN;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.DROP_TABLE_IF_EXISTS;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_COMMON_EXPENDITURE;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_CREATED_AT;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_DESCRIPTION;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_ID;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_LOCATION;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_NAME;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.LOG;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.TABLE_MEMBER_EXPENDITURE;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.TABLE_TRIP;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.TABLE_TRIP_MEMBER;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating  tables
        db.execSQL(CREATE_TABLE_TRIP);
        db.execSQL(CREATE_TABLE_TRIP_MEMBER);
        db.execSQL(CREATE_TABLE_MEMBER_EXPENDITURE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL(DROP_TABLE_IF_EXISTS + TABLE_TRIP);
        db.execSQL(DROP_TABLE_IF_EXISTS + TABLE_TRIP_MEMBER);
        db.execSQL(DROP_TABLE_IF_EXISTS + TABLE_MEMBER_EXPENDITURE);
        // create new tables
        onCreate(db);
    }

    /**
     * Add a Trip
     */
    public long addTrip(Trip trip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, trip.getName());
        values.put(KEY_LOCATION, trip.getLocation());
        values.put(KEY_DESCRIPTION, trip.getDescription());
        values.put(KEY_COMMON_EXPENDITURE, trip.getCommonExpenditureAmount());
        values.put(KEY_CREATED_AT, getDateTime());
        // insert row
        return db.insert(TABLE_TRIP, null, values);
    }

    /**
     * Get Trip by Id
     */
    public Trip getTrip(long trip_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_TRIP + " WHERE "
                + KEY_ID + " = " + trip_id;
        Log.e(LOG, selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        Trip.TripBuilder builder= new Trip.TripBuilder().
                id(cursor.getInt(cursor.getColumnIndex(KEY_ID))).
                name(cursor.getString(cursor.getColumnIndex(KEY_NAME))).
                location(cursor.getString(cursor.getColumnIndex(KEY_LOCATION))).
                description(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))).
                commonExpenditure(cursor.getString(cursor.getColumnIndex(KEY_COMMON_EXPENDITURE)));

        return builder.build();
    }

    /**
     * Getting Trip List
     * */
    public List<Trip> getAllTrip() {
        List<Trip> tripList = new ArrayList<Trip>();
        String selectQuery = "SELECT  * FROM " + TABLE_TRIP;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Trip.TripBuilder builder= new Trip.TripBuilder().
                        id(cursor.getInt(cursor.getColumnIndex(KEY_ID))).
                        name(cursor.getString(cursor.getColumnIndex(KEY_NAME))).
                        location(cursor.getString(cursor.getColumnIndex(KEY_LOCATION))).
                        description(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))).
                        commonExpenditure(cursor.getString(cursor.getColumnIndex(KEY_COMMON_EXPENDITURE)));
                Trip trip = builder.build();
                // adding to todo list
                tripList.add(trip);
            } while (cursor.moveToNext());
        }

        return tripList;
    }


    /**
     * getting Trip List count
     */
    public int getTripListCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TRIP;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        // return count
        return count;
    }

    /**
     * Updating a Trip
     */
    public int updateTrip(Trip trip) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, trip.getName());
        values.put(KEY_LOCATION, trip.getLocation());
        values.put(KEY_CREATED_AT, trip.getDescription());
        values.put(KEY_CREATED_AT, trip.getCommonExpenditureAmount());
        // updating row
        return db.update(TABLE_TRIP, values, KEY_ID + " = ?",
                new String[] { String.valueOf(trip.getId()) });
    }

    /**
     * Deleting a Trip
     */
    public void deleteTrip(long trip_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRIP, KEY_ID + " = ?",
                new String[] { String.valueOf(trip_id) });
    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }

    /**
     * get datetime
     * */
    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                DATE_TIME_PATTERN, Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}