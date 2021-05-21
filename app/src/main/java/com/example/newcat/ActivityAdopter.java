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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    Button saveButton, adoptionContract;
    ImageButton messenger, fb, home, addPageButton;
    EditText adopterName, address, familyMembers, environment, adopterId, birthday, adoptDate, contactNumber, predictedExpense, catsAtHome;
    CheckBox familyAgree;
    ToggleButton sexuality;
    ViewPager pager;
    Spinner city;
    ImageView catImg;
    
    ArrayList<View> adopterPageArrayList = new ArrayList<>();
    DataBaseUtils dataBaseUtils;
    ArrayList<DataBaseAdopter> data;
    //ArrayList<DataBaseCat> dataCat;
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
        //dataCat = dataBaseUtils.getCatDataFromDB();

        home = (ImageButton) findViewById((R.id.home_button));
        home.setOnClickListener(this);

        addPageButton = (ImageButton) findViewById(R.id.add_page_button);
        addPageButton.setOnClickListener(this);

        pager= (ViewPager) findViewById(R.id.view_pager_adopter);
        for (int i = 0; i < data.size() ; i++) {
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
        openSpecificPage();
    }

    @Override
    public void onClick(View v) {
        if (v == home){
            Intent homeIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.MainActivity");
            homeIntent.setComponent(cn);
            startActivity(homeIntent);
        }
        if (v == addPageButton) {
            getContentResolver().insert(DataBaseAdopter.CONTENT_URI_ADOPTER, dataBaseUtils.createAdopterData(new DataBaseAdopter()));
            mAdopterActivityAdapter.notifyDataSetChanged();
            for (int i = 0; i < data.size() ; i++) {
                adopterPageArrayList.add(LayoutInflater.from(this).inflate(R.layout.view_pager_adopter, null));
            }
        }
    }
    public void openSpecificPage() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            Log.i(TAG, "--------------------------------- not null --> open Specific page = " + bundle.getInt("page"));
            int page = bundle.getInt("page");
            pager.setCurrentItem(page-1, false);
        } else {
            Log.i(TAG, "++++++++++++++++++++++++++++++++ null --> open last page = ");
            pager.setCurrentItem(data.size()-1, false);
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
        public int getItemPosition(Object object){return POSITION_NONE;}

        private AdapterView.OnItemSelectedListener mSpinSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        };

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            globalPosition = position;
            ((ViewPager) container).addView(adopterPageArrayList.get(position));

            sexuality = (ToggleButton) adopterPageArrayList.get(position).findViewById(R.id.sexuality_button);
            familyAgree = (CheckBox) adopterPageArrayList.get(position).findViewById(R.id.adopter_familyAgree);
            saveButton = (Button) adopterPageArrayList.get(position).findViewById(R.id.save_button);
            adoptionContract = (Button) adopterPageArrayList.get(position).findViewById(R.id.adoption_contract);
            city = (Spinner) adopterPageArrayList.get(position).findViewById(R.id.spinner_address);
            catImg = (ImageView) adopterPageArrayList.get(position).findViewById(R.id.adopter_cat_img);

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
            birthday.setText(data.get(position).getBirthday());
            adoptDate = (EditText) adopterPageArrayList.get(position).findViewById((R.id.adopter_adoptionDate));
            adoptDate.setText(data.get(position).getAdoptDate());
            contactNumber = (EditText) adopterPageArrayList.get(position).findViewById(R.id.adopter_contact);
            contactNumber.setText(data.get(position).getContactNumber());
            predictedExpense = (EditText) adopterPageArrayList.get(position).findViewById(R.id.adopter_predictedExpense);
            predictedExpense.setText(data.get(position).getPredictedExpense());
            catsAtHome = (EditText) adopterPageArrayList.get(position).findViewById(R.id.adopter_catsAtHome);
            catsAtHome.setText(data.get(position).getCatsAtHome());

            sexuality.setTag("ActivityAdopter" +position + "sexuality");
            familyAgree.setTag("ActivityAdopter" +position + "familyAgree");
            saveButton.setTag("ActivityAdopter" +position + "saveButton");
            adoptionContract.setTag("ActivityAdopter" +position + "adoptionContract");
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
            city.setTag("ActivityAdopter" +position + "city");
            catImg.setTag("ActivityAdopter" +position + "catImg");
            findTagFunction(position + "familyAgree").setOnClickListener(this);
            findTagFunction(position + "saveButton").setOnClickListener(this);
            findTagFunction(position + "adoptionContract").setOnClickListener(this);
            findTagFunction(position + "catImg").setOnClickListener(this);

            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(context, R.array.address_array, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            city.setAdapter(arrayAdapter);
            city.setSelection(0, false);
            city.setOnItemSelectedListener(mSpinSelectedListener);
            sexuality.setTextOff("male");
            sexuality.setTextOn("female");

            ((CheckBox)findTagFunction(position + "familyAgree")).setChecked(data.get(position).getFamilyAgree());
            ((EditText)findTagFunction(position + "adopterName")).setText(data.get(position).getName());
            ((EditText)findTagFunction(position + "address")).setText(data.get(position).getAddr());
            ((EditText)findTagFunction(position + "familyMembers")).setText(data.get(position).getFamilyMembers());
            ((EditText)findTagFunction(position + "environment")).setText(data.get(position).getEnvironment());
            ((EditText)findTagFunction(position + "adopterId")).setText(data.get(position).getAdopterId());
            ((EditText)findTagFunction(position + "birthday")).setText(data.get(position).getBirthday());
            ((EditText)findTagFunction(position + "adopDate")).setText(data.get(position).getAdoptDate());
            ((EditText)findTagFunction(position + "contactNumber")).setText(data.get(position).getContactNumber());
            ((EditText)findTagFunction(position + "predictedExpense")).setText(data.get(position).getPredictedExpense());
            ((EditText)findTagFunction(position + "catsAtHome")).setText(data.get(position).getCatsAtHome());
            ((Spinner)findTagFunction(position + "city")).setSelection(data.get(position).getCity());
            //((ImageView)findTagFunction(position + "catImg")).setImageResource(data.get(position).getCatImg());

            return adopterPageArrayList.get(position);
        }
        @Override
        public void onClick(View v) {
            if(v == findTagFunction(location + "catImg")){
//                Intent intent = new Intent();
//                ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityCat");
//                Bundle bundle = new Bundle();
//                long id = searchCatArray.get(Integer.valueOf((v.getTag()).toString())).getId();
//                int page = (int)id;
//                bundle.putInt("page", page);
//                intent.putExtras(bundle);
//                intent.setComponent(cn);
//                startActivity(intent);
            }
            if (v ==  findTagFunction(location + "familyAgree")) {
                ((CheckBox)pager.findViewWithTag("ActivityAdopter" +location + "familyAgree")).setChecked(true);
            }
            if (v == findTagFunction(location + "adoptionContract")) {
                Log.i(TAG, "-------adoption Contract clicked");
                Intent intent = new Intent();
                ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityContract");
                intent.setComponent(cn);
                startActivity(intent);
            }
            if(v == findTagFunction(location + "saveButton")) {
                Log.i(TAG, "--------------------save button clicked");
                int id = location + 1;
                ContentValues values = new ContentValues();

                values.put(DataBaseAdopter.NAME, ((EditText)findTagFunction( location + "adopterName")).getText().toString());
                values.put(DataBaseAdopter.ADDRESS, ((EditText)findTagFunction( location + "address")).getText().toString());
                values.put(DataBaseAdopter.FAMILY_MEMBERS, ((EditText)findTagFunction( location + "familyMembers")).getText().toString());
                values.put(DataBaseAdopter.ENVIRONMENT, ((EditText)findTagFunction( location + "environment")).getText().toString());
                values.put(DataBaseAdopter.ADOPTER_ID, ((EditText)findTagFunction( location + "adopterId")).getText().toString());
                values.put(DataBaseAdopter.ADOPTER_BIRTHDAY, ((EditText)findTagFunction( location + "birthday")).getText().toString());
                values.put(DataBaseAdopter.ADOPTION_DATE, ((EditText)findTagFunction( location + "adopDate")).getText().toString());
                values.put(DataBaseAdopter.CONTACT_NUMBER, ((EditText)findTagFunction(location + "contactNumber")).getText().toString());
                values.put(DataBaseAdopter.PREDICTED_EXPENSE, ((EditText)findTagFunction( location + "predictedExpense")).getText().toString());
                values.put(DataBaseAdopter.CATS_AT_HOME, ((EditText)findTagFunction( location + "catsAtHome")).getText().toString());
                values.put(DataBaseAdopter.FAMILY_AGREE, ((CheckBox)findTagFunction( location + "familyAgree")).isChecked());
                values.put(DataBaseAdopter.ADOPTER_SEXUALITY, ((ToggleButton)findTagFunction(location + "sexuality")).isChecked());
                values.put(DataBaseAdopter.CITY, ((Spinner)findTagFunction(location + "city")).getSelectedItemPosition());

                getContentResolver().update(DataBaseAdopter.CONTENT_URI_ADOPTER, values, DataBaseAdopter._ID + " = " + id, null);
                dataBaseUtils.showAdopDataBaseResult();
            }
        }
        public View findTagFunction(String tag) {
            return pager.findViewWithTag("ActivityAdopter"+tag);
        }
    }
}
