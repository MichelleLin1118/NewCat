package com.example.newcat;

import android.net.Uri;
import android.renderscript.Double2;
import android.widget.ImageView;

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
    public static String FAMILY_AGREE = "family agree";
    public static String ADOPTER_SEXUALITY = "sexuality";

    public static final String AUTHORITY = "com.example.newcat";
    public static final Uri CONTENT_URI_ADOPTER = Uri.parse("content://" + AUTHORITY + "/" + TABLE_ADOPTER);

    private long id;
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
}
