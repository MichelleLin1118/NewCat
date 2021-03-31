package com.example.newcat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ActivityCat extends Activity implements View.OnClickListener {
    String TAG = "homework";
    Button saveButton;
    ImageButton home;
    CheckBox vaccine, deworm, bloodTest,ligation, antiparasite, nailsCutted, earsCleaned, allCheck, mixed;
    EditText weight, birth, adoptionDate, vaccineName, about,others, colorEdit;
    ImageView catImg, catImg2, catImg3;
    ToggleButton sexuality;
    Spinner color;
    ViewPager pager;

    ArrayList<View> catPageArrayList = new ArrayList<>();
    DataBaseUtils dataBaseUtils;
    ArrayList<DataBaseCat> data;
    int location, globalPosition;
    CatActivityAdapter mCatActivityAdapter = new CatActivityAdapter(this);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//    public ArrayList<Integer> InitCatPicture(int cat1, int cat2, int cat3) {
//        ArrayList<Integer> catPic = new ArrayList<>();
//        catPic.add(cat1);
//        catPic.add(cat2);
//        catPic.add(cat3);
//        return catPic;
//    }


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, "cat onCreate");
        setContentView(R.layout.activity_cat);

        dataBaseUtils = new DataBaseUtils(this);
        data = dataBaseUtils.getCatDataFromDB();

        pager= (ViewPager) findViewById(R.id.view_pager_cat);
        for (int i = 0; i < data.size() ; i++) {
            catPageArrayList.add(LayoutInflater.from(this).inflate(R.layout.view_pager_cat, null));
        }
        pager.setAdapter(mCatActivityAdapter);
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
                data = dataBaseUtils.getCatDataFromDB();
                mCatActivityAdapter.notifyDataSetChanged();
            }
            @Override
            public void onPageScrollStateChanged ( int state){

            }
        });


        dataBaseUtils.showCatDataBaseResult();

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


    class CatActivityAdapter extends PagerAdapter implements View.OnClickListener {

        Context context;
        int pictureIndex;

        public CatActivityAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return catPageArrayList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView(catPageArrayList.get(position));
        }

        @Override
        public int getItemPosition(Object object){return POSITION_NONE;}

        private AdapterView.OnItemSelectedListener mSpinSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i(TAG, "page = " +location + " select spinner" + position);
                //save the data into db
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        };

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.i(TAG,"instantiateItem");
            Log.i(TAG, "location = " + location);
            globalPosition = position;
            pictureIndex = 0;
            ((ViewPager) container).addView(catPageArrayList.get(position));
            sexuality.setText("unknown");
            sexuality.setTextOff("male");
            sexuality.setTextOn("female");

            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(context, R.array.color_array, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            color.setAdapter(arrayAdapter);
            color.setSelection(0, false);
            color.setOnItemSelectedListener(mSpinSelectedListener);

            vaccine = (CheckBox) catPageArrayList.get(position).findViewById(R.id.vaccine);
            deworm = (CheckBox) catPageArrayList.get(position).findViewById(R.id.deworm);
            bloodTest = (CheckBox) catPageArrayList.get(position).findViewById(R.id.blood_test);
            ligation = (CheckBox) catPageArrayList.get(position).findViewById(R.id.ligation);
            antiparasite = (CheckBox) catPageArrayList.get(position).findViewById(R.id.antiparasite);
            earsCleaned = (CheckBox) catPageArrayList.get(position).findViewById(R.id.cleanEar);
            nailsCutted = (CheckBox) catPageArrayList.get(position).findViewById(R.id.cutNails);
            allCheck = (CheckBox) catPageArrayList.get(position).findViewById(R.id.allCheck);
            saveButton = (Button) catPageArrayList.get(position).findViewById(R.id.save_button);

            weight = (EditText) catPageArrayList.get(position).findViewById(R.id.weight);
            birth = (EditText) catPageArrayList.get(position).findViewById((R.id.birth));
            vaccineName= (EditText) catPageArrayList.get(position).findViewById((R.id.vaccineName));
            about= (EditText) catPageArrayList.get(position).findViewById((R.id.about));
            others= (EditText) catPageArrayList.get(position).findViewById((R.id.others_cat));
            adoptionDate= (EditText) catPageArrayList.get(position).findViewById((R.id.adoptionDate));
            catImg = (ImageView) catPageArrayList.get(position).findViewById(R.id.cat1);
            sexuality = (ToggleButton) catPageArrayList.get(position).findViewById(R.id.sexuality_button);
            color = (Spinner) catPageArrayList.get(position).findViewById(R.id.spinner_color);
            colorEdit = (EditText) catPageArrayList.get(position).findViewById(R.id.color_editText);
            mixed = (CheckBox) catPageArrayList.get(position).findViewById(R.id.mixed);


            vaccine.setTag("ActivityCat" +position + "vaccine");
            deworm.setTag("ActivityCat" +position + "deworm");
            bloodTest.setTag("ActivityCat" +position + "bloodTest");
            ligation.setTag("ActivityCat" +position + "ligation");
            antiparasite.setTag("ActivityCat" +position + "antiparasite");
            earsCleaned.setTag("ActivityCat" +position + "earsCleaned");
            nailsCutted.setTag("ActivityCat" +position + "nailsCutted");
            allCheck.setTag("ActivityCat" +position + "allCheck");
            saveButton.setTag("ActivityCat" +position + "saveButton");
            weight.setTag("ActivityCat" +position +"weight");
            birth.setTag("ActivityCat" +position +"birth");
            vaccineName.setTag("ActivityCat" +position +"vaccineName");
            about.setTag("ActivityCat" +position +"about");
            others.setTag("ActivityCat" +position +"others");
            adoptionDate.setTag("ActivityCat" +position +"adoptionDate");
            catImg.setTag("ActivityCat" + position + "catImg");
            sexuality.setTag("ActivityCat" +position +"sexuality");
            color.setTag("ActivityCat" +position +"color");
            colorEdit.setTag("ActivityCat" +position + "colorEdit");
            mixed.setTag("ActivityCat" + position + "mixed");

            pager.findViewWithTag("ActivityCat" +position + "saveButton").setOnClickListener(this);
            pager.findViewWithTag("ActivityCat" +position + "vaccine").setOnClickListener(this);
            pager.findViewWithTag("ActivityCat" +position + "deworm").setOnClickListener(this);
            pager.findViewWithTag("ActivityCat" +position + "bloodTest").setOnClickListener(this);
            pager.findViewWithTag("ActivityCat" +position + "ligation").setOnClickListener(this);
            pager.findViewWithTag("ActivityCat" +position + "antiparasite").setOnClickListener(this);
            pager.findViewWithTag("ActivityCat" +position + "earsCleaned").setOnClickListener(this);
            pager.findViewWithTag("ActivityCat" +position + "nailsCutted").setOnClickListener(this);
            pager.findViewWithTag("ActivityCat" +position + "allCheck").setOnClickListener(this);
            pager.findViewWithTag("ActivityCat" +position + "catImg").setOnClickListener(this);
            pager.findViewWithTag("ActivityCat" +position + "mixed").setOnClickListener(this);


            Log.i(TAG, "=========all = " + data.get(position).getAllCheck());
            ((CheckBox)(pager.findViewWithTag("ActivityCat" +position + "vaccine"))).setChecked(data.get(position).getVac());
            ((CheckBox)(pager.findViewWithTag("ActivityCat" +position + "deworm"))).setChecked(data.get(position).getDew());
            ((CheckBox)(pager.findViewWithTag("ActivityCat" +position + "bloodTest"))).setChecked(data.get(position).getBlood());
            ((CheckBox)(pager.findViewWithTag("ActivityCat" +position + "ligation"))).setChecked(data.get(position).getLig());
            ((CheckBox)(pager.findViewWithTag("ActivityCat" +position + "antiparasite"))).setChecked(data.get(position).getAntiparasite());
            ((CheckBox)(pager.findViewWithTag("ActivityCat" +position + "nailsCutted"))).setChecked(data.get(position).getnailsCutted());
            ((CheckBox)(pager.findViewWithTag("ActivityCat" +position + "earsCleaned"))).setChecked(data.get(position).getearsCleaned());
            ((CheckBox)(pager.findViewWithTag("ActivityCat" +position + "allCheck"))).setChecked(data.get(position).getAllCheck());
            ((CheckBox)(pager.findViewWithTag("ActivityCat" +position + "mixed"))).setChecked(data.get(position).getMixed());
            ((EditText)(pager.findViewWithTag("ActivityCat" +position + "weight"))).setText(data.get(position).getWeight());
            ((EditText)(pager.findViewWithTag("ActivityCat" +position + "birth"))).setText(data.get(position).getBirth());
            ((EditText)(pager.findViewWithTag("ActivityCat" +position + "vaccineName"))).setText(data.get(position).getVaccineName());
            ((EditText)(pager.findViewWithTag("ActivityCat" +position + "about"))).setText(data.get(position).getAbout());
            ((EditText)(pager.findViewWithTag("ActivityCat" +position + "others"))).setText(data.get(position).getOther());
            ((EditText)(pager.findViewWithTag("ActivityCat" +position + "adoptionDate"))).setText(data.get(position).getAdoption());
            ((ImageView)(pager.findViewWithTag("ActivityCat" +position + "catImg"))).setImageResource(data.get(position).getCatPic().get(0));

            allCheckedWithGlobalPosition(data.get(position).getAllCheck());
            mixedCheck(data.get(position).getMixed());
            return catPageArrayList.get(position);
        }



        @Override
        public void onClick(View v) {
            Log.i(TAG, "onClick");
            Log.i(TAG, "v.getTag() = " + v.getTag());

            if (v == pager.findViewWithTag("ActivityCat" +location + "catImg")){
                int co = (pictureIndex+1)%3;
                Log.i(TAG, "----------(pictureIndex+1)%3) = " + co);
                ((ImageView)(pager.findViewWithTag("ActivityCat" +location + "catImg"))).setImageResource(data.get(location).getCatPic().get((pictureIndex+1)%3));
                pictureIndex = pictureIndex + 1;
            }
            if (v == pager.findViewWithTag("ActivityCat" +location + "allCheck")) {
                Log.i(TAG, "allCheck checked");
                if (((CheckBox) pager.findViewWithTag("ActivityCat" + location + "allCheck")).isChecked() == true) {
                    allCheckedWithLocation(true);
                } else {
                    allCheckedWithLocation(false);
                }
            }
            
            if(v == pager.findViewWithTag("ActivityCat" +location + "vaccine")) { ((CheckBox)pager.findViewWithTag("ActivityCat" +location + "vaccine")).setChecked(true); allCheckFalse();}
            if(v == pager.findViewWithTag("ActivityCat" +location + "deworm")) {((CheckBox)pager.findViewWithTag("ActivityCat" +location + "deworm")).setChecked(true);allCheckFalse();}
            if(v == pager.findViewWithTag("ActivityCat" +location + "bloodTest")) {((CheckBox)pager.findViewWithTag("ActivityCat" +location + "bloodTest")).setChecked(true);allCheckFalse();}
            if(v == pager.findViewWithTag("ActivityCat" +location + "ligation")) {((CheckBox)pager.findViewWithTag("ActivityCat" +location + "ligation")).setChecked(true);allCheckFalse();}
            if(v == pager.findViewWithTag("ActivityCat" +location + "antiparasite")) {((CheckBox)pager.findViewWithTag("ActivityCat" +location + "antiparasite")).setChecked(true);allCheckFalse();}
            if(v == pager.findViewWithTag("ActivityCat" +location + "earsCleaned")) {((CheckBox)pager.findViewWithTag("ActivityCat" +location + "earsCleaned")).setChecked(true);allCheckFalse();}
            if(v == pager.findViewWithTag("ActivityCat" +location + "nailsCutted")) {((CheckBox)pager.findViewWithTag("ActivityCat" +location + "nailsCutted")).setChecked(true);allCheckFalse();}

            if(v == pager.findViewWithTag("ActivityCat" + location + "mixed")) {
                if (((CheckBox) pager.findViewWithTag("ActivityCat" + location + "mixed")).isChecked() == true) {
                    mixedCheck(false);
                } else {
                   mixedCheck(true);
                }
            }
            if(v == pager.findViewWithTag("ActivityCat" + location + "saveButton")) {
                int id = location +1;
                Log.i(TAG, ">>>>>>>>>> spinner = " + ((Spinner)(pager.findViewWithTag("ActivityCat" + location + "color"))).getSelectedItemPosition());

                ContentValues values = new ContentValues();
                values.put(DataBaseCat.WEIGHT, (((EditText)(pager.findViewWithTag("ActivityCat" + location + "weight"))).getText().toString()));
                values.put(DataBaseCat.BIRTH, (((EditText)(pager.findViewWithTag("ActivityCat" + location + "birth"))).getText().toString()));
                values.put(DataBaseCat.ADOPTION, (((EditText)(pager.findViewWithTag("ActivityCat" + location + "adoptionDate"))).getText().toString()));
                values.put(DataBaseCat.COLOR, (((Spinner)(pager.findViewWithTag("ActivityCat" + location + "color"))).getSelectedItemPosition()));
                values.put(DataBaseCat.VACCINE_NAME, (((EditText)(pager.findViewWithTag("ActivityCat" + location + "vaccineName"))).getText().toString()));
                values.put(DataBaseCat.ABOUT,(((EditText)(pager.findViewWithTag("ActivityCat" + location + "about"))).getText().toString()));
                values.put(DataBaseCat.OTHER, (((EditText)(pager.findViewWithTag("ActivityCat" + location + "others"))).getText().toString()));
                values.put(DataBaseCat.VACCINE, (((CheckBox)(pager.findViewWithTag("ActivityCat" + location + "vaccine"))).isChecked()));
                values.put(DataBaseCat.LIGATION, (((CheckBox)(pager.findViewWithTag("ActivityCat" + location + "ligation"))).isChecked()));
                values.put(DataBaseCat.DEWORM, (((CheckBox)(pager.findViewWithTag("ActivityCat" + location + "deworm"))).isChecked()));
                values.put(DataBaseCat.BLOOD_TEST,(((CheckBox)(pager.findViewWithTag("ActivityCat" + location + "bloodTest"))).isChecked()));
                values.put(DataBaseCat.EARS_CLEANED, (((CheckBox)(pager.findViewWithTag("ActivityCat" + location + "earsCleaned"))).isChecked()));
                values.put(DataBaseCat.NAILS_CUTTED,(((CheckBox)(pager.findViewWithTag("ActivityCat" + location + "nailsCutted"))).isChecked()));
                values.put(DataBaseCat.ANTIPARASITE,(((CheckBox)(pager.findViewWithTag("ActivityCat" + location + "antiparasite"))).isChecked()));
                values.put(DataBaseCat.MIXED, (((CheckBox)(pager.findViewWithTag("ActivityCat" + location + "mixed"))).isChecked()));
                //values.put(DataBaseCat.SEXUALITY, (((ToggleButton)(pager.findViewWithTag("ActivityCat" + location + "sexuality"))).isChecked()));
                values.put(DataBaseCat.ALL_CHECK, (((CheckBox)(pager.findViewWithTag("ActivityCat" + location + "allCheck"))).isChecked()));
                getContentResolver().update(DataBaseCat.CONTENT_URI_CAT, values, DataBaseCat._ID + " = " + id, null);
                dataBaseUtils.showCatDataBaseResult();
            }

        }
        public void mixedCheck(boolean mixedCalled) {
            if (mixedCalled == true) {
                ((CheckBox)pager.findViewWithTag("ActivityCat" +location + "mixed")).setChecked(true);
                ((EditText)pager.findViewWithTag("ActivityCat" +location + "colorEdit")).setVisibility(View.GONE);
                ((Spinner)pager.findViewWithTag("ActivityCat" +location +"color")).setVisibility(View.VISIBLE);
            } else {
                ((CheckBox)pager.findViewWithTag("ActivityCat" +location + "mixed")).setChecked(false);
                ((EditText)pager.findViewWithTag("ActivityCat" +location + "colorEdit")).setVisibility(View.VISIBLE);
                ((Spinner)pager.findViewWithTag("ActivityCat" +location +"color")).setVisibility(View.GONE);
                allCheckFalse();
            }
        }
        public void allCheckFalse () {
            ((CheckBox)pager.findViewWithTag("ActivityCat" +location + "allCheck")).setChecked(false);
        }
        public void allCheckedWithGlobalPosition(boolean allCalled) {
            Log.i(TAG, "+++++++++ all globalPosition = " + globalPosition + " all Called: " + allCalled);
            if (allCalled == true) {
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "mixed")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "vaccine")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "deworm")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "bloodTest")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "ligation")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "antiparasite")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "earsCleaned")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "nailsCutted")).setChecked(true);
                mixedCheck(true);
            } else {
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "mixed")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "vaccine")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "deworm")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "bloodTest")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "ligation")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "antiparasite")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "earsCleaned")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + globalPosition + "nailsCutted")).setChecked(false);
                mixedCheck(false);
            }
        }
        public void allCheckedWithLocation(boolean allCalled) {
            Log.i(TAG, "+++++++++ all location = " + location + " all Called: " + allCalled);
            if (allCalled == true) {
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "mixed")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "vaccine")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "deworm")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "bloodTest")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "ligation")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "antiparasite")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "earsCleaned")).setChecked(true);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "nailsCutted")).setChecked(true);
                mixedCheck(true);
            } else {
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "mixed")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "vaccine")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "deworm")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "bloodTest")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "ligation")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "antiparasite")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "earsCleaned")).setChecked(false);
                ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "nailsCutted")).setChecked(false);
                mixedCheck(false);
            }
        }
    }
}
