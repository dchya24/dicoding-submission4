package com.example.dchya24.submission4.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

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

}
