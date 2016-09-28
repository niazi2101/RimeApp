package com.example.rafi715.rimeapplication.Calling;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Hamza Khan Niaz on 4/7/2016.
 */
public class DBhandler extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "CallingSystem";
    private static final int DATABASE_VERSION = 1;
    public static final String CALLLOG_TABLE_NAME = "table_callLogs";
    public static final String CALLOG_COLUMN_ID = "_id";
    public static final String CALLOG_COLUMN_CALLEE_NAME = "call_remoteID";
    public static final String CALLOG_COLUMN_DATETIME = "call_datetime";
    public static final String CALLOG_COLUMN_DURATION = "call_duration";
    public static final String CALLOG_COLUMN_ENDCAUSE = "call_endcause";



    public DBhandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

                db.execSQL("CREATE TABLE IF NOT EXISTS " + CALLLOG_TABLE_NAME + "( " +
                        CALLOG_COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                        CALLOG_COLUMN_CALLEE_NAME + " TEXT, " +
                        CALLOG_COLUMN_DATETIME + " DATETIME, " +
                        CALLOG_COLUMN_DURATION + " TEXT, " +
                        CALLOG_COLUMN_ENDCAUSE + " TEXT );");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CALLLOG_TABLE_NAME);
        onCreate(db);
    }

    /*
    To insert a new Person, we use the creatively named insertPerson() method.
    We use the SQLiteOpenHelper method getWritableDatabase() to get an SQLiteDatabase object
    reference to our already created database. The Person details are stored in
    a ContentValues object, with the appropriate column name as key,
    and corresponding data as value. We then call SQLiteDatabase’s insert method
    with the person table name, and the ContentValues object.
    NOTE that we left out the CALLOG_COLUMN_ID column,
    which was specified as a primary key.
    It automatically increments.


     */
    public boolean insertCallLog(DBhandler handle,String name, String dateTime,
                                String duration, String endCause) {
        SQLiteDatabase db = handle.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CALLOG_COLUMN_CALLEE_NAME, name);
        contentValues.put(CALLOG_COLUMN_DATETIME, dateTime);
        contentValues.put(CALLOG_COLUMN_DURATION, duration);
        contentValues.put(CALLOG_COLUMN_ENDCAUSE, endCause);


        db.insert(CALLLOG_TABLE_NAME, null, contentValues);

    return true;
    }

   public boolean updateCallLog(Integer id, String name, String dateTime,
                                String duration, String endCause) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
       contentValues.put(CALLOG_COLUMN_CALLEE_NAME, name);
       contentValues.put(CALLOG_COLUMN_DATETIME, dateTime);
       contentValues.put(CALLOG_COLUMN_DURATION, duration);

       contentValues.put(CALLOG_COLUMN_ENDCAUSE, endCause);


        db.update(CALLLOG_TABLE_NAME, contentValues, CALLOG_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)});
        return true;


    }

    public Cursor getCallLog(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + CALLLOG_TABLE_NAME + " WHERE " +
                CALLOG_COLUMN_ID + "=?", new String[]{Integer.toString(id)});

        return res;
    }

    public Cursor getAllCallLogs() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + CALLLOG_TABLE_NAME, null);
        return res;
    }

    public Integer deleteCallLog(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(CALLLOG_TABLE_NAME,
                CALLOG_COLUMN_ID + " = ? ",
                new String[]{Integer.toString(id)});
    }


}