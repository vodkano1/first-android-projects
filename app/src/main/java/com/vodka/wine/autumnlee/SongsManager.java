package com.vodka.wine.autumnlee;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;


public class SongsManager{

    // Constructor
    public SongsManager(){

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
