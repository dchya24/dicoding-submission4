package com.example.dchya24.submission4.views;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.adapter.DiscoverPageAdapter;
import com.example.dchya24.submission4.settings.SettingActivity;

public class MainActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabs);
        tabLayout.addTab(tabLayout.newTab().setText("Movie"));
        tabLayout.addTab(tabLayout.newTab().setText("Tv Show"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        viewPager = findViewById(R.id.viewpager);
        DiscoverPageAdapter discoverPageAdapter = new DiscoverPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(discoverPageAdapter);
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
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if(searchManager != null){
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.dumy_title));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    int position = tabLayout.getSelectedTabPosition();
                    if(position == 0){
                        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra(SearchActivity.SEARCH_STATUS, 1);
                        intent.putExtra(SearchActivity.SEARCH_QUERY, query);
                        startActivity(intent);

                    }
                    else if(position == 1){
                        Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                        intent.putExtra(SearchActivity.SEARCH_STATUS, 2);
                        intent.putExtra(SearchActivity.SEARCH_QUERY, query);
                        startActivity(intent);
                    }
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        if(menuItem.getItemId() == R.id.menu_locale){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }else if(menuItem.getItemId() == R.id.menu_reminder){
            Intent intent = new Intent(this, SettingActivity.class);
            startActivity(intent);
        }else if(menuItem.getItemId() == R.id.item_fav){
            Intent intent = new Intent(this, FavoriteActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(menuItem);
    }

}
