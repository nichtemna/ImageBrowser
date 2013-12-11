package com.nichtemna.FileManager.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
public class ViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mFiles;
    private LayoutInflater mLayoutInflater;

    public ViewPagerAdapter(Context pContext, List<String> pFiles) {
        mContext = pContext;
        mFiles = pFiles;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mFiles.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view.equals(o);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView view = (ImageView) mLayoutInflater.inflate(R.layout.item_photo, container, false);
        view.setImageBitmap(ImageUtils.getBitmapFromPath(mFiles.get(position)));
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
