package com.example.dchya24.submission4.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dchya24.submission4.views.MovieCatalogueFragment;
import com.example.dchya24.submission4.views.TvShowCatalogueFragment;

public class DiscoverPageAdapter extends FragmentStatePagerAdapter{
    private int tabSize;

    public DiscoverPageAdapter(FragmentManager fm, int tabSize) {
        super(fm);
        this.tabSize = tabSize;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                MovieCatalogueFragment fragment = new MovieCatalogueFragment();
                return fragment;
            case 1:
                TvShowCatalogueFragment tvShowCatalogueFragment = new TvShowCatalogueFragment();
                return tvShowCatalogueFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabSize;
    }
}
