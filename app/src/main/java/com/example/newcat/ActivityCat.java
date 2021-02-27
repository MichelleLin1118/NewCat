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
import android.widget.CompoundButton;
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
    CheckBox vac, dew, blood,lig, antipara, cutNail, cleanEar, all, mixed;
    ImageView catImg;
    ToggleButton sexuality;
    ViewPager pager;
    EditText weight, birth, adoptionDate, vaccine, about,others, colorEdit;
    Spinner color;
    //ArrayList<View> catDataArrayList = new ArrayList<>();
    ArrayList<View> catPageArrayList = new ArrayList<>();
    ArrayList<DataCat> data = new ArrayList<>();
    int index = 0;
    int location;
    CatFragmentAdapter mCatFragmentAdapter = new CatFragmentAdapter(this);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");




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
                R.drawable.cat1,
                R.drawable.cat12,
                R.drawable.cat13,
                new Date(120,1,1),
                new Date(88, 2,2),
                3 , false));
        data.add(new DataCat(false, true, false,true,false,true,false,false, "brown", "don't know", "hi", true, "hi", R.drawable.cat21, R.drawable.cat22, R.drawable.b_cat_white, new Date(89,3,3), new Date(91, 4,4), 2, true));
        data.add(new DataCat(false,false,false,false,false,false,false,true,"orange", "don't know", "bye", true, "bye", R.drawable.or1, R.drawable.or2,R.drawable.or3, new Date(92, 5,5), new Date(93,6,6),3, true));
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
        pager.setAdapter(mCatFragmentAdapter);
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
                mCatFragmentAdapter.notifyDataSetChanged();
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


    class CatFragmentAdapter extends PagerAdapter implements View.OnClickListener {

        Context context;

        public CatFragmentAdapter(Context context){
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
            vac = (CheckBox) catPageArrayList.get(position).findViewById(R.id.vaccine);
            dew = (CheckBox) catPageArrayList.get(position).findViewById(R.id.deworm);
            blood = (CheckBox) catPageArrayList.get(position).findViewById(R.id.blood_test);
            lig = (CheckBox) catPageArrayList.get(position).findViewById(R.id.ligation);
            antipara = (CheckBox) catPageArrayList.get(position).findViewById(R.id.antiparasite);
            cleanEar = (CheckBox) catPageArrayList.get(position).findViewById(R.id.cleanEar);
            cutNail = (CheckBox) catPageArrayList.get(position).findViewById(R.id.cutNails);
            all = (CheckBox) catPageArrayList.get(position).findViewById(R.id.all);
            mixed = (CheckBox) catPageArrayList.get(position).findViewById(R.id.mixed);

            color = (Spinner) catPageArrayList.get(position).findViewById(R.id.spinner_color);
            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(context, R.array.color_array, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            color.setAdapter(arrayAdapter);
            color.setSelection(0, false);
            color.setOnItemSelectedListener(mSpinSelectedListener);

            colorEdit = (EditText) catPageArrayList.get(position).findViewById(R.id.color_editText);

            weight = (EditText) catPageArrayList.get(position).findViewById(R.id.weight);
            weight.setText(Integer.toString(data.get(position).getWeight()));
            birth = (EditText) catPageArrayList.get(position).findViewById((R.id.birth));
            birth.setText(sdf.format(data.get(position).getBirth()));
            vaccine= (EditText) catPageArrayList.get(position).findViewById((R.id.vaccineName));
            vaccine.setText(data.get(position).getVaccineName());
            about= (EditText) catPageArrayList.get(position).findViewById((R.id.about));
            about.setText(data.get(position).getAbout());
            others= (EditText) catPageArrayList.get(position).findViewById((R.id.others_cat));
            others.setText(data.get(position).getOther());
            adoptionDate= (EditText) catPageArrayList.get(position).findViewById((R.id.adoptionDate));
            adoptionDate.setText(sdf.format(data.get(position).getAdoption()));

            vac.setChecked(data.get(position).getVac());
            vac.setOnClickListener(this);
            dew.setChecked(data.get(position).getDew());
            dew.setOnClickListener(this);
            blood.setChecked(data.get(position).getBlood());
            blood.setOnClickListener(this);
            lig.setChecked(data.get(position).getLig());
            lig.setOnClickListener(this);
            antipara.setChecked(data.get(position).getAntiparasite());
            antipara.setOnClickListener(this);
            cleanEar.setChecked(data.get(position).getCleanEar());
            cleanEar.setOnClickListener(this);
            cutNail.setChecked(data.get(position).getCutNail());
            cutNail.setOnClickListener(this);
            //all.setChecked(data.get(position).getAll());
            //all.setOnClickListener(this);

            //mixed.setChecked(data.get(position).getMixed());
            mixed.setOnClickListener(this);
            mixed.setTag("ActivityCat" + location + "mixed");
            if (data.get(location).getMixed() == true) {
                colorEdit.setVisibility(View.GONE);
                color.setVisibility(View.VISIBLE);
            } else {
                colorEdit.setVisibility(View.VISIBLE);
                color.setVisibility(View.GONE);
            }

            color.setTag("ActivityCat" +location +"color");
            colorEdit.setTag("ActivityCat" +location + "colorEdit");

            all.setTag("ActivityCat" +location + "all");
            all.setOnClickListener(this);

            mixed.setTag("ActivityCat" +location + "mixed");
            vac.setTag("ActivityCat" +location + "vac");
            dew.setTag("ActivityCat" +location + "dew");

            catImg = (ImageView) catPageArrayList.get(position).findViewById(R.id.cat1);
            catImg.setImageResource(data.get(position).getCat());

            return catPageArrayList.get(position);
        }

        @Override
        public void onClick(View v) {
            Log.i(TAG, "onClick");
            Log.i(TAG, "v.getTag() = " + v.getTag());
            if (v == pager.findViewWithTag("ActivityCat" +location + "all")) {
                Log.i(TAG, "all checked");
                ((CheckBox)pager.findViewWithTag("ActivityCat" +location + "mixed")).setChecked(true);
                ((CheckBox)pager.findViewWithTag("ActivityCat" +location + "vac")).setChecked(true);
                ((CheckBox)pager.findViewWithTag("ActivityCat" +location + "dew")).setChecked(true);

            }
            if(v == pager.findViewWithTag("ActivityCat" + location + "mixed")) {
                Log.i(TAG, "mixed checked");
                Log.i(TAG, "mixed = " + ((CheckBox) pager.findViewWithTag("ActivityCat" + location + "mixed")).isChecked());
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
