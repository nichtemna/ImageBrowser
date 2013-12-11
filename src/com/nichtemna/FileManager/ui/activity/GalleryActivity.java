package com.nichtemna.FileManager.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import com.nichtemna.FileManager.R;
import com.nichtemna.FileManager.model.Folder;
import com.nichtemna.FileManager.ui.adapter.ViewPagerAdapter;

/**
 * Created by Shishova Galina
 * nichtemna@gmail.com
 */
public class GalleryActivity extends Activity {
    public static final String FOLDER = "folder";
    public static final String POSITION = "position";

    private ViewPager mViewPager;
    private ViewPagerAdapter mPagerAdapter;
    private Folder mFolder;

    public static void start(Activity activity, Folder pFolder, int position) {
        Intent intent = new Intent(activity, GalleryActivity.class);
        intent.putExtra(FOLDER, pFolder);
        intent.putExtra(POSITION, position);
        activity.startActivity(intent);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        mViewPager = (ViewPager) findViewById(R.id.activity_gallery_viewPager);
        if (getIntent().hasExtra(FOLDER)) {
            mFolder = getIntent().getParcelableExtra(FOLDER);
            int position = getIntent().getIntExtra(POSITION, 0);
            if (mFolder.getFiles().size() > 0) {
                mPagerAdapter = new ViewPagerAdapter(this, mFolder.getFiles());
                mViewPager.setAdapter(mPagerAdapter);
                mViewPager.setCurrentItem(position);
            }
        }
    }
}