package com.vodka.wine.autumnlee;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    ConstraintLayout mh;
    ImageButton btnGo;
    MediaPlayer song;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        song.stop();
    }
}
