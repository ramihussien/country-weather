package com.harri.harriinterviewtask.network.config;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManager {

    private static final String DEFAULT_BASE_URL = "https://restcountries.eu/";

    private static RetrofitManager INSTANCE = null;

    private OkHttpClient mOkHttpClient = buildClient();

    private Retrofit mRetrofit = buildRetrofit(mOkHttpClient, DEFAULT_BASE_URL);

    private RetrofitManager() {

    }

    public static synchronized RetrofitManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RetrofitManager();
        }
        return INSTANCE;
    }


    private OkHttpClient buildClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor(provideHeaderInterceptor());

        return builder.build();
    }

    public void changeBaseUrl(String newBaseUrl) {
        mRetrofit = buildRetrofit(mOkHttpClient, newBaseUrl);
    }

    private Interceptor provideHeaderInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder builder = request.newBuilder()
                        .addHeader("Accept", "application/json")
                        .addHeader("Connection", "close");

                request = builder.build();

                return chain.proceed(request);
            }
        };
    }

    private Retrofit buildRetrofit(OkHttpClient client, String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    public Retrofit getRetrofit() {
        return mRetrofit;
    }

    public <T> T createService(Class<T> service) {
        return mRetrofit.create(service);
    }
}
