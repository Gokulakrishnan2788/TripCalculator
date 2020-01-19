package com.gokulPramati.tripcalculator.database;

/**
 * Created by Gokulakrishnan Mani on 2020-01-17.
 */
public class DatabaseConstant {

    //date time pattern
    static final String DATE_TIME_PATTERN="yyyy-MM-dd HH:mm:ss";

    // Logcat tag
    static final String LOG = "DatabaseHelper";

    // Database Version
    static final int DATABASE_VERSION = 1;

    // Database Name
    static final String DATABASE_NAME = "tripmanager";

    // Table Names
    static final String TABLE_TRIP = "trip";
    static final String TABLE_TRIP_MEMBER = "trip_member";
    static final String TABLE_MEMBER_EXPENDITURE = "member_expenditure";

    // Common column names
    static final String KEY_ID = "id";
    static final String KEY_NAME = "name";
    static final String KEY_DESCRIPTION = "description";
    static final String KEY_TRIP_ID = "trip_id";
    static final String KEY_CREATED_AT = "created_at";

    // TABLE_TRIP Table - column nmaes
    static final String KEY_LOCATION = "location";
    static final String KEY_COMMON_EXPENDITURE = "common_expenditure";

    // TABLE_TRIP_MEMBER Table - column names
    static final String KEY_EMAIL = "email";
    static final String KEY_PHONE_NUMBER = "phone_number";
    static final String KEY_INITIAL_CONTRIBUTION = "initial_contribution";

    // TABLE_MEMBER_EXPENDITURE Table - column names
    static final String KEY_MEMBER_ID = "member_id";
    static final String KEY_AMOUNT = "amount";

    //Drop Table
    static final String DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";

    // TRIP table create statement
    static final String CREATE_TABLE_TRIP = "CREATE TABLE "
            + TABLE_TRIP + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME
            + " TEXT," + KEY_LOCATION + " TEXT," + KEY_DESCRIPTION + " TEXT," +
            KEY_COMMON_EXPENDITURE + " TEXT," + KEY_CREATED_AT
            + " DATETIME" + ")";

    // Trip Member table create statement
    static final String CREATE_TABLE_TRIP_MEMBER = "CREATE TABLE " + TABLE_TRIP_MEMBER
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_TRIP_ID + " INTEGER,"
            + KEY_NAME + " TEXT," + KEY_EMAIL + " TEXT," + KEY_PHONE_NUMBER + " TEXT," +
            KEY_INITIAL_CONTRIBUTION + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

    // Member Expenditure table create statement
    static final String CREATE_TABLE_MEMBER_EXPENDITURE = "CREATE TABLE "
            + TABLE_MEMBER_EXPENDITURE + "(" + KEY_ID + " INTEGER PRIMARY KEY,"+ KEY_TRIP_ID + " INTEGER," + KEY_MEMBER_ID + " INTEGER,"
            + KEY_AMOUNT + " TEXT," + KEY_DESCRIPTION + " TEXT,"
            + KEY_CREATED_AT + " DATETIME" + ")";

}
