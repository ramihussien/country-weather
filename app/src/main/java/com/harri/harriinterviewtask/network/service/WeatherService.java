package com.harri.harriinterviewtask.network.service;

import com.harri.harriinterviewtask.model.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WeatherService {

    @GET("data/2.5/forecast")
    Call<Weather> getWeatherDetails(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String appId);
}
