package com.example.dchya24.submission4.viewmodels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.Nullable;

import com.example.dchya24.submission4.api.TvShowDetailApiResponse;
import com.example.dchya24.submission4.repository.TvShowDetailApiRepository;

public class TvShowDetailViewModel extends ViewModel {
    private MediatorLiveData<TvShowDetailApiResponse> mediatorLiveData = new MediatorLiveData<>();
    private TvShowDetailApiRepository tvShowDetailApiRepository = new TvShowDetailApiRepository();

    public LiveData<TvShowDetailApiResponse> getData(int id){
        mediatorLiveData.addSource(tvShowDetailApiRepository.getTvShowDetail(id), new Observer<TvShowDetailApiResponse>() {
            @Override
            public void onChanged(@Nullable TvShowDetailApiResponse tvShowDetailApiResponse) {
                mediatorLiveData.setValue(tvShowDetailApiResponse);
            }
        });

        return mediatorLiveData;
    }
}
