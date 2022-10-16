package com.example.kursovayael;

public class ClothingSize {

    private String dressType;
    private String user_id;
    private String RU;
    private String Int;
    private String EU;
    private String US;
    private String UK;
    private String age;
    private String sex;

    public ClothingSize(String dressType, String user_id, String RU, String anInt, String EU, String US, String UK, String age, String sex) {
        this.dressType = dressType;
        this.user_id = user_id;
        this.RU = RU;
        Int = anInt;
        this.EU = EU;
        this.US = US;
        this.UK = UK;
        this.age = age;
        this.sex = sex;
    }

    public String getRU() {
        return RU;
    }

    public void setRU(String RU) {
        this.RU = RU;
    }

    public String getUS() {
        return US;
    }

    public void setUS(String US) {
        this.US = US;
    }

    public String getInt() {
        return Int;
    }

    public void setInt(String anInt) {
        Int = anInt;
    }

    public String getEU() {
        return EU;
    }

    public void setEU(String EU) {
        this.EU = EU;
    }

    public String getUK() {
        return UK;
    }

    public void setUK(String UK) {
        this.UK = UK;
    }

    public String getDressType() {
        return dressType;
    }

    public void setDressType(String dressType) {
        this.dressType = dressType;
    }

}
