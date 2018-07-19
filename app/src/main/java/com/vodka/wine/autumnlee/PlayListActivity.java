package com.vodka.wine.autumnlee;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;

public class PlayListActivity extends Activity {
    // Songs list
    public ArrayList<String> listTitle = new ArrayList<>();
    LinearLayout listBH;
    ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);

        listBH = findViewById(R.id.listBaiHat);
        listBH.setBackgroundResource(R.drawable.bg_thu);
        listView = findViewById(R.id.listSong);

        onActivityResult(101, 101, this.getIntent());

        // get all songs from sdcard
        Log.i("xxx", "onCreate: create Playlist activity");

            // Adding menuItems to ListView
            ArrayAdapter<?> arrayAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.playlist_item, listTitle);

            listView.setAdapter(arrayAdapter);

            // listening to single listitem click
            listView.setOnItemClickListener(new OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    // Starting new intent
                    Intent in = new Intent(getApplicationContext(),
                            AndroidBuildingMusicPlayerActivity.class);
                    // Sending songIndex to PlayerActivity
                    in.putExtra("songIndex", position);
                    setResult(100, in);
                    // Closing PlayListView
                    finish();
                }
            });
    }

    @Override
    public Intent getIntent() {
        return super.getIntent();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 101) {
            listTitle = data.getStringArrayListExtra("ListTitles");
        }
    }
}