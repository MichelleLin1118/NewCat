package com.example.newcat;

import android.net.Uri;

public class DataBaseCat {
    public static String TABLE_CAT = "cat";
    public static String TABLE_CAT_ID = "cat/#";

    public static String _ID = "_id";
    public static String WEIGHT = "weight";
    public static String BIRTH = "birth";
    public static String ADOPTION = "adoption";
    public static String COLOR = "color";
    public static String VACCINE_NAME = "vaccine_name";
    public static String ABOUT = "about";
    public static String OTHER = "other";

    public static String VACCINE = "vaccine";
    public static String LIGATION = "ligation";
    public static String DEWORM = "deworm";
    public static String EARS_CLEANED = "ears_cleaned";
    public static String NAILS_CUTTED = "nails_cutted";
    public static String MIXED = "mixed";
    public static String ALL = "all";
    public static String SEXUALITY = "sexuality";

    public static final String AUTHORITY = "com.example.newcat";
    public static final Uri CONTENT_URI_CAT = Uri.parse("content://" + AUTHORITY + "/" + TABLE_CAT);

    private long id;
    private String weight, birth, adoption, color, vaccineName, about, other;
    private boolean vac, lig, blood, dew, earsCleaned, nailsCutted, antiparasite, all, mixed, sex;

    public DataBaseCat(long id, String weight, String birth, String adoption, String color, String vaccineName, String about, String other, boolean vac, boolean lig, boolean blood, boolean dew, boolean earsCleaned, boolean nailsCutted, boolean antiparasite, boolean all, boolean mixed, boolean sex) {
        this.id = id;
        this.weight = weight;
        this.birth = birth;
        this.adoption = adoption;
        this.color = color;
        this.vaccineName = vaccineName;
        this.about = about;
        this.other = other;
        this.vac = vac;
        this.lig = lig;
        this.blood = blood;
        this.dew = dew;
        this.earsCleaned = earsCleaned;
        this.nailsCutted = nailsCutted;
        this.antiparasite = antiparasite;
        this.all = all;
        this.mixed = mixed;
        this.sex = sex;
    }



    @Override
    public String toString() {
        return "DataBaseCat id = " + id +
                "DataBaseCat weight = " + weight +
                "DataBaseCat birth = " + birth +
                "DataBaseCat adoption date = " + adoption +
                "DataBaseCat color = " + color +
                "DataBaseCat vaccine name = " + vaccineName +
                "DataBaseCat sexuality = " + sex +
                "DataBaseCat vaccine = " + vac +
                "DataBaseCat ligation = " + lig +
                "DataBaseCat blood test = " + blood +
                "DataBaseCat deworm = " + dew +
                "DataBaseCat ears cleaned = " + earsCleaned +
                "DataBaseCat nails cutted = " + nailsCutted +
                "DataBaseCat antiparasite = " + antiparasite +
                "DataBaseCat mixed = " + mixed +
                "DataBaseCat all = " + all;
    }


}
