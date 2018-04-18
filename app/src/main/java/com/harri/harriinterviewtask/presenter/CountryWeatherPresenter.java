package com.harri.harriinterviewtask.presenter;

import android.widget.Button;

import com.harri.harriinterviewtask.contract.CountryWeatherContract;
import com.harri.harriinterviewtask.model.Country;
import com.harri.harriinterviewtask.model.Weather;
import com.harri.harriinterviewtask.network.config.RetrofitManager;
import com.harri.harriinterviewtask.network.service.CountryService;
import com.harri.harriinterviewtask.network.service.WeatherService;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryWeatherPresenter implements CountryWeatherContract.Presenter {

    private static final String COUNTRY_BASE_URL = "https://restcountries.eu/";

    private CountryWeatherContract.View mCountryWeatherView;

    private RetrofitManager mRetrofitManager;

    private CountryService mCountryService;

    private Call<List<Country>> mCountryListCall;

    public CountryWeatherPresenter(CountryWeatherContract.View view) {
        mCountryWeatherView = view;

        mCountryWeatherView.setPresenter(this);

        mRetrofitManager = RetrofitManager.getInstance();

        mRetrofitManager.changeBaseUrl(COUNTRY_BASE_URL);

        mCountryService = mRetrofitManager.createService(CountryService.class);
    }

    @Override
    public void getAllCountries() {
        mCountryListCall = mCountryService.getAllCountries();

        mCountryListCall.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if(response.isSuccessful()) {
                    mCountryWeatherView.initCountryRecyclerView(response.body());
                }

            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }
        });
    }

    @Override
    public void start() {

    }
}
