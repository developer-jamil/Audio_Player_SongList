package com.jamillabltd.audioplayer_homework;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SongListActivity extends AppCompatActivity {
    public static String ALBUM_NAME = "";


    ListView listView;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    private MediaPlayer mediaPlayer;

    private int currentPlayingPosition = -1; // Initialize to an invalid position
    FloatingActionButton floatingActionButton;


    //controller
    String checkSongStatus = "";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);


        listView = findViewById(R.id.listViewId);

        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);

        TextView singerName = findViewById(R.id.singerNameId);
        singerName.setText(ALBUM_NAME+"\'s Song");

        //=====================================
        if (ALBUM_NAME.contains(getString(R.string.singer_1))) {
            table_singer_1();
        } else if (ALBUM_NAME.contains(getString(R.string.singer_2))) {
            table_singer_2();
        } else if (ALBUM_NAME.contains(getString(R.string.singer_3))) {
            table_singer_3();
        } else if (ALBUM_NAME.contains(getString(R.string.singer_4))) {
            table_singer_4();
        } else if (ALBUM_NAME.contains(getString(R.string.singer_5))) {
            table_singer_5();
        } else if (ALBUM_NAME.contains(getString(R.string.singer_6))) {
            table_singer_6();
        }
        //======================================

        floatingActionButton = findViewById(R.id.floatingButtonId);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //song status
                if (checkSongStatus.contains("Resumed")) {
                    Toast.makeText(SongListActivity.this, "Sopted", Toast.LENGTH_SHORT).show();
                } else if (checkSongStatus.contains("Playing_Song")) {
                    Toast.makeText(SongListActivity.this, "Sopted", Toast.LENGTH_SHORT).show();
                } else if (checkSongStatus.contains("Paused")){
                    Toast.makeText(SongListActivity.this, "Started", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(SongListActivity.this, "DF", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }


    //=== TODO: Create adapter =====
    private class MyAdapter extends BaseAdapter {

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
            View myView = layoutInflater.inflate(R.layout.list_item, parent, false);

            hashMap = arrayList.get(position);

            TextView songName = myView.findViewById(R.id.songNameId);
            songName.setText(hashMap.get("song_name"));

            CardView songArea = myView.findViewById(R.id.songAreaId);
            songArea.setOnClickListener(v -> {

                //=======================
                singerFunction(position);
                //=======================


            });

            return myView;
        }
    }
    //==============================

    private void singerFunction(int position) {
        if (position == currentPlayingPosition) {
            // Clicked on the same item, toggle play/pause
            if (mediaPlayer != null) {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    checkSongStatus = "Paused";
                    floatingActionButton.setImageResource(R.drawable.baseline_play_arrow_24);
                } else {
                    mediaPlayer.start();
                    checkSongStatus = "Resumed";
                    floatingActionButton.setImageResource(R.drawable.baseline_pause_24);
                }
            }
        } else {
            // Clicked on a different item, stop the previous song and play the new one
            stopMediaPlayer(); // Stop the previous song

            mediaPlayer = new MediaPlayer();
            try {
                // Access the song URL from the HashMap based on the position
                String songUrl = arrayList.get(position).get("song_url");

                // Show "Please Wait" toast message
                checkSongStatus = "Loading From Server";
                Toast.makeText(SongListActivity.this, ""+checkSongStatus, Toast.LENGTH_SHORT).show();
                mediaPlayer.setDataSource(songUrl);
                mediaPlayer.prepare();

                // Set a completion listener to display "End Song" when playback completes
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        checkSongStatus = "End Song";
                        floatingActionButton.setImageResource(R.drawable.baseline_play_arrow_24);
                    }
                });

                mediaPlayer.start();

                // Show "Playing Song" toast message
                checkSongStatus = "Playing_Song";
                floatingActionButton.setImageResource(R.drawable.baseline_pause_24);
                currentPlayingPosition = position;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


    private void stopMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    // ...
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopMediaPlayer(); // Release MediaPlayer resources when the activity is destroyed
    }


    //TODO: Singer Tahsan
    private void table_singer_1() {
        hashMap = new HashMap<>();
        hashMap.put("song_name", "Song 1");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/song_4.mp3");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("song_name", "Song 2");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/song_5.mp3");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("song_name", "Song 3");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/song_6.mp3");
        arrayList.add(hashMap);

    }

    //TODO: Singer Arijit
    private void table_singer_2() {
        hashMap = new HashMap<>();
        hashMap.put("song_name", "Song 1");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/song_6.mp3");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("song_name", "Song 2");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/song_5.mp3");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("song_name", "Song 3");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/song_4.mp3");
        arrayList.add(hashMap);

    }

    //TODO: Singer Neha ***
    private void table_singer_3() {
        hashMap = new HashMap<>();
        hashMap.put("song_name", "Lamborghini - Neha Kakkar");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/neha/lamborghini_neha.mp3");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("song_name", "Baarish Mein Tum - Neha");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/neha/barish_neha.mp3");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("song_name", " O Sajna - Neha");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/neha/o_sajna_neha.mp3");
        arrayList.add(hashMap);

    }

    //TODO: Singer Tonoy ***
    private void table_singer_4() {
        hashMap = new HashMap<>();
        hashMap.put("song_name", "Dheeme Dheem");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/tonoy/dheeme_dheeme_tonoy.mp3");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("song_name", "12 Ladke - Tony Kakkar");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/tonoy/ladke_tonoy.mp3");
        arrayList.add(hashMap);



    }

    //TODO: Singer Badsha
    private void table_singer_5() {
        hashMap = new HashMap<>();
        hashMap.put("song_name", "Song 1");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/song_6.mp3");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("song_name", "Song 2");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/song_5.mp3");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("song_name", "Song 3");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/song_4.mp3");
        arrayList.add(hashMap);

    }

    //TODO: Singer Honey
    private void table_singer_6() {
        hashMap = new HashMap<>();
        hashMap.put("song_name", "Song 1");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/song_6.mp3");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("song_name", "Song 2");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/song_5.mp3");
        arrayList.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("song_name", "Song 3");
        hashMap.put("song_url", "https://lrjamil.000webhostapp.com/songs/song_4.mp3");
        arrayList.add(hashMap);

    }





}