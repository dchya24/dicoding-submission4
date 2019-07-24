package com.example.dchya24.submission4.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dchya24.submission4.R;
import com.example.dchya24.submission4.adapter.CreatorAdapter;
import com.example.dchya24.submission4.adapter.GenreAdapter;
import com.example.dchya24.submission4.adapter.SeasonAdapter;
import com.example.dchya24.submission4.api.TvShowDetailApiResponse;
import com.example.dchya24.submission4.model.TvShow;
import com.example.dchya24.submission4.viewmodels.TvShowDetailViewModel;

public class TvShowDetailActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "extra_data";
    private ImageView imgPoster, imgBackdrop;
    private TextView tvTitle, tvRelease, tvRate, tvOverview, textUserScore;
    private TextView textTags, textOverview, textCreator, textSeasonList, textRelease;
    private ProgressBar progressBar;
    private GenreAdapter genreAdapter;
    private CreatorAdapter creatorAdapter;
    private SeasonAdapter seasonAdapter;
    private TvShow tvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        tvTitle = findViewById(R.id.tv_title);
        tvRelease = findViewById(R.id.tv_release);
        imgBackdrop = findViewById(R.id.img_back);
        imgPoster = findViewById(R.id.img_poster);
        progressBar = findViewById(R.id.progressBar);
        tvRate = findViewById(R.id.tv_user_score);
        tvOverview = findViewById(R.id.tv_overview);
        textTags = findViewById(R.id.text_view_tag);
        textUserScore = findViewById(R.id.text_user_score);
        textOverview = findViewById(R.id.text_overview);
        textCreator = findViewById(R.id.text_creator);
        textSeasonList = findViewById(R.id.text_season);
        textRelease = findViewById(R.id.text_release_date);

        RecyclerView rvGenre = findViewById(R.id.rv_genre);
        RecyclerView rvCreator = findViewById(R.id.rv_creator);
        RecyclerView rvSeason = findViewById(R.id.rv_season);

        setViewVisible(false);
        showLoading(true);

        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rvGenre.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL));
        rvCreator.setLayoutManager(new GridLayoutManager(this, 3));
        rvSeason.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        genreAdapter = new GenreAdapter();
        seasonAdapter = new SeasonAdapter(this);
        creatorAdapter = new CreatorAdapter(this);

        rvCreator.setAdapter(creatorAdapter);
        rvGenre.setAdapter(genreAdapter);
        rvSeason.setAdapter(seasonAdapter);

        int i = getIntent().getIntExtra(EXTRA_DATA, 0);
        TvShowDetailViewModel tvShowDetailViewModel = ViewModelProviders.of(this).get(TvShowDetailViewModel.class);
        tvShowDetailViewModel.getData(i).observe(this, getTvShowDetail);
    }

    private Observer<TvShowDetailApiResponse> getTvShowDetail = new Observer<TvShowDetailApiResponse>() {
        @Override
        public void onChanged(@Nullable TvShowDetailApiResponse tvShowDetailApiResponse) {
            if(tvShowDetailApiResponse == null){
                Toast.makeText(getApplicationContext(), "Something Error!", Toast.LENGTH_LONG).show();
            }

            if(tvShowDetailApiResponse.getThrowable() == null){
                tvShow = tvShowDetailApiResponse.getTvShow();
                setItemView();
                setViewVisible(true);
                showLoading(false);

            }else{
                Toast.makeText(getApplicationContext(), tvShowDetailApiResponse.getThrowable().getMessage(), Toast.LENGTH_LONG).show();
                showLoading(false);
            }
        }
    };

    public void showLoading(boolean loading){
        if(loading){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    public void setViewVisible(boolean visible){
        if(visible){
            imgPoster.setVisibility(View.VISIBLE);
            textSeasonList.setVisibility(View.VISIBLE);
            textCreator.setVisibility(View.VISIBLE);
            textOverview.setVisibility(View.VISIBLE);
            textUserScore.setVisibility(View.VISIBLE);
            textTags.setVisibility(View.VISIBLE);
            textRelease.setVisibility(View.VISIBLE);
        }else{

            imgPoster.setVisibility(View.GONE);
            textSeasonList.setVisibility(View.GONE);
            textCreator.setVisibility(View.GONE);
            textOverview.setVisibility(View.GONE);
            textUserScore.setVisibility(View.GONE);
            textTags.setVisibility(View.GONE);
            textRelease.setVisibility(View.GONE);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        finish();

        return super.onOptionsItemSelected(menuItem);
    }

    private void setItemView(){
        if(getSupportActionBar() != null) getSupportActionBar().setTitle(tvShow.getName());

        tvRelease.setText(tvShow.getFirstAirDate());
        tvTitle.setText(tvShow.getName());
        tvOverview.setText(tvShow.getOverview());
        tvRate.setText(tvShow.getVoteAverage());

        Glide.with(getApplicationContext()).load(tvShow.getPosterPath()).into(imgPoster);
        Glide.with(getApplicationContext()).load(tvShow.getBackdropPath()).into(imgBackdrop);

        genreAdapter.setGenreArrayList(tvShow.getGenres());
        seasonAdapter.setSeasonArrayList(tvShow.getSeasons());
        creatorAdapter.setCreatedByArrayList(tvShow.getCreatedBy());

    }
}
