package com.nichtemna.FileManager.ui.activity;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import com.nichtemna.FileManager.R;
import com.nichtemna.FileManager.loader.FolderLoader;
import com.nichtemna.FileManager.model.Folder;
import com.nichtemna.FileManager.ui.adapter.FoldersAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements AdapterView.OnItemClickListener {
    public static final String EXTERNAL_BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();

    private FoldersAdapter mPreviewAdapter;
    private GridView mGridView;
    private ProgressBar mProgressBar;
    private List<Folder> mFolders = new ArrayList<Folder>();
    private LoaderManager.LoaderCallbacks<List<Folder>> mLoaderCallbacks = new LoaderManager.LoaderCallbacks<List<Folder>>() {
        @Override
        public Loader<List<Folder>> onCreateLoader(int i, Bundle bundle) {
            return new FolderLoader(MainActivity.this, EXTERNAL_BASE_PATH);
        }

        @Override
        public void onLoadFinished(Loader<List<Folder>> listLoader, List<Folder> folders) {
            mFolders = folders;
            mPreviewAdapter.clear();
            for (Folder folder : folders) {
                mPreviewAdapter.add(folder);
            }
            mPreviewAdapter.notifyDataSetChanged();
            mProgressBar.setVisibility(View.GONE);
        }

        @Override
        public void onLoaderReset(Loader<List<Folder>> listLoader) {

        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        getSupportLoaderManager().initLoader(0, null, mLoaderCallbacks).forceLoad();
    }

    private void initViews() {
        mGridView = (GridView) findViewById(R.id.activity_main_gridview);
        mProgressBar = (ProgressBar) findViewById(R.id.activity_main_progressBar);
        mPreviewAdapter = new FoldersAdapter(this, mFolders);
        mGridView.setAdapter(mPreviewAdapter);
        mGridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        FolderActivity.start(MainActivity.this, mFolders.get(i));
    }
}
