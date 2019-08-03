package com.example.dchya24.submission4.api;

import okhttp3.ResponseBody;

public class JsonApiResponse {
    private ResponseBody responseBody;
    private Throwable throwable;

    public JsonApiResponse(ResponseBody responseBody) {
        this.responseBody = responseBody;
        this.throwable = null;
    }

    public JsonApiResponse(Throwable throwable) {
        this.responseBody = null;
        this.throwable = throwable;
    }


    public JsonApiResponse() {
    }

    public ResponseBody getResponseBody(){
        return responseBody;
    }

    public Throwable getThrowable(){
        return throwable;
    }

    public void setResponseBody(ResponseBody responseBody) {
        this.responseBody = responseBody;
    }

    public void setThrowable(Throwable throwable) {
        this.throwable = throwable;
    }
}
