package com.example.newcat;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NewCatContentProvider extends ContentProvider {

    public static String DATABASE_NAME = "newcat.db";
    private static UriMatcher matcher;
    private static final int MATCH_CAT = 100;
    private static final int MATCH_ADOPTER = 200;


    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(DataBaseCat.AUTHORITY, DataBaseCat.TABLE_CAT, MATCH_CAT);
        matcher.addURI(DataBaseAdopter.AUTHORITY, DataBaseAdopter.TABLE_ADOPTER, MATCH_ADOPTER);

    }
    private SQLiteDatabase newCatDb;

    private void InitDb() {
        CatDataHelper catDataHelper = new CatDataHelper(getContext());
        newCatDb = catDataHelper.getWritableDatabase();
    }


    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        if (newCatDb == null) {
            InitDb();
        }

        switch (matcher.match(uri)) {
            case MATCH_CAT:
                cursor = newCatDb.query(DataBaseCat.TABLE_CAT, projection, selection, selectionArgs, null, null, sortOrder, null);
                break;
            case MATCH_ADOPTER:
                cursor = newCatDb.query(DataBaseAdopter.TABLE_ADOPTER, projection, selection, selectionArgs, null, null, sortOrder, null);
                break;
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        long rowID = 0;
        if (newCatDb == null) {InitDb();}
        Uri mUri = null;
        switch (matcher.match(uri)) {
            case MATCH_CAT:
                rowID = newCatDb.insert(DataBaseCat.TABLE_CAT, null, values);
                if (rowID > 0) {
                    mUri = ContentUris.withAppendedId(DataBaseCat.CONTENT_URI_CAT, rowID);
                }
                break;
            case MATCH_ADOPTER:
                rowID = newCatDb.insert(DataBaseAdopter.TABLE_ADOPTER, null, values);
                if (rowID > 0) {
                    mUri = ContentUris.withAppendedId(DataBaseAdopter.CONTENT_URI_ADOPTER, rowID);
                }
                break;
        }

        return mUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (newCatDb == null) {InitDb();}
        int count = 0;
        switch (matcher.match(uri)) {
            case MATCH_CAT:
                count = newCatDb.delete(DataBaseCat.TABLE_CAT, selection, selectionArgs);
                break;
            case MATCH_ADOPTER:
                count = newCatDb.delete(DataBaseAdopter.TABLE_ADOPTER, selection, selectionArgs);
                break;
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (newCatDb == null) {InitDb();}
        int count = 0;
        switch (matcher.match(uri)) {
            case MATCH_CAT:
                count = newCatDb.update(DataBaseCat.TABLE_CAT, values, selection, selectionArgs);
                break;
            case MATCH_ADOPTER:
                count = newCatDb.update(DataBaseAdopter.TABLE_ADOPTER, values, selection, selectionArgs);
                break;
        }
        return count;
    }

    class CatDataHelper extends SQLiteOpenHelper {

        public CatDataHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DataBaseCat.TABLE_CAT + "(" +
                    DataBaseCat._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DataBaseCat.WEIGHT + " TEXT NOT NULL, " +
                    DataBaseCat.BIRTH + " INTEGER, " +
                    DataBaseCat.ADOPTION + " INTEGER, " +
                    DataBaseCat.COLOR + " INTEGER, " +
                    DataBaseCat.VACCINE_NAME + " TEXT NOT NULL, " +
                    DataBaseCat.ABOUT + " TEXT, " +
                    DataBaseCat.OTHER + " TEXT, " +
                    DataBaseCat.VACCINE + " boolean DEFAULT 0, " +
                    DataBaseCat.LIGATION + " boolean DEFAULT 0, " +
                    DataBaseCat.BLOOD_TEST + " boolean DEFAULT 0, " +
                    DataBaseCat.DEWORM + " boolean DEFAULT 0, " +
                    DataBaseCat.EARS_CLEANED + " boolean DEFAULT 0, " +
                    DataBaseCat.NAILS_CUTTED + " boolean DEFAULT 0, " +
                    DataBaseCat.ANTIPARASITE + " boolean DEFAULT 0, " +
                    DataBaseCat.MIXED + " boolean DEFAULT 0, " +
                    DataBaseCat.SEXUALITY + " boolean DEFAULT 0, " +
                    DataBaseCat.ALL_CHECK + " boolean DEFAULT 0, " +
                    DataBaseCat.CAT_IMG + " INTEGER, " +
                    DataBaseCat.CAT_IMG2 + " INTEGER, " +
                    DataBaseCat.CAT_IMG3 + " INTEGER, " +
                    DataBaseCat.ADOPTER_NAME + " TEXT, " +
                    "UNIQUE(" + DataBaseCat._ID + ")" + ");" );
            db.execSQL("CREATE TABLE " + DataBaseAdopter.TABLE_ADOPTER + "(" +
                    DataBaseAdopter._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DataBaseAdopter.NAME + " TEXT NOT NULL, " +
                    DataBaseAdopter.CITY + " INTEGER, " +
                    DataBaseAdopter.ADDRESS + " TEXT, " +
                    DataBaseAdopter.FAMILY_MEMBERS + " TEXT, " +
                    DataBaseAdopter.ENVIRONMENT + " TEXT, " +
                    DataBaseAdopter.ADOPTER_ID + " TEXT, " +
                    DataBaseAdopter.ADOPTER_BIRTHDAY + " TEXT, " +
                    DataBaseAdopter.ADOPTION_DATE + " TEXT, " +
                    DataBaseAdopter.CONTACT_NUMBER + " TEXT, " +
                    DataBaseAdopter.PREDICTED_EXPENSE + " TEXT, " +
                    DataBaseAdopter.CATS_AT_HOME + " TEXT, " +
                    DataBaseAdopter.FAMILY_AGREE + " boolean DEFAULT 0, " +
                    DataBaseAdopter.ADOPTER_SEXUALITY + " boolean DEFAULT 0, " +
                    DataBaseAdopter.CAT_IMG + " INTEGER, " +
                    DataBaseAdopter.CAT_IMG2 + " INTEGER, " +
                    DataBaseAdopter.CAT_IMG3 + " INTEGER, " +
                    "UNIQUE(" + DataBaseAdopter._ID + ")" + ");" );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
