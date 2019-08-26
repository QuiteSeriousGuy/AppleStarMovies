package com.chefcat.applestaralbums.view.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.chefcat.applestaralbums.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2};
    private final Context mContext;
    private AlbumFragment albumMainFragment;
    private AlbumFragment albumViewedFragment;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
        albumMainFragment = new AlbumFragment();
        albumViewedFragment = new AlbumFragment();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return albumMainFragment;
            case 1:
                return albumViewedFragment;
            default:
                return new Fragment();
        }
    }

    public AlbumFragment getAlbumMainFragment() {
        return albumMainFragment;
    }

    public AlbumFragment getAlbumViewedFragment() {
        return albumViewedFragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        return 2;
    }
}