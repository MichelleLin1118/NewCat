package com.example.newcat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ActivityMain extends Activity implements View.OnClickListener{

    String TAG = "homework";
    ImageButton cat, info, home, adop, cal, search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_main);

        cat = (ImageButton) findViewById(R.id.cat_button);
        cat.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        cat.setOnClickListener(this);

        cal = (ImageButton) findViewById((R.id.calendar_button));
        cal.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        cal.setOnClickListener(this);

        info = (ImageButton) findViewById(R.id.info_button);
        info.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        info.setOnClickListener(this);

        adop = (ImageButton) findViewById(R.id.adopter_button);
        adop.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        adop.setOnClickListener(this);


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
        if (v == info){
            Intent infoIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityInfo");
            infoIntent.setComponent(cn);
            startActivity(infoIntent);
        }

        if (v == adop){
            Intent adopIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityAdopter");
            adopIntent.setComponent(cn);
            startActivity(adopIntent);
        }

        if (v == cal){
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
            Intent searchIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivitySearch");
            searchIntent.setComponent(cn);
            startActivity(searchIntent);
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