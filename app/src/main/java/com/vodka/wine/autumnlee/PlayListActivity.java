package com.vodka.wine.autumnlee;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class PlayListActivity extends ListActivity {
    // Songs list
    public ArrayList<HashMap<String, String>> songsList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.playlist);

        ArrayList<HashMap<String, String>> songsListData = new ArrayList<>();

        SongsManager plm = new SongsManager();
        // get all songs from sdcard
        ArrayList<HashMap<String,String>> songsList = plm.getPlayList(Environment.getDataDirectory().toString());
        Log.i("xxx", "onCreate: songList " + songsList + " path: " + Environment.getDataDirectory().toString());
        if(songsList!=null){
            for(int i=0;i<songsList.size();i++){
                String fileName=songsList.get(i).get("songTitle");
                String filePath=songsList.get(i).get("songPath");
                //here you will get list of file name and file path that present in your device
                Log.i("xxx "," name ="+fileName +" path = "+filePath);
            }
        }

        // looping through playlist
        for (int i = 0; i < songsList.size(); i++) {
            // creating new HashMap
            HashMap<String, String> song = songsList.get(i);

            // adding HashList to ArrayList
            songsListData.add(song);
        }

        // Adding menuItems to ListView
        ListAdapter adapter = new SimpleAdapter(this, songsListData,
                R.layout.playlist, new String[] { "songTitle" }, new int[] {
                R.id.songTitle});

        setListAdapter(adapter);

        // selecting single ListView item
        ListView lv = getListView();
        // listening to single listitem click
        lv.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // getting listitem index
//                int songIndex = position;

                // Starting new intent
                Intent in = new Intent(getApplicationContext(),
                        MainActivity.class);
                // Sending songIndex to PlayerActivity
                in.putExtra("songIndex", position);
                setResult(100, in);
                // Closing PlayListView
                finish();
            }
        });

    }
}
