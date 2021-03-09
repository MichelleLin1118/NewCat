package com.example.newcat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActivityAdopter extends Activity implements View.OnClickListener {
    String TAG = "homework";
    ImageButton messenger, fb, home;
    EditText name, address, familyMembers, environment, id, birthday, adoptDate, contactNumber, predictedExpense, catsAtHome;
    CheckBox familyAgree;
    ToggleButton sexuality;
    ViewPager pager;
    
    ArrayList<View> adopterPageArrayList = new ArrayList<>();
    ArrayList<DataAdopter> data = new ArrayList<>();
    int index = 0;
    int location;
    AdopterCheckBoxListener mAdopterCheckBoxListener = new AdopterCheckBoxListener();
    AdopterActivityAdapter mAdopterActivityAdapter = new AdopterActivityAdapter(this);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    private void initAdopterData() {
        data.add(new DataAdopter());
    }
    
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_adopter);
        initAdopterData();


        pager= (ViewPager) findViewById(R.id.view_pager_adopter);
        for (int i = 0; i < 3 ; i++) {
            adopterPageArrayList.add(LayoutInflater.from(this).inflate(R.layout.view_pager_adopter, null));
        }
        pager.setAdapter(mAdopterActivityAdapter);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

            @Override
            public void onPageScrolled(int position,float positionOffSet,int posotionOffSetPixels){
                Log.i(TAG, "onPageScrolled = " + position);
            }
            @Override
            public void onPageSelected ( int position){
                Log.i(TAG, "onPageSelected = " + position);
                location = position;
                Log.i(TAG, "location = " + location);
                mAdopterActivityAdapter.notifyDataSetChanged();
            }
            @Override
            public void onPageScrollStateChanged ( int state){

            }
        });

        //Access db
        ContentValues values = new ContentValues();
        values.put(DataBaseAdopter._ID, 1);
        values.put(DataBaseAdopter.NAME, "James");
        values.put(DataBaseAdopter.FAMILY_AGREE, true);
        getContentResolver().insert(DataBaseAdopter.CONTENT_URI_ADOPTER, values);

        Cursor cursor = getContentResolver().query(DataBaseAdopter.CONTENT_URI_ADOPTER, null, null, null, null );
        while (cursor.moveToNext()) {
            Log.i(TAG, "db" + cursor.getString(0));
            Log.i(TAG, "db" + cursor.getString(1));
            Log.i(TAG, "db" + cursor.getString(2));

        }

    }

    @Override
    public void onClick(View v) {
        if (v == home){
            Intent homeIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.MainActivity");
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

    class AdopterActivityAdapter extends PagerAdapter implements View.OnClickListener {
        
        Context context;
        public AdopterActivityAdapter(Context context){
            this.context = context;
        }

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

            familyAgree = (CheckBox) adopterPageArrayList.get(position).findViewById(R.id.adopter_familyAgree);
            familyAgree.setChecked(data.get(position).getFamilyAgree());
            familyAgree.setOnClickListener(this);
            familyAgree.setTag("ActivityAdopter" +location + "familyAgree");

            name = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_name));
            name.setText(data.get(position).getName());
            address = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_address));
            address.setText(data.get(position).getName());
            familyMembers = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_familyMembers));
            familyMembers.setText(data.get(position).getfamilyMembers());
            environment = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_environment));
            environment.setText(data.get(position).getEnvironment());
            id = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_id));
            id.setText(data.get(position).getId());
            birthday = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_birthday));
            birthday.setText(sdf.format(data.get(position).getBirthday()));
            adoptDate = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_adoptionDate));
            adoptDate.setText(sdf.format(data.get(position).getAdoptDate()));
            contactNumber = (EditText) adopterPageArrayList.get(position).findViewById(R.id.adopter_contact);
            contactNumber.setText(Integer.toString(data.get(position).getContactNumber()));
            predictedExpense = (EditText) adopterPageArrayList.get(position).findViewById(R.id.adopter_predictedExpense);
            predictedExpense.setText(Integer.toString(data.get(position).getPredictedExpense()));
            catsAtHome = (EditText) adopterPageArrayList.get(position).findViewById(R.id.adopter_catsAtHome);
            catsAtHome.setText(data.get(position).getCatsAtHome());


            return adopterPageArrayList.get(position);
        }
        @Override
        public void onClick(View v) {
            if (v ==  pager.findViewWithTag("ActivityAdopter" +location + "familyAgree")) {
                Log.i(TAG, "familyAgree check");
                ((CheckBox)pager.findViewWithTag("ActivityAdopter" +location + "familyAgree")).setChecked(true);

            }
        }
    }
}
