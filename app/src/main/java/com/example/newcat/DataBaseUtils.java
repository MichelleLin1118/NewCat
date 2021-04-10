package com.example.newcat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;

import static java.lang.Boolean.valueOf;

public class DataBaseUtils {
    public static final String TAG = "DataBaseUtils ";
    public Context context;

    public DataBaseUtils(Context context) {
        this.context = context;
    }

    public void showCatDataBaseResult() {
        Cursor cursor = context.getContentResolver().query(DataBaseCat.CONTENT_URI_CAT, null, null, null, null);
        while (cursor.moveToNext()) {
            /*Log.i(TAG, "db " + cursor.getString(0));
            Log.i(TAG, "db" + cursor.getString(1));
            Log.i(TAG, "db" + cursor.getString(2));
            Log.i(TAG, "db" + cursor.getString(3));
            Log.i(TAG, "db" + cursor.getString(4));
            Log.i(TAG, "db" + cursor.getString(5));
            Log.i(TAG, "db" + cursor.getString(6));
            Log.i(TAG, "db" + cursor.getString(7));
            Log.i(TAG, "db" + cursor.getString(8));
            Log.i(TAG, "db" + cursor.getString(9));
            Log.i(TAG, "deworm" + cursor.getString(10));
            Log.i(TAG, "ears" + cursor.getString(11));
            Log.i(TAG, "nails_cutted" + cursor.getString(12));
            Log.i(TAG, "mixed" + cursor.getString(13));
            Log.i(TAG, "sexuality" + cursor.getString(14));
            Log.i(TAG, "all" + cursor.getString(15));*/
            Log.i(TAG, "------------ cat" );
        }
    }

    public void showAdopDataBaseResult() {
        Cursor cursor = context.getContentResolver().query(DataBaseAdopter.CONTENT_URI_ADOPTER, null, null, null, null);
        while (cursor.moveToNext()) {
            Log.i(TAG, "adopter db >>>>>>>>>>>>>>>>>>>" + cursor.getString(1));//cursor.getString(cursor.getColumnIndex(DataBaseAdopter.NAME)));
           /* Log.i(TAG, "db " + cursor.getString(0));
            Log.i(TAG, "db" + cursor.getString(1));
            Log.i(TAG, "db" + cursor.getString(2));
            Log.i(TAG, "db" + cursor.getString(3));
            Log.i(TAG, "db" + cursor.getString(4));
            Log.i(TAG, "db" + cursor.getString(5));
            Log.i(TAG, "db" + cursor.getString(6));
            Log.i(TAG, "db" + cursor.getString(7));
            Log.i(TAG, "db" + cursor.getString(8));
            Log.i(TAG, "db" + cursor.getString(9));
            Log.i(TAG, "db" + cursor.getString(10));
            Log.i(TAG, "db" + cursor.getString(11));
            Log.i(TAG, "db" + cursor.getString(12));
            Log.i(TAG, "db" + cursor.getString(13));
            Log.i(TAG, "db" + cursor.getString(14));
            Log.i(TAG, "db" + cursor.getString(15));*/
        }
    }

    public ArrayList<DataBaseCat> getCatDataFromDB () {
        Log.i(TAG, "============== getCatDataFromDB ");
        ArrayList<DataBaseCat> catData = new ArrayList<DataBaseCat>();
         Cursor cursor = context.getContentResolver().query(DataBaseCat.CONTENT_URI_CAT, null, null, null, null);
         while (cursor.moveToNext()) {
             Long id = Long.parseLong(cursor.getString(cursor.getColumnIndex(DataBaseCat._ID)));
             String weight = cursor.getString(cursor.getColumnIndex(DataBaseCat.WEIGHT));
             String birth = cursor.getString(cursor.getColumnIndex(DataBaseCat.BIRTH));
             String adoption  = cursor.getString(cursor.getColumnIndex(DataBaseCat.ADOPTION));
             int color = Integer.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseCat.COLOR)));
             Log.i(TAG, "++++++++ color = " + color);
             String vaccineName = cursor.getString(cursor.getColumnIndex(DataBaseCat.VACCINE_NAME));
             String about = cursor.getString(cursor.getColumnIndex(DataBaseCat.ABOUT));
             String other = cursor.getString(cursor.getColumnIndex(DataBaseCat.OTHER));

             boolean vaccine = getBooleanFromDB(cursor, DataBaseCat.VACCINE);
             boolean ligation = getBooleanFromDB(cursor, DataBaseCat.LIGATION);
             boolean bloodTest = getBooleanFromDB(cursor, DataBaseCat.BLOOD_TEST);
             boolean deworm = getBooleanFromDB(cursor, DataBaseCat.DEWORM);
             boolean earsCleaned = getBooleanFromDB(cursor, DataBaseCat.EARS_CLEANED);
             boolean nailsCutted = getBooleanFromDB(cursor, DataBaseCat.NAILS_CUTTED);
             boolean antiparasite = getBooleanFromDB(cursor, DataBaseCat.ANTIPARASITE);
             boolean allCheck = getBooleanFromDB(cursor, DataBaseCat.ALL_CHECK);
             boolean mixed = getBooleanFromDB(cursor, DataBaseCat.MIXED);
             boolean sexuality = getBooleanFromDB(cursor, DataBaseCat.SEXUALITY);
             Log.i(TAG, "all check ==================================" + allCheck);
             int catImg = Integer.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseCat.CAT_IMG)));
             int catImg2 = Integer.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseCat.CAT_IMG2)));
             int catImg3 = Integer.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseCat.CAT_IMG3)));
             ArrayList<Integer> catPic = new ArrayList<Integer>();
             catPic.add(catImg);
             catPic.add(catImg2);
             catPic.add(catImg3);

             DataBaseCat catdb = new DataBaseCat(id, weight, birth, adoption, color, vaccineName, about, other, vaccine, ligation, bloodTest, deworm, earsCleaned, nailsCutted, antiparasite, allCheck, mixed, sexuality, catImg,catImg2, catImg3, catPic);
             catData.add(catdb);
         }
         return catData;
    }
    private boolean getBooleanFromDB (Cursor cursor, String columnName) {
        if (cursor.getString(cursor.getColumnIndex(columnName)).equals("1")) {
            return true;
        } else {
            return false;
        }
    }


    public ArrayList<DataBaseAdopter> getAdopterDataFromDB () {
        ArrayList<DataBaseAdopter> adopterData = new ArrayList<DataBaseAdopter>();
        Cursor cursor = context.getContentResolver().query(DataBaseAdopter.CONTENT_URI_ADOPTER, null, null, null, null);
        while (cursor.moveToNext()) {
            Long id = Long.parseLong(cursor.getString(cursor.getColumnIndex(DataBaseAdopter._ID)));
            String name = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.NAME));
            String address = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.ADDRESS));
            String familyMembers = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.FAMILY_MEMBERS));
            String environment = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.ENVIRONMENT));
            String adopterId = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.ADOPTER_ID));
            String birthday = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.ADOPTER_BIRTHDAY));
            String adoptDate = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.ADOPTION_DATE));
            String contactNumber = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.CONTACT_NUMBER));
            String predictedExpense = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.PREDICTED_EXPENSE));
            String catsAtHome = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.CATS_AT_HOME));
            Boolean familyAgree = valueOf(cursor.getString(cursor.getColumnIndex(DataBaseAdopter.FAMILY_AGREE)));
            Boolean sexuality = valueOf(cursor.getString(cursor.getColumnIndex(DataBaseAdopter.ADOPTER_SEXUALITY)));

            DataBaseAdopter adopdb = new DataBaseAdopter(id, name, address, familyMembers, environment, adopterId, birthday, adoptDate, contactNumber, predictedExpense, catsAtHome, familyAgree, sexuality);
            adopterData.add(adopdb);
        }
        return adopterData;
    }

    public ContentValues createCatData(DataBaseCat cat) {
        ContentValues values = new ContentValues();
        values.put(DataBaseCat.WEIGHT, cat.getWeight());
        values.put(DataBaseCat.BIRTH, cat.getBirth());
        values.put(DataBaseCat.ADOPTION, cat.getAdoption());
        values.put(DataBaseCat.COLOR, cat.getColor());
        values.put(DataBaseCat.VACCINE_NAME, cat.getVaccineName());
        values.put(DataBaseCat.ABOUT,cat.getAbout());
        values.put(DataBaseCat.OTHER, cat.getOther());
        values.put(DataBaseCat.VACCINE, cat.getVac());
        values.put(DataBaseCat.LIGATION, cat.getLig());
        values.put(DataBaseCat.DEWORM, cat.getDew());
        values.put(DataBaseCat.EARS_CLEANED, cat.getearsCleaned());
        values.put(DataBaseCat.NAILS_CUTTED, cat.getnailsCutted());
        values.put(DataBaseCat.MIXED, cat.getMixed());
        values.put(DataBaseCat.SEXUALITY, cat.getSexuality());
        values.put(DataBaseCat.ALL_CHECK, cat.getAllCheck());
        values.put(DataBaseCat.CAT_IMG, cat.getCatImg());
        values.put(DataBaseCat.CAT_IMG2, cat.getCatImg2());
        values.put(DataBaseCat.CAT_IMG3, cat.getCatImg3());

        return values;
    }


    public ContentValues createAdopterData (DataBaseAdopter adop) {
        ContentValues values = new ContentValues();
        values.put(DataBaseAdopter.NAME, adop.getName());
        Log.i(TAG, " name ===============" + adop.getName());
        values.put(DataBaseAdopter.ADDRESS, adop.getAddr());
        values.put(DataBaseAdopter.FAMILY_MEMBERS, adop.getFamilyMembers());
        values.put(DataBaseAdopter.ENVIRONMENT, adop.getEnvironment());
        values.put(DataBaseAdopter.ADOPTER_ID, adop.getAdopterId());
        values.put(DataBaseAdopter.ADOPTER_BIRTHDAY, adop.getBirthday());
        values.put(DataBaseAdopter.ADOPTION_DATE, adop.getAdoptDate());
        values.put(DataBaseAdopter.CONTACT_NUMBER, adop.getContactNumber());
        values.put(DataBaseAdopter.PREDICTED_EXPENSE, adop.getPredictedExpense());
        values.put(DataBaseAdopter.CATS_AT_HOME, adop.getCatsAtHome());
        values.put(DataBaseAdopter.FAMILY_AGREE, adop.getFamilyAgree());
        values.put(DataBaseAdopter.ADOPTER_SEXUALITY, adop.getSexuality());

        return values;
    }
}
