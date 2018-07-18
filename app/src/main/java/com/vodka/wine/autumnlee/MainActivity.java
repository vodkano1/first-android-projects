package com.vodka.wine.autumnlee;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static final int RUNTIME_PERMISSION_CODE = 7;

    ConstraintLayout mh;
    ImageButton btnGo;
    MediaPlayer song;

    private ArrayList<HashMap<String, String>> songsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("xxx", "onCreate: create main activity");

        AndroidRuntimePermission();
        final ArrayList<String> songsListTitle = new ArrayList<>();
        final ArrayList<String> songsListPath = new ArrayList<>();

        SongsManager songManager = new SongsManager();
        songsList = songManager.getPlayList(getApplicationContext());
        for (int i = 0; i < songsList.size(); i++) {
            // creating new HashMap
            HashMap<String, String> song = songsList.get(i);

            // adding HashList to ArrayList
            songsListTitle.add(song.get("songTitle"));
            songsListPath.add(song.get("songPath"));
        }
        Log.i("xxx", "song title: " + songsListTitle + " song path " + songsListPath);


        mh = findViewById(R.id.manhinh);
        btnGo = findViewById(R.id.buttonGo);

        mh.setBackgroundResource(R.drawable.bg2);

        song = MediaPlayer.create(getApplicationContext(), R.raw.mylove);
        song.start();

        btnGo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                Log.i("xxx", "onClick: go to next screen");
                Intent i = new Intent(getApplicationContext(), AndroidBuildingMusicPlayerActivity.class);
                song.pause();
                i.putStringArrayListExtra("ListTitle", songsListTitle);
                i.putStringArrayListExtra("ListPath", songsListPath);
                startActivityForResult(i, 99);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        song.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        song.release();
    }

    public void AndroidRuntimePermission(){

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

            if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

                if(shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)){

                    AlertDialog.Builder alert_builder = new AlertDialog.Builder(MainActivity.this);
                    alert_builder.setMessage("External Storage Permission is Required.");
                    alert_builder.setTitle("Please Grant Permission.");
                    alert_builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            ActivityCompat.requestPermissions(
                                    MainActivity.this,
                                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                    RUNTIME_PERMISSION_CODE
                            );
                        }
                    });

                    alert_builder.setNeutralButton("Cancel",null);

                    AlertDialog dialog = alert_builder.create();

                    dialog.show();

                }
                else {

                    ActivityCompat.requestPermissions(
                            MainActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            RUNTIME_PERMISSION_CODE
                    );
                }
            }else {

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults){

        switch(requestCode){

            case RUNTIME_PERMISSION_CODE:{

                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){

                }
                else {

                }
            }
        }
    }
}
