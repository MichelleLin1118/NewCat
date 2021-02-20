package com.example.newcat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
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
    EditText weight, birth, adoptionDate, color, vaccine, about,others;
    //ArrayList<View> catDataArrayList = new ArrayList<>();
    ArrayList<View> catPageArrayList = new ArrayList<>();
    ArrayList<DataCat> data = new ArrayList<>();
    int index = 0;
    CatFragmentAdapter mCatFragmentAdapter = new CatFragmentAdapter();
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
                3 , true));
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

    }

    @Override
    public void onClick(View v) {
        if (v == home){
            Intent homeIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.cat", "com.example.cat.MainActivity");
            homeIntent.setComponent(cn);
            startActivity(homeIntent);
        }
    }

//    class CatCheckBoxListener implements CompoundButton.OnCheckedChangeListener {
//
//        @Override
//        public void onCheckedChanged(CompoundButton v, boolean isChecked) {
//            if (v == vac) {
//                Log.i(TAG, "vac click");
//                data.get(index).setVac(isChecked);
//            }
//            if (v == dew) {
//                data.get(index).setDew(isChecked);
//            }
//            if (v == blood) {
//                data.get(index).setBlood(isChecked);
//            }
//            if (v == lig) {
//                data.get(index).setLig (isChecked);
//            }
//            if (v == antipara) {
//                data.get(index).setAntiparasite (isChecked);
//            }
//            if (v == cutNail) {
//                data.get(index).setCutNail (isChecked);
//            }
//            if (v == cleanEar) {
//                data.get(index).setCleanEar (isChecked);
//            }
//            if (v == all) {
//                data.get(index).setVac (isChecked);
//                data.get(index).setDew(isChecked);
//                data.get(index).setBlood(isChecked);
//                data.get(index).setLig (isChecked);
//                data.get(index).setAntiparasite (isChecked);
//                data.get(index).setCutNail (isChecked);
//                data.get(index).setCleanEar (isChecked);
//            }
//        }
//    }
    class CatFragmentAdapter extends PagerAdapter implements View.OnClickListener {

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
        public Object instantiateItem(ViewGroup container, int position) {
            index = position;
            ((ViewPager) container).addView(catPageArrayList.get(position));
            vac = (CheckBox) catPageArrayList.get(position).findViewById(R.id.vaccine);
            //vac.setOnCheckedChangeListener(mCatCheckBoxListener);
            dew = (CheckBox) catPageArrayList.get(position).findViewById(R.id.deworm);
            //dew.setOnCheckedChangeListener(mCatCheckBoxListener);
            blood = (CheckBox) catPageArrayList.get(position).findViewById(R.id.blood_test);
            //blood.setOnCheckedChangeListener(mCatCheckBoxListener);
            lig = (CheckBox) catPageArrayList.get(position).findViewById(R.id.ligation);
            //lig.setOnCheckedChangeListener(mCatCheckBoxListener);
            antipara = (CheckBox) catPageArrayList.get(position).findViewById(R.id.antiparasite);
            //antipara.setOnCheckedChangeListener(mCatCheckBoxListener);
            cleanEar = (CheckBox) catPageArrayList.get(position).findViewById(R.id.cleanEar);
            //cleanEar.setOnCheckedChangeListener(mCatCheckBoxListener);
            cutNail = (CheckBox) catPageArrayList.get(position).findViewById(R.id.cutNails);
            //cutNail.setOnCheckedChangeListener(mCatCheckBoxListener);
            all = (CheckBox) catPageArrayList.get(position).findViewById(R.id.all);
            //all.setOnCheckedChangeListener(mCatCheckBoxListener);
            mixed = (CheckBox) catPageArrayList.get(position).findViewById(R.id.mixed);
            //mixed.setOnCheckedChangeListener(mCatCheckBoxListener);

            weight = (EditText) catPageArrayList.get(position).findViewById(R.id.weight);
            weight.setText(Integer.toString(data.get(position).getWeight()));
            birth = (EditText) catPageArrayList.get(position).findViewById((R.id.birth));
            birth.setText(sdf.format(data.get(position).getBirth()));
            vaccine= (EditText) catPageArrayList.get(position).findViewById((R.id.vaccineName));
            vaccine.setText(data.get(position).getVaccineName());
            color = (EditText) catPageArrayList.get(position).findViewById((R.id.colorKind));
            color.setText(data.get(position).getColor());
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
            all.setChecked(data.get(position).getAll());
            all.setOnClickListener(this);
            mixed.setChecked(data.get(position).getMixed());
            mixed.setOnClickListener(this);

            catImg = (ImageView) catPageArrayList.get(position).findViewById(R.id.cat1);
            catImg.setImageResource(data.get(position).getCat());

            return catPageArrayList.get(position);
        }

        @Override
        public void onClick(View v) {
            DataCat dataCat = new DataCat();
            if (all.isChecked())   {
                Log.i(TAG, "all is checked");
            }
            if (v == all)  {
                Log.i(TAG, "all check box click");
                boolean checked = all.isChecked();
                if (checked) {

                    dataCat.setMixed(true);
                    dataCat.setVac(true);
                    dataCat.setDew(true);
                    mixed.setChecked(true);
                    vac.setChecked(true);
                    dew.setChecked(true);
                } else {

                }
            }
//            if (all.isChecked()) {
//                Log.i(TAG, "all check");
//                dataCat.setMixed(true);
//                dataCat.setVac(true);
//                dataCat.setDew(true);
//                dataCat.setBlood(true);
//                dataCat.setLig(true);
//                dataCat.setAntiparasite(true);
//                dataCat.setCleanEar(true);
//                dataCat.setCutNail(true);
//                dataCat.setAll(true);
//            }
            if (vac.isChecked()) {
                Log.i(TAG, "vac check");
                dataCat.setVac(true);
            }
            if (dew.isChecked()) {
                Log.i(TAG, "dew check");
                dataCat.setDew(true);
            }
            if (blood.isChecked()) {
                Log.i(TAG, "blood check");
                dataCat.setBlood(true);
            }
            if (lig.isChecked()) {
                Log.i(TAG, "lig check");
                dataCat.setLig(true);
            }
            if (antipara.isChecked()) {
                Log.i(TAG, "antipara check");
                dataCat.setAntiparasite(true);
            }
            if (cleanEar.isChecked()) {
                Log.i(TAG, "cleanEar check");
                dataCat.setCleanEar(true);
            }
            if (cutNail.isChecked()) {
                Log.i(TAG, "cutNail check");
                dataCat.setCutNail(true);
            }

            if (mixed.isChecked()) {
                dataCat.setMixed(true);
            }

        }
    }
}
