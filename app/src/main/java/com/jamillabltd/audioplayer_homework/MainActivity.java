package com.jamillabltd.audioplayer_homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    GridView gridView;

    HashMap<String, String> hashMap;
    ArrayList< HashMap<String, String>> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridViewId);

        MyAdapter myAdapter = new MyAdapter();
        gridView.setAdapter(myAdapter);

        //crate table
        createTable();

    }


    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @SuppressLint("MissingInflatedId")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = layoutInflater.inflate(R.layout.grid_item_view, parent, false);

            hashMap = arrayList.get(position);

            TextView tvTitle = myView.findViewById(R.id.tvTitleId);
            tvTitle.setText(hashMap.get("title"));

            //set the image
            String getTvImage = hashMap.get("icon_url");

            ImageView tvImage = myView.findViewById(R.id.tvIconId);
            Picasso.get().load(getTvImage)
                    .placeholder(R.drawable.loading)
                    .into(tvImage);

            CardView gridViewArea = myView.findViewById(R.id.gridViewAreaId);
            gridViewArea.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SongListActivity.ALBUM_NAME = tvTitle.getText().toString();
                    startActivity(new Intent(MainActivity.this, SongListActivity.class));
                }

            });


            return myView;
        }
    }

    private void createTable() {

        //== 1st item ===========
        hashMap = new HashMap<>();
        hashMap.put("icon_url", "https://assets.telegraphindia.com/abp/2021/Dec/1639134432_123-28.jpg");
        hashMap.put("title", getString(R.string.singer_1));
        arrayList.add(hashMap);

        //== 2nd item ===========
        hashMap = new HashMap<>();
        hashMap.put("icon_url", "https://static.toiimg.com/thumb/msid-91352401,width-1280,resizemode-4/91352401.jpg");
        hashMap.put("title", getString(R.string.singer_2));
        arrayList.add(hashMap);

        //== 3rd item ===========
        hashMap = new HashMap<>();
        hashMap.put("icon_url", "https://fabceleby.in/wp-content/uploads/2022/07/Neha-Kakkar1.jpg");
        hashMap.put("title", getString(R.string.singer_3));
        arrayList.add(hashMap);

        //== 4th item ===========
        hashMap = new HashMap<>();
        hashMap.put("icon_url", "https://images.hindustantimes.com/img/2021/12/03/1600x900/759d7636-5363-11ec-997e-74ec8c0ca0c9_1638506926420.jpg");
        hashMap.put("title", getString(R.string.singer_4));
        arrayList.add(hashMap);


        //== 5th item ===========
        hashMap = new HashMap<>();
        hashMap.put("icon_url", "https://assets.telegraphindia.com/abp/2023/Mar/1678295664_bad.jpg");
        hashMap.put("title", getString(R.string.singer_5));
        arrayList.add(hashMap);


        //== 6th item ===========
        hashMap = new HashMap<>();
        hashMap.put("icon_url", "https://static.india.com/wp-content/uploads/2023/02/Honey-Singh-2.jpg");
        hashMap.put("title", getString(R.string.singer_6));
        arrayList.add(hashMap);

      

    }



}