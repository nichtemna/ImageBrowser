package com.nichtemna.FileManager.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.nichtemna.FileManager.R;
import com.nichtemna.FileManager.model.Folder;
import com.nichtemna.FileManager.utils.ImageUtils;

import java.io.File;
import java.util.List;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class FoldersAdapter extends ArrayAdapter<Folder> {
    private List<Folder> mFolders;
    private Context mContext;
    private LayoutInflater mInflater;

    public FoldersAdapter(Context pContext, List<Folder> pFolders) {
        super(pContext, R.layout.cell_folders, pFolders);
        mContext = pContext;
        mFolders = pFolders;
        mInflater = (LayoutInflater) pContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder = null;
        if (view == null) {
            view = mInflater.inflate(R.layout.cell_folders, viewGroup, false);
            holder = new Holder();
            holder.imageView = (ImageView) view.findViewById(R.id.row_folder_imageview);
            holder.textView = (TextView) view.findViewById(R.id.row_folder_textview);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        String folderName = mFolders.get(i).getPath().substring(mFolders.get(i).getPath().lastIndexOf("/") + 1);
        holder.textView.setText(folderName);
        holder.imageView.setImageBitmap(ImageUtils.getBitmapFromPath(mFolders.get(i).getFiles().get(0)));
        return view;
    }

    private class Holder {
        ImageView imageView;
        TextView textView;
    }
}
