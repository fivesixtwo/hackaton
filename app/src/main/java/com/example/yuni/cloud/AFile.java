package com.example.yuni.cloud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class AFile extends BaseAdapter {
    UniDevFileData uniDevFileData;
    Context context;
    public AFile(){

        uniDevFileData = new UniDevFileData("", context);
    }

    public AFile(String path, Context context){

        uniDevFileData = new UniDevFileData(path, context);
        this.context = context;
    }

    public AFile(UniDevFileData uniDevFileData, Context context){
        this.uniDevFileData = uniDevFileData;
        this.context = context;

    }

    @Override
    public int getCount() {
        return uniDevFileData.files.size();
    }

    @Override
    public Object getItem(int i) {
        return uniDevFileData.files.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();

        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listiem, viewGroup, false);
        }
        TextView text = (TextView) view.findViewById(R.id.list_Text);
        text.setText(uniDevFileData.files.get(i).name);
        ImageView imageView = (ImageView) view.findViewById(R.id.list_Img);
        if(uniDevFileData.files.get(i).thumbNail != null){
            imageView.setImageBitmap(uniDevFileData.files.get(i).thumbNail);
        }else{
            imageView.setImageResource(R.mipmap.docu);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }

        });
        return view;
    }
}
