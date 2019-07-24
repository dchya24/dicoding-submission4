package com.example.dchya24.submission4.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
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
import com.example.dchya24.submission4.adapter.GenreAdapter;
import com.example.dchya24.submission4.api.MovieDetailApiResponse;
import com.example.dchya24.submission4.model.Movie;
import com.example.dchya24.submission4.viewmodels.MovieDetailViewModel;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String EXTRA_DATA = "extra_data";
    private ImageView imgPoster, imgBakdrop;
    private TextView tvTitle, tvRelease, tvRuntime, tvRate, tvOverview, textUserScore, textTags, textOverview, textRelease, tvFavorit;
    private ProgressBar progressBar;
    private Movie movie;
    private RecyclerView rvGenre;
    private GenreAdapter genreAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        progressBar = findViewById(R.id.progressBar);
        imgPoster = findViewById(R.id.img_poster);
        imgBakdrop = findViewById(R.id.img_back);
        tvTitle = findViewById(R.id.tv_title);
        tvRelease = findViewById(R.id.tv_release);
        tvRuntime = findViewById(R.id.tv_runtime);
        rvGenre = findViewById(R.id.rv_genre);
        tvOverview = findViewById(R.id.tv_overview);
        tvRate = findViewById(R.id.tv_user_score);
        textUserScore = findViewById(R.id.text_user_score);
        textTags = findViewById(R.id.text_view_tag);
        textOverview = findViewById(R.id.text_overview);
        textRelease = findViewById(R.id.text_release_date);
        tvFavorit = findViewById(R.id.tv_favorite);

        setViewVisible(false);
        showFavoriteView(false);
        genreAdapter = new GenreAdapter();
        rvGenre.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.HORIZONTAL));

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get data from intent
        int filmId = getIntent().getIntExtra(EXTRA_DATA, 0);
        MovieDetailViewModel movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        movieDetailViewModel.getData(filmId).observe(this, getMovieDetail);
    }

    private Observer<MovieDetailApiResponse> getMovieDetail = new Observer<MovieDetailApiResponse>() {
        @Override
        public void onChanged(@Nullable MovieDetailApiResponse data) {
            if (data == null) {
                Toast.makeText(getApplicationContext(), data.getThrowable().getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }

            if(data.getThrowable() == null){
                movie = data.getMovie();
                setView();
                setViewVisible(true);
            }else{
                Toast.makeText(getApplicationContext(), data.getThrowable().getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        }
    };

    private void setViewVisible(boolean b){
        if(b){
            textOverview.setVisibility(View.VISIBLE);
            imgPoster.setVisibility(View.VISIBLE);
            textUserScore.setVisibility(View.VISIBLE);
            textTags.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
            textRelease.setVisibility(View.VISIBLE);
        }else{
            textOverview.setVisibility(View.GONE);
            imgPoster.setVisibility(View.GONE);
            textUserScore.setVisibility(View.GONE);
            textTags.setVisibility(View.GONE);
            textRelease.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void setView(){
        if(getSupportActionBar() != null) getSupportActionBar().setTitle(movie.getTitle());


        tvRelease.setText(movie.getReleaseDate());
        tvRuntime.setText(String.format("%s %s", movie.getRuntime(), getResources().getString(R.string.text_runtime)));
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvRate.setText(movie.getVoteAverage());

        Glide.with(this).load(movie.getBackdropPath()).into(imgBakdrop);
        Glide.with(this).load(movie.getPosterPath()).into(imgPoster);
        genreAdapter.setGenreArrayList(movie.getGenres());
        rvGenre.setAdapter(genreAdapter);
        showFavoriteView(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        finish();

        return super.onOptionsItemSelected(menuItem);
    }

    private void showFavoriteView(boolean b){
        if(b){
            tvFavorit.setVisibility(View.VISIBLE);
        }else{
            tvFavorit.setVisibility(View.GONE);
        }
    }
}
