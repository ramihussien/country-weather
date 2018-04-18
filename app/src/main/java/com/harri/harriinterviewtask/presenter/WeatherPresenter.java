package com.harri.harriinterviewtask.presenter;

import com.harri.harriinterviewtask.contract.WeatherContract;
import com.harri.harriinterviewtask.model.Weather;
import com.harri.harriinterviewtask.network.config.RetrofitManager;
import com.harri.harriinterviewtask.network.service.WeatherService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherPresenter implements WeatherContract.Presenter {

    private static final String WEATHER_BASE_URL = "http://api.openweathermap.org/";

    private WeatherContract.View mWeatherView;

    private RetrofitManager mRetrofitManager;

    private WeatherService mWeatherService;

    private Call<Weather> mWeatherCall;

    public WeatherPresenter(WeatherContract.View view) {
        mWeatherView = view;

        mWeatherView.setPresenter(this);

        mRetrofitManager = RetrofitManager.getInstance();

        mRetrofitManager.changeBaseUrl(WEATHER_BASE_URL);

        mWeatherService = mRetrofitManager.createService(WeatherService.class);
    }

    @Override
    public void getWeatherDetails(double lat, double lon, String appId) {
        mWeatherCall = mWeatherService.getWeatherDetails(lat, lon, appId);

        mWeatherCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if(response.isSuccessful()) {
                    mWeatherView.setWeatherData(response.body());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {

            }
        });
    }

    @Override
    public void start() {

    }
}
