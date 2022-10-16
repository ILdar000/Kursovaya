package com.example.kursovayael;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.kursovayael.dBHelpers.DbHelperHistoryRequest;
import com.example.kursovayael.entity.RequestHistory;

import java.util.ArrayList;
import java.util.List;

public class HistoryRequestActivity extends AppCompatActivity {

    ListView countriesList;

    private DbHelperHistoryRequest dbHelperHistoryRequest;
    private List<RequestHistory> clothingSizes = new ArrayList<>();
    private SQLiteDatabase database;
    private int idDeleteHistory;
    private String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_request);

        Bundle arguments = getIntent().getExtras();
        idUser = arguments.get("idUser").toString();
        System.out.println("asdasdasda"+idUser);
        String requestGetTeachersSchedules = "SELECT * FROM "+DbHelperHistoryRequest.TABLE_REQUEST_HISTORY+ " WHERE " +
                DbHelperHistoryRequest.KEY_USER_ID + " = '"+idUser+"' ;";
        dbHelperHistoryRequest = new DbHelperHistoryRequest(this);
        System.out.println(requestGetTeachersSchedules);
        database = dbHelperHistoryRequest.getWritableDatabase();

        Cursor cursor = database.rawQuery(requestGetTeachersSchedules,null);
//        Cursor cursor = database.query(DbHelperHistoryRequest.TABLE_REQUEST_HISTORY,null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DbHelperHistoryRequest.KEY_ID);
            int user_idIndex = cursor.getColumnIndex(DbHelperHistoryRequest.KEY_USER_ID);
            int type_of_clothingIndex = cursor.getColumnIndex(DbHelperHistoryRequest.KEY_TYPE_OF_CLOTHING);
            int ruIndex = cursor.getColumnIndex(DbHelperHistoryRequest.KEY_RU);
            int intIndex = cursor.getColumnIndex(DbHelperHistoryRequest.KEY_INT);
            int euIndex = cursor.getColumnIndex(DbHelperHistoryRequest.KEY_EU);
            int usIndex = cursor.getColumnIndex(DbHelperHistoryRequest.KEY_US);
            int ukIndex = cursor.getColumnIndex(DbHelperHistoryRequest.KEY_UK);
            int ageIndex = cursor.getColumnIndex(DbHelperHistoryRequest.KEY_AGE);
            int sexIndex = cursor.getColumnIndex(DbHelperHistoryRequest.KEY_SEX);
            do {
                System.out.println("aaaaaaaaaaaaaaaaaa "+ idUser);
//                        cursor.getString(user_idIndex)+
//                        cursor.getString(type_of_clothingIndex)+ cursor.getString(ruIndex)+
//                        cursor.getString(intIndex)+ cursor.getString(euIndex)+
//                        cursor.getString(usIndex)+ cursor.getString(ukIndex)+
//                        cursor.getString(ageIndex)+ cursor.getString(sexIndex));
                clothingSizes.add(
                        new RequestHistory(cursor.getInt(idIndex), cursor.getString(user_idIndex),
                                cursor.getString(type_of_clothingIndex), cursor.getString(ruIndex),
                                cursor.getString(intIndex), cursor.getString(euIndex),
                                cursor.getString(usIndex), cursor.getString(ukIndex),
                                cursor.getString(ageIndex), cursor.getString(sexIndex)));
            } while (cursor.moveToNext());
        } else {
            Log.d("mLog", "0 rows");
        }
        cursor.close();

        for (int i = 0; i <clothingSizes.size(); i++) {
            System.out.println(clothingSizes.get(i).getSex());
        }
        // получаем элемент ListView
        countriesList = findViewById(R.id.requestHistoryList);
        // создаем адаптер
        ClothingSizzeAdapter stateAdapter = new ClothingSizzeAdapter(this, R.layout.list_item, clothingSizes);
        // устанавливаем адаптер
        countriesList.setAdapter(stateAdapter);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                RequestHistory selectedState = (RequestHistory)parent.getItemAtPosition(position);
                idDeleteHistory = selectedState.getId();
                selectedState.getId();
            }
        };
        countriesList.setOnItemClickListener(itemListener);
    }

    public void buttonBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("idUser", idUser);
        startActivity(intent);
    }


    public void buttonDeleteEntry(View view) {
        if (idDeleteHistory !=0) {
            database.delete(DbHelperHistoryRequest.TABLE_REQUEST_HISTORY, "_id = ?", new String[]{String.valueOf(idDeleteHistory)});
            finish();
            overridePendingTransition(0, 0);
            startActivity(getIntent());
            overridePendingTransition(0, 0);
        }else {
            Toast.makeText(getApplicationContext(), "Выберите одну из историей!",
                    Toast.LENGTH_SHORT).show();
        }
    }

}