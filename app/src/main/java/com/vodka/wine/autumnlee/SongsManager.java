package com.vodka.wine.autumnlee;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class SongsManager {

    private ArrayList<HashMap<String, String>> songsList = new ArrayList<>();
//    Context context;
    ContentResolver contentResolver;
    Cursor cursor;
    Uri uri;

    // Constructor
    public SongsManager(){

    }

    public ArrayList<HashMap<String, String>> getPlayList(Context context) {
        HashMap<String, String> song;
        contentResolver = context.getContentResolver();
        uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        cursor = contentResolver.query(uri,
                null,
                null,
                null,
                null);
        if (cursor == null) {
            Toast.makeText(context, "Something went wrong", Toast.LENGTH_LONG);

        } else if (!cursor.moveToFirst()) {
            Toast.makeText(context, "No Music found on storage", Toast.LENGTH_LONG);
        }
        else {
            int Title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int Path = cursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            do {
                String SongTitle = cursor.getString(Title);
                String SongPath = cursor.getString(Path);
                song = new HashMap<>();
                song.put("songTitle", SongTitle);
                song.put("songPath", SongPath);
                // Adding Media File Names to ListElementsArrayList.
                songsList.add(song);
                Log.i("xxx", "getPlayList: show song list: " + songsList);

            } while (cursor.moveToNext());
        }
        Log.i("xxx", "getPlayList: return songs list: " + songsList);
        return songsList;
    }

}
