package com.example.newcat;

import android.net.Uri;
import android.renderscript.Double2;
import android.widget.ImageView;

import java.net.URL;
import java.util.Date;

public class DataBaseAdopter {
    public static String TABLE_ADOPTER = "adopter";
    public static String TABLE_ADOPTER_ID = "adopter/#";

    public static String _ID = "_id";
    public static String NAME = "name";
    public static String ADDRESS = "address";
    public static String FAMILY_MEMBERS = "family_members";
    public static String ENVIRONMENT = "environment";
    public static String ADOPTER_ID = "adopter_id";
    public static String ADOPTER_BIRTHDAY = "birthday";
    public static String ADOPTION_DATE = "adoption_date";
    public static String CONTACT_NUMBER = "contact_number";
    public static String PREDICTED_EXPENSE = "predicted_expense";
    public static String CATS_AT_HOME = "cats_at_home";
    public static String FAMILY_AGREE = "family_agree";
    public static String ADOPTER_SEXUALITY = "sexuality";
    public static String CAT_IMG = "catImg";
    public static String CAT_IMG2 = "catImg2";
    public static String CAT_IMG3 = "catImg3";
    public static String CITY = "city";

    public static final String AUTHORITY = "com.example.newcat";
    public static final Uri CONTENT_URI_ADOPTER = Uri.parse("content://" + AUTHORITY + "/" + TABLE_ADOPTER);

    private long id;
    private String name, addr, familyMembers, environment, adopterId, birthday, adoptDate, contactNumber, predictedExpense, catsAtHome;
    private int city;
    private long catImg,catImg2,catImg3;
    private boolean familyAgree, sexuality;

    public DataBaseAdopter(long id, String name, int city, String addr, String familyMembers, String environment, String adopterId, String birthday, String adoptDate, String contactNumber, String predictedExpense, String catsAtHome, boolean familyAgree, boolean sexuality, long catImg, long catImg2, long catImg3) {
        this.id = id;
        this.name = name;
        this.addr = addr;
        this.city = city;
        this.familyMembers = familyMembers;
        this.environment = environment;
        this.adopterId = adopterId;
        this.birthday = birthday;
        this.adoptDate = adoptDate;
        this.contactNumber = contactNumber;
        this.predictedExpense = predictedExpense;
        this.catsAtHome = catsAtHome;
        this.familyAgree = familyAgree;
        this.sexuality = sexuality;
        this.catImg = catImg;
        this.catImg2 = catImg2;
        this.catImg3 = catImg3;
    }

    public DataBaseAdopter(long id, String name, int city) {
        this.id = id;
        this.name = name;
        this.addr = "0";
        this.city = city;
        this.familyMembers = "0";
        this.environment = "0";
        this.adopterId = "0";

        this.familyAgree = true;
        this.sexuality = true;

        this.birthday = "0";
        this.adoptDate = "0";

        this.contactNumber = "0";
        this.predictedExpense = "0";
        this.catsAtHome = "0";
        this.catImg = 0;
        this.catImg2 = 0;
        this.catImg3 = 0;
    }


    public DataBaseAdopter() {
        this.name = "name";
        this.addr = "0";
        this.city = 0;
        this.familyMembers = "0";
        this.environment = "0";
        this.adopterId = "0";

        this.familyAgree = true;
        this.sexuality = true;

        this.birthday = "0";
        this.adoptDate = "0";

        this.contactNumber = "0";
        this.predictedExpense = "0";
        this.catsAtHome = "0";
        this.catImg = 0;
        this.catImg2 = 0;
        this.catImg3 = 0;
    }





    public long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(String familyMembers) {
        this.familyMembers = familyMembers;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getAdopterId() {
        return adopterId;
    }

    public void setAdopterId(String adopterId) {
        this.adopterId = adopterId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAdoptDate() {
        return adoptDate;
    }

    public void setAdoptDate(String adoptDate) {
        this.adoptDate = adoptDate;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPredictedExpense() {
        return predictedExpense;
    }

    public void setPredictedExpense(String predictedExpense) { this.predictedExpense = predictedExpense; }

    public String getCatsAtHome() {
        return catsAtHome;
    }

    public void setCatsAtHome(String catsAtHome) { this.catsAtHome = catsAtHome; }
    public boolean getFamilyAgree() {
        return familyAgree;
    }
    public void setFamilyAgree(boolean familyAgree) {
        this.familyAgree = familyAgree;
    }
    public boolean getSexuality() {
        return sexuality;
    }
    public void setSexuality(boolean sexuality) {
        this.sexuality = sexuality;
    }
    public int getCity() {return city;}
    public void setCity(int city) {this.city = city;}

    public long getCatImg() {return catImg;}
    public void setCatImg(long catImg) {this.catImg = catImg;}
    public long getCatImg2() {return catImg2;}
    public void setCatImg2(long catImg2) {this.catImg2 = catImg2;}
    public long getCatImg3() {return catImg3;}
    public void setCatImg3(long catImg3) {this.catImg3 = catImg3;}
    @Override
    public String toString() {
        return "DataBaseAdopter id = " + id +
                "DataBaseAdopter name = " + name +
                "DataBaseAdopter address = " + addr +
                "DataBaseAdopter city = " + city +
                "DataBaseAdopter family members = " + familyMembers +
                "DataBaseAdopter environment = " + environment +
                "DataBaseAdopter adopter Id = " + adopterId +
                "DataBaseAdopter birthday = " + birthday +
                "DataBaseAdopter adoption date = " + adoptDate +
                "DataBaseAdopter contactNumber = " + contactNumber +
                "DataBaseAdopter predictedExpense = " + predictedExpense +
                "DataBaseAdopter cats at home = " + catsAtHome +
                "DataBaseAdopter family agree = " + familyAgree +
                "DataBaseAdopter sexuality = " + sexuality +
                "DataBaseAdopter cat image = " + catImg ;
    }
}
