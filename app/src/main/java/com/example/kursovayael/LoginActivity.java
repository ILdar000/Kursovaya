package com.example.kursovayael;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.kursovayael.dBHelpers.DbHelperUsers;
import com.example.kursovayael.entity.User;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {


    private List<User> users = new ArrayList<>();
    private EditText login;
    private EditText password;

    private DbHelperUsers dbHelperUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dbHelperUsers = new DbHelperUsers(this);
    }

    public void onRegistrationButton(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }


    public void onLoginButton(View view) {
        SQLiteDatabase database = dbHelperUsers.getWritableDatabase();

//        database.execSQL("create table IF NOT EXISTS " + DbHelperUsers.TABLE_USER + " (" + DbHelperUsers.KEY_ID + " integer primary key, " +
//                        DbHelperUsers.KEY_NAME + " text, "+
//                        DbHelperUsers.KEY_LOGIN +" text, "+
//                        DbHelperUsers.KEY_PASSWORD + " text "
//        + ");"
//        );

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
                users.add(new User(cursor.getInt(idIndex),cursor.getString(nameIndex),
                                    cursor.getString(loginIndex),cursor.getString(passwordIndex)));
            } while (cursor.moveToNext());
        }else {
            Log.d("mLog", "0 rows");
        }
        cursor.close();

        login = (EditText) findViewById(R.id.editTextLogin);
        password = (EditText) findViewById(R.id.editTextPassword);
        for (int i = 0; i < users.size(); i++) {
            if (login.getText().toString().equals(users.get(i).getLogin()) && password.getText().toString().equals(users.get(i).getPassword())) {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("user", users.get(i)); //Optional parameters
                intent.putExtra("idUser", users.get(i).getId());
                startActivity(intent);
                Toast.makeText(this, "Вход в аккаунт", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Пользователь не найден.", Toast.LENGTH_SHORT).show();
                System.err.println("Пользователь не найден");
            }
        }
    }
}