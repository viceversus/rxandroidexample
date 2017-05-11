package com.viceversus.rxandroidexample;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ken on 5/9/17.
 */

public interface StudioGhibliService {
    @GET("films?")
    Observable<List<Film>> getFilmData(@Query("limit") String limit);
}
