package com.example.newcat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
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
            Log.i(TAG, ">>>>>>>>>>>>>>>>>> img1 = " + cursor.getLong(cursor.getColumnIndex(DataBaseCat.CAT_IMG)));
            Log.i(TAG, ">>>>>>>>>>>>>>>>>> img2 = " + cursor.getLong(cursor.getColumnIndex(DataBaseCat.CAT_IMG2)));
            Log.i(TAG, ">>>>>>>>>>>>>>>>>> img3 = " + cursor.getLong(cursor.getColumnIndex(DataBaseCat.CAT_IMG3)));
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
//            Log.i(TAG, "adopter db >>>>>>>>>>>>>>>>>>>" + cursor.getString(cursor.getColumnIndex(DataBaseAdopter.NAME)));
//            Log.i(TAG, "db " + cursor.getString(0));
//            Log.i(TAG, "db" + cursor.getString(1));
//            Log.i(TAG, "db" + cursor.getString(2));
//            Log.i(TAG, "db" + cursor.getString(3));
//            Log.i(TAG, "db" + cursor.getString(4));
//            Log.i(TAG, "db" + cursor.getString(5));
//            Log.i(TAG, "db" + cursor.getString(6));
//            Log.i(TAG, "db" + cursor.getString(7));
//            Log.i(TAG, "db" + cursor.getString(8));
//            Log.i(TAG, "db" + cursor.getString(9));
//            Log.i(TAG, "db" + cursor.getString(10));
//            Log.i(TAG, "db" + cursor.getString(11));
//            Log.i(TAG, "db" + cursor.getString(12));
//            Log.i(TAG, "db" + cursor.getString(13));
//            Log.i(TAG, "db" + cursor.getString(14));
            Log.i(TAG, "------------ adopter" );
        }
    }

    public ArrayList<DataBaseCat> getCatDataWithColorFromDB (int colorIndex) {
        ArrayList<DataBaseCat> catData = new ArrayList<DataBaseCat>();
        Cursor cursor = context.getContentResolver().query(DataBaseCat.CONTENT_URI_CAT, null, DataBaseCat.COLOR + " = " + colorIndex, null, null);
        while (cursor.moveToNext()) {
            int color = Integer.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseCat.COLOR)));
            String birth = cursor.getString(cursor.getColumnIndex(DataBaseCat.BIRTH));
            String name = cursor.getString(cursor.getColumnIndex(DataBaseCat.ADOPTER_NAME));
            long id = cursor.getLong(cursor.getColumnIndex(DataBaseCat._ID));
            DataBaseCat catColorDb = new DataBaseCat(id, color, birth, name);
            catData.add(catColorDb);
        }
        return catData;
    }
    public DataBaseCat getCatDataWithAdopterNameFromDB (String adopterName) {
        DataBaseCat catData = null;
        Cursor cursor = context.getContentResolver().query(DataBaseCat.CONTENT_URI_CAT, null, DataBaseCat.ADOPTER_NAME + " =?", new String[]{adopterName}, null);
        while (cursor.moveToNext()) {
            int color = Integer.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseCat.COLOR)));
            long catImg = cursor.getLong(cursor.getColumnIndex(DataBaseCat.CAT_IMG));
            String birth = cursor.getString(cursor.getColumnIndex(DataBaseCat.BIRTH));
            catData = new DataBaseCat(color, birth, catImg);
        }
        return catData;
    }

    public DataBaseAdopter getAdopterDataWithAdopterNameFromDB (String adopterName) {
        DataBaseAdopter adoptCityDb = null;
        Cursor cursor = context.getContentResolver().query(DataBaseAdopter.CONTENT_URI_ADOPTER, null, DataBaseAdopter.NAME + " =?", new String[]{adopterName}, null);
        while (cursor.moveToNext()) {
            int city = Integer.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseAdopter.CITY)));
            String name = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.NAME));
            long id = cursor.getLong(cursor.getColumnIndex(DataBaseAdopter._ID));
            adoptCityDb = new DataBaseAdopter(id, name, city);
        }
        return adoptCityDb;
    }

    public ArrayList<DataBaseAdopter> getAdopterDataWithCityFromDB (int cityIndex) {
        ArrayList<DataBaseAdopter> adopData = new ArrayList<DataBaseAdopter>();
        Cursor cursor = context.getContentResolver().query(DataBaseAdopter.CONTENT_URI_ADOPTER,null, DataBaseAdopter.CITY + " = " + cityIndex, null, null);
        while (cursor.moveToNext()) {
            int city = Integer.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseAdopter.CITY)));
            String name = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.NAME));
            long id = cursor.getLong(cursor.getColumnIndex(DataBaseAdopter._ID));
            DataBaseAdopter adoptCityDb = new DataBaseAdopter(id, name, city);
            adopData.add(adoptCityDb);
        }
        return adopData;
    }

    public ArrayList<DataBaseCat> getCatDataFromDB () {
        ArrayList<DataBaseCat> catData = new ArrayList<DataBaseCat>();
         Cursor cursor = context.getContentResolver().query(DataBaseCat.CONTENT_URI_CAT, null, null, null, null);
         while (cursor.moveToNext()) {
             long id = Long.parseLong(cursor.getString(cursor.getColumnIndex(DataBaseCat._ID)));
             String weight = cursor.getString(cursor.getColumnIndex(DataBaseCat.WEIGHT));
             String birth = cursor.getString(cursor.getColumnIndex(DataBaseCat.BIRTH));
             String adoption  = cursor.getString(cursor.getColumnIndex(DataBaseCat.ADOPTION));
             int color = Integer.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseCat.COLOR)));
             String vaccineName = cursor.getString(cursor.getColumnIndex(DataBaseCat.VACCINE_NAME));
             String about = cursor.getString(cursor.getColumnIndex(DataBaseCat.ABOUT));
             String other = cursor.getString(cursor.getColumnIndex(DataBaseCat.OTHER));
             String adopterName = cursor.getString(cursor.getColumnIndex(DataBaseCat.ADOPTER_NAME));
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
             long catImg = cursor.getLong(cursor.getColumnIndex(DataBaseCat.CAT_IMG));
             long catImg2 = cursor.getLong(cursor.getColumnIndex(DataBaseCat.CAT_IMG2));
             long catImg3 = cursor.getLong(cursor.getColumnIndex(DataBaseCat.CAT_IMG3));
             //ArrayList<Integer> catPic = new ArrayList<Integer>();
//             catPic.add(catImg);
//             catPic.add(catImg2);
//             catPic.add(catImg3);
             long[] catPic = new long[3];
             catPic[0] = catImg;
             catPic[1] = catImg2;
             catPic[2] = catImg3;
             DataBaseCat catdb = new DataBaseCat(id, weight, birth, adoption, color, vaccineName, about, other, vaccine, ligation, bloodTest, deworm, earsCleaned, nailsCutted, antiparasite, allCheck, mixed, sexuality, catImg,catImg2, catImg3, catPic, adopterName);
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
            long id = Long.parseLong(cursor.getString(cursor.getColumnIndex(DataBaseAdopter._ID)));
            String name = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.NAME));
            int city = Integer.valueOf(cursor.getString(cursor.getColumnIndex(DataBaseAdopter.CITY)));
            String address = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.ADDRESS));
            String familyMembers = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.FAMILY_MEMBERS));
            String environment = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.ENVIRONMENT));
            String adopterId = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.ADOPTER_ID));
            String birthday = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.ADOPTER_BIRTHDAY));
            String adoptDate = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.ADOPTION_DATE));
            String contactNumber = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.CONTACT_NUMBER));
            String predictedExpense = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.PREDICTED_EXPENSE));
            String catsAtHome = cursor.getString(cursor.getColumnIndex(DataBaseAdopter.CATS_AT_HOME));
            boolean familyAgree = getBooleanFromDB(cursor,DataBaseAdopter.FAMILY_AGREE);
            boolean sexuality = getBooleanFromDB(cursor,DataBaseAdopter.ADOPTER_SEXUALITY);
            long catImg = cursor.getLong(cursor.getColumnIndex(DataBaseAdopter.CAT_IMG));


            DataBaseAdopter adopdb = new DataBaseAdopter(id, name, city, address, familyMembers, environment, adopterId, birthday, adoptDate, contactNumber, predictedExpense, catsAtHome, familyAgree, sexuality, catImg);
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
        values.put(DataBaseCat.ADOPTER_NAME, cat.getAdopterName());

        return values;
    }


    public ContentValues createAdopterData (DataBaseAdopter adop) {
        ContentValues values = new ContentValues();
        values.put(DataBaseAdopter.NAME, adop.getName());
        values.put(DataBaseAdopter.CITY, adop.getCity());
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
        values.put(DataBaseAdopter.CAT_IMG, adop.getCatImg());

        return values;
    }
}
