package com.example.prueba_mapas.common;

import com.androidnetworking.error.ANError;

import org.json.JSONException;

import java.util.List;

import okhttp3.Response;

public class BaseCallback<T> implements IBaseCallback<T> {
    @Override
    public void onResults(List<T> results) {

    }

    @Override
    public void onResult(T result) {

    }

    @Override
    public void onResult(String result) {

    }

    @Override
    public void onSuccess(String id) {

    }

    @Override
    public void onError(Exception e) {

    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onError(ANError anError) {

    }

    @Override
    public void onError(JSONException JSONException) {

    }

    @Override
    public void onError(String error) {

    }

    @Override
    public void onUploadProgress(int progress) {

    }

    @Override
    public void onResponse(Response response) {

    }
}
