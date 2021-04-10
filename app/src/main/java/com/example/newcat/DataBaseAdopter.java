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

    public static final String AUTHORITY = "com.example.newcat";
    public static final Uri CONTENT_URI_ADOPTER = Uri.parse("content://" + AUTHORITY + "/" + TABLE_ADOPTER);

    private Long id;
    private String name, addr, familyMembers, environment, adopterId, birthday, adoptDate, contactNumber, predictedExpense, catsAtHome;
    private boolean familyAgree, sexuality;

    public DataBaseAdopter(long id, String name, String addr, String familyMembers, String environment, String adopterId, String birthday, String adoptDate, String contactNumber, String predictedExpense, String catsAtHome, boolean familyAgree, boolean sexuality) {
        this.id = id;
        this.name = name;
        this.addr = addr;
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
    }

    public DataBaseAdopter(String name, String adoptDate, boolean familyAgree, boolean sexuality) {
        this.name = name;
        this.addr = null;
        this.familyMembers = null;
        this.environment = null;
        this.adopterId = "A000000000";
        this.birthday = "";
        this.adoptDate = adoptDate;
        this.contactNumber = "";
        this.predictedExpense = "";
        this.catsAtHome = "";
        this.familyAgree = familyAgree;
        this.sexuality = sexuality;
    }

    public DataBaseAdopter(String name, boolean sexuality) {
        this.name = name;
        this.addr = null;
        this.familyMembers = null;
        this.environment = null;
        this.adopterId = "A000000000";
        this.birthday = "";
        this.adoptDate = "";
        this.contactNumber = "";
        this.predictedExpense = "";
        this.catsAtHome = "";
        this.familyAgree = false;
        this.sexuality = sexuality;
    }

    public DataBaseAdopter() {
        this.name = "name";
        this.addr = "addr";
        this.familyMembers = "fam";
        this.environment = "env";
        this.adopterId = "adopId";

        this.familyAgree = false;
        this.sexuality = false;

        this.birthday = "birth";
        this.adoptDate = "date";

        this.contactNumber = "cont";
        this.predictedExpense = "pre-ex";
        this.catsAtHome = "cats";
    }


    @Override
    public String toString() {
        return "DataBaseAdopter id = " + id +
                "DataBaseAdopter name = " + name +
                "DataBaseAdopter addr = " + addr +
                "DataBaseAdopter familyMembers = " + familyMembers +
                "DataBaseAdopter environment = " + environment +
                "DataBaseAdopter adopterId = " + adopterId +
                "DataBaseAdopter birthday = " + birthday +
                "DataBaseAdopter adoptDate = " + adoptDate +
                "DataBaseAdopter contactNumber = " + contactNumber +
                "DataBaseAdopter predictedExpense = " + predictedExpense +
                "DataBaseAdopter catsAtHome = " + catsAtHome +
                "DataBaseAdopter familyAgree = " + familyAgree +
                "DataBaseAdopter sexuality = " + sexuality ;
    }


    public Long getId() {
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

    public void setCatsAtHome(String catsAtHome) {
        this.catsAtHome = catsAtHome;
    }

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

}
