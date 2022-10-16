package com.example.kursovayael;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TypeOfClothingActivity extends AppCompatActivity {

    private String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_of_clothing);
        Bundle arguments = getIntent().getExtras();
        if (arguments.get("idUser") != null)
            idUser = arguments.get("idUser").toString();
    }

    public void clothesMain(View view) {
        Intent intent = new Intent(this, FindOutSizeOfClothesActivity.class);
        intent.putExtra("idUser", idUser);
        startActivity(intent);
    }

    public void shoesAndHeadClothes(View view) {
        Intent intent = new Intent(this, ShoesAndHeadActivity.class);
        intent.putExtra("idUser", idUser);
        startActivity(intent);
    }
}