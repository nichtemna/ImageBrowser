package com.nichtemna.FileManager.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import com.nichtemna.FileManager.R;
import com.nichtemna.FileManager.utils.ImageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class ImagesAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private List<String> mFiles;
    private LayoutInflater mLayoutInflater;

    public ImagesAdapter(Context mContext, List<String> mFiles) {
        super(mContext, R.layout.cell_image, mFiles);
        this.mContext = mContext;
        this.mFiles = mFiles;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Holder holder = null;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.cell_image, parent, false);
            holder = new Holder();
            holder.imageView = (ImageView) view.findViewById(R.id.cell_image_imageView);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        holder.imageView.setImageBitmap(ImageUtils.getBitmapFromPath(mFiles.get(position)));
        return view;
    }


    private class Holder {
        private ImageView imageView;
    }
}
