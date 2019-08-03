package com.example.dchya24.submission4.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.dchya24.submission4.views.MovieFavoriteFragment;
import com.example.dchya24.submission4.views.TvShowFavoritFragment;

public class FavoritePageAdapter extends FragmentStatePagerAdapter {
    private int tabSize;

    public FavoritePageAdapter(FragmentManager fm, int tabSize) {
        super(fm);
        this.tabSize = tabSize;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                MovieFavoriteFragment movieFavoriteFragment = new MovieFavoriteFragment();
                return movieFavoriteFragment;
            case 1:
                TvShowFavoritFragment tvShowFavoritFragment= new TvShowFavoritFragment();
                return tvShowFavoritFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabSize;
    }
}
