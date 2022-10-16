package com.example.kursovayael;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kursovayael.entity.RequestHistory;

import java.util.List;

public class ClothingSizzeAdapter extends ArrayAdapter<RequestHistory> {

    private LayoutInflater inflater;
    private int layout;
    private List<RequestHistory> сlothingSizes;

    public ClothingSizzeAdapter(Context context, int resource, List<RequestHistory> сlothingSizes) {
        super(context, resource, сlothingSizes);
        this.сlothingSizes = сlothingSizes;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView flagView = view.findViewById(R.id.imageViewClothingTypes);
        TextView textViewInListRu = view.findViewById(R.id.textViewInListRu);
        TextView textViewInListUS = view.findViewById(R.id.textViewInListUS);
        TextView textViewInListInt = view.findViewById(R.id.textViewInListInt);
        TextView textViewInListEU = view.findViewById(R.id.textViewInListEU);
        TextView textViewInListUK = view.findViewById(R.id.textViewInListUK);
        TextView textViewInListAge = view.findViewById(R.id.textViewInListAge);
        TextView textViewInListSex = view.findViewById(R.id.textViewInListSex);

        RequestHistory clothingSize = сlothingSizes.get(position);
        switch (clothingSize.getTypeOfClothing()) {
            case "Джинсы,Штаны":
                flagView.setImageResource(R.drawable.jeans);
                break;
            case "Платье":
                flagView.setImageResource(R.drawable.wear);
                break;
            case "Рубашка,куртки костюмы":
            case "Рубашка":
                flagView.setImageResource(R.drawable.shirt);
                break;
            case "Юбка":
                flagView.setImageResource(R.drawable.skirt);
                break;
            case "Обувь":
                flagView.setImageResource(R.drawable.ic_shoes);
                break;
            case "Головной убор":
                flagView.setImageResource(R.drawable.ic_head);
                break;
            default:
                System.err.println("Неверный тип одежды -> " + clothingSize.getTypeOfClothing());
        }

        textViewInListRu.setText(clothingSize.getRu());
        textViewInListUS.setText(clothingSize.getUs());
        textViewInListInt.setText(clothingSize.getInt());
        textViewInListEU.setText(clothingSize.getEu());
        textViewInListUK.setText(clothingSize.getUk());
        textViewInListAge.setText(clothingSize.getAge());
        textViewInListSex.setText(clothingSize.getSex());

        return view;
    }
}