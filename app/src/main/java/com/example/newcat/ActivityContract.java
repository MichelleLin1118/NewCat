package com.example.newcat;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ComponentName;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ActivityContract extends Activity implements View.OnClickListener, View.OnLongClickListener{

    String TAG = "homework";
    ImageView contractImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_adoption_contract);

        contractImg = (ImageView) findViewById(R.id.contract_img);
        contractImg.setOnLongClickListener(this);

        if (contractImg == null) {
            contractImg.setImageResource(R.drawable.b_search);
        } else {
            Log.i(TAG, "........................................");
            //Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, data.get(position).getCatPic()[pictureIndex%3]);
            //contractImg.setImageURI(uri);
        }
    }
    @Override
    protected void onStart(){
        super.onStart();
        Log.i(TAG, "onStart");
    }
    @Override
    public void onPause(){
        super.onPause();
        Log.i(TAG, "onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Log.i(TAG, "onStop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }

    @Override
    public void onClick(View v) {
        if (v == contractImg) {
            // if long click doesn't do, I did button ui for the two 功能
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (v == contractImg) {
            // can we have two 功能 here? 1.take picture 2.download
        }
        return false;
    }

}