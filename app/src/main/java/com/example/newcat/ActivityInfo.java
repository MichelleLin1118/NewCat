package com.example.newcat;

import android.Manifest;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class ActivityInfo extends Activity implements View.OnClickListener{
    String TAG = "homework";
    ListView hospitals;
    ImageButton home;

    @Override
    public void onCreate (Bundle bundle) {
        super.onCreate(bundle);
        Log.i(TAG, "onCreate");
        setContentView(R.layout.activity_info);

        home = (ImageButton) findViewById(R.id.home_button);
        home.setOnClickListener(this);

        hospitals = findViewById(R.id.list);
        Adapter ad = new Adapter(this);
        hospitals.setAdapter(ad);
    }
    @Override
    public void onClick(View v) {
        if (v == home){
            Intent homeIntent = new Intent();
            ComponentName cn = new ComponentName("com.example.newcat", "com.example.newcat.ActivityMain");
            homeIntent.setComponent(cn);
            startActivity(homeIntent);
        }

    }

    public class Adapter extends BaseAdapter {
        TextView name;
        TextView contact;
        TextView address;
        Context context;
        LayoutInflater inflater;// = (LayoutInflater) ;

        String[] hospi = {"醫院",  "台北市", "大安動物醫院", "慈愛動物醫院(台北總院)", "伊甸動物醫院", "太僕動物醫院", "隆記動物醫院", "南京太僕動物醫院", "展望急診動物醫院", "阿牛犬貓急診醫院", " 布達羊急診動物醫院", "全民動物醫院(北投分院)", "全國動物醫院(台北分院)", "大群動物醫院",
                "新北市", "來安動物醫院", " 中日動物醫院", "提姆沃克動物醫院", "祐全動物醫院",
                "桃園市", "磨鼻子動物醫院", "元氣動物醫院", "品湛動物醫院", "元氣動物醫院(藝文分院)",
                "台中市/彰化縣", "全國動物醫院(總院)", "康德動物醫院", "台灣動物醫院(教學醫院)", "艾利動物醫院", "吉米哈利動物醫院", "毛公館動物醫院", "慈愛動物醫院(台中總院)", "成大動物醫院", "快樂寵物醫院",
                "台南市", "慈愛動物醫院(台南總院)", "全國動物醫院(永康院)",
                "高雄市", "烏鐸動物醫院", "冠安動物醫院"};
        String[] cont = {"電話", " ", "02-2363-2020", "02-2556-3320", "02-8509-2579", "02-2517-0902", "02-2760-7639", "02-2756-2005", "02-2388-0122", "02-2882-7381", "02-2834-1119", "02-2893-9752", "02-8791-8706", "02-2930-5557",
                " ", "02-2211-8890", "02-2226-3639", "02-8982-9291", "02-2997-5827",
                " ", "03-453-5740", "03-333-3816", "03-336-3252", "03-355-3911",
                " ", "04-2371-0496", "04-2241-2700", "04-2317-7069", "04-2258-9518", "04-2320-6910", "04-2380-9513", "04-2406-6688", "04-2639-8365", "04-738-4978",
                " ", "06-220-3166", "06-313-3116",
                " ", "07-722-0804", "07-223-6451"};
        String[] addr = {"地址", " ", "100台北市中正區羅斯福路四段162號1樓", "103台北市大同區寧夏路1號", "104台北市中山區北安路554巷33號", " 104台北市中山區龍江路260號", "105台北市松山區民生東路五段212巷1號", "105台北市松山區南京東路五段286號", "108台北市萬華區中華路二段2號", "111台北市士林區基河路238號", "111台北市士林區忠誠路一段102號", "112台北市北投區懷德街6-3號", "114台北市內湖區舊宗路一段30巷13號", "116台北市文山區羅斯福路六段206號",
                " ", "231新北市新店區安康路二段115-1號", "235新北市中和區中山路三段2號", "241新北市三重區中正北路23號", "242新北市新莊區幸福路795號",
                " ", "320桃園市中壢區延平路20號", "330桃園市桃園區三民路三段381號", "330桃園市桃園區民生路495-9號", "330桃園市桃園區莊敬路一段156號",
                " ", "403台中市西區五權八街100號", "406台中市北屯區崇德路二段270號", "407台中市西屯區青海路二段69號", "407台中市西屯區惠中路二段41號", "408台中市南屯區大聖街250號", "408台中市南屯區公益路二段685號", "412台中市大里區國光路二段539號", "436台中市清水區臨港路五段658巷27號", "500彰化縣彰化市彰南路二段180號",
                " ", "702台南市南區西門路一段473號", "710台南市永康區中華路103號2樓",
                " ", "802高雄市苓雅區中正一路139號", "802高雄市苓雅區中正二路131-1號"};

        public Adapter(Context context) {
            this.context = context;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return hospi.length;
        }

        @Override
        public Object getItem(int position) {
            return hospi[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            view = inflater.inflate(R.layout.z_list_hospital, parent, false);
            name = (TextView) view.findViewById(R.id.hospital_name);
            contact = (TextView) view.findViewById(R.id.hospital_contact);
            address = (TextView) view.findViewById(R.id.hospital_address);

            name.setText(hospi[position]);
            contact.setText(cont[position]);
            address.setText(addr[position]);

            name.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Log.i("TEST", "name click");
                    Intent intent = new Intent();
                    intent.setAction(intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.google.com/search?q=" + hospi[position]));
                    startActivity(intent);
                }
            });

            contact.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Log.i("TEST", "contact click");
                    Intent intent = new Intent();
                    intent.setAction(intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:")); //+ getPhoneNum));
                    if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                        ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.CALL_PHONE}, 1);
                    }else {
                        startActivity(intent);
                    }
                    intent.setData(Uri.parse("http://www.google.com/search?q=" + cont[position]));
                    startActivity(intent);
                }
            });
            address.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Log.i("TEST", "address click");
                    Intent intent = new Intent();
                    intent.setAction(intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.google.com/search?q=" + addr[position]));
                    startActivity(intent);
                }
            });


            if (position == 0 ) {
                name.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                contact.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
                address.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));

                name.setTextColor(getResources().getColor(R.color.morandi_Parchment));
                name.setBackgroundColor(getResources().getColor(R.color.morandi_Armadillo));
                contact.setTextColor(getResources().getColor(R.color.morandi_Parchment));
                contact.setBackgroundColor(getResources().getColor(R.color.morandi_Armadillo));
                address.setTextColor(getResources().getColor(R.color.morandi_Parchment));
                address.setBackgroundColor(getResources().getColor(R.color.morandi_Armadillo));
            }

            if (position != 0) {
                if (position%2 == 0) {
                    name.setTextColor(getResources().getColor(R.color.text_brown));
                    name.setBackgroundColor(getResources().getColor(R.color.morandi_Soft_Amber));
                    contact.setTextColor(getResources().getColor(R.color.text_brown));
                    contact.setBackgroundColor(getResources().getColor(R.color.morandi_Soft_Amber));
                    address.setTextColor(getResources().getColor(R.color.text_brown));
                    address.setBackgroundColor(getResources().getColor(R.color.morandi_Soft_Amber));
                }
                if (position%2 != 0) {
                    name.setTextColor(getResources().getColor(R.color.text_brown));
                    name.setBackgroundColor(getResources().getColor(R.color.morandi_Donkey_Brown));
                    contact.setTextColor(getResources().getColor(R.color.text_brown));
                    contact.setBackgroundColor(getResources().getColor(R.color.morandi_Donkey_Brown));
                    address.setTextColor(getResources().getColor(R.color.text_brown));
                    address.setBackgroundColor(getResources().getColor(R.color.morandi_Donkey_Brown));
                }
                if (cont[position].equals(" ")) {
                    name.setBackgroundColor(getResources().getColor(R.color.morandi_bone));
                    contact.setBackgroundColor(getResources().getColor(R.color.morandi_bone));
                    address.setBackgroundColor(getResources().getColor(R.color.morandi_bone));
                }
            }
            return view;
        }



    }
}
