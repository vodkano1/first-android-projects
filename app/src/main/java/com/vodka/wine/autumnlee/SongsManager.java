package com.vodka.wine.autumnlee;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class SongsManager{

    private Context context;

    private List<String> ListElementsArrayList;
//    List<String> ListElementsArrayList;
    // Constructor
    public SongsManager(){

    }

    public void getAllMp3Files() {
        ContentResolver contentResolver;
        contentResolver = context.getContentResolver();
        Uri uri = MediaStore.Audio.Media.INTERNAL_CONTENT_URI;
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        if(cursor == null) {
            Log.i("xxx", "getAllMp3Files: somethings went wrong");
        } else if (!cursor.moveToFirst()) {
            Log.i("xxx", "no mp3 file");
        } else {
            int Title = cursor.getColumnIndex(MediaStore.Audio.Media.TITLE);

            //Getting Song ID From Cursor.
            //int id = cursor.getColumnIndex(MediaStore.Audio.Media._ID);

            do {

                // You can also get the Song ID using cursor.getLong(id).
                //long SongID = cursor.getLong(id);

                String SongTitle = cursor.getString(Title);

                // Adding Media File Names to ListElementsArrayList.

                ListElementsArrayList.add(SongTitle);

            } while (cursor.moveToNext());
        }
    }
    ArrayList<HashMap<String,String>> getPlayList(String rootPath) {
        ArrayList<HashMap<String,String>> fileList = new ArrayList<>();


        try {
            File rootFolder = new File(rootPath);
            Log.i("xxx", "getPlayList: rootFolder " + rootFolder);
            File[] files = rootFolder.listFiles(); //here you will get NPE if directory doesn't contains  any file,handle it like this.
            for (File file : files) {
                if (file.isDirectory()) {
                    if (getPlayList(file.getAbsolutePath()) != null) {
                        Log.i("xxx", "getPlayList: file directory " + file.getAbsolutePath());
                        fileList.addAll(getPlayList(file.getAbsolutePath()));
                    } else {
                        break;
                    }
                } else if (file.getName().endsWith(".mp3")) {
                    Log.i("xxx", "getPlayList: file name " + file.getName());
                    HashMap<String, String> song = new HashMap<>();
                    song.put("songPath", file.getAbsolutePath());
                    song.put("songTitle", file.getName());
                    fileList.add(song);
                }
            }
            return fileList;
        } catch (Exception e) {
            Log.i("xxx", "getPlayList: Exception " + e);
            return null;
        }
    }
}
