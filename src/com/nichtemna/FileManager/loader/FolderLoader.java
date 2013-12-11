package com.nichtemna.FileManager.loader;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import com.nichtemna.FileManager.model.Folder;
import com.nichtemna.FileManager.utils.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class FolderLoader extends AsyncTaskLoader<List<Folder>> {
    private String mPath;
    private Context mContext;

    public FolderLoader(Context context, String mPath) {
        super(context);
        this.mPath = mPath;
        this.mContext = context;
    }

    @Override
    public List<Folder> loadInBackground() {
        return FileUtils.getFoldersWithFiles(mPath);
    }
}
