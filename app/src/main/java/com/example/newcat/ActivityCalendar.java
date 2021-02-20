package com.example.newcat;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ActivityCalendar extends Activity implements View.OnClickListener{

    ImageButton home;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        home = (ImageButton) findViewById((R.id.home_button));
        home.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        home.setOnClickListener(this);
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
}


/* calendar 功能：
- 記錄送貓日期
- 記錄貓咪醫療重要日期
- 每兩個禮拜彈出通知提醒用者聯絡收養人，確認貓狀況


 */
