package com.example.testapp.staticModules;

import com.google.gson.Gson;

public final class GsonWorker {
    private static GsonWorker instance;
    private Gson gson;

    private GsonWorker() {
        this.gson = new Gson();
    }

    public static GsonWorker getInstance() {
        if (instance == null) {
            instance = new GsonWorker();
        }
        return instance;
    }


    public Gson getGson() {
        return this.gson;
    }
    public <T> Object fromGson(String value, Class<T> objClass) {
        return gson.fromJson(value, objClass);
    }
}

