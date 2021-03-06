package com.example.newcat;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ActivityCat extends Activity implements View.OnClickListener {
    String TAG = "homework";
    Button saveButton;
    ImageButton home, addPageButton;
    CheckBox vaccine, deworm, bloodTest,ligation, antiparasite, nailsCutted, earsCleaned, allCheck, mixed;
    EditText weight, birth, adoptionDate, vaccineName, about,others, colorEdit, adopterName;
    ImageView catImg;
    ToggleButton sexuality;
    Spinner color;
    ViewPager pager;
    Uri uri;
    String[] checkbox = new String[]{"vaccine", "deworm", "bloodTest","ligation", "antiparasite", "nailsCutted", "earsCleaned", "allCheck", "mixed"};
    ArrayList<View> catPageArrayList = new ArrayList<>();
    DataBaseUtils dataBaseUtils;
    ArrayList<DataBaseCat> data;
    //ArrayList<DataBaseAdopter> adopData;
    int location, globalPosition;
    int REQUEST_IMAGE_SELECT = 100;
    int PERMISSION_REQUEST_CODE = 100;
    int pictureIndex = 0;
    long[] pictureId = new long[3];
    Uri selectedImage;
    CatActivityAdapter mCatActivityAdapter = new CatActivityAdapter(this);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, "cat onCreate");
        setContentView(R.layout.activity_cat);

        dataBaseUtils = new DataBaseUtils(this);
        data = dataBaseUtils.getCatDataFromDB();
        //adopData = dataBaseUtils.getAdopterDataFromDB();

        home = (ImageButton) findViewById((R.id.home_button));
        home.setOnClickListener(this);

        addPageButton = (ImageButton) findViewById(R.id.add_page_button);
        addPageButton.setOnClickListener(this);

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
                pictureIndex = 0; //
                Log.i(TAG, "location = " + location);
                data = dataBaseUtils.getCatDataFromDB();
                mCatActivityAdapter.notifyDataSetChanged();
            }
            @Override
            public void onPageScrollStateChanged ( int state){

            }
        });
        dataBaseUtils.showCatDataBaseResult();
        openSpecificPage();
    }

    @Override
    public void onClick(View v) {
        if (v == home){
            Intent homeIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.MainActivity");
            homeIntent.setComponent(cn);
            startActivity(homeIntent);
        }
        if (v == addPageButton) {
            getContentResolver().insert(DataBaseCat.CONTENT_URI_CAT, dataBaseUtils.createCatData(new DataBaseCat()));
            data = dataBaseUtils.getCatDataFromDB();
            catPageArrayList.add(LayoutInflater.from(this).inflate(R.layout.view_pager_cat, null));
            mCatActivityAdapter.notifyDataSetChanged();
        }
    }
    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(ActivityCat.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

        }else {
            ActivityCompat.requestPermissions(ActivityCat.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
        }
    }

    private void requestPermission(int PERMISSION_REQUEST_CODE) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(ActivityCat.this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(ActivityCat.this, "Need permission!", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(ActivityCat.this, new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, this.PERMISSION_REQUEST_CODE);
        }
    }

    public void openPicByUri() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_IMAGE_SELECT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intentData) {
        super.onActivityResult(requestCode, resultCode, intentData);
        if (requestCode == REQUEST_IMAGE_SELECT && resultCode == RESULT_OK) {
            selectedImage = intentData.getData();
            ContentResolver resolver = getApplicationContext().getContentResolver();
            String[] mProjection = {
                    MediaStore.Images.Media._ID,
                    MediaStore.Images.Media.DATA,
                    MediaStore.Images.Media.DISPLAY_NAME
            };
            Cursor cursor = resolver.query(selectedImage, mProjection, null, null, null);
            int idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID);
            int nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME);

            if (cursor != null && cursor.moveToFirst()) {
                long id = cursor.getLong(idColumn);
                String imgPath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                String fileName = cursor.getString(nameColumn);
                ((ImageView)(pager.findViewWithTag("ActivityCat" + location + "catImg"))).setImageURI(intentData.getData());
                uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id);
                Log.i(TAG, ">>>>>>>>>>>>>>>>>>>>> pictureIndex = " + pictureIndex);
                if (pictureIndex%3 == 0) {
                    pictureId[0] = id;
                }
                else if (pictureIndex%3 == 1) {
                    pictureId[1] = id;
                }
                else if (pictureIndex%3 == 2) {
                    pictureId[2] = id;
                }
                saveFunction();
                data = dataBaseUtils.getCatDataFromDB();
                mCatActivityAdapter.notifyDataSetChanged();
            }
        }
    }
    public void openSpecificPage() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            int page = bundle.getInt("page");
            pager.setCurrentItem(page-1, false);
        } else {
            pager.setCurrentItem(data.size()-1, false);
        }
    }
    public void saveFunction() {
        int id = location +1;
        ContentValues values = new ContentValues();
        values.put(DataBaseCat.WEIGHT, (((EditText)(tagFunction( location + "weight"))).getText().toString()));
        values.put(DataBaseCat.BIRTH, (((EditText)(tagFunction( location + "birth"))).getText().toString()));
        values.put(DataBaseCat.ADOPTION, (((EditText)(tagFunction( location + "adoptionDate"))).getText().toString()));
        values.put(DataBaseCat.COLOR, (((Spinner)(tagFunction( location + "color"))).getSelectedItemPosition()));
        values.put(DataBaseCat.VACCINE_NAME, (((EditText)(tagFunction( location + "vaccineName"))).getText().toString()));
        values.put(DataBaseCat.ABOUT,(((EditText)(tagFunction( location + "about"))).getText().toString()));
        values.put(DataBaseCat.OTHER, (((EditText)(tagFunction( location + "others"))).getText().toString()));
        values.put(DataBaseCat.ADOPTER_NAME, (((EditText)(tagFunction(location + "adopterName"))).getText().toString().trim()));
        values.put(DataBaseCat.VACCINE, (((CheckBox)(tagFunction( location + "vaccine"))).isChecked()));
        values.put(DataBaseCat.LIGATION, (((CheckBox)(tagFunction( location + "ligation"))).isChecked()));
        values.put(DataBaseCat.DEWORM, (((CheckBox)(tagFunction( location + "deworm"))).isChecked()));
        values.put(DataBaseCat.BLOOD_TEST,(((CheckBox)(tagFunction( location + "bloodTest"))).isChecked()));
        values.put(DataBaseCat.EARS_CLEANED, (((CheckBox)(tagFunction( location + "earsCleaned"))).isChecked()));
        values.put(DataBaseCat.NAILS_CUTTED,(((CheckBox)(tagFunction( location + "nailsCutted"))).isChecked()));
        values.put(DataBaseCat.ANTIPARASITE,(((CheckBox)(tagFunction( location + "antiparasite"))).isChecked()));
        values.put(DataBaseCat.MIXED, (((CheckBox)(tagFunction( location + "mixed"))).isChecked()));
        values.put(DataBaseCat.SEXUALITY, (((ToggleButton)(tagFunction( location + "sexuality"))).isChecked()));
        values.put(DataBaseCat.ALL_CHECK, (((CheckBox)(tagFunction( location + "allCheck"))).isChecked()));
        values.put(DataBaseCat.CAT_IMG, pictureId[0]);
        values.put(DataBaseCat.CAT_IMG2, pictureId[1]);
        values.put(DataBaseCat.CAT_IMG3, pictureId[2]);
        //nameFunction(((EditText) tagFunction(location + "adopterName")).getText().toString());
        getContentResolver().update(DataBaseCat.CONTENT_URI_CAT, values, DataBaseCat._ID + " = " + id, null);
        dataBaseUtils.showCatDataBaseResult();
    }

    public View tagFunction(String tag) {
        return pager.findViewWithTag("ActivityCat"+tag);
    }

    public void nameFunction(String adopterName) {
        if (dataBaseUtils.getAdopterDataWithAdopterNameFromDB(adopterName).getName().equals("name")) {
            //getContentResolver().insert(DataBaseAdopter.CONTENT_URI_ADOPTER, dataBaseUtils.createAdopterData(new DataBaseAdopter(adopterName)));
        } else {
            Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, data.get(globalPosition).getCatImg());
            //dataBaseUtils.getAdopterDataWithAdopterNameFromDB(adopterName).setCatImg().setImageURI(uri);
            // send the first cat img to adopter file (catImg go adopter's first, if it is default --> set img; if not --> set img2
        }
    }


    class CatActivityAdapter extends PagerAdapter implements View.OnClickListener, View.OnLongClickListener {

        Context context;

        public CatActivityAdapter(Context context){
            this.context = context;
        }

        @Override
        public int getCount() {
            return data.size();
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
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) { }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        };

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Log.i(TAG,"instantiateItem");
            Log.i(TAG, "location = " + location);
            globalPosition = position;
            ((ViewPager) container).addView(catPageArrayList.get(position));

            vaccine = (CheckBox) catPageArrayList.get(position).findViewById(R.id.vaccine);
            deworm = (CheckBox) catPageArrayList.get(position).findViewById(R.id.deworm);
            bloodTest = (CheckBox) catPageArrayList.get(position).findViewById(R.id.blood_test);
            ligation = (CheckBox) catPageArrayList.get(position).findViewById(R.id.ligation);
            antiparasite = (CheckBox) catPageArrayList.get(position).findViewById(R.id.antiparasite);
            earsCleaned = (CheckBox) catPageArrayList.get(position).findViewById(R.id.cleanEar);
            nailsCutted = (CheckBox) catPageArrayList.get(position).findViewById(R.id.cutNails);
            allCheck = (CheckBox) catPageArrayList.get(position).findViewById(R.id.allCheck);
            saveButton = (Button) catPageArrayList.get(position).findViewById(R.id.save_button);
            mixed = (CheckBox) catPageArrayList.get(position).findViewById(R.id.mixed);

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
            adopterName = (EditText) catPageArrayList.get(position).findViewById(R.id.adopter_name);

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
            adopterName.setTag("ActivityCat" + position + "adopterName");

            sexuality.setTextOff("male");
            sexuality.setTextOn("female");

            ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(context, R.array.color_array, android.R.layout.simple_spinner_item);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            color.setAdapter(arrayAdapter);
            color.setSelection(0, false);
            color.setOnItemSelectedListener(mSpinSelectedListener);

            tagFunction(position + "saveButton").setOnClickListener(this);
            tagFunction(position + "catImg").setOnClickListener(this);
            tagFunction(position + "catImg").setOnLongClickListener(this);
            for (int i=0; i< checkbox.length; i++){
                tagFunction(position + checkbox[i]).setOnClickListener(this);
            }

            ((CheckBox) tagFunction(position + "vaccine")).setChecked(data.get(position).getVac());
            ((CheckBox) tagFunction(position + "deworm")).setChecked(data.get(position).getDew());
            ((CheckBox) tagFunction(position + "bloodTest")).setChecked(data.get(position).getBlood());
            ((CheckBox) tagFunction(position + "ligation")).setChecked(data.get(position).getLig());
            ((CheckBox) tagFunction(position + "antiparasite")).setChecked(data.get(position).getAntiparasite());
            ((CheckBox) tagFunction(position + "nailsCutted")).setChecked(data.get(position).getnailsCutted());
            ((CheckBox) tagFunction(position + "earsCleaned")).setChecked(data.get(position).getearsCleaned());
            ((CheckBox) tagFunction(position + "allCheck")).setChecked(data.get(position).getAllCheck());
            ((CheckBox) tagFunction(position + "mixed")).setChecked(data.get(position).getMixed());
            ((EditText) tagFunction(position + "weight")).setText(data.get(position).getWeight());
            ((EditText) tagFunction(position + "birth")).setText(data.get(position).getBirth());
            ((EditText) tagFunction(position + "vaccineName")).setText(data.get(position).getVaccineName());
            ((EditText) tagFunction(position + "about")).setText(data.get(position).getAbout());
            ((EditText) tagFunction(position + "others")).setText(data.get(position).getOther());
            ((EditText) tagFunction(position + "adoptionDate")).setText(data.get(position).getAdoption());
            ((EditText) tagFunction(position + "adopterName")).setText(data.get(position).getAdopterName());
            ((Spinner) tagFunction(position + "color")).setSelection(data.get(position).getColor());

            if (data.get(position).getCatPic()[pictureIndex%3] != 0) {
                Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, data.get(position).getCatPic()[pictureIndex%3]);
                ((ImageView) tagFunction(position + "catImg")).setImageURI(uri);
            }
            ((ToggleButton) tagFunction(position + "sexuality")).setText(getSexuality(data.get(position).getSexuality()));

            mixedCheck(data.get(position).getMixed(), position);
            return catPageArrayList.get(position);
        }


        @Override
        public void onClick(View v) {
            Log.i(TAG, "onClick");
            Log.i(TAG, "v.getTag() = " + v.getTag());

            if (v == tagFunction(location + "catImg")){
                Log.i(TAG, "-- picture index = " + pictureIndex);
                if (data.get(location).getCatPic()[(pictureIndex)%3] != 0) {
                    Uri uri = ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, data.get(location).getCatPic()[(pictureIndex+1)%3]);
                    ((ImageView) tagFunction(location + "catImg")).setImageURI(uri);
                    pictureIndex = pictureIndex + 1;
                }
                Log.i(TAG, ">>>>>>>>>>>>>>>>>>> pictureIndex = " + pictureIndex);
            }
            if (v == tagFunction(location + "allCheck")) {
                if (((CheckBox) tagFunction( location + "allCheck")).isChecked() == true) {
                    allCheckedClick(true);} else { allCheckedClick(false);
                }
            }
            if(v == tagFunction(location + "vaccine")) {checkboxFunction("vaccine");}
            if(v == tagFunction(location + "deworm")) {checkboxFunction("deworm");}
            if(v == tagFunction(location + "bloodTest")) {checkboxFunction("bloodTest");}
            if(v == tagFunction(location + "ligation")) {checkboxFunction("ligation");}
            if(v == tagFunction(location + "antiparasite")) {checkboxFunction("antiparasite");}
            if(v == tagFunction(location + "earsCleaned")) {checkboxFunction("earsCleaned");}
            if(v == tagFunction(location + "nailsCutted")) {checkboxFunction("nailsCutted");}

            if(v == tagFunction( location + "mixed")) {
                if (((CheckBox) tagFunction( location + "mixed")).isChecked() == true) {
                    mixedCheck(true, location); } else { mixedCheck(false, location);
                }
            }
            if(v == tagFunction( location + "saveButton")) {
                Log.i(TAG, "--------------------save button clicked");
                saveFunction();
            }

        }
        public void mixedCheck(boolean mixedCalled, int index) {
            if (mixedCalled == true) {
                ((CheckBox) tagFunction(index + "mixed")).setChecked(true);
                ((EditText) tagFunction(index + "colorEdit")).setVisibility(View.GONE);
                ((Spinner) tagFunction(index +"color")).setVisibility(View.VISIBLE);
                ((Spinner) tagFunction(index +"color")).setSelection(data.get(index).getColor());
            } else {
                ((CheckBox) tagFunction(index + "mixed")).setChecked(false);
                ((EditText) tagFunction(index + "colorEdit")).setVisibility(View.VISIBLE);
                ((Spinner) tagFunction(index +"color")).setVisibility(View.GONE);
                ((Spinner) tagFunction(index +"color")).setSelection(data.get(index).getColor());
                allCheckFalse();
            }
        }
        public void allCheckFalse () {
            ((CheckBox) tagFunction(location + "allCheck")).setChecked(false);
        }
        public void allCheckedInit(boolean allCalled) {
            if (allCalled == true) {
                for (int i=0; i < checkbox.length; i ++) {
                    ((CheckBox) tagFunction(globalPosition + checkbox[i])).setChecked(true); }
                mixedCheck(true, globalPosition);
            } else {
                ((CheckBox) tagFunction(globalPosition + "allCheck")).setChecked(false); }
        }
        public void allCheckedClick(boolean allCalled) {
            if (allCalled == true) {
                for (int i = 0; i < checkbox.length; i++) {
                    ((CheckBox) tagFunction(location + checkbox[i])).setChecked(true); }
                mixedCheck(true, location);
            } else {
                for (int i = 0; i < checkbox.length; i++) {
                    ((CheckBox) tagFunction(location + checkbox[i])).setChecked(false); }
                mixedCheck(false, location);
            }
        }

        public String getSexuality (boolean sexuality) {
            return sexuality ? "FEMALE" : "MALE";
        }

        public void checkboxFunction (String tag) {
            if (((CheckBox) tagFunction(location + tag)).isChecked() == false) {
                    ((CheckBox) tagFunction(location + tag)).setChecked(false); allCheckFalse();
            } else {((CheckBox) tagFunction(location + tag)).setChecked(true);
            }
        }@Override
        public boolean onLongClick(View v) {
            if (v == tagFunction(location + "catImg")) {
                checkPermission();
                openPicByUri();
            }
            return false;
        }

    }


}
