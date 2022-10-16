package com.example.kursovayael;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kursovayael.algorithmsForObtainingClothingSize.AlgorithmsForObtainingClothingSizeBaby;
import com.example.kursovayael.algorithmsForObtainingClothingSize.AlgorithmsForObtainingClothingSizeWoman;
import com.example.kursovayael.algorithmsForObtainingClothingSize.AlgorithmsForObtainingClothingSizeMan;
import com.example.kursovayael.dBHelpers.DbHelperHistoryRequest;
import com.example.kursovayael.dBHelpers.DbHelperHistoryRequest;
import com.example.kursovayael.dBHelpers.DbHelperHistoryRequest;
import com.example.kursovayael.entity.RequestHistory;
import com.example.kursovayael.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FindOutSizeOfClothesActivity extends AppCompatActivity {
    ImageView imageClothingType;
    TextView chestGirth;
    TextView waistGirth;
    TextView hipGirth;
    TextView heightGirth;
    TextView weightGirth;
    int chestSize;
    int waistSize;
    int hipSize;

    Dialog dialog;

    Spinner spinnerSex;
    Spinner spinnerTypeOfClothing;
    Spinner spinnerAge;

    AlgorithmsForObtainingClothingSizeWoman algorithmsForObtainingClothingSizeWoman = new AlgorithmsForObtainingClothingSizeWoman();
    AlgorithmsForObtainingClothingSizeMan algorithmsForObtainingClothingSizeMan = new AlgorithmsForObtainingClothingSizeMan();
    AlgorithmsForObtainingClothingSizeBaby algorithmsForObtainingClothingSizeBaby = new AlgorithmsForObtainingClothingSizeBaby();

    List<String> tableTypeSizeSpinner = new ArrayList<>();
    Map<Integer, List<String>> tableTypeSizeWoman = algorithmsForObtainingClothingSizeWoman.initialization();
    Map<Integer, List<String>> tableTypeSizeMan = algorithmsForObtainingClothingSizeMan.initialization();
    Map<Integer, List<String>> tableTypeSizeBaby = algorithmsForObtainingClothingSizeBaby.initialization();

    TextView RU;
    TextView Int;
    TextView EU;
    TextView US;
    TextView UK;

    String typeOfClothing;
    String sex;
    String age;
    private Integer heightSize;
    private Integer weightSize;

    ArrayAdapter<?> adapterTypeOfClothing;

    private DbHelperHistoryRequest dbHelperHistoryRequest;
    private List<RequestHistory> requestHistories = new ArrayList<>();
    private String idUser;

    Button buttonHowGetSize;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_out_size_of_clothes);

        Bundle arguments = getIntent().getExtras();
        idUser = arguments.get("idUser").toString();

        buttonHowGetSize = findViewById(R.id.buttonHowGetSize);

        dialog = new Dialog(this);
        dbHelperHistoryRequest = new DbHelperHistoryRequest(this);

        RU = findViewById(R.id.textViewRU);
        Int = findViewById(R.id.textViewInt);
        EU = findViewById(R.id.textViewEU);
        US = findViewById(R.id.textViewUS);
        UK = findViewById(R.id.textViewUK);

        chestGirth = findViewById(R.id.chestGirth);
        waistGirth = findViewById(R.id.waistCircumference);
        hipGirth = findViewById(R.id.hipGirth);

        weightGirth = findViewById(R.id.weightGirth);
        heightGirth = findViewById(R.id.heightGirth);

        spinnerSex = findViewById(R.id.spinnerSex);
        spinnerTypeOfClothing = findViewById(R.id.spinnerTypeOfClothing);
        spinnerAge = findViewById(R.id.spinnerAge);
        imageClothingType = findViewById(R.id.imageClothingType);

        // Настраиваем адаптер
        adapterTypeOfClothing =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, tableTypeSizeSpinner);

        adapterTypeOfClothing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Вызываем адаптер
        spinnerTypeOfClothing.setAdapter(adapterTypeOfClothing);

        // Настраиваем адаптер
        ArrayAdapter<?> adapterSex =
                ArrayAdapter.createFromResource(this, R.array.sex,
                        android.R.layout.simple_spinner_item);
        adapterSex.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Вызываем адаптер
        spinnerSex.setAdapter(adapterSex);

        // Настраиваем адаптер
        ArrayAdapter<?> adapterAge =
                ArrayAdapter.createFromResource(this, R.array.age,
                        android.R.layout.simple_spinner_item);
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Вызываем адаптер
        spinnerAge.setAdapter(adapterAge);

        spinnerAge.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.age);
                age = choose[selectedItemPosition];
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinnerSex.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose = getResources().getStringArray(R.array.sex);
                sex = choose[selectedItemPosition];
                tableTypeSizeSpinner = new ArrayList<>();
                if (sex.equals("Мужской")) {
                    tableTypeSizeSpinner = Arrays.asList(getResources().getStringArray(R.array.typeOfClothingMan));
                    adapterTypeOfClothing =
                            ArrayAdapter.createFromResource(parent.getContext(), R.array.typeOfClothingMan, android.R.layout.simple_spinner_item);
                } else {
                    adapterTypeOfClothing =
                            ArrayAdapter.createFromResource(parent.getContext(), R.array.typeOfClothingWoman, android.R.layout.simple_spinner_item);
                }
                adapterTypeOfClothing.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                spinnerTypeOfClothing.setAdapter(adapterTypeOfClothing);
                adapterTypeOfClothing.notifyDataSetChanged();

                getResult(chestGirth, waistGirth, hipGirth,heightGirth,weightGirth);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinnerTypeOfClothing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {

                String[] choose;
                if (sex.equals("Мужской")) {
                    choose = getResources().getStringArray(R.array.typeOfClothingMan);
                } else
                    choose = getResources().getStringArray(R.array.typeOfClothingWoman);
                typeOfClothing = choose[selectedItemPosition];
                switch (typeOfClothing) {
                    case "Джинсы,Штаны":
                        imageClothingType.setImageResource(R.drawable.jeans);
                        break;
                    case "Платье":
                        imageClothingType.setImageResource(R.drawable.wear);
                        break;
                    case "Рубашка":
                    case "Рубашка,куртки костюмы":
                        imageClothingType.setImageResource(R.drawable.shirt);
                        break;
                    case "Юбка":
                        imageClothingType.setImageResource(R.drawable.skirt);
                        break;
                    default:
                        System.err.println("Неверный тип одежды -> " + typeOfClothing);
                }
                getResult(chestGirth, waistGirth, hipGirth,heightGirth,weightGirth);
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        buttonHowGetSize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.setContentView(R.layout.custom_dialog);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                ImageView imageView = dialog.findViewById(R.id.imageViewClose);
                Button button = dialog.findViewById(R.id.buttonOk);

                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                        Toast.makeText(FindOutSizeOfClothesActivity.this,"Окно закрыто",Toast.LENGTH_SHORT).show();
                    }
                });
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });

    }

    public void buttonGetResult(View view) {
        getResult(chestGirth, waistGirth, hipGirth,heightGirth,weightGirth);
    }

    private void getResult(TextView chestGirth, TextView waistGirth, TextView hipGirth, TextView heightGirth, TextView weightGirth) {
        if (!String.valueOf(chestGirth.getText()).equals("") && !String.valueOf(waistGirth.getText()).equals("") && !String.valueOf(hipGirth.getText()).equals("")) {
            int chest = Integer.parseInt(String.valueOf(chestGirth.getText()));
            int waist = Integer.parseInt(String.valueOf(waistGirth.getText()));
            int hip = Integer.parseInt(String.valueOf(hipGirth.getText()));
            int height = 0;
            int weight = 0;
            if (!age.equals("Взрослый")) {
                if (!String.valueOf(heightGirth.getText()).equals("") && !String.valueOf(weightGirth.getText()).equals("")) {
                    height = Integer.parseInt(String.valueOf(heightGirth.getText()));
                    weight = Integer.parseInt(String.valueOf(weightGirth.getText()));
                }else {
                    Toast.makeText(this, "Заполните все для детей!", Toast.LENGTH_SHORT).show();
                }
            }
            if (chest != 0 && waist != 0 && hip != 0) {
                switch (sex) {
                    case "Мужской":
                        if (age.equals("Взрослый")) {
                            setResultMan(chest, waist, hip);
                        } else {
                            if (height!= 0 && weight !=0) {
                                setResultBaby(chest, waist, hip,height,weight);
                            } else
                                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case "Женский":
                        if (age.equals("Взрослый")) {
                            setResultWoman(chest, waist, hip);
                        } else {
                            if (height!= 0 && weight !=0) {
                                setResultBaby(chest, waist, hip,height,weight);
                            } else
                                Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        System.err.println("Неверный тип пола -> " + typeOfClothing);
                }
            }
        } else
            Toast.makeText(this, "Заполните все поля!", Toast.LENGTH_SHORT).show();
    }

    private void setResultBaby(int chest, int waist, int hip, int height, int weight) {
        switch (typeOfClothing) {
            case "Джинсы,Штаны":
            case "Юбка":
                waistSize = algorithmsForObtainingClothingSizeBaby.getSizeBreast(41, waist);
                hipSize = algorithmsForObtainingClothingSizeBaby.getSizeBreast(41, hip);
                heightSize = algorithmsForObtainingClothingSizeBaby.getSizeHeight(50, height);
                weightSize = algorithmsForObtainingClothingSizeBaby.getSizeWeight(3, weight);


                if (waistSize == -1) {
                    Toast.makeText(this, "Не стандарный обхват талии", Toast.LENGTH_SHORT).show();
                }

                if (hipSize == -1) {
                    Toast.makeText(this, "Не стандарный обхват бедра!", Toast.LENGTH_SHORT).show();
                }

                if (heightSize == -1) {
                    Toast.makeText(this, "Не стандарный рост!", Toast.LENGTH_SHORT).show();
                }

                if (weightSize == -1) {
                    Toast.makeText(this, "Не стандарный вес!", Toast.LENGTH_SHORT).show();
                }
                if (waistSize != -1 && hipSize != -1 && heightSize != -1 && weightSize != -1)
                    fillInDimensionsBaby(Math.min(waistSize, Math.min(hipSize, Math.min(heightSize, weightSize))),
                        Math.max(waistSize, Math.max(hipSize, Math.max(heightSize, weightSize))));
                break;
            case "Платье":
            case "Рубашка,куртки костюмы":
                chestSize = algorithmsForObtainingClothingSizeBaby.getSizeBreast(41, chest);
                waistSize = algorithmsForObtainingClothingSizeBaby.getSizeBreast(41, waist);
                hipSize = algorithmsForObtainingClothingSizeBaby.getSizeBreast(41, hip);
                heightSize = algorithmsForObtainingClothingSizeBaby.getSizeHeight(50, height);
                weightSize = algorithmsForObtainingClothingSizeBaby.getSizeWeight(3, weight);

                if (chestSize == -1)
                    Toast.makeText(this, "Не стандарный обхват груди!", Toast.LENGTH_SHORT).show();


                if (waistSize == -1)
                    Toast.makeText(this, "Не стандарный обхват талии!", Toast.LENGTH_SHORT).show();


                if (hipSize == -1)
                    Toast.makeText(this, "Не стандарный обхват бедра!", Toast.LENGTH_SHORT).show();


                if (heightSize == -1)
                    Toast.makeText(this, "Не стандарный рост!", Toast.LENGTH_SHORT).show();


                if (weightSize == -1)
                    Toast.makeText(this, "Не стандарный вес!", Toast.LENGTH_SHORT).show();

                if ( waistSize != -1 && hipSize != -1 && heightSize != -1 && weightSize != -1 && chestSize != -1)
                fillInDimensionsBaby(Math.min(Math.min(Math.min(Math.min(heightSize, weightSize), chestSize), waistSize), hipSize),
                        Math.max(Math.max(Math.max(Math.max(heightSize, weightSize), chestSize), waistSize), hipSize));
                break;
            default:
                System.err.println("Неверный тип одежды -> " + typeOfClothing);
        }
    }


    private void setResultMan(int chest, int waist, int hip) {
        switch (typeOfClothing) {
            case "Джинсы,Штаны":
                waistSize = algorithmsForObtainingClothingSizeMan.getSizeForJeans(70, waist);
                hipSize = algorithmsForObtainingClothingSizeMan.getSizeForJeans(92, hip);

                if (waistSize == -1)
                    Toast.makeText(this, "Не стандарный обхват талии!", Toast.LENGTH_SHORT).show();


                if (hipSize == -1)
                    Toast.makeText(this, "Не стандарный обхват бедра!", Toast.LENGTH_SHORT).show();

                if ( waistSize != -1 && hipSize != -1)
                    fillInDimensionsMan(Math.min(waistSize, hipSize), Math.max(waistSize, hipSize));
                break;
            case "Рубашка,куртки костюмы":
                chestSize = algorithmsForObtainingClothingSizeMan.getSizeOuterwear(88, chest);
                waistSize = algorithmsForObtainingClothingSizeMan.getSizeOuterwear(70, waist);
                hipSize = algorithmsForObtainingClothingSizeMan.getSizeOuterwear(92, hip);

                if (chestSize == -1)
                    Toast.makeText(this, "Не стандарный обхват груди!", Toast.LENGTH_SHORT).show();

                if (waistSize == -1)
                    Toast.makeText(this, "Не стандарный обхват талии!", Toast.LENGTH_SHORT).show();


                if (hipSize == -1)
                    Toast.makeText(this, "Не стандарный обхват бедра!", Toast.LENGTH_SHORT).show();

                if ( waistSize != -1 && hipSize != -1 && chestSize != -1)
                    fillInDimensions(Math.min(Math.min(chestSize, waistSize), hipSize), Math.max(Math.max(chestSize, waistSize), hipSize));
                break;
            default:
                System.err.println("Неверный тип одежды у мужчин-> " + typeOfClothing);
        }
    }


    private void setResultWoman(int chest, int waist, int hip) {
        switch (typeOfClothing) {
            case "Джинсы,Штаны":
                waistSize = algorithmsForObtainingClothingSizeWoman.getSizeForJeans(89, waist);
                hipSize = algorithmsForObtainingClothingSizeWoman.getSizeForJeans(58, hip);

                if (waistSize == -1)
                    Toast.makeText(this, "Не стандарный обхват талии!", Toast.LENGTH_SHORT).show();

                if (hipSize == -1)
                    Toast.makeText(this, "Не стандарный обхват бедра!", Toast.LENGTH_SHORT).show();

                if (waistSize != -1 && hipSize != -1)
                    fillInDimensions(Math.min(waistSize, hipSize), Math.max(waistSize, hipSize));
                break;
            case "Платье":
                chestSize = algorithmsForObtainingClothingSizeWoman.getSizeForDresses(58, chest);
                waistSize = algorithmsForObtainingClothingSizeWoman.getSizeForDresses(78, waist);
                hipSize = algorithmsForObtainingClothingSizeWoman.getSizeForDresses(84, hip);

                if (chestSize == -1)
                    Toast.makeText(this, "Не стандарный обхват груди!", Toast.LENGTH_SHORT).show();

                if (waistSize == -1)
                    Toast.makeText(this, "Не стандарный обхват талии!", Toast.LENGTH_SHORT).show();


                if (hipSize == -1)
                    Toast.makeText(this, "Не стандарный обхват бедра!", Toast.LENGTH_SHORT).show();

                if ( waistSize != -1 && hipSize != -1 && chestSize != -1)
                    fillInDimensions(Math.min(Math.min(chestSize, waistSize), hipSize), Math.max(Math.max(chestSize, waistSize), hipSize));
                break;
            case "Рубашка":
                chestSize = algorithmsForObtainingClothingSizeWoman.getSizeForShirts(76, chest);
                waistSize = algorithmsForObtainingClothingSizeWoman.getSizeForShirts(58, waist);
                hipSize = algorithmsForObtainingClothingSizeWoman.getSizeForShirts(60, hip);

                if (chestSize == -1)
                    Toast.makeText(this, "Не стандарный обхват груди!", Toast.LENGTH_SHORT).show();

                if (waistSize == -1)
                    Toast.makeText(this, "Не стандарный обхват талии!", Toast.LENGTH_SHORT).show();


                if (hipSize == -1)
                    Toast.makeText(this, "Не стандарный обхват бедра!", Toast.LENGTH_SHORT).show();

                if ( waistSize != -1 && hipSize != -1 && chestSize != -1)
                    fillInDimensions(Math.min(Math.min(chestSize, waistSize), hipSize), Math.max(Math.max(chestSize, waistSize), hipSize));
                break;
            case "Юбка":
                waistSize = algorithmsForObtainingClothingSizeWoman.getSizeForSkirts(60, waist);
                hipSize = algorithmsForObtainingClothingSizeWoman.getSizeForSkirts(84, hip);

                if (waistSize == -1)
                    Toast.makeText(this, "Не стандарный обхват талии", Toast.LENGTH_SHORT).show();


                if (hipSize == -1)
                    Toast.makeText(this, "Не стандарный обхват бедра!", Toast.LENGTH_SHORT).show();

                if (waistSize != -1 && hipSize != -1)
                    fillInDimensions(Math.min(waistSize, hipSize), Math.max(waistSize, hipSize));
                break;
            default:
                System.err.println("Неверный тип одежды у женщин-> " + typeOfClothing);
        }
    }

    public void fillInDimensionsMan(int sizeUserMin, int sizeUserMax) {
        if (sizeUserMin == sizeUserMax) {
            RU.setText(String.valueOf(sizeUserMax));
            Int.setText(Objects.requireNonNull(tableTypeSizeMan.get(sizeUserMax)).get(0));
            EU.setText(Objects.requireNonNull(tableTypeSizeMan.get(sizeUserMax)).get(1));
            US.setText(Objects.requireNonNull(tableTypeSizeMan.get(sizeUserMax)).get(2));
            UK.setText(Objects.requireNonNull(tableTypeSizeMan.get(sizeUserMax)).get(3));
        } else {
            RU.setText(String.valueOf(sizeUserMin + "-" + sizeUserMax));
            Int.setText(String.valueOf(Objects.requireNonNull(tableTypeSizeMan.get(sizeUserMin)).get(0) + "-" +
                    Objects.requireNonNull(tableTypeSizeMan.get(sizeUserMax)).get(0)));
            EU.setText(String.valueOf(tableTypeSizeMan.get(sizeUserMin).get(1) + "-" +
                    tableTypeSizeMan.get(sizeUserMax).get(1)));
            US.setText(String.valueOf(tableTypeSizeMan.get(sizeUserMin).get(2) + "-" +
                    tableTypeSizeMan.get(sizeUserMax).get(2)));
            UK.setText(String.valueOf(tableTypeSizeMan.get(sizeUserMin).get(3) + "-" +
                    tableTypeSizeMan.get(sizeUserMax).get(3)));
        }
        addHistoryInDb();
    }

    public void fillInDimensionsBaby(int sizeUserMin, int sizeUserMax) {
        if (sizeUserMin == sizeUserMax) {
            RU.setText(String.valueOf(sizeUserMax));
            Int.setText(Objects.requireNonNull(tableTypeSizeBaby.get(sizeUserMax)).get(0));
            EU.setText(Objects.requireNonNull(tableTypeSizeBaby.get(sizeUserMax)).get(1));
            US.setText(Objects.requireNonNull(tableTypeSizeBaby.get(sizeUserMax)).get(2));
            UK.setText(Objects.requireNonNull(tableTypeSizeBaby.get(sizeUserMax)).get(3));
        } else {
            RU.setText(String.valueOf(sizeUserMin + "-" + sizeUserMax));
            System.out.println("aaaaaaaaaaaaaaaaaa"+sizeUserMin);
            Int.setText(String.valueOf(Objects.requireNonNull(tableTypeSizeBaby.get(sizeUserMin)).get(0) + "-" +
                    Objects.requireNonNull(tableTypeSizeBaby.get(sizeUserMax)).get(0)));
            EU.setText(String.valueOf(tableTypeSizeBaby.get(sizeUserMin).get(1) + "-" +
                    tableTypeSizeBaby.get(sizeUserMax).get(1)));
            US.setText(String.valueOf(tableTypeSizeBaby.get(sizeUserMin).get(2) + "-" +
                    tableTypeSizeBaby.get(sizeUserMax).get(2)));
            UK.setText(String.valueOf(tableTypeSizeBaby.get(sizeUserMin).get(3) + "-" +
                    tableTypeSizeBaby.get(sizeUserMax).get(3)));
        }
        addHistoryInDb();
    }

    public void fillInDimensions(int sizeUserMin, int sizeUserMax) {
        if (sizeUserMin == sizeUserMax) {
            RU.setText(String.valueOf(sizeUserMax));
            Int.setText(Objects.requireNonNull(tableTypeSizeWoman.get(sizeUserMax)).get(0));
            EU.setText(Objects.requireNonNull(tableTypeSizeWoman.get(sizeUserMax)).get(1));
            US.setText(Objects.requireNonNull(tableTypeSizeWoman.get(sizeUserMax)).get(2));
            UK.setText(Objects.requireNonNull(tableTypeSizeWoman.get(sizeUserMax)).get(3));
        } else {
            RU.setText(String.valueOf(sizeUserMin + "-" + sizeUserMax));
            Int.setText(String.valueOf(Objects.requireNonNull(tableTypeSizeWoman.get(sizeUserMin)).get(0) + "-" +
                    Objects.requireNonNull(tableTypeSizeWoman.get(sizeUserMax)).get(0)));
            EU.setText(String.valueOf(tableTypeSizeWoman.get(sizeUserMin).get(1) + "-" +
                    tableTypeSizeWoman.get(sizeUserMax).get(1)));
            US.setText(String.valueOf(tableTypeSizeWoman.get(sizeUserMin).get(2) + "-" +
                    tableTypeSizeWoman.get(sizeUserMax).get(2)));
            UK.setText(String.valueOf(tableTypeSizeWoman.get(sizeUserMin).get(3) + "-" +
                    tableTypeSizeWoman.get(sizeUserMax).get(3)));
        }
        addHistoryInDb();
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
        String ageDb;
        String sexDb;

        if (age.equals("Взрослый"))
            ageDb = "В";
        else
            ageDb = "Д";

        if (sex.equals("Мужской"))
            sexDb = "М";
        else
            sexDb = "Ж";

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