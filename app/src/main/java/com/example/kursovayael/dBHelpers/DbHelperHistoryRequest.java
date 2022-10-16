package com.example.kursovayael.dBHelpers;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelperHistoryRequest extends SQLiteOpenHelper {

    public static String DATABASE_PATH;
    public static String DATABASE_NAME = "kursovaya";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_REQUEST_HISTORY = "request_history";


    public static final String KEY_ID = "_id";
    public static final String KEY_USER_ID = "user_id";
    public static final String KEY_TYPE_OF_CLOTHING = "type_of_clothing";
    public static final String KEY_RU = "ru";
    public static final String KEY_INT = "int";
    public static final String KEY_EU = "eu";
    public static final String KEY_US = "us";
    public static final String KEY_UK = "uk";
    public static final String KEY_AGE = "age";
    public static final String KEY_SEX = "sex";
    private Context myContext;

    public DbHelperHistoryRequest(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_REQUEST_HISTORY + "("+ KEY_ID+ " integer primary key, " +
                KEY_USER_ID + " text, "+
                KEY_TYPE_OF_CLOTHING +" text, "+
                KEY_RU +" text, "+
                KEY_INT +" text, "+
                KEY_EU +" text, "+
                KEY_US +" text, "+
                KEY_EU +" text, "+
                KEY_AGE +" text, "+
                KEY_SEX + " text "
                + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_REQUEST_HISTORY);
    }
    public SQLiteDatabase open()throws SQLException {
        return SQLiteDatabase.openDatabase(DATABASE_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
