package com.example.dchya24.submission4.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.dchya24.submission4.api.MovieDetailApiResponse;
import com.example.dchya24.submission4.repository.MovieDetailApiRepository;

public class MovieDetailViewModel extends ViewModel {
    private MediatorLiveData<MovieDetailApiResponse> movieDetailApiResponseMediatorLiveData = new MediatorLiveData<>();
    private MovieDetailApiRepository movieDetailApiRepository = new MovieDetailApiRepository();

    public LiveData<MovieDetailApiResponse> getData(int id){
        movieDetailApiResponseMediatorLiveData
                .addSource(movieDetailApiRepository.getMovieDetail(id), new Observer<MovieDetailApiResponse>() {
            @Override
            public void onChanged(@Nullable MovieDetailApiResponse movieDetailApiResponse) {
                movieDetailApiResponseMediatorLiveData.setValue(movieDetailApiResponse);
            }
        });

        return movieDetailApiResponseMediatorLiveData;
    }
}
