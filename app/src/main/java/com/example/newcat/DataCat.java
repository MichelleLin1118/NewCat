package com.example.newcat;

import java.util.Date;

public class DataCat {

    public DataCat (boolean vac, boolean lig, boolean blood, boolean dew, boolean cleanEar, boolean cutNail, boolean antiparasite, boolean all, String color, String vaccineName, String about, boolean sex, String other, int cat, int cat2, int cat3, Date birth, Date adoption, int weight, boolean mixed) {
        this.mixed = mixed;
        this.vac = vac;
        this.lig = lig;
        this.blood = blood;
        this.dew = dew;
        this.cleanEar = cleanEar;
        this.cutNail = cutNail;
        this.antiparasite = antiparasite;
        this.all = all;
        this.color = color;
        this.vaccineName = vaccineName;
        this.about = about;
        this.sex = sex;
        this.other = other;
        this.cat = cat;
        this.cat2 = cat2;
        this.cat3 = cat3;
        this.birth = birth;
        this.adoption = adoption;
        this.weight = weight;
    }

    public DataCat(boolean vac, boolean lig, boolean blood, boolean dew, String color, boolean sex, boolean mixed) {
        this.mixed = mixed;
        this.vac = vac;
        this.lig = lig;
        this.blood = blood;
        this.dew = dew;
        this.cleanEar = false;
        this.cutNail = false;
        this.antiparasite = false;
        this.all = false;
        this.color = color;
        this.vaccineName = "";
        this.about = "";
        this.sex = sex;
        this.other = "";
        this.cat = R.drawable.b_cat_white;
        this.cat2 = R.drawable.b_cat_white;
        this.cat3 = R.drawable.b_cat_white;
        this.birth = new Date(1,1,1);
        this.adoption = new Date(1, 1, 1);
        this.weight = 0;
    }

    public DataCat() {
        this.mixed = false;
        this.vac = false;
        this.lig = false;
        this.blood = false;
        this.dew = false;
        this.cleanEar = false;
        this.cutNail = false;
        this.antiparasite = false;
        this.all = false;
        this.color = "";
        this.vaccineName = "";
        this.about = "";
        this.sex = false;
        this.other = "";
        this.cat = R.drawable.b_cat_white;
        this.cat2 = R.drawable.b_cat_white;
        this.cat3 = R.drawable.b_cat_white;
        this.birth = new Date(1,1,1);
        this.adoption = new Date(1, 1, 1);
        this.weight = 0;
    }

    private boolean vac, lig, blood, dew, cleanEar, cutNail, antiparasite, all, mixed;
    private String color;
    private String vaccineName;
    private String about;
    private boolean sex;
    private String other;
    private int cat;
    private int cat2;
    private int cat3;
    private Date birth, adoption;
    private int weight;

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

    public boolean getCleanEar() {
        return cleanEar;
    }

    public void setCleanEar(boolean cleanEar) {
        this.cleanEar = cleanEar;
    }

    public boolean getCutNail() {
        return cutNail;
    }

    public void setCutNail(boolean cutNail) {
        this.cutNail = cutNail;
    }

    public boolean getAntiparasite() {
        return antiparasite;
    }

    public void setAntiparasite(boolean antiparasite) {
        this.antiparasite = antiparasite;
    }

    public boolean getAll() {
        return all;
    }

    public void setAll(boolean all) {
        this.all = all;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String type) {
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


    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public int getCat2() {
        return cat2;
    }

    public void setCat2(int cat2) {
        this.cat2 = cat2;
    }

    public int getCat3() {
        return cat3;
    }

    public void setCat3(int cat3) {
        this.cat3 = cat3;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Date getAdoption() {
        return adoption;
    }

    public void setAdoption(Date adoption) {
        this.adoption = adoption;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
