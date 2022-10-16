package com.example.kursovayael.dBHelpers;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DbHelperUsers extends SQLiteOpenHelper {

    public static String DATABASE_PATH;
    public static String DATABASE_NAME = "kursovaya";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_USER = "users";


    public static final String KEY_ID = "_id";
    public static final String KEY_NAME = "name";
    public static final String KEY_LOGIN = "login";
    public static final String KEY_PASSWORD = "password";
    private Context myContext;

    public DbHelperUsers(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.myContext = context;
        DATABASE_PATH = context.getFilesDir().getPath() + DATABASE_NAME;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USER + "("+ KEY_ID+ " integer primary key, " +
                KEY_NAME + " text, "+
                KEY_LOGIN +" text, "+
                KEY_PASSWORD + " text "
                + ");"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + TABLE_USER);
    }
    public SQLiteDatabase open()throws SQLException {
        return SQLiteDatabase.openDatabase(DATABASE_PATH, null, SQLiteDatabase.OPEN_READWRITE);
    }
}
