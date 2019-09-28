package com.example.dchya24.submission4.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.dchya24.submission4.api.JsonApiResponse;
import com.example.dchya24.submission4.repository.MovieDiscoverApiRepository;

public class MovieDiscoverViewModel extends ViewModel {
    private MediatorLiveData<JsonApiResponse> mediatorLiveData = new MediatorLiveData<>();
    private MovieDiscoverApiRepository movieDiscoverApiRepository = new MovieDiscoverApiRepository();

    public LiveData<JsonApiResponse> getData(){
        mediatorLiveData.addSource(movieDiscoverApiRepository.getMovieDiscover(), new Observer<JsonApiResponse>() {

            @Override
            public void onChanged(@Nullable JsonApiResponse jsonApiResponse) {
                mediatorLiveData.setValue(jsonApiResponse);
            }
        });

        return mediatorLiveData;
    }

    public  LiveData<JsonApiResponse> searchData(String query){
        Log.d("Query", query);
        mediatorLiveData.addSource(movieDiscoverApiRepository.searchMovieDiscover(query), new Observer<JsonApiResponse>() {
            @Override
            public void onChanged(@Nullable JsonApiResponse jsonApiResponse) {
                mediatorLiveData.setValue(jsonApiResponse);
            }
        });

        return mediatorLiveData;
    }
}
