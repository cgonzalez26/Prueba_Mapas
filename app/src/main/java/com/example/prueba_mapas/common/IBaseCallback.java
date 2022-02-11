package com.example.prueba_mapas.common;

import com.androidnetworking.error.ANError;

import org.json.JSONException;

import java.util.List;

import okhttp3.Response;

public interface IBaseCallback<T> {
    void onResults(List<T> results);
    void onResult(T result);
    void onResult(String result);
    void onSuccess(String id);
    void onError(Exception e);
    void onError(Throwable throwable);
    void onError(ANError anError);
    void onError(JSONException JSONException);
    void onError(String error);
    void onUploadProgress(int progress);
    void onResponse(Response response);
}
