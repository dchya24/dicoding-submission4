package com.example.dchya24.submission4.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.dchya24.submission4.api.JsonApiResponse;
import com.example.dchya24.submission4.repository.TvShowDiscoverApiRepository;

public class TvShowDiscoverViewModel extends ViewModel {
    private MediatorLiveData<JsonApiResponse> apiResponseMediatorLiveData = new MediatorLiveData<>();
    private TvShowDiscoverApiRepository tvShowDiscoverApiRepository = new TvShowDiscoverApiRepository();

    public LiveData<JsonApiResponse> getData(){
        apiResponseMediatorLiveData.addSource(tvShowDiscoverApiRepository.getDiscoverTvShow(), new Observer<JsonApiResponse>() {
            @Override
            public void onChanged(@Nullable JsonApiResponse jsonApiResponse) {
                apiResponseMediatorLiveData.setValue(jsonApiResponse);
            }
        });

        return apiResponseMediatorLiveData;
    }

}
