package com.example.kursovayael;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String idUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle arguments = getIntent().getExtras();
        if (arguments.get("idUser") != null)
            idUser = arguments.get("idUser").toString();
    }

    public void buttonFindOutSizeOfClothes(View view) {
        Intent intent = new Intent(this, TypeOfClothingActivity.class);
        intent.putExtra("idUser", idUser);
        startActivity(intent);
    }

    public void buttonRequestHistory(View view) {
        Intent intent = new Intent(this, HistoryRequestActivity.class);
        intent.putExtra("idUser", idUser);
        startActivity(intent);
    }
}