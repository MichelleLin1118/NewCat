package com.example.newcat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ActivityMain extends Activity implements View.OnClickListener{

    String TAG = "homework";
    ImageButton cat, information, home, adopter, calendar, search;
    DataBaseUtils dataBaseUtils = new DataBaseUtils(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        cat = (ImageButton) findViewById(R.id.cat_button);
        cat.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        cat.setOnClickListener(this);

        calendar= (ImageButton) findViewById((R.id.calendar_button));
        calendar.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        calendar.setOnClickListener(this);

        information = (ImageButton) findViewById(R.id.info_button);
        information.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        information.setOnClickListener(this);

        adopter = (ImageButton) findViewById(R.id.adopter_button);
        adopter.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        adopter.setOnClickListener(this);


        home = (ImageButton) findViewById((R.id.home_button));
        home.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        home.setOnClickListener(this);

        search = (ImageButton) findViewById((R.id.search_button));
        search.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        search.setOnClickListener(this);



//        ImageUtils im = new ImageUtils();
//
//        im.calculateDisplay(this);
//        ViewGroup.LayoutParams cat_layout= cat.getLayoutParams();
//        cat_layout.width = im.getDisplayWidth() / 3;
//        cat_layout.height = im.getDisplayWidth() / 3;
//        cat.setLayoutParams(cat_layout);
//        cat.setOnClickListener(this);

//        //Access db
//        ContentValues values = new ContentValues();
//        //values.put(DataBaseCat._ID, 1);
//        values.put(DataBaseCat.WEIGHT, "3");
//        values.put(DataBaseCat.ALL, true);
//        getContentResolver().insert(DataBaseCat.CONTENT_URI_CAT, values);
//
//        Cursor cursor = getContentResolver().query(DataBaseCat.CONTENT_URI_CAT, null, null, null, null );
//        while (cursor.moveToNext()) {
//            Log.i(TAG, "db" + cursor.getString(0));
//            Log.i(TAG, "db" + cursor.getString(1));
//            Log.i(TAG, "db" + cursor.getString(2));
//
//        }

    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "onStart");

    }
    @Override
    public void onClick(View v) {
        if (v == cat){
            Intent catIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityCat");
            catIntent.setComponent(cn);
            startActivity(catIntent);

        }
        if (v == information){
            Intent informationIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityInformation");
            informationIntent.setComponent(cn);
            startActivity(informationIntent);
        }

        if (v == adopter){
            Intent adopterIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityAdopter");
            adopterIntent.setComponent(cn);
            startActivity(adopterIntent);
        }

        if (v == calendar){
            Intent calIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityCalendar");
            calIntent.setComponent(cn);
            startActivity(calIntent);
        }
        if (v == home){
            Intent homeIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityMain");
            homeIntent.setComponent(cn);
            startActivity(homeIntent);
        }
        if (v == search) {
            //dataBaseUtils.createCatData(new DataBaseCat());
            getContentResolver().insert(DataBaseCat.CONTENT_URI_CAT, dataBaseUtils.createCatData(new DataBaseCat()));
            getContentResolver().insert(DataBaseCat.CONTENT_URI_CAT, dataBaseUtils.createCatData(new DataBaseCat()));
            getContentResolver().insert(DataBaseAdopter.CONTENT_URI_ADOPTER, dataBaseUtils.createAdopterData(new DataBaseAdopter()));
            getContentResolver().insert(DataBaseAdopter.CONTENT_URI_ADOPTER, dataBaseUtils.createAdopterData(new DataBaseAdopter()));

            DataBaseCat cat = new DataBaseCat();
            DataBaseAdopter adop = new DataBaseAdopter();
            Log.i(TAG, "------------------------------------------");
            dataBaseUtils.showCatDataBaseResult();
            Log.i(TAG, "------------------------------------------");
            dataBaseUtils.showAdopDataBaseResult();
            Log.i(TAG, "------------------------------------------");


//            Intent searchIntent = new Intent();
//            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivitySearch");
//            searchIntent.setComponent(cn);
//            startActivity(searchIntent);
        }


    }

    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG, "onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG, "onStop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

}