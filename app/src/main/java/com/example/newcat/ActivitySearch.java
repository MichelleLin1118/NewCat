package com.example.newcat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ActivitySearch extends Activity implements View.OnClickListener {
    String TAG = "homework";
    ListView searchList;
    ImageButton home, black, white, orange, calico, tuxedo, tabby, otherColor;
    RadioButton taipei, newTaipei, taoyuan, hsinchuCity, hsinchuCounty, miaoli, taichung, otherCity;
    Button clear;
    RadioGroup groupOne, groupTwo;
    DataBaseUtils dataBaseUtils;
    ArrayList<DataBaseCat> searchCatArray;
    ArrayList<DataBaseAdopter> searchAdopterArray;
    Adapter adapter;
    int globalPosition = 0;
    int searchListCount = 0;
    //calico = 三花; tuxedo cat (燕尾服貓)= 賓士貓; tabby = 虎斑;

    @Override
    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_search);
        adapter = new Adapter(this);
        dataBaseUtils = new DataBaseUtils(this);


        searchList = (ListView) findViewById(R.id.list_search);
        searchList.setAdapter(adapter);

        home = (ImageButton) findViewById(R.id.home_button);
        home.setOnClickListener(this);

        black = (ImageButton) findViewById(R.id.cat_black_button);
        black.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        black.setOnClickListener(this);

        white = (ImageButton) findViewById(R.id.cat_white_button);
        white.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        white.setOnClickListener(this);

        orange = (ImageButton) findViewById(R.id.cat_orange_button);
        orange.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        orange.setOnClickListener(this);

        calico = (ImageButton) findViewById(R.id.cat_calico_button);
        calico.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        calico.setOnClickListener(this);

        tuxedo = (ImageButton) findViewById(R.id.cat_tuxedo_button);
        tuxedo.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        tuxedo.setOnClickListener(this);

        tabby = (ImageButton) findViewById(R.id.cat_tabby_button);
        tabby.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        tabby.setOnClickListener(this);

        otherColor = (ImageButton) findViewById(R.id.cat_other_button);
        otherColor.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        otherColor.setOnClickListener(this);

        clear = (Button) findViewById(R.id.clear_button);
        clear.setOnClickListener(this);


        taipei = (RadioButton) findViewById(R.id.radio_taipei);
        newTaipei = (RadioButton) findViewById(R.id.radio_new_taipei);
        taoyuan = (RadioButton) findViewById(R.id.radio_taoyuan);
        hsinchuCity = (RadioButton) findViewById(R.id.radio_hsinchu_city);
        hsinchuCounty = (RadioButton) findViewById(R.id.radio_hsinchu_county);
        miaoli = (RadioButton) findViewById(R.id.radio_miaoli);
        taichung = (RadioButton) findViewById(R.id.radio_taichung);
        otherCity = (RadioButton) findViewById(R.id.radio_others);
        taipei.setOnClickListener(this);
        newTaipei.setOnClickListener(this);
        taoyuan.setOnClickListener(this);
        hsinchuCity.setOnClickListener(this);
        hsinchuCounty.setOnClickListener(this);
        miaoli.setOnClickListener(this);
        taichung.setOnClickListener(this);
        otherCity.setOnClickListener(this);
        groupOne = (RadioGroup) findViewById(R.id.radio_group_1);
        groupTwo = (RadioGroup) findViewById(R.id.radio_group_2);
    }

    @Override
    public void onClick(View v) {
        if (v == home){
            Intent homeIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityMain");
            homeIntent.setComponent(cn);
            startActivity(homeIntent);
        }
        if(v == black){
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(1);
            colorOnClickFunction();
        }
        if(v == white) {
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(2);
            colorOnClickFunction();
        }
        if(v == orange) {
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(3);
            colorOnClickFunction();
        }
        if(v == calico) {
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(4);
            colorOnClickFunction();
        }
        if(v == tuxedo) {
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(5);
            colorOnClickFunction();
        }
        if(v == tabby) {
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(6);
            colorOnClickFunction();
        }
        if(v == otherColor) {
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(7);
            colorOnClickFunction();
        }
        if(v == taipei || v == newTaipei || v == taoyuan || v == hsinchuCity || v == hsinchuCounty || v == miaoli || v == taichung || v == otherCity) {
            boolean checked = ((RadioButton)v).isChecked();
            switch (v.getId()) {
                case R.id.radio_taipei:
                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityFromDB(1);
                    groupTwo.clearCheck();
                    cityOnClickFunction();
                    break;
                case R.id.radio_new_taipei:
                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityFromDB(2);
                    groupTwo.clearCheck();
                    cityOnClickFunction();
                    break;
                case R.id.radio_taoyuan:
                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityFromDB(3);
                    groupTwo.clearCheck();
                    cityOnClickFunction();
                    break;
                case R.id.radio_hsinchu_city:
                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityFromDB(4);
                    groupTwo.clearCheck();
                    cityOnClickFunction();
                    break;
                case R.id.radio_hsinchu_county:
                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityFromDB(5);
                    groupOne.clearCheck();
                    cityOnClickFunction();
                    break;
                case R.id.radio_miaoli:
                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityFromDB(6);
                    groupOne.clearCheck();
                    cityOnClickFunction();
                    break;
                case R.id.radio_taichung:
                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityFromDB(7);
                    groupOne.clearCheck();
                    cityOnClickFunction();
                    break;
                case R.id.radio_others:
                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityFromDB(8);
                    groupOne.clearCheck();
                    cityOnClickFunction();
                    break;
            }
        }
        if (v == clear) {
            for (int i=0; i<7; i++) {
                searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(i);
                colorOnClickFunction();
            }
            for (int j=0; j<8; j++) {
                searchAdopterArray = dataBaseUtils.getAdopterDataWithCityFromDB(j);
                groupOne.clearCheck();
                groupTwo.clearCheck();
                cityOnClickFunction();
            }
        }
    }


    public class Adapter extends BaseAdapter {
        TextView catColor, catBirth, adopterCity, adopterName;
        View search_cat, search_adop;
        ImageView catImg;
        Context context;
        LayoutInflater inflater;


        public Adapter (Context context) {
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
                return searchListCount;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            globalPosition = position;
            view = inflater.inflate(R.layout.z_list_search, parent, false);
            search_cat = (View) view.findViewById(R.id.search_cat);
            search_cat.setTag(position);
            search_adop = (View) view.findViewById(R.id.search_adopter);
            search_adop.setTag(position);

            catImg = (ImageView) view.findViewById(R.id.cat_img);
            catColor = (TextView) view.findViewById(R.id.cat_color);
            catBirth = (TextView) view.findViewById(R.id.cat_birth);
            adopterCity = (TextView) view.findViewById(R.id.adopter_city);
            adopterName = (TextView) view.findViewById(R.id.adopter_name);

            catColor.setText(catColorFunction(searchCatArray.get(position).getColor()));
            catBirth.setText(searchCatArray.get(position).getBirth());
            adopterCity.setText(adopterCityFunction(searchAdopterArray.get(position).getCity()));
            adopterName.setText(searchAdopterArray.get(position).getName());

            if (searchAdopterArray.get(position).getCatImg() != 0) {
                Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, searchCatArray.get(position).getCatImg());
                catImg.setImageURI(uri);
            }

            search_cat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityCat");
                    Bundle bundle = new Bundle();
                    long id = searchCatArray.get(Integer.valueOf((v.getTag()).toString())).getId();
                    int page = (int)id;
                    bundle.putInt("page", page);
                    intent.putExtras(bundle);
                    intent.setComponent(cn);
                    startActivity(intent);
                }
            });

            search_adop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityAdopter");
                    Bundle bundle = new Bundle();
                    long id = searchAdopterArray.get(Integer.valueOf((v.getTag()).toString())).getId();
                    int page = (int)id;
                    bundle.putInt("page", page);
                    intent.putExtras(bundle);
                    intent.setComponent(cn);
                    startActivity(intent);
                }
            });

            return view;
        }
    }

    public String catColorFunction(int colorIndex) {
        if (colorIndex == 1) {
            return "Black";
        }else if (colorIndex == 2) {
            return "White";
        }else if (colorIndex == 3) {
            return "Orange";
        }else if (colorIndex == 4) {
            return "Calico";
        }else if (colorIndex == 5) {
            return "Tuxedo";
        }else if (colorIndex == 6) {
            return "Tabby";
        }else {
            return "other colors";
        }
    }
     public String adopterCityFunction(int cityIndex) {
         if (cityIndex == 1) {
             return "Taipei";
         }else if (cityIndex == 2) {
             return "New Taipei";
         }else if (cityIndex == 3) {
             return "Taoyuan";
         }else if (cityIndex == 4) {
             return "Hsinchu City";
         }else if (cityIndex == 5) {
             return "Hsinchu County";
         }else if (cityIndex == 6) {
             return "Miaoli";
         }else if (cityIndex == 7) {
             return "Taichung";
         }else {
             return "other cities";
         }
     }

     public void colorOnClickFunction() {
         searchAdopterArray = new ArrayList<DataBaseAdopter>();
         for (int i = 0; i < searchCatArray.size(); i ++) {
             searchAdopterArray.add(dataBaseUtils.getAdopterDataWithAdopterNameFromDB(searchCatArray.get(i).getAdopterName()));
             searchAdopterArray.get(i).setCatImg(dataBaseUtils.getCatDataWithAdopterNameFromDB(searchCatArray.get(i).getAdopterName()).getCatImg());
         }
         searchListCount = searchCatArray.size();
         adapter.notifyDataSetChanged();
     }

     public void cityOnClickFunction() {
         searchCatArray = new ArrayList<DataBaseCat>();
         for (int i = 0; i < searchAdopterArray.size(); i ++) {
             searchCatArray.add(dataBaseUtils.getCatDataWithAdopterNameFromDB(searchAdopterArray.get(i).getName()));
             searchAdopterArray.get(i).setCatImg(dataBaseUtils.getCatDataWithAdopterNameFromDB(searchAdopterArray.get(i).getName()).getCatImg());
         }
         searchListCount = searchAdopterArray.size();
         adapter.notifyDataSetChanged();
         if (searchCatArray == null) {
             for (int i=0; i<7; i++) {
                 searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(i);
                 colorOnClickFunction();
             }
             for (int j=0; j<8; j++) {
                 searchAdopterArray = dataBaseUtils.getAdopterDataWithCityFromDB(j);
                 groupOne.clearCheck();
                 groupTwo.clearCheck();
                 cityOnClickFunction();
             }
         }
     }


}
