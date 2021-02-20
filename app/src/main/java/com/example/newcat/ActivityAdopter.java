package com.example.newcat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

public class ActivityAdopter extends Activity implements View.OnClickListener {
    String TAG = "homework";
    ImageButton messenger, fb, home;
    String name, addr, familyBackground, environment, id;
    Date birthday, adoptDate;
    int phone, expenseOnCat, otherCats;
    CheckBox familyAgree;
    ToggleButton sexuality;
    ViewPager pager;

    ArrayList<View> adopterPageArrayList = new ArrayList<>();
    ArrayList<DataAdopter> data = new ArrayList<>();
    int index = 0;
    AdopterCheckBoxListener mAdopterCheckBoxListener = new AdopterCheckBoxListener();
    AdopterFragmentAdapter mAdopterFragmentAdapter = new AdopterFragmentAdapter();


    private void initAdopterData() {
        data.add(new DataAdopter());
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_adopter);
        initAdopterData();


        pager= (ViewPager) findViewById(R.id.view_pager_adopter);
        //catDataArrayList.add(LayoutInflater.from(getActivity()).inflate(R.layout.view_pager_adopter, null));
        for (int i = 0; i < 3 ; i++) {
            adopterPageArrayList.add(LayoutInflater.from(this).inflate(R.layout.view_pager_adopter, null));
        }
        pager.setAdapter(mAdopterFragmentAdapter);

    }

    @Override
    public void onClick(View v) {
        if (v == home){
            Intent homeIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.cat", "com.example.cat.MainActivity");
            homeIntent.setComponent(cn);
            startActivity(homeIntent);
        }
        if(v == messenger){
            Intent messengerIntent = new Intent(Intent.ACTION_VIEW);
            messengerIntent.setData(Uri.parse("fb://"));
        }
        if (v == fb) {
            Intent fbIntent = new Intent(Intent.ACTION_VIEW);
            fbIntent.setData(Uri.parse("https://www.facebook.com/")); //+getFbId() in adopter db
        }
    }

    class AdopterCheckBoxListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton v, boolean isChecked) {
            if (v == familyAgree) {
                data.get(index).setFamilyAgree(isChecked);
            }
        }
    }

    class AdopterFragmentAdapter extends PagerAdapter implements View.OnClickListener {

        @Override
        public int getCount() {
            return adopterPageArrayList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(adopterPageArrayList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            index = position;
            ((ViewPager) container).addView(adopterPageArrayList.get(position));

            messenger = (ImageButton) findViewById(R.id.adopter_messenger_button);
            messenger.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            messenger.setOnClickListener(this);

            fb = (ImageButton) findViewById((R.id.adopter_fb_button));
            fb.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            fb.setOnClickListener(this);

            sexuality = (ToggleButton) findViewById(R.id.sexuality_button);
            sexuality.setText("unknown");
            sexuality.setTextOff("male");
            sexuality.setTextOn("female");

            return adopterPageArrayList.get(position);
        }
        @Override
        public void onClick(View v) {
            DataAdopter dataAdopter = new DataAdopter();
            if (familyAgree.isChecked()) {
                Log.i(TAG, "vac check");
                dataAdopter.setFamilyAgree(true);
            }
        }
    }
}
