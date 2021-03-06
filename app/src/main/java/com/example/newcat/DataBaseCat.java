package com.example.newcat;

import android.net.Uri;

public class DataBaseCat {
    public static String TABLE_CAT = "cat";
    public static String TABLE_CAT_ID = "cat/#";

    public static String _ID = "_id";
    public static String WEIGHT = "weight";
    public static String ALL = "all";

    public static final String AUTHORITY = "com.example.newcat";
    public static final Uri CONTENT_URI_CAT = Uri.parse("content://" + AUTHORITY + "/" + TABLE_CAT);

    private long id;
    private String weight;
    private boolean all;


    public DataBaseCat(long id, String weight, boolean all) {
        this.id = id;
        this.weight = weight;
        this.all = all;
    }

    @Override
    public String toString() {
        return "DataBaseCat id = " + id + "DataBaseCat weight = " + weight + "DataBaseCat all = " + all;
    }


}
