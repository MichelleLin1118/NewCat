package com.example.newcat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ActivitySearch extends Activity implements View.OnClickListener {
    String TAG = "homework";
    ListView search;
    ImageButton home, black, white, orange, calico, tuxedo, tabby, other;
    //calico = 三花; tuxedo cat (燕尾服貓)= 賓士貓; tabby = 虎斑;

    @Override
    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_search);
        Adapter ad = new Adapter(this);

        search = findViewById(R.id.list_search);
        search.setAdapter(ad);

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

        other = (ImageButton) findViewById(R.id.cat_tabby_button);
        other.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        other.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == home){
            Intent homeIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.MainActivity");
            homeIntent.setComponent(cn);
            startActivity(homeIntent);
        }
        // if click "color cat button": jump to the section of such color
    }

    public class Adapter extends BaseAdapter {
        TextView catColor, catBirth, adopterCity, adopterName;
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
            return cat_pic.length;
        }

        @Override
        public Object getItem(int position) {
            return cat_pic[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = inflater.inflate(R.layout.z_list_search, parent, false);
            catImg = (ImageView) view.findViewById(R.id.cat_img);
            catColor = (TextView) view.findViewById(R.id.cat_color);
            catBirth = (TextView) view.findViewById(R.id.cat_birth);
            adopterCity = (TextView) view.findViewById(R.id.adopter_city);
            adopterName = (TextView) view.findViewById(R.id.adopter_name);

            catImg.setImageResource(cat_pic[position]);
            catColor.setText(cat_color[position]);
            catBirth.setText(cat_birth[position]);
            adopterCity.setText(adopter_city[position]);
            adopterName.setText(adopter_name[position]);

            catImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityCat");
                    intent.setComponent(cn);
                    startActivity(intent);
                    // open to specific cat page
                }
            });

            adopterName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityAdopter");
                    intent.setComponent(cn);
                    startActivity(intent);
                    // open to specific adopter page
                }
            });

            if (position == 0 ) {
                catColor.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                catColor.setTextColor(getResources().getColor(R.color.morandi_Parchment));
                catColor.setBackgroundColor(getResources().getColor(R.color.morandi_Armadillo));
                catBirth.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                catBirth.setTextColor(getResources().getColor(R.color.morandi_Parchment));
                catBirth.setBackgroundColor(getResources().getColor(R.color.morandi_Armadillo));
                adopterName.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                adopterName.setTextColor(getResources().getColor(R.color.morandi_Parchment));
                adopterName.setBackgroundColor(getResources().getColor(R.color.morandi_Armadillo));
                adopterCity.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                adopterCity.setTextColor(getResources().getColor(R.color.morandi_Parchment));
                adopterCity.setBackgroundColor(getResources().getColor(R.color.morandi_Armadillo));
            }
            if (position != 0) {
                if (position%2 == 0) {
                    catColor.setTextColor(getResources().getColor(R.color.text_brown));
                    catColor.setBackgroundColor(getResources().getColor(R.color.morandi_Soft_Amber));
                    catBirth.setTextColor(getResources().getColor(R.color.text_brown));
                    catBirth.setBackgroundColor(getResources().getColor(R.color.morandi_Soft_Amber));
                    adopterName.setTextColor(getResources().getColor(R.color.text_brown));
                    adopterName.setBackgroundColor(getResources().getColor(R.color.morandi_Soft_Amber));
                    adopterCity.setTextColor(getResources().getColor(R.color.text_brown));
                    adopterCity.setBackgroundColor(getResources().getColor(R.color.morandi_Soft_Amber));

                }
                if (position%2 != 0) {
                    catColor.setTextColor(getResources().getColor(R.color.text_brown));
                    catColor.setBackgroundColor(getResources().getColor(R.color.morandi_Donkey_Brown));
                    catBirth.setTextColor(getResources().getColor(R.color.text_brown));
                    catBirth.setBackgroundColor(getResources().getColor(R.color.morandi_Donkey_Brown));
                    adopterName.setTextColor(getResources().getColor(R.color.text_brown));
                    adopterName.setBackgroundColor(getResources().getColor(R.color.morandi_Donkey_Brown));
                    adopterCity.setTextColor(getResources().getColor(R.color.text_brown));
                    adopterCity.setBackgroundColor(getResources().getColor(R.color.morandi_Donkey_Brown));
                }
            }


            return view;
        }
    }



}
