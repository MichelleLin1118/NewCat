package com.example.newcat;

import android.net.Uri;

import java.util.ArrayList;


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
    public static String BLOOD_TEST = "blood_test";
    public static String EARS_CLEANED = "ears_cleaned";
    public static String NAILS_CUTTED = "nails_cutted";
    public static String ANTIPARASITE = "antiparasite";
    public static String MIXED = "mixed";
    public static String ALL_CHECK = "all_check";
    public static String SEXUALITY = "sexuality";

    public static String CAT_IMG = "cat_img";
    //public static int CAT_IMG;

    public static final String AUTHORITY = "com.example.newcat";
    public static final Uri CONTENT_URI_CAT = Uri.parse("content://" + AUTHORITY + "/" + TABLE_CAT);

    private long id;
    private String weight, birth, adoption, vaccineName, about, other;
    private int color, catImg;
    private boolean vac, lig, blood, dew, earsCleaned, nailsCutted, antiparasite, allCheck, mixed, sex;
    private ArrayList<Integer> catPic;

    public DataBaseCat(long id, String weight, String birth, String adoption, int color, String vaccineName, String about, String other, boolean vac, boolean lig, boolean blood, boolean dew, boolean earsCleaned, boolean nailsCutted, boolean antiparasite, boolean allCheck, boolean mixed, boolean sex, int catImg) {
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
        this.allCheck = allCheck;
        this.mixed = mixed;
        this.sex = sex;
        this.catImg = catImg;
    }


    public DataBaseCat(boolean vac, boolean lig, boolean blood, boolean dew, int color, boolean sex, boolean mixed) {
        this.mixed = mixed;
        this.vac = vac;
        this.lig = lig;
        this.blood = blood;
        this.dew = dew;
        this.earsCleaned = false;
        this.nailsCutted = false;
        this.antiparasite = false;
        this.allCheck = false;
        this.color = color;
        this.vaccineName = "";
        this.about = "";
        this.sex = sex;
        this.other = "";
        // catPic = no picture
//        this.cat = R.drawable.b_cat_white;
//        this.cat2 = R.drawable.b_cat_white;
//        this.cat3 = R.drawable.b_cat_white;
        this.birth = "151515";
        this.adoption = "151515";
        this.weight = "0";
    }

    public DataBaseCat() {
        this.mixed = false;
        this.vac = false;
        this.lig = false;
        this.blood = false;
        this.dew = false;
        this.earsCleaned = false;
        this.nailsCutted = false;
        this.antiparasite = false;
        this.allCheck = false;
        this.color = 0;
        this.vaccineName = "vacName";
        this.about = "about";
        this.sex = false;
        this.other = "other";
        this.catImg = R.drawable.b_cat_white;//initDefaultCatPicture();
        // catPic = no picture
//        this.cat = R.drawable.b_cat_white;
//        this.cat2 = R.drawable.b_cat_white;
//        this.cat3 = R.drawable.b_cat_white;
        this.birth = "birth";
        this.adoption = "adoption";
        this.weight = "weight:0";
    }
    
    public ArrayList<Integer> initDefaultCatPicture() {
        ArrayList<Integer> initDefaultCatPicture = new ArrayList<Integer>();
        initDefaultCatPicture.add(R.drawable.b_cat_white);
        return initDefaultCatPicture;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }
    public boolean getMixed() {
        return mixed;
    }

    public void setMixed(boolean mixed) {
        this.mixed = mixed;
    }

    public boolean getVac() {
        return vac;
    }

    public void setVac(boolean vac) {
        this.vac = vac;
    }

    public boolean getLig() {
        return lig;
    }

    public void setLig(boolean lig) {
        this.lig = lig;
    }

    public boolean getBlood() {
        return blood;
    }

    public void setBlood(boolean blood) {
        this.blood = blood;
    }

    public boolean getDew() {
        return dew;
    }

    public void setDew(boolean dew) {
        this.dew = dew;
    }

    public boolean getearsCleaned() {
        return earsCleaned;
    }

    public void setearsCleaned(boolean earsCleaned) {
        this.earsCleaned = earsCleaned;
    }

    public boolean getnailsCutted() {
        return nailsCutted;
    }

    public void setnailsCutted(boolean nailsCutted) {
        this.nailsCutted = nailsCutted;
    }

    public boolean getAntiparasite() {
        return antiparasite;
    }

    public void setAntiparasite(boolean antiparasite) {
        this.antiparasite = antiparasite;
    }

    public boolean getAllCheck() {
        return allCheck;
    }

    public void setAllCheck(boolean allCheck) {
        this.allCheck = allCheck;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int type) {
        this.color = type;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public boolean getSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public void setCatPic (ArrayList<Integer> catPic) {this.catPic = catPic;}

    public ArrayList<Integer> getCatPic() {return catPic;}


    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getAdoption() {
        return adoption;
    }

    public void setAdoption(String adoption) {
        this.adoption = adoption;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getCatImg() {return catImg;}

    public void setCatImg(int catImg) {this.catImg = catImg;}

    @Override
    public String toString() {
        return "DataBaseCat id = " + id +
                "DataBaseCat weight = " + weight +
                "DataBaseCat birth = " + birth +
                "DataBaseCat adoption String = " + adoption +
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
                "DataBaseCat allCheck = " + allCheck;
    }


}
