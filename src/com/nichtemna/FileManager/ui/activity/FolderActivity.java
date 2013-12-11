package com.nichtemna.FileManager.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.nichtemna.FileManager.R;
import com.nichtemna.FileManager.model.Folder;
import com.nichtemna.FileManager.ui.adapter.ImagesAdapter;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class FolderActivity extends Activity implements AdapterView.OnItemClickListener {
    public static final String FOLDER = "folder";
    private GridView mGridView;
    private ImagesAdapter mImagesAdapter;
    private Folder mFolder;

    public static void start(Activity activity, Folder folder) {
        Intent intent = new Intent(activity, FolderActivity.class);
        intent.putExtra(FOLDER, folder);
        activity.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder);
        mGridView = (GridView) findViewById(R.id.activity_folder_gridview);
        if (getIntent().hasExtra(FOLDER)) {
            mFolder = getIntent().getParcelableExtra(FOLDER);
            if (mFolder != null) {
                mImagesAdapter = new ImagesAdapter(this, mFolder.getFiles());
                mGridView.setAdapter(mImagesAdapter);
            }
        }
        mGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        GalleryActivity.start(FolderActivity.this, mFolder, i);
    }
}