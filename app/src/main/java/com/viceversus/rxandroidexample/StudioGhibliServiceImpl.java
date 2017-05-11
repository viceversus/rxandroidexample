package com.viceversus.rxandroidexample;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ken on 5/9/17.
 */

public class StudioGhibliServiceImpl {
    private static StudioGhibliServiceImpl instance;
    private StudioGhibliService service;

    private StudioGhibliServiceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://ghibliapi.herokuapp.com")
            .build();

        service = retrofit.create(StudioGhibliService.class);
    }

    public static StudioGhibliService getInstance() {
        if (instance == null) {
            instance = new StudioGhibliServiceImpl();
        }

        return instance.service;
    }
}
