package com.example.ima.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.ima.activity.R;
import com.example.ima.activity.ShowImageActivity;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Ahyan on 7/23/2019.
 */

public class GalleryAdapterString extends BaseAdapter {

    private Context ctx;
    private int pos;
    private LayoutInflater inflater;
    private ImageView ivGallery;
    ArrayList<String> mArrayUri;
    private int id ;


    public GalleryAdapterString(Context ctx, ArrayList<String> mArrayUri,int id) {

        this.ctx = ctx;
        this.mArrayUri = mArrayUri;
        this.id=id;
    }



    @Override
    public int getCount() {
        return mArrayUri.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayUri.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        pos = position;
        inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View itemView = inflater.inflate(R.layout.img_item, parent, false);

        ivGallery = (ImageView) itemView.findViewById(R.id.ivGallery);

        ivGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ctx, ShowImageActivity.class);
                i.putExtra("path", mArrayUri.get(position));
                i.putExtra("id",id);
                i.putExtra("header", "Show Image");
                ctx.startActivity(i);

            }
        });

        File f = new File(mArrayUri.get(position));

        Bitmap b = BitmapFactory.decodeFile(f.getAbsolutePath());
        ivGallery.setImageBitmap(b);

        return itemView;
    }
}