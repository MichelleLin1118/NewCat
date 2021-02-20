package com.example.newcat;

import java.net.URL;
import java.util.Date;

public class DataAdopter {


    public DataAdopter(String name, String addr, String familyBackground, String environment, String id, Date birthday, Date adoptDate, URL fb, int phone, int expenseOnCat, int otherCats, boolean familyAgree, boolean sexuality) {
        this.name = name;
        this.addr = addr;
        this.familyBackground = familyBackground;
        this.environment = environment;
        this.id = id;
        this.birthday = birthday;
        this.adoptDate = adoptDate;
        this.fb = fb;
        this.phone = phone;
        this.expenseOnCat = expenseOnCat;
        this.otherCats = otherCats;
        this.familyAgree = familyAgree;
        this.sexuality = sexuality;
    }

    public DataAdopter(String name, boolean sexuality) {
        this.name = name;
        this.addr = null;
        this.familyBackground = null;
        this.environment = null;
        this.id = "A000000000";
        this.birthday = new Date();
        this.adoptDate = new Date();
        this.fb = null;
        this.phone = 0000000000;
        this.expenseOnCat = 0;
        this.otherCats = 0;
        this.familyAgree = false;
        this.sexuality = sexuality;
    }

    public DataAdopter(String name, Date adoptDate, URL fb, boolean familyAgree, boolean sexuality) {
        this.name = name;
        this.addr = null;
        this.familyBackground = null;
        this.environment = null;
        this.id = "A000000000";
        this.birthday = new Date();
        this.adoptDate = adoptDate;
        this.fb = fb;
        this.phone = 0000000000;
        this.expenseOnCat = 0;
        this.otherCats = 0;
        this.familyAgree = familyAgree;
        this.sexuality = sexuality;
    }

    public DataAdopter() {
        this.name = null;
        this.addr = null;
        this.familyBackground = null;
        this.environment = null;
        this.id = "A000000000";

        this.familyAgree = false;
        this.sexuality = false;

        this.birthday = new Date();
        this.adoptDate = new Date();
        this.fb = null;

        this.phone = 0000000000;
        this.expenseOnCat = 0;
        this.otherCats = 0;
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

    public String getFamilyBackground() {
        return familyBackground;
    }

    public void setFamilyBackground(String familyBackground) {
        this.familyBackground = familyBackground;
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getAdoptDate() {
        return adoptDate;
    }

    public void setAdoptDate(Date adoptDate) {
        this.adoptDate = adoptDate;
    }

    public URL getFb() {
        return fb;
    }

    public void setFb(URL fb) {
        this.fb = fb;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getExpenseOnCat() {
        return expenseOnCat;
    }

    public void setExpenseOnCat(int expenseOnCat) {
        this.expenseOnCat = expenseOnCat;
    }

    public int getOtherCats() {
        return otherCats;
    }

    public void setOtherCats(int otherCats) {
        this.otherCats = otherCats;
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



    private String name, addr, familyBackground, environment, id;
    private Date birthday, adoptDate;
    private URL fb;
    private int phone, expenseOnCat, otherCats;
    private boolean familyAgree, sexuality;

}
