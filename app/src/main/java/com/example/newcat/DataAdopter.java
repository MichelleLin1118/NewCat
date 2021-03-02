package com.example.newcat;

import java.net.URL;
import java.util.Date;

public class DataAdopter {


    public DataAdopter(String name, String addr, String familyMembers, String environment, String id, Date birthday, Date adoptDate, URL fb, int contactNumber, int predictedExpense, int catsAtHome, boolean familyAgree, boolean sexuality) {
        this.name = name;
        this.addr = addr;
        this.familyMembers = familyMembers;
        this.environment = environment;
        this.id = id;
        this.birthday = birthday;
        this.adoptDate = adoptDate;
        this.fb = fb;
        this.contactNumber = contactNumber;
        this.predictedExpense = predictedExpense;
        this.catsAtHome = catsAtHome;
        this.familyAgree = familyAgree;
        this.sexuality = sexuality;
    }

    public DataAdopter(String name, boolean sexuality) {
        this.name = name;
        this.addr = null;
        this.familyMembers = null;
        this.environment = null;
        this.id = "A000000000";
        this.birthday = new Date();
        this.adoptDate = new Date();
        this.fb = null;
        this.contactNumber = 0000000000;
        this.predictedExpense = 0;
        this.catsAtHome = 0;
        this.familyAgree = false;
        this.sexuality = sexuality;
    }

    public DataAdopter(String name, Date adoptDate, URL fb, boolean familyAgree, boolean sexuality) {
        this.name = name;
        this.addr = null;
        this.familyMembers = null;
        this.environment = null;
        this.id = "A000000000";
        this.birthday = new Date();
        this.adoptDate = adoptDate;
        this.fb = fb;
        this.contactNumber = 0000000000;
        this.predictedExpense = 0;
        this.catsAtHome = 0;
        this.familyAgree = familyAgree;
        this.sexuality = sexuality;
    }

    public DataAdopter() {
        this.name = null;
        this.addr = null;
        this.familyMembers = null;
        this.environment = null;
        this.id = "A000000000";

        this.familyAgree = false;
        this.sexuality = false;

        this.birthday = new Date();
        this.adoptDate = new Date();
        this.fb = null;

        this.contactNumber = 0000000000;
        this.predictedExpense = 0;
        this.catsAtHome = 0;
    }


    private String name, addr, familyMembers, environment, id;
    private Date birthday, adoptDate;
    private URL fb;
    private int contactNumber, predictedExpense, catsAtHome;
    private boolean familyAgree, sexuality;






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

    public String getfamilyMembers() {
        return familyMembers;
    }

    public void setfamilyMembers(String familyMembers) {
        this.familyMembers = familyMembers;
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

    public int getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getPredictedExpense() {
        return predictedExpense;
    }

    public void setPredictedExpense(int predictedExpense) {
        this.predictedExpense = predictedExpense;
    }

    public int getCatsAtHome() {
        return catsAtHome;
    }

    public void setCatsAtHome(int catsAtHome) {
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
