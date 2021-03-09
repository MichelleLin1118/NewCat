package com.example.newcat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    ImageButton home;
    CheckBox vaccine, deworm, bloodTest,ligation, antiparasite, nailsCutted, earsCleaned, all, mixed;
    ImageView catImg;
    ToggleButton sexuality;
    ViewPager pager;
    EditText weight, birth, adoptionDate, vaccineName, about,others, colorEdit;
    Spinner color;
    //ArrayList<View> catDataArrayList = new ArrayList<>();
    ArrayList<View> catPageArrayList = new ArrayList<>();
    ArrayList<DataCat> data = new ArrayList<>();
    int index = 0;
    int location;
    CatActivityAdapter mCatActivityAdapter = new CatActivityAdapter(this);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    public ArrayList<Integer> InitCatPicture(int cat1, int cat2, int cat3) {
        ArrayList<Integer> catPic = new ArrayList<>();
        catPic.add(cat1);
        catPic.add(cat2);
        catPic.add(cat3);
        return catPic;
    }

    private void initCatData() {
        data.add(new DataCat(true,
                false,
                true,
                false,
                true,
                true,
                false,
                false,
                "white",
                "don't know",
                "hello",
                true,
                "hello",
                InitCatPicture(R.drawable.cat1, R.drawable.cat2, R.drawable.cat3),
                new Date(120,1,1),
                new Date(88, 2,2),
                3 , false));
//        data.add(new DataCat(false, true, false,true,false,true,false,false, "brown", "don't know", "hi", true, "hi", R.drawable.cat21, R.drawable.cat22, R.drawable.b_cat_white, new Date(89,3,3), new Date(91, 4,4), 2, true));
//        data.add(new DataCat(false,false,false,false,false,false,false,true,"orange", "don't know", "bye", true, "bye", R.drawable.or1, R.drawable.or2,R.drawable.or3, new Date(92, 5,5), new Date(93,6,6),3, true));
        data.add(new DataCat());

    }
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_cat);
        initCatData();

        pager= (ViewPager) findViewById(R.id.view_pager_cat);
        //catDataArrayList.add(LayoutInflater.from(getActivity()).inflate(R.layout.view_pager_cat, null));
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
                mCatActivityAdapter.notifyDataSetChanged();
            }
            @Override
            public void onPageScrollStateChanged ( int state){

            }
        });

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
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.i(TAG,"instantiateItem");
            Log.i(TAG, "location = " + location);
            index = position;
            ((ViewPager) container).addView(catPageArrayList.get(position));

            vaccine = (CheckBox) catPageArrayList.get(position).findViewById(R.id.vaccine);
            deworm = (CheckBox) catPageArrayList.get(position).findViewById(R.id.deworm);
            bloodTest = (CheckBox) catPageArrayList.get(position).findViewById(R.id.blood_test);
            ligation = (CheckBox) catPageArrayList.get(position).findViewById(R.id.ligation);
            antiparasite = (CheckBox) catPageArrayList.get(position).findViewById(R.id.antiparasite);
            earsCleaned = (CheckBox) catPageArrayList.get(position).findViewById(R.id.cleanEar);
            nailsCutted = (CheckBox) catPageArrayList.get(position).findViewById(R.id.cutNails);
            all = (CheckBox) catPageArrayList.get(position).findViewById(R.id.all);

            vaccine.setChecked(data.get(position).getVac());
            vaccine.setOnClickListener(this);
            deworm.setChecked(data.get(position).getDew());
            deworm.setOnClickListener(this);
            bloodTest.setChecked(data.get(position).getBlood());
            bloodTest.setOnClickListener(this);
            ligation.setChecked(data.get(position).getLig());
            ligation.setOnClickListener(this);
            antiparasite.setChecked(data.get(position).getAntiparasite());
            antiparasite.setOnClickListener(this);
            earsCleaned.setChecked(data.get(position).getCleanEar());
            earsCleaned.setOnClickListener(this);
            nailsCutted.setChecked(data.get(position).getCutNail());
            nailsCutted.setOnClickListener(this);
            all.setOnClickListener(this);

            vaccine.setTag("ActivityCat" +location + "vaccine");
            deworm.setTag("ActivityCat" +location + "deworm");
            bloodTest.setTag("ActivityCat" +location + "bloodTest");
            ligation.setTag("ActivityCat" +location + "ligation");
            antiparasite.setTag("ActivityCat" +location + "antiparasite");
            earsCleaned.setTag("ActivityCat" +location + "earsCleaned");
            nailsCutted.setTag("ActivityCat" +location + "nailsCutted");
            all.setTag("ActivityCat" +location + "all");

            weight = (EditText) catPageArrayList.get(position).findViewById(R.id.weight);
            weight.setText(Integer.toString(data.get(position).getWeight()));
            birth = (EditText) catPageArrayList.get(position).findViewById((R.id.birth));
            birth.setText(sdf.format(data.get(position).getBirth()));
            vaccineName= (EditText) catPageArrayList.get(position).findViewById((R.id.vaccineName));
            vaccineName.setText(data.get(position).getVaccineName());
            about= (EditText) catPageArrayList.get(position).findViewById((R.id.about));
            about.setText(data.get(position).getAbout());
            others= (EditText) catPageArrayList.get(position).findViewById((R.id.others_cat));
            others.setText(data.get(position).getOther());
            adoptionDate= (EditText) catPageArrayList.get(position).findViewById((R.id.adoptionDate));
            adoptionDate.setText(sdf.format(data.get(position).getAdoption()));

            catImg = (ImageView) catPageArrayList.get(position).findViewById(R.id.cat1);
            catImg.setImageResource(data.get(position).getCatPic().get(0));
            pictureIndex = 0;
            catImg.setTag("ActivityCat" + location + "catImg");
            pager.findViewWithTag("ActivityCat" +location + "catImg").setOnClickListener(this);

            sexuality = (ToggleButton) findViewById(R.id.sexuality_button);
            sexuality.setText("unknown");
            sexuality.setTextOff("male");
            sexuality.setTextOn("female");

            color = (Spinner) catPageArrayList.get(position).findViewById(R.id.spinner_color);
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(context, R.array.color_array, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            color.setAdapter(arrayAdapter);
            color.setSelection(0, false);
            color.setOnItemSelectedListener(mSpinSelectedListener);
            color.setTag("ActivityCat" +location +"color");
            colorEdit = (EditText) catPageArrayList.get(position).findViewById(R.id.color_editText);
            colorEdit.setTag("ActivityCat" +location + "colorEdit");


            mixed = (CheckBox) catPageArrayList.get(position).findViewById(R.id.mixed);
            mixed.setOnClickListener(this);
            mixed.setTag("ActivityCat" + location + "mixed");
            mixedClick(data.get(position).getMixed());

            return catPageArrayList.get(position);
        }

        public void mixedClick(boolean mixedCalled) {
            if (mixedCalled == true) {
                ((CheckBox)pager.findViewWithTag("ActivityCat" +location + "mixed")).setChecked(true);
                ((EditText)pager.findViewWithTag("ActivityCat" +location + "colorEdit")).setVisibility(View.GONE);
                ((Spinner)pager.findViewWithTag("ActivityCat" +location +"color")).setVisibility(View.VISIBLE);
            } else {
                ((CheckBox)pager.findViewWithTag("ActivityCat" +location + "mixed")).setChecked(true);
                ((EditText)pager.findViewWithTag("ActivityCat" +location + "colorEdit")).setVisibility(View.VISIBLE);
                ((Spinner)pager.findViewWithTag("ActivityCat" +location +"color")).setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            Log.i(TAG, "onClick");
            Log.i(TAG, "v.getTag() = " + v.getTag());
            if (v == pager.findViewWithTag("ActivityCat" +location + "catImg")){
                Log.i(TAG, "pic index = " + pictureIndex);
                Log.i(TAG, "index" + data.get(location).getCatPic().get((pictureIndex+1)%3));
                catImg.setImageResource(data.get(location).getCatPic().get((pictureIndex+1)%3));
                pictureIndex = pictureIndex + 1;
                mCatActivityAdapter.notifyDataSetChanged();

            }
            if (v == pager.findViewWithTag("ActivityCat" +location + "all")) {
                Log.i(TAG, "all checked");
                if (((CheckBox) pager.findViewWithTag("ActivityCat" + location + "all")).isChecked() == false) {
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "mixed")).setChecked(true);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "vaccine")).setChecked(true);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "deworm")).setChecked(true);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "bloodTest")).setChecked(true);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "ligation")).setChecked(true);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "antiparasite")).setChecked(true);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "earsCleaned")).setChecked(true);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "nailsCutted")).setChecked(true);
                    mixedClick(true);
                } else {
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "mixed")).setChecked(false);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "vaccine")).setChecked(false);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "deworm")).setChecked(false);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "bloodTest")).setChecked(false);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "ligation")).setChecked(false);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "antiparasite")).setChecked(false);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "earsCleaned")).setChecked(false);
                    ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "nailsCutted")).setChecked(false);
                    mixedClick(false);
                }
            }
            
            if(v == pager.findViewWithTag("ActivityCat" +location + "vaccine")) { ((CheckBox)pager.findViewWithTag("ActivityCat" +location + "vaccine")).setChecked(true); }
            if(v == pager.findViewWithTag("ActivityCat" +location + "deworm")) {((CheckBox)pager.findViewWithTag("ActivityCat" +location + "deworm")).setChecked(true);}
            if(v == pager.findViewWithTag("ActivityCat" +location + "bloodTest")) {((CheckBox)pager.findViewWithTag("ActivityCat" +location + "bloodTest")).setChecked(true);}
            if(v == pager.findViewWithTag("ActivityCat" +location + "ligation")) {((CheckBox)pager.findViewWithTag("ActivityCat" +location + "ligation")).setChecked(true);}
            if(v == pager.findViewWithTag("ActivityCat" +location + "antiparasite")) {((CheckBox)pager.findViewWithTag("ActivityCat" +location + "antiparasite")).setChecked(true);}
            if(v == pager.findViewWithTag("ActivityCat" +location + "earsCleaned")) {((CheckBox)pager.findViewWithTag("ActivityCat" +location + "earsCleaned")).setChecked(true);}
            if(v == pager.findViewWithTag("ActivityCat" +location + "nailsCutted")) {((CheckBox)pager.findViewWithTag("ActivityCat" +location + "nailsCutted")).setChecked(true);}

            if(v == pager.findViewWithTag("ActivityCat" + location + "mixed")) {
                if (((CheckBox) pager.findViewWithTag("ActivityCat" + location + "mixed")).isChecked() == true) {
                    pager.findViewWithTag("ActivityCat" + location +"colorEdit").setVisibility(View.GONE);
                    pager.findViewWithTag("ActivityCat" + location + "color").setVisibility(View.VISIBLE);
                } else {
                    pager.findViewWithTag("ActivityCat" + location +"colorEdit").setVisibility(View.VISIBLE);
                    pager.findViewWithTag("ActivityCat" + location +"color").setVisibility(View.GONE);
                }
            }

        }
    }
}
