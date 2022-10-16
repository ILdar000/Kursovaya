package com.example.kursovayael;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kursovayael.dBHelpers.DbHelperUsers;
import com.example.kursovayael.entity.User;

import java.util.ArrayList;
import java.util.List;

public class RegistrationActivity extends AppCompatActivity {

    private TextView name;
    private TextView login;
    private TextView password;
    private DbHelperUsers dbHelperUsers;
    private List<User> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        dbHelperUsers = new DbHelperUsers(this);
    }

    public void registrationButton(View view) {
        SQLiteDatabase database = dbHelperUsers.getWritableDatabase();

        name = (TextView) findViewById(R.id.editTextTextPersonName);
        login = (TextView) findViewById(R.id.editTextTextLogin);
        password = (TextView) findViewById(R.id.editTextTextPassword);

        Cursor cursor = database.query(DbHelperUsers.TABLE_USER, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DbHelperUsers.KEY_ID);
            int nameIndex = cursor.getColumnIndex(DbHelperUsers.KEY_NAME);
            int loginIndex = cursor.getColumnIndex(DbHelperUsers.KEY_LOGIN);
            int passwordIndex = cursor.getColumnIndex(DbHelperUsers.KEY_PASSWORD);
            do {
                Log.d("mLog", "ID" + cursor.getInt(idIndex) +
                        ", name" + cursor.getString(nameIndex) +
                        ", login" + cursor.getString(loginIndex) +
                        ", password" + cursor.getString(passwordIndex)
                );
                users.add(new User(cursor.getInt(idIndex), cursor.getString(nameIndex),
                        cursor.getString(loginIndex), cursor.getString(passwordIndex)));
            } while (cursor.moveToNext());
        } else {
            Log.d("mLog", "0 rows");
        }
        cursor.close();

        boolean thereIsSimilarRecord = false;
        for (int i = 0; i < users.size(); i++) {
            if (login.getText().toString().equals(users.get(i).getLogin()) && password.getText().toString().equals(users.get(i).getPassword())) {
                thereIsSimilarRecord = true;
                Toast.makeText(this, "Пользователь с таким логином уже существует!", Toast.LENGTH_SHORT).show();
            }
        }
        if (!thereIsSimilarRecord) {
            ContentValues contentValues4 = new ContentValues();

            contentValues4.put(DbHelperUsers.KEY_NAME, name.getText().toString());
            contentValues4.put(DbHelperUsers.KEY_LOGIN, login.getText().toString());
            contentValues4.put(DbHelperUsers.KEY_PASSWORD, password.getText().toString());

            database.insert(DbHelperUsers.TABLE_USER, null, contentValues4);

            Toast.makeText(this, "Пользователь создан.", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelButton(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}