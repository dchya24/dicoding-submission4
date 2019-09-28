package com.example.dchya24.submission4.views;

import android.app.SearchManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.adapter.MovieListAdapter;
import com.example.dchya24.submission4.adapter.TvShowListAdapter;
import com.example.dchya24.submission4.api.JsonApiResponse;
import com.example.dchya24.submission4.model.DiscoverMovie;
import com.example.dchya24.submission4.model.DiscoverTvShow;
import com.example.dchya24.submission4.settings.SettingActivity;
import com.example.dchya24.submission4.support.SearchSupport;
import com.example.dchya24.submission4.viewmodels.MovieDiscoverViewModel;
import com.example.dchya24.submission4.viewmodels.TvShowDiscoverViewModel;

import org.json.JSONArray;
import org.json.JSONObject;

public class SearchActivity extends AppCompatActivity {
    public static final String SEARCH_STATUS = "search_status";
    public static final String SEARCH_QUERY = "search query";
    private int STATUS;
    private SearchSupport searchSupport;
    private RecyclerView rvSearch;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        progressBar = findViewById(R.id.pb_search);
        rvSearch = findViewById(R.id.rv_search);
        rvSearch.setLayoutManager(new LinearLayoutManager(this));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        searchSupport = new SearchSupport();

        showPogressBar(true);

        /*
         * 1 = Movie
         * 2 = Tv Show
         */
        STATUS = getIntent().getIntExtra(SEARCH_STATUS, 1);
        String query = getIntent().getStringExtra(SEARCH_QUERY);

        search(query);

    }

    private void search(String value){
        if(STATUS == 1){
            MovieDiscoverViewModel movieDiscoverViewModel = ViewModelProviders.of(this).get(MovieDiscoverViewModel.class);
            movieDiscoverViewModel.searchData(value).observe(this, getListData);

        }else if(STATUS == 2){
            TvShowDiscoverViewModel tvShowDiscoverViewModel = ViewModelProviders.of(this).get(TvShowDiscoverViewModel.class);
            tvShowDiscoverViewModel.searchData(value).observe(this, getListData);
        }
    }

    private Observer<JsonApiResponse> getListData = new Observer<JsonApiResponse>() {
        @Override
        public void onChanged(JsonApiResponse jsonApiResponse) {
            if(jsonApiResponse == null){
                Toast.makeText(getApplicationContext(), "Something Error", Toast.LENGTH_LONG).show();
            }

            if(jsonApiResponse.getThrowable() == null){
                try {
                    String data = jsonApiResponse.getResponseBody().string();
                    JSONObject jsonObject = new JSONObject(data);
                    JSONArray results = jsonObject.getJSONArray("results");
                    searchSupport.clearData();

                    for(int i = 0; i < results.length(); i++){
                        if(STATUS == 1){
                            DiscoverMovie discoverMovie = new DiscoverMovie(results.getJSONObject(i));
                            searchSupport.addDiscoverMovie(discoverMovie);
                        }
                        else if(STATUS == 2){
                            DiscoverTvShow discoverTvShow = new DiscoverTvShow(results.getJSONObject(i));
                            searchSupport.addDiscoverTvShow(discoverTvShow);
                        }
                    }

                    setAdapter();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }else{
                String activityName = getApplication().getClass().getSimpleName();
                String  errMessage =  jsonApiResponse.getThrowable().getMessage();
                Toast.makeText(getApplicationContext(), errMessage, Toast.LENGTH_LONG).show();
                showPogressBar(false);
                Log.e(activityName, errMessage);
            }
        }
    };

    private void setAdapter(){
        if(STATUS == 1){
            MovieListAdapter movieListAdapter = new MovieListAdapter(this);
            movieListAdapter.setDiscoverMovies(searchSupport.getDiscoverMovies());
            rvSearch.setAdapter(movieListAdapter);
        }
        else if(STATUS == 2){
            TvShowListAdapter tvShowListAdapter = new TvShowListAdapter(this);
            tvShowListAdapter.setdIscoverTvShowArrayList(searchSupport.getDiscoverTvShows());
            rvSearch.setAdapter(tvShowListAdapter);
        }
    }

    private void showPogressBar(boolean b){
        if(b){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        if(searchManager != null){
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setQueryHint(SEARCH_QUERY);
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String text) {
                    search(text);
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
        }else{
            finish();
        }

        return super.onOptionsItemSelected(menuItem);
    }
}
