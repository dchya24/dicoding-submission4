package com.example.dchya24.submission4.views;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.adapter.FavoritePageAdapter;
import com.example.dchya24.submission4.settings.SettingActivity;

public class FavoriteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TabLayout tabLayout = findViewById(R.id.favorite_tabs);
        final ViewPager viewPager = findViewById(R.id.viewpager);

        FavoritePageAdapter favoritePageAdapter = new FavoritePageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(favoritePageAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        Intent intent;
        switch (menuItem.getItemId()){
            case R.id.menu_locale:
                intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
                break;
            case R.id.menu_reminder:
                intent = new Intent(FavoriteActivity.this, SettingActivity.class);
                startActivity(intent);
                break;
            default:
                finish();
                break;
        }


        return super.onOptionsItemSelected(menuItem);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.favorite, menu);


        return true;
    }
}
