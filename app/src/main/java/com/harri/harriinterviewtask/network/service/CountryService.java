package com.harri.harriinterviewtask.network.service;

import com.harri.harriinterviewtask.model.Country;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CountryService {

    @GET("rest/v1/all/")
    Call<List<Country>> getAllCountries();
}
