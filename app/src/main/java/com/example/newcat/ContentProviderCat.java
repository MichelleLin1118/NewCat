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

public class ContentProviderCat extends ContentProvider {

    public static String DATABASE_NAME = "cat.db";
    private static UriMatcher matcher;
    private static final int MATCH_CAT = 100;
    static {
        matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(DataBaseCat.AUTHORITY, DataBaseCat.TABLE_CAT, MATCH_CAT);
    }
    private SQLiteDatabase mDb;

    private void InitDb() {
        CatDataHelper catDataHelper = new CatDataHelper(getContext());
        mDb = catDataHelper.getWritableDatabase();
    }


    @Override
    public boolean onCreate() {
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        if (mDb == null) {
            InitDb();
        }

        cursor = mDb.query(DataBaseCat.TABLE_CAT, projection, selection, selectionArgs, null, null, sortOrder, null);
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
        if (mDb == null) {InitDb();}
        Uri mUri = null;
        rowID = mDb.insert(DataBaseCat.TABLE_CAT, null, values);
        if (rowID > 0) {
            mUri = ContentUris.withAppendedId(DataBaseCat.CONTENT_URI_CAT, rowID);
        }
        return mUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (mDb == null) {InitDb();}
        int count = mDb.delete(DataBaseCat.TABLE_CAT, selection, selectionArgs);

        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        if (mDb == null) {InitDb();}
        int count = mDb.update(DataBaseCat.TABLE_CAT, values, selection, selectionArgs);

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
                    DataBaseCat.ALL + "boolean DEFAULT 0, " +
                    "UNIQUE(" + DataBaseCat._ID + ")" + ");" );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
