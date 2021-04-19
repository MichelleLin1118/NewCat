package com.example.newcat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
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
import android.widget.TextView;

import java.util.ArrayList;

public class ActivitySearch extends Activity implements View.OnClickListener {
    String TAG = "homework";
    ListView searchList;
    ImageButton home, black, white, orange, calico, tuxedo, tabby, otherColor;
    //RadioButton taipei, newTaipei, taoyuan, hsinchuCity, hsinchuCounty, miaoli, taichung, otherCity;
    Button clear;
    DataBaseUtils dataBaseUtils;
    ArrayList<DataBaseCat> searchCatArray;
    ArrayList<DataBaseAdopter> searchAdopArray;
    Adapter ad;
    int globalPosition = 0;
    //calico = 三花; tuxedo cat (燕尾服貓)= 賓士貓; tabby = 虎斑;

    @Override
    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_search);
        ad = new Adapter(this);
        dataBaseUtils = new DataBaseUtils(this);


        searchList = (ListView) findViewById(R.id.list_search);
        searchList.setAdapter(ad);

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
    }

    @Override
    public void onClick(View v) {
        if (v == home){
            Log.i(TAG, "searchList home button pressed >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>.");
            Intent homeIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityMain");
            homeIntent.setComponent(cn);
            startActivity(homeIntent);
        }
        if(v == black){
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(1);
            ad.notifyDataSetChanged();
        }
        if(v == white) {
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(2);
            ad.notifyDataSetChanged();
        }
        if(v == orange) {
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(3);
            ad.notifyDataSetChanged();
        }
        if(v == calico) {
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(4);
            ad.notifyDataSetChanged();
        }
        if(v == tuxedo) {
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(5);
            ad.notifyDataSetChanged();
        }
        if(v == tabby) {
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(6);
            ad.notifyDataSetChanged();
        }
        if(v == otherColor) {
            searchCatArray = dataBaseUtils.getCatDataWithColorFromDB(7);
            ad.notifyDataSetChanged();
        }
    }

    public class Adapter extends BaseAdapter {
        TextView catColor, catBirth, adopterCity, adopterName;
        View search_cat, search_adop;
        ImageView catImg;
        Context context;
        LayoutInflater inflater;

        int[] cat_pic = {R.drawable.b_cat_white};
        String[] cat_color = {"花色"};
        String[] cat_birth = {"貓咪出生日期"};

        String[] adopter_city = {"縣市"};
        String[] adopter_name = {"名字"};


        public Adapter (Context context) {
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            if (searchCatArray == null) {
                return 0;
            } else {
                return searchCatArray.size();
            }
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

            catImg.setImageResource(searchCatArray.get(position).getCatImg3());
            catColor.setText(catColorFunction());
            catBirth.setText(searchCatArray.get(position).getBirth());
            //adopterCity.setText(searchAdopArray.get(position).getCity()); // how to find city?
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

    public String catColorFunction() {
        if ((searchCatArray.get(globalPosition).getColor()) == 1) {
            return "Black";
        }
        if ((searchCatArray.get(globalPosition).getColor()) == 2) {
            return "White";
        }
        if ((searchCatArray.get(globalPosition).getColor()) == 3) {
            return "Orange";
        }
        if ((searchCatArray.get(globalPosition).getColor()) == 4) {
            return "Calico";
        }
        if ((searchCatArray.get(globalPosition).getColor()) == 5) {
            return "Tuxedo";
        }
        if ((searchCatArray.get(globalPosition).getColor()) == 6) {
            return "Tabby";
        }
        else {
            return "others";
        }
        //((searchCatArray.get(globalPosition).getColor()) == 7)
    }
     public void adopterCityFunction() {
        String name, city;
        name = searchCatArray.get(globalPosition).getAdopterName();

     }


}
