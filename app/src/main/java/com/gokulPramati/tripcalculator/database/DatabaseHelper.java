package com.gokulPramati.tripcalculator.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.gokulPramati.tripcalculator.model.MemberExpenditures;
import com.gokulPramati.tripcalculator.model.Trip;
import com.gokulPramati.tripcalculator.model.TripMember;

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
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_AMOUNT;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_COMMON_EXPENDITURE;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_CREATED_AT;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_DESCRIPTION;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_EMAIL;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_ID;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_INITIAL_CONTRIBUTION;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_LOCATION;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_MEMBER_ID;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_NAME;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_PHONE_NUMBER;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.KEY_TRIP_ID;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.LOG;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.TABLE_MEMBER_EXPENDITURE;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.TABLE_TRIP;
import static com.gokulPramati.tripcalculator.database.DatabaseConstant.TABLE_TRIP_MEMBER;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static DatabaseHelper mInstance = null;

    public static DatabaseHelper getInstance(Context ctx) {
        /**
         * use the application context as suggested by CommonsWare.
         * this will ensure that you dont accidentally leak an Activitys
         * context (see this article for more information:
         * http://android-developers.blogspot.nl/2009/01/avoiding-memory-leaks.html)
         */
        if (mInstance == null) {
            mInstance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return mInstance;
    }
    private DatabaseHelper(Context context) {
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

  /*-----------------------------------------------------------------------------------
       TRIP TABLE MANIPULATIONS
  ---------------------------------------------------------------------------------------*/
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


  /*-----------------------------------------------------------------------------------
        MEMBER TABLE MANIPULATIONS
  ---------------------------------------------------------------------------------------*/


    /**
     * Add a Trip
     */
    public long addMember(TripMember tripMember) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TRIP_ID, tripMember.getTripId());
        values.put(KEY_NAME, tripMember.getName());
        values.put(KEY_EMAIL, tripMember.getEmail());
        values.put(KEY_PHONE_NUMBER, tripMember.getPhoneNumber());
        values.put(KEY_INITIAL_CONTRIBUTION, tripMember.getInitialContribution());
        values.put(KEY_CREATED_AT, getDateTime());
        // insert row
        return db.insert(TABLE_TRIP_MEMBER, null, values);
    }

    /**
     * Get Trip by Id
     */
    public TripMember getMember(long trip_id,long member_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_TRIP + " WHERE "
                + KEY_ID + " = " + member_id + " AND " + KEY_TRIP_ID + " = " + trip_id;
        Log.e(LOG, selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        TripMember.TripMemberBuilder builder= new TripMember.TripMemberBuilder().
                id(cursor.getInt(cursor.getColumnIndex(KEY_ID))).
                tripId(cursor.getInt(cursor.getColumnIndex(KEY_TRIP_ID))).
                name(cursor.getString(cursor.getColumnIndex(KEY_NAME))).
                email(cursor.getString(cursor.getColumnIndex(KEY_EMAIL))).
                phoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PHONE_NUMBER))).
                initialContribution(cursor.getString(cursor.getColumnIndex(KEY_INITIAL_CONTRIBUTION)));
        return builder.build();
    }

    /**
     * Getting Trip List
     * */
    public List<TripMember> getAllMember() {
        List<TripMember> tripMemberList = new ArrayList<TripMember>();
        String selectQuery = "SELECT  * FROM " + TABLE_TRIP_MEMBER;
        Log.e(LOG, selectQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                TripMember.TripMemberBuilder builder= new TripMember.TripMemberBuilder().
                        id(cursor.getInt(cursor.getColumnIndex(KEY_ID))).
                        tripId(cursor.getInt(cursor.getColumnIndex(KEY_TRIP_ID))).
                        name(cursor.getString(cursor.getColumnIndex(KEY_NAME))).
                        email(cursor.getString(cursor.getColumnIndex(KEY_EMAIL))).
                        phoneNumber(cursor.getString(cursor.getColumnIndex(KEY_PHONE_NUMBER))).
                        initialContribution(cursor.getString(cursor.getColumnIndex(KEY_INITIAL_CONTRIBUTION)));
                TripMember tripMember = builder.build();
                // adding to todo list
                tripMemberList.add(tripMember);
            } while (cursor.moveToNext());
        }
        return tripMemberList;
    }


    /**
     * getting Trip List count
     */
    public int getMemberListCount() {
        String countQuery = "SELECT  * FROM " + TABLE_TRIP_MEMBER;
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
    public int updateMember(TripMember tripMember) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_TRIP_ID, tripMember.getTripId());
        values.put(KEY_NAME, tripMember.getName());
        values.put(KEY_EMAIL, tripMember.getEmail());
        values.put(KEY_PHONE_NUMBER, tripMember.getPhoneNumber());
        values.put(KEY_INITIAL_CONTRIBUTION, tripMember.getInitialContribution());
        values.put(KEY_CREATED_AT, getDateTime());
        // updating row
        return db.update(TABLE_TRIP, values, KEY_ID + " = ?",
                new String[] { String.valueOf(tripMember.getId()) });
    }

    /**
     * Deleting a Trip
     */
    public void deleteMember(long trip_id,long member_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TRIP, KEY_ID + " = ?",
                new String[] { String.valueOf(trip_id) });
    }


  /*-----------------------------------------------------------------------------------
        EXPENDITURE TABLE MANIPULATIONS
  ---------------------------------------------------------------------------------------*/


    /**
     * Add a Trip
     */
    public long addExpenditure(MemberExpenditures memberExpenditures) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MEMBER_ID, memberExpenditures.getMemberId());
        values.put(KEY_TRIP_ID, memberExpenditures.getTripId());
        values.put(KEY_DESCRIPTION, memberExpenditures.getDescription());
        values.put(KEY_AMOUNT, memberExpenditures.getAmount());
        values.put(KEY_CREATED_AT, getDateTime());
        // insert row
        return db.insert(TABLE_MEMBER_EXPENDITURE, null, values);
    }

    /**
     * Get Trip by Id
     */
    public MemberExpenditures getExpenditure(long trip_id,long member_id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT  * FROM " + TABLE_TRIP + " WHERE "
                + KEY_TRIP_ID + " = " + trip_id +" AND "+ KEY_MEMBER_ID+ " = " + member_id;
        Log.e(LOG, selectQuery);

        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null)
            cursor.moveToFirst();
        MemberExpenditures.MemberExpenditureBuilder builder= new MemberExpenditures.MemberExpenditureBuilder().
                tripId(cursor.getInt(cursor.getColumnIndex(KEY_TRIP_ID))).
                memberId(cursor.getInt(cursor.getColumnIndex(KEY_MEMBER_ID))).
                description(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))).
                amount(cursor.getString(cursor.getColumnIndex(KEY_AMOUNT)));

        return builder.build();
    }

    /**
     * Getting Trip List
     * */
    public List<MemberExpenditures> getAllExpenditure() {
        List<MemberExpenditures> memberExpendituresList = new ArrayList<MemberExpenditures>();
        String selectQuery = "SELECT  * FROM " + TABLE_MEMBER_EXPENDITURE;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                MemberExpenditures.MemberExpenditureBuilder builder= new MemberExpenditures.MemberExpenditureBuilder().
                        tripId(cursor.getInt(cursor.getColumnIndex(KEY_TRIP_ID))).
                        memberId(cursor.getInt(cursor.getColumnIndex(KEY_MEMBER_ID))).
                        description(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION))).
                        amount(cursor.getString(cursor.getColumnIndex(KEY_AMOUNT)));
                MemberExpenditures memberExpenditures = builder.build();
                // adding to  list
                memberExpendituresList.add(memberExpenditures);
            } while (cursor.moveToNext());
        }

        return memberExpendituresList;
    }


    /**
     * getting Trip List count
     */
    public int getExpenditureListCount() {
        String countQuery = "SELECT  * FROM " + TABLE_MEMBER_EXPENDITURE;
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
    public int updateExpenditure(MemberExpenditures memberExpenditures) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MEMBER_ID, memberExpenditures.getMemberId());
        values.put(KEY_TRIP_ID, memberExpenditures.getTripId());
        values.put(KEY_DESCRIPTION, memberExpenditures.getDescription());
        values.put(KEY_AMOUNT, memberExpenditures.getAmount());
        values.put(KEY_CREATED_AT, getDateTime());
        // updating row
        return db.update(TABLE_TRIP, values, KEY_ID + " = ?",
                new String[] { String.valueOf(memberExpenditures.getMemberId()) });
    }

    /**
     * Deleting a Trip
     */
//    public void deleteExpenditure(long trip_id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_TRIP, KEY_ID + " = ?",
//                new String[] { String.valueOf(trip_id) });
//    }



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