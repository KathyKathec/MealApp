package com.example.appfinal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class MealCategory implements Parcelable {


    private String strMeal;
    private String strMealThumb;

    public MealCategory(int id, String nombre, String imagen) {
        this.idMeal=id;
        this.strMeal=nombre;
        this.strMealThumb=imagen;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public int getIdMeal() {
        return idMeal;
    }

    public void setIdMeal(int idMeal) {
        this.idMeal = idMeal;
    }

    private int idMeal;

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }



    protected MealCategory(Parcel in) {
        strMeal = in.readString();
        strMealThumb = in.readString();
        idMeal = in.readInt();
    }

    public static final Creator<MealCategory> CREATOR = new Creator<MealCategory>() {
        @Override
        public MealCategory createFromParcel(Parcel in) {
            return new MealCategory(in);
        }

        @Override
        public MealCategory[] newArray(int size) {
            return new MealCategory[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(strMeal);
        parcel.writeString(strMealThumb);
        parcel.writeInt(idMeal);
    }
}
