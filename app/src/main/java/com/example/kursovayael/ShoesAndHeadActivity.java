package com.example.kursovayael;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kursovayael.algorithmsForObtainingClothingSize.AlgorithmsForObtainingClothingSizeBaby;
import com.example.kursovayael.algorithmsForObtainingClothingSize.AlgorithmsForObtainingClothingSizeWoman;
import com.example.kursovayael.dBHelpers.DbHelperHistoryRequest;
import com.example.kursovayael.entity.RequestHistory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ShoesAndHeadActivity extends AppCompatActivity {
    AlgorithmsForObtainingClothingSizeWoman algorithmsForObtainingClothingSizeWoman = new AlgorithmsForObtainingClothingSizeWoman();

    TextView RU;
    TextView Int;
    TextView EU;
    TextView US;
    TextView UK;
    EditText footLength;
    EditText headCircumference;
    ImageView imageClothingType;
    private String idUser;
    Spinner spinnerAge;

    String typeOfClothing;

    String age;

    private DbHelperHistoryRequest dbHelperHistoryRequest;
    private List<RequestHistory> requestHistories = new ArrayList<>();
    boolean footLengthBool = true;
    boolean headCircumferenceBool = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoes_and_head);

        Bundle arguments = getIntent().getExtras();
        idUser = arguments.get("idUser").toString();

        dbHelperHistoryRequest = new DbHelperHistoryRequest(this);

        RU = findViewById(R.id.textViewRU);
        Int = findViewById(R.id.textViewInt);
        EU = findViewById(R.id.textViewEU);
        US = findViewById(R.id.textViewUS);
        UK = findViewById(R.id.textViewUK);
        spinnerAge = findViewById(R.id.spinnerAge2);
        footLength = findViewById(R.id.footLength);
        headCircumference = findViewById(R.id.headCircumference);
        imageClothingType = findViewById(R.id.imageClothingType2);

        initialization();

        headCircumference.addTextChangedListener(new TextWatcher() {
                 @Override
                 public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                     footLength.getText().clear();
//                     footLength.setText("");
                 }

                 @Override
                 public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                     footLength.setText("");
                 }
                 @Override
                 public void afterTextChanged(Editable editable) {
//                     if (!footLengthBool) {
                     if (!footLength.getText().toString().equals(""))
                         footLength.setText("");
//                         footLengthBool = false;
//                     }else
//                         headCircumferenceBool = true;
                 }
             }
        );


        footLength.addTextChangedListener(new TextWatcher() {
                  @Override
                  public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                      headCircumference.getText().clear();
                  }

                  @Override
                  public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  }

                  @Override
                  public void afterTextChanged(Editable editable) {
//                      System.out.println("headCircumferenceBool"+headCircumferenceBool);
//                      if (!headCircumferenceBool) {
                      if (!headCircumference.getText().toString().equals("")) {
                          headCircumference.setText("");
                      }
//                          headCircumferenceBool = false;
//                      }else
//                          footLengthBool = false;
//                     headCircumference.getText().clear();
                  }
              }
        );

        // Настраиваем адаптер
        ArrayAdapter<?> adapterAge =
                ArrayAdapter.createFromResource(this, R.array.ageShoes,
                        android.R.layout.simple_spinner_item);
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Вызываем адаптер
        spinnerAge.setAdapter(adapterAge);

        spinnerAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.ageShoes);
                age = choose[selectedItemPosition];
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

//        switch (typeOfClothing) {
//            case "Джинсы,Штаны":
//                imageClothingType.setImageResource(R.drawable.jeans);
//                break;
//            case "Платье":
//                imageClothingType.setImageResource(R.drawable.wear);
//                break;
//            case "Рубашка":
//            case "Рубашка,куртки костюмы":
//                imageClothingType.setImageResource(R.drawable.shirt);
//                break;
//            case "Юбка":
//                imageClothingType.setImageResource(R.drawable.skirt);
//                break;
//            default:
//                System.err.println("Неверный тип одежды -> " + typeOfClothing);
//        }
    }

    public void buttonGetResult(View view) {
        int sizeDress = -1;
        int sizeHead = -1;
        if (!headCircumference.getText().toString().equals("")) {
            sizeHead = algorithmsForObtainingClothingSizeWoman.getSizeHead(Integer.parseInt(headCircumference.getText().toString()));
            imageClothingType.setImageResource(R.drawable.ic_head);
            typeOfClothing = "Головной убор";
            fillInHeadCircumference(sizeHead);
        }else{
            imageClothingType.setImageResource(R.drawable.ic_shoes);
            sizeDress = algorithmsForObtainingClothingSizeWoman.getSizeShoes(Double.parseDouble(footLength.getText().toString()));
            typeOfClothing = "Обувь";
            fillInFootLength(sizeDress);
        }

        addHistoryInDb();
    }
    String ageDb;
    String sexDb;

    private Map<Integer, List<String>> tableTypeSizeHeadCircumferenceWoman = algorithmsForObtainingClothingSizeWoman.tableTypeSizeHeadCircumference();
    private Map<Integer, List<String>> tableTypeSizeHeadCircumferenceBaby = AlgorithmsForObtainingClothingSizeBaby.tableTypeSizeHeadCircumference();

    public void fillInHeadCircumference(int sizeUser) {
        switch (age) {
            case "Женский":
                sexDb = "Ж";
                ageDb = "В";
                RU.setText(String.valueOf(sizeUser));
                Int.setText(Objects.requireNonNull(tableTypeSizeHeadCircumferenceWoman.get(sizeUser)).get(0));
                US.setText(Objects.requireNonNull( tableTypeSizeHeadCircumferenceWoman.get(sizeUser)).get(1));
                UK.setText(Objects.requireNonNull( tableTypeSizeHeadCircumferenceWoman.get(sizeUser)).get(1));
                break;
            case "Мужской":
                sexDb = "М";
                ageDb = "В";
                RU.setText(String.valueOf(sizeUser));
                Int.setText(Objects.requireNonNull(tableTypeSizeHeadCircumferenceWoman.get(sizeUser)).get(0));
                US.setText(Objects.requireNonNull( tableTypeSizeHeadCircumferenceWoman.get(sizeUser)).get(1));
                UK.setText(Objects.requireNonNull( tableTypeSizeHeadCircumferenceWoman.get(sizeUser)).get(1));
                break;
            case "Детский":
                sexDb = "--";
                ageDb = "Д";
                RU.setText(String.valueOf(sizeUser));
                Int.setText(Objects.requireNonNull(tableTypeSizeHeadCircumferenceBaby.get(sizeUser)).get(0));
                EU.setText(Objects.requireNonNull( tableTypeSizeHeadCircumferenceBaby.get(sizeUser)).get(1));
                US.setText(Objects.requireNonNull( tableTypeSizeHeadCircumferenceBaby.get(sizeUser)).get(2));
                UK.setText(Objects.requireNonNull( tableTypeSizeHeadCircumferenceBaby.get(sizeUser)).get(3));
                break;
            default:
                break;
        }
    }



    public void fillInFootLength(int sizeUser) {
        switch (age) {
            case "Женский":
                sexDb = "Ж";
                ageDb = "В";
                RU.setText(String.valueOf(sizeUser));
                EU.setText(String.valueOf(Objects.requireNonNull(tableTypeSize.get(sizeUser)).get(0)));
                US.setText(String.valueOf(Objects.requireNonNull(sizeUSWoman.get(sizeUser)).toString()));
                UK.setText(String.valueOf(Objects.requireNonNull(tableTypeSize.get(sizeUser)).get(1)));
                break;
            case "Мужской":
                sexDb = "М";
                ageDb = "В";
                RU.setText(String.valueOf(sizeUser));
                EU.setText(String.valueOf(Objects.requireNonNull(tableTypeSize.get(sizeUser)).get(0)));
                US.setText(String.valueOf(Objects.requireNonNull(sizeUSMan.get(sizeUser)).toString()));
                UK.setText(String.valueOf(Objects.requireNonNull(tableTypeSize.get(sizeUser)).get(1)));
                break;
            case "Детский":
                sexDb = "--";
                ageDb = "Д";
                RU.setText(String.valueOf(sizeUser));
                EU.setText(String.valueOf(Objects.requireNonNull(tableTypeSize.get(sizeUser)).get(0)));
                US.setText(String.valueOf(Objects.requireNonNull(sizeUSBaby.get(sizeUser)).toString()));
                UK.setText(Objects.requireNonNull(tableTypeSize.get(sizeUser)).get(1));
                break;
            default:
                break;
        }
//        addHistoryInDb();
    }

    private Map<Integer, List<Integer>> tableTypeSize;
    double[] arraySizeUSBaby = {1,2,3,4,5,5.5,6,7,8,9,9.5,10,11,11.5,12,13};
    Map<Integer, Double> sizeUSBaby = new HashMap<>();
    Map<Integer, Double> sizeUSMan = new HashMap<>();
    Map<Integer, Double> sizeUSWoman = new HashMap<>();
    double[] arraySizeUSMan = {6,7,8,9,10,11,12,13,14,15,16,17,18};
    double[] arraySizeUSWoman = {5,6,7,8,9,10,11,12,13,14};
    public void initialization() {
        int ru = 15;
        int eu = 16;
        int uk = 0;
        for (int i = 0; i < arraySizeUSBaby.length; i++) {
            sizeUSBaby.put(ru+i,arraySizeUSBaby[i]);
        }

        for (int i = 0; i < arraySizeUSMan.length; i++) {
            sizeUSMan.put(38+i,arraySizeUSMan[i]);
        }

        for (int i = 0; i < arraySizeUSWoman.length; i++) {
            sizeUSWoman.put(35+i,arraySizeUSWoman[i]);
        }
        tableTypeSize = new HashMap<>();

        for (int i = 0; i < 37; i++) {
            tableTypeSize.put(ru, Arrays.asList(eu, uk));
            ru = ru + 1;
            eu = eu + 1;
            uk = uk + 1;
        }
    }
    public void addHistoryInDb() {
        SQLiteDatabase database = dbHelperHistoryRequest.getWritableDatabase();
//        database.execSQL("drop table if exists " + DbHelperHistoryRequest.TABLE_REQUEST_HISTORY);

//        database.execSQL("create table " + DbHelperHistoryRequest.TABLE_REQUEST_HISTORY + "("+ DbHelperHistoryRequest.KEY_ID+ " integer primary key, " +
//                DbHelperHistoryRequest.KEY_USER_ID + " text, "+
//                DbHelperHistoryRequest.KEY_TYPE_OF_CLOTHING +" text, "+
//                DbHelperHistoryRequest.KEY_RU +" text, "+
//                DbHelperHistoryRequest.KEY_INT +" text, "+
//                DbHelperHistoryRequest.KEY_EU +" text, "+
//                DbHelperHistoryRequest.KEY_US +" text, "+
//                DbHelperHistoryRequest.KEY_UK +" text, "+
//                DbHelperHistoryRequest.KEY_AGE +" text, "+
//                DbHelperHistoryRequest.KEY_SEX + " text "
//                + ");"
//        );

        System.out.println("asdasdasda"+idUser);
        String requestGetTeachersSchedules = "SELECT * FROM "+DbHelperHistoryRequest.TABLE_REQUEST_HISTORY+ " WHERE " +
                DbHelperHistoryRequest.KEY_USER_ID + " = '"+idUser+"' ;";

//        Cursor cursor = database.query(DbHelperHistoryRequest.TABLE_REQUEST_HISTORY,null, null, null, null, null, null);
        Cursor cursor = database.rawQuery(requestGetTeachersSchedules,null);

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
                Log.d("mLog", "ID" + cursor.getInt(idIndex) +
                        ", name" + cursor.getString(user_idIndex) +
                        ", login" + cursor.getString(type_of_clothingIndex) +
                        ", password" + cursor.getString(ruIndex)
                );
                requestHistories.add(
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
        RequestHistory requestOur = new RequestHistory(
                idUser,
                typeOfClothing,
                RU.getText().toString(),
                Int.getText().toString(),
                EU.getText().toString(),
                US.getText().toString(),
                UK.getText().toString(),
                ageDb,
                sexDb

        );
        boolean thereIsSimilarRecord = false;
        for (int i = 0; i < requestHistories.size(); i++) {
            System.out.println(
                    requestOur.getUserId() +"  ; "+requestHistories.get(i).getUserId() + " \n" +
                            requestOur.getTypeOfClothing() +"  ; "+requestHistories.get(i).getTypeOfClothing() + " \n" +
                            requestOur.getRu() +"  ; "+requestHistories.get(i).getRu() + " \n"+
                            requestOur.getEu() +"  ; "+requestHistories.get(i).getEu() + " \n"+
                            requestOur.getUs() +"  ; "+requestHistories.get(i).getUs() + " \n"+
                            requestOur.getUk() +"  ; "+requestHistories.get(i).getUk()
            );
            System.out.println("--------------------------");
            if(
                    requestOur.getUserId().equals(requestHistories.get(i).getUserId()) &&
                            requestOur.getTypeOfClothing().equals(requestHistories.get(i).getTypeOfClothing()) &&
                            requestOur.getRu().equals(requestHistories.get(i).getRu()) &&
                            requestOur.getEu().equals(requestHistories.get(i).getEu()) &&
                            requestOur.getUs().equals(requestHistories.get(i).getUs()) &&
                            requestOur.getUk().equals(requestHistories.get(i).getUk()) &&
                            requestOur.getAge().equals(requestHistories.get(i).getAge()) &&
                            requestOur.getSex().equals(requestHistories.get(i).getSex())
            ){
                thereIsSimilarRecord = true;
                break;
            }
        }
        if (!thereIsSimilarRecord){
            ContentValues contentValues = new ContentValues();

            contentValues.put(DbHelperHistoryRequest.KEY_USER_ID, idUser);
            contentValues.put(DbHelperHistoryRequest.KEY_TYPE_OF_CLOTHING, requestOur.getTypeOfClothing());
            contentValues.put(DbHelperHistoryRequest.KEY_RU, requestOur.getRu());
            contentValues.put(DbHelperHistoryRequest.KEY_INT, requestOur.getInt());
            contentValues.put(DbHelperHistoryRequest.KEY_EU, requestOur.getEu());
            contentValues.put(DbHelperHistoryRequest.KEY_US, requestOur.getUs());
            contentValues.put(DbHelperHistoryRequest.KEY_UK, requestOur.getUk());
            contentValues.put(DbHelperHistoryRequest.KEY_AGE, ageDb);
            contentValues.put(DbHelperHistoryRequest.KEY_SEX, sexDb);

            database.insert(DbHelperHistoryRequest.TABLE_REQUEST_HISTORY, null, contentValues);
            Toast.makeText(this, "Данные добавлены в бд.", Toast.LENGTH_SHORT).show();

        }
    }
}