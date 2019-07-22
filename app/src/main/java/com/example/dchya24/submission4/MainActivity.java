package com.example.dchya24.submission4;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.dchya24.submission4.fragment.MovieCatalogueFragment;
import com.example.dchya24.submission4.fragment.MovieFavoritFragment;
import com.example.dchya24.submission4.fragment.TvShowCatalogueFragment;
import com.example.dchya24.submission4.fragment.TvShowFavoritFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()) {
                case R.id.navigation_movie:
                    fragment = new MovieCatalogueFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_tv_show:
                    fragment = new TvShowCatalogueFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_movie_favorit:
                    fragment = new MovieFavoritFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.navigation_tv_show_favorit:
                    fragment = new TvShowFavoritFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(savedInstanceState == null){
            Fragment fragment = new MovieCatalogueFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(intent);

        return true;
    }

}
