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

public class ContentProviderAdopter extends ContentProvider {

    public static String DATABASE_NAME_ADOPTER = "adopter.db";
    private static UriMatcher matcher;
    private static final int MATCH_ADOPTER = 100;

    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(DataBaseAdopter.AUTHORITY, DataBaseAdopter.TABLE_ADOPTER, MATCH_ADOPTER);
    }
    private SQLiteDatabase adpDb;

    private void InitDb() {
        AdopterDataHelper adpDataHelper = new AdopterDataHelper(getContext());
        adpDb = adpDataHelper.getWritableDatabase();
    }


    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        if (adpDb == null) {
            InitDb();
        }

        cursor = adpDb.query(DataBaseAdopter.TABLE_ADOPTER, projection, selection, selectionArgs, null, null, sortOrder, null);
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
        if (adpDb == null) {InitDb();}
        Uri mUri = null;
        rowID = adpDb.insert(DataBaseAdopter.TABLE_ADOPTER, null, values);
        if (rowID > 0) {
            mUri = ContentUris.withAppendedId(DataBaseAdopter.CONTENT_URI_ADOPTER, rowID);
        }
        return mUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (adpDb == null) {InitDb();}
        int count = adpDb.delete(DataBaseAdopter.TABLE_ADOPTER, selection, selectionArgs);

        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (adpDb == null) {InitDb();}
        int count = adpDb.update(DataBaseAdopter.TABLE_ADOPTER, values, selection, selectionArgs);

        return count;
    }

    class AdopterDataHelper extends SQLiteOpenHelper {

        public AdopterDataHelper(Context context) {
            super(context, DATABASE_NAME_ADOPTER, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + DataBaseAdopter.TABLE_ADOPTER + "(" +
                    DataBaseAdopter._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DataBaseAdopter.NAME + " TEXT NOT NULL, " +
                    DataBaseAdopter.ADDRESS + "TEXT" +
                    DataBaseAdopter.FAMILY_MEMBERS + "TEXT" +
                    DataBaseAdopter.ENVIRONMENT + "TEXT" +
                    DataBaseAdopter.ADOPTER_ID + "TEXT" +
                    DataBaseAdopter.ADOPTER_BIRTHDAY + "TEXT" +
                    DataBaseAdopter.ADOPTION_DATE + "TEXT" +
                    DataBaseAdopter.CONTACT_NUMBER + "TEXT" +
                    DataBaseAdopter.PREDICTED_EXPENSE + "TEXT" +
                    DataBaseAdopter.CATS_AT_HOME + "TEXT" +
                    DataBaseAdopter.FAMILY_AGREE + "boolean DEFAULT 0, " +
                    DataBaseAdopter.ADOPTER_SEXUALITY + "boolean DEFAULT 0, " +
                    "UNIQUE(" + DataBaseAdopter._ID + ")" + ");" );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
