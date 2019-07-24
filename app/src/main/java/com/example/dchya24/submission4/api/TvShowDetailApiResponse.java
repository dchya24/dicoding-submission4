package com.example.dchya24.submission4.api;

import com.example.dchya24.submission4.model.TvShow;

public class TvShowDetailApiResponse {
    private TvShow tvShow;
    private Throwable throwable;

    public TvShowDetailApiResponse(TvShow tvShow) {
        this.tvShow = tvShow;
        this.throwable = null;
    }

    public TvShowDetailApiResponse(Throwable throwable) {
        this.tvShow = null;
        this.throwable = throwable;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
