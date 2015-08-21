package com.example.yuni.cloud;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Lee Hyo Seung on 2015-08-12.
 */
public class UniDevFileData {
    public class UFile{
        public String path;
        //path of the file
        public String name;
        //name of the file
        public long size;
        //size of the file;
        public long movieLength;
        //movie Length(millisecond)
        public Bitmap thumbNail;
        public File file;

        public UFile(String path){
            try {
                file = new File(path);
                this.path = file.getPath();
                this.name = file.getName();
                size = file.length();
                if(isMovie()){
                    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
                    retriever.setDataSource(path);
                    String time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
                    movieLength = Long.parseLong(time);
                    thumbNail = ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND);
                }
            }catch (Exception e){
                file = null;
            }
        }
        //constructor made by path

        public boolean isMovie(){
            if(name.endsWith(".avi") || name.endsWith(".mp4")){
                return true;
            }else{
                return false;
            }
        }
    }
    public ArrayList<UFile> files;
    public UniDevFileData(String dirpath){
        files = new ArrayList<UFile>();
        File fileRoot = new File(dirpath);
        if(fileRoot.exists()){
            Log.i("Test","Ang?");
        }else {
            Log.i("Test","Ang!");
        }
        if(fileRoot.canRead()){
            Log.i("Test", "I can read");
        }
        if(fileRoot.isDirectory()){
            if(fileRoot.list() == null)
                Log.i("Test", "Fucking Fuck!");
            Log.i("Test", "list count: "+ fileRoot.list().length);
            String[] fileList = fileRoot.list().clone();
            for(String filename : fileList){
                files.add(new UFile(fileRoot.getPath()+"/"+filename));
            }
        }
    }
}
