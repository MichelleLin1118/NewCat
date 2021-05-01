package com.example.newcat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
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

        otherColor = (ImageButton) findViewById(R.id.cat_tabby_button);
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
            searchAdopterArray = new ArrayList<DataBaseAdopter>();
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(1);
            for (int i = 0; i < searchCatArray.size(); i ++) {
                searchAdopterArray.add(dataBaseUtils.getAdopterDataWithAdopterNameFromDB(searchCatArray.get(i).getAdopterName()));
            }
            searchListCount = searchCatArray.size();
            adapter.notifyDataSetChanged();
        }
        if(v == white) {
            searchAdopterArray = new ArrayList<DataBaseAdopter>();
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(2);
            for (int i = 0; i < searchCatArray.size(); i ++) {
                searchAdopterArray.add(dataBaseUtils.getAdopterDataWithAdopterNameFromDB(searchCatArray.get(i).getAdopterName()));
            }
            searchListCount = searchCatArray.size();
            adapter.notifyDataSetChanged();
        }
//        if(v == orange) {
//            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(3);
//            searchAdopterArray = dataBaseUtils.getAdopterDataWithAdopterNameFromDB(searchAdopterArray.get(globalPosition).getName());
//            searchListCount = searchCatArray.size();
//            adapter.notifyDataSetChanged();
//        }
//        if(v == calico) {
//            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(4);
//            searchAdopterArray = dataBaseUtils.getAdopterDataWithAdopterNameFromDB(searchAdopterArray.get(globalPosition).getName());
//            searchListCount = searchCatArray.size();
//            adapter.notifyDataSetChanged();
//        }
//        if(v == tuxedo) {
//            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(5);
//            searchAdopterArray = dataBaseUtils.getAdopterDataWithAdopterNameFromDB(searchAdopterArray.get(globalPosition).getName());
//            searchListCount = searchCatArray.size();
//            adapter.notifyDataSetChanged();
//        }
//        if(v == tabby) {
//            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(6);
//            searchAdopterArray = dataBaseUtils.getAdopterDataWithAdopterNameFromDB(searchAdopterArray.get(globalPosition).getName());
//            searchListCount = searchCatArray.size();
//            adapter.notifyDataSetChanged();
//        }
//        if(v == otherColor) {
//            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(7);
//            searchAdopterArray = dataBaseUtils.getAdopterDataWithAdopterNameFromDB(searchAdopterArray.get(globalPosition).getName());
//            searchListCount = searchCatArray.size();
//            adapter.notifyDataSetChanged();
//        }
        if(v == taipei || v == newTaipei || v == taoyuan || v == hsinchuCity || v == hsinchuCounty || v == miaoli || v == taichung || v == otherCity) {
            boolean checked = ((RadioButton)v).isChecked();
            switch (v.getId()) {
                case R.id.radio_taipei:
                    Log.i(TAG, "------------------ radio taipei");
                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityfromDB(1);
                    searchCatArray = new ArrayList<DataBaseCat>();
                    for (int i = 0; i < searchAdopterArray.size(); i ++) {
                        searchCatArray.add(dataBaseUtils.getCatDataWithAdopterNameFromDB(searchAdopterArray.get(i).getName()));
                    }
                    groupTwo.clearCheck();
                    searchListCount = searchAdopterArray.size();
                    adapter.notifyDataSetChanged();
                    break;
//                case R.id.radio_new_taipei:
//                    Log.i(TAG, "------------------ radio new_taipei");
//                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityfromDB(2);
//                    groupTwo.clearCheck();
//                    searchListCount = searchAdopterArray.size();
//                    adapter.notifyDataSetChanged();
//                    break;
//                case R.id.radio_taoyuan:
//                    Log.i(TAG, "------------------ radio taoyuan");
//                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityfromDB(3);
//                    groupTwo.clearCheck();
//                    searchListCount = searchAdopterArray.size();
//                    adapter.notifyDataSetChanged();
//                    break;
//                case R.id.radio_hsinchu_city:
//                    Log.i(TAG, "------------------ radio hsinchu_city");
//                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityfromDB(4);
//                    groupTwo.clearCheck();
//                    searchListCount = searchAdopterArray.size();
//                    adapter.notifyDataSetChanged();
//                    break;
//                case R.id.radio_hsinchu_county:
//                    Log.i(TAG, "------------------ radio hsinchu_county");
//                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityfromDB(5);
//                    groupOne.clearCheck();
//                    searchListCount = searchAdopterArray.size();
//                    adapter.notifyDataSetChanged();
//                    break;
//                case R.id.radio_miaoli:
//                    Log.i(TAG, "------------------ radio miaoli");
//                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityfromDB(6);
//                    groupOne.clearCheck();
//                    searchListCount = searchAdopterArray.size();
//                    adapter.notifyDataSetChanged();
//                    break;
//                case R.id.radio_taichung:
//                    Log.i(TAG, "------------------ radio taichung");
//                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityfromDB(7);
//                    groupOne.clearCheck();
//                    searchListCount = searchAdopterArray.size();
//                    adapter.notifyDataSetChanged();
//                    break;
//                case R.id.radio_others:
//                    Log.i(TAG, "------------------ radio others");
//                    searchAdopterArray = dataBaseUtils.getAdopterDataWithCityfromDB(8);
//                    groupOne.clearCheck();
//                    searchListCount = searchAdopterArray.size();
//                    adapter.notifyDataSetChanged();
//                    break;

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
            search_adop = (View) view.findViewById(R.id.search_adopter);

            catImg = (ImageView) view.findViewById(R.id.cat_img);
            catColor = (TextView) view.findViewById(R.id.cat_color);
            catBirth = (TextView) view.findViewById(R.id.cat_birth);
            adopterCity = (TextView) view.findViewById(R.id.adopter_city);
            adopterName = (TextView) view.findViewById(R.id.adopter_name);

            catImg.setImageResource(searchCatArray.get(position).getCatImg());
            catColor.setText(catColorFunction(searchCatArray.get(position).getColor()));
            catBirth.setText(searchCatArray.get(position).getBirth());
            adopterCity.setText(adopterCityFunction(searchAdopterArray.get(position).getCity()));
            adopterName.setText(searchCatArray.get(position).getAdopterName());

            search_cat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityCat");
                    intent.setComponent(cn);
                    startActivity(intent);
                    // open to specific cat page
                }
            });

            search_adop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityAdopter");
                    intent.setComponent(cn);
                    startActivity(intent);
                    // open to specific adopter page
                }
            });



            return view;
        }
    }

    public String catColorFunction(int colorIndex) {
        if (colorIndex == 1) {
            return "Black";
        }
        if (colorIndex == 2) {
            return "White";
        }
        if (colorIndex == 3) {
            return "Orange";
        }
        if (colorIndex == 4) {
            return "Calico";
        }
        if (colorIndex == 5) {
            return "Tuxedo";
        }
        if (colorIndex == 6) {
            return "Tabby";
        }
        else {
            return "others";
        }
    }
     public String adopterCityFunction(int cityIndex) {
        Log.i(TAG, "'''''''''''''''''''''''''''''''''''''''''''''''''''''" + cityIndex);
         if (cityIndex == 1) {
             return "Taipei";
         }
         if (cityIndex == 2) {
             return "New Taipei";
         }
         if (cityIndex == 3) {
             return "Taoyuan";
         }
         if (cityIndex == 4) {
             return "Hsinchu City";
         }
         if (cityIndex == 5) {
             return "Hsinchu County";
         }
         if (cityIndex == 6) {
             return "Miaoli";
         }
         if (cityIndex == 7) {
             return "Taichung";
         }
         else {
             return "others";
         }

     }


}
