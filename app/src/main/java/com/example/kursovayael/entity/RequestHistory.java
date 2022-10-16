package com.example.kursovayael.entity;

import java.io.Serializable;

public class RequestHistory implements Serializable {
    private int id;
    private String userId;
    private String typeOfClothing;
    private String ru;
    private String Int;
    private String eu;
    private String us;
    private String uk;
    private String age;
    private String sex;

    public RequestHistory(int id, String userId, String typeOfClothing, String ru, String Int, String eu, String us, String uk, String age, String sex) {
        this.id = id;
        this.userId = userId;
        this.typeOfClothing = typeOfClothing;
        this.ru = ru;
        this.Int = Int;
        this.eu = eu;
        this.us = us;
        this.uk = uk;
        this.age = age;
        this.sex = sex;
    }

    public RequestHistory(String userId, String typeOfClothing, String ru, String Int, String eu, String us, String uk, String age, String sex) {
        this.userId = userId;
        this.typeOfClothing = typeOfClothing;
        this.ru = ru;
        this.Int = Int;
        this.eu = eu;
        this.us = us;
        this.uk = uk;
        this.age = age;
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTypeOfClothing() {
        return typeOfClothing;
    }

    public void setTypeOfClothing(String typeOfClothing) {
        this.typeOfClothing = typeOfClothing;
    }

    public String getRu() {
        return ru;
    }

    public void setRu(String ru) {
        this.ru = ru;
    }

    public String getInt() {
        return Int;
    }

    public void setInt(String anInt) {
        Int = anInt;
    }

    public String getEu() {
        return eu;
    }

    public void setEu(String eu) {
        this.eu = eu;
    }

    public String getUs() {
        return us;
    }

    public void setUs(String us) {
        this.us = us;
    }

    public String getUk() {
        return uk;
    }

    public void setUk(String uk) {
        this.uk = uk;
    }
}
