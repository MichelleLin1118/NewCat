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
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
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
    Button saveButton;
    ImageButton messenger, fb, home;
    EditText adopterName, address, familyMembers, environment, adopterId, birthday, adoptDate, contactNumber, predictedExpense, catsAtHome;
    CheckBox familyAgree;
    ToggleButton sexuality;
    ViewPager pager;
    
    ArrayList<View> adopterPageArrayList = new ArrayList<>();
    DataBaseUtils dataBaseUtils;
    ArrayList<DataBaseAdopter> data;
    int location, globalPosition;
    AdopterActivityAdapter mAdopterActivityAdapter = new AdopterActivityAdapter(this);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    private void initAdopterData() {
        data.add(new DataBaseAdopter());
    }
    
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, "adopter onCreate");
        setContentView(R.layout.activity_adopter);

        dataBaseUtils = new DataBaseUtils(this);
        data = dataBaseUtils.getAdopterDataFromDB();

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
                data = dataBaseUtils.getAdopterDataFromDB();
                mAdopterActivityAdapter.notifyDataSetChanged();
            }
            @Override
            public void onPageScrollStateChanged ( int state){

            }
        });

        dataBaseUtils.showAdopDataBaseResult();

    }

    @Override
    public void onClick(View v) {
        if (v == home){
            Intent homeIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.MainActivity");
            homeIntent.setComponent(cn);
            startActivity(homeIntent);
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
            globalPosition = position;
            ((ViewPager) container).addView(adopterPageArrayList.get(position));

//            messenger = (ImageButton) adopterPageArrayList.get(position).findViewById(R.id.adopter_messenger_button);
//            messenger.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//            messenger.setTag("ActivityAdopter" +position + "messenger");
//            pager.findViewWithTag("ActivityAdopter" +position + "messenger").setOnClickListener(this);
//            fb = (ImageButton) adopterPageArrayList.get(position).findViewById((R.id.adopter_fb_button));
//            fb.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
//            fb.setTag("ActivityAdopter" +position + "fb");
//            pager.findViewWithTag("ActivityAdopter" +position + "fb").setOnClickListener(this);

            sexuality = (ToggleButton) adopterPageArrayList.get(position).findViewById(R.id.sexuality_button);
            familyAgree = (CheckBox) adopterPageArrayList.get(position).findViewById(R.id.adopter_familyAgree);
            saveButton = (Button) adopterPageArrayList.get(position).findViewById(R.id.save_button);

            sexuality.setText("unknown");
            sexuality.setTextOff("male");
            sexuality.setTextOn("female");

            adopterName = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_name));
            adopterName.setText(data.get(position).getName());
            address = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_address));
            address.setText(data.get(position).getAddr());
            familyMembers = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_familyMembers));
            familyMembers.setText(data.get(position).getFamilyMembers());
            environment = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_environment));
            environment.setText(data.get(position).getEnvironment());
            adopterId = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_id));
            adopterId.setText(data.get(position).getAdopterId());
            birthday = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_birthday));
            birthday.setText(sdf.format(data.get(position).getBirthday()));
            adoptDate = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_adoptionDate));
            adoptDate.setText(sdf.format(data.get(position).getAdoptDate()));
            contactNumber = (EditText) adopterPageArrayList.get(position).findViewById(R.id.adopter_contact);
            contactNumber.setText(data.get(position).getContactNumber());
            predictedExpense = (EditText) adopterPageArrayList.get(position).findViewById(R.id.adopter_predictedExpense);
            predictedExpense.setText(data.get(position).getPredictedExpense());
            catsAtHome = (EditText) adopterPageArrayList.get(position).findViewById(R.id.adopter_catsAtHome);
            catsAtHome.setText(data.get(position).getCatsAtHome());

            sexuality.setTag("ActivityAdopter" +position + "sexuality");
            familyAgree.setTag("ActivityAdopter" +position + "familyAgree");
            saveButton.setTag("ActivityAdopter" +position + "saveButton");
            adopterName.setTag("ActivityAdopter" +position + "adopterName");
            address.setTag("ActivityAdopter" +position + "address");
            familyMembers.setTag("ActivityAdopter" +position + "familyMembers");
            environment.setTag("ActivityAdopter" +position + "environment");
            adopterId.setTag("ActivityAdopter" +position + "adopterId");
            birthday.setTag("ActivityAdopter" +position + "birthday");
            adoptDate.setTag("ActivityAdopter" +position + "adopDate");
            contactNumber.setTag("ActivityAdopter" +position + "contactNumber");
            predictedExpense.setTag("ActivityAdopter" +position + "predictedExpense");
            catsAtHome.setTag("ActivityAdopter" +position + "catsAtHome");
            pager.findViewWithTag("ActivityAdopter" +position + "familyAgree").setOnClickListener(this);
            pager.findViewWithTag("ActivityAdopter" +position + "saveButton").setOnClickListener(this);

            ((CheckBox)(pager.findViewWithTag("ActivityAdopter" +position + "familyAgree"))).setChecked(data.get(position).getFamilyAgree());
//            ((EditText)(pager.findViewWithTag("ActivityAdopter" +position + "adopterName").setText(data.get(position).getName())));
//            ((EditText)(pager.findViewWithTag("ActivityAdopter" +position + "address").setText(data.get(position).getAddr())));
//            ((EditText)(pager.findViewWithTag("ActivityAdopter" +position + "familyMembers").setText(data.get(position).getFamilyMembers())));
//            ((EditText)(pager.findViewWithTag("ActivityAdopter" +position + "environment").setText(data.get(position).getEnvironment())));
//            ((EditText)(pager.findViewWithTag("ActivityAdopter" +position + "adopterId").setText(data.get(position).getAdopterId())));
//            ((EditText)(pager.findViewWithTag("ActivityAdopter" +position + "birthday").setText(data.get(position).getBirthday())));
//            ((EditText)(pager.findViewWithTag("ActivityAdopter" +position + "adopDate").setText(data.get(position).getAdoptDate())));
//            ((EditText)(pager.findViewWithTag("ActivityAdopter" +position + "contactNumber").setText(data.get(position).getContactNumber())));
//            ((EditText)(pager.findViewWithTag("ActivityAdopter" +position + "predictedExpense").setText(data.get(position).getPredictedExpense())));
//            ((EditText)(pager.findViewWithTag("ActivityAdopter" +position + "catsAtHome").setText(data.get(position).getCatsAtHome())));

            return adopterPageArrayList.get(position);
        }
        @Override
        public void onClick(View v) {
            if (v ==  pager.findViewWithTag("ActivityAdopter" +location + "familyAgree")) {
                Log.i(TAG, "familyAgree check");
                ((CheckBox)pager.findViewWithTag("ActivityAdopter" +location + "familyAgree")).setChecked(true);
            }
            if(v == pager.findViewWithTag("ActivityCat" + location + "saveButton")) {
                int id = location + 1;
                ContentValues values = new ContentValues();

                values.put(DataBaseAdopter.NAME, (((EditText)(pager.findViewWithTag("ActivityAdopter" + location + "adopterName"))).getText().toString()));
                values.put(DataBaseAdopter.ADDRESS, (((EditText)(pager.findViewWithTag("ActivityAdopter" + location + "address"))).getText().toString()));
                values.put(DataBaseAdopter.FAMILY_MEMBERS, (((EditText)(pager.findViewWithTag("ActivityAdopter" + location + "familyMembers"))).getText().toString()));
                values.put(DataBaseAdopter.ENVIRONMENT, (((EditText)(pager.findViewWithTag("ActivityAdopter" + location + "environment"))).getText().toString()));
                values.put(DataBaseAdopter.ADOPTER_ID, (((EditText)(pager.findViewWithTag("ActivityAdopter" + location + "adopterId"))).getText().toString()));
                values.put(DataBaseAdopter.ADOPTER_BIRTHDAY, (((EditText)(pager.findViewWithTag("ActivityAdopter" + location + "birthday"))).getText().toString()));
                values.put(DataBaseAdopter.ADOPTION_DATE, (((EditText)(pager.findViewWithTag("ActivityAdopter" + location + "adopDate"))).getText().toString()));
                values.put(DataBaseAdopter.CONTACT_NUMBER, (((EditText)(pager.findViewWithTag("ActivityAdopter" + location + "contactNumber"))).getText().toString()));
                values.put(DataBaseAdopter.PREDICTED_EXPENSE, (((EditText)(pager.findViewWithTag("ActivityAdopter" + location + "predictedExpense"))).getText().toString()));
                values.put(DataBaseAdopter.CATS_AT_HOME, (((EditText)(pager.findViewWithTag("ActivityAdopter" + location + "catsAtHome"))).getText().toString()));
                values.put(DataBaseAdopter.FAMILY_AGREE, (((CheckBox)(pager.findViewWithTag("ActivityAdopter" + location + "familyAgree"))).isChecked()));
                //values.put(DataBaseAdopter.ADOPTER_SEXUALITY, (((ToggleButton)(pager.findViewWithTag("ActivityAdopter" + location + "sexuality"))).isChecked()));

                getContentResolver().update(DataBaseAdopter.CONTENT_URI_ADOPTER, values, DataBaseAdopter._ID + " = " + id, null);
                dataBaseUtils.showAdopDataBaseResult();
            }
//            if(v == pager.findViewWithTag("ActivityAdopter" +location + "messenger" )){
//                Intent messengerIntent = new Intent(Intent.ACTION_VIEW);
//                messengerIntent.setData(Uri.parse("fb://"));
//            }
//            if (v == pager.findViewWithTag("ActivityAdopter" +location + "fb" )) {
//                Intent fbIntent = new Intent(Intent.ACTION_VIEW);
//                fbIntent.setData(Uri.parse("https://www.facebook.com/")); //+getFbId() in adopter db
//            }
        }
    }
}
