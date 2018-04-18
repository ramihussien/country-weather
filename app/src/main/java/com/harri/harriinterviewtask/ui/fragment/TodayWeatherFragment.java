package com.harri.harriinterviewtask.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.harri.harriinterviewtask.R;
import com.harri.harriinterviewtask.model.Weather;
import com.harri.harriinterviewtask.model.WeatherList;

import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayWeatherFragment extends Fragment {


    private static final String TAG = "TodayWeatherFragment";

    private TextView mDate;

    private TextView mWeatherTemp;

    private TextView mWeatherPressure;

    private TextView mWeatherHumidity;

    public TodayWeatherFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, getResources().getString(R.string.fragment_created));

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today_weather, container, false);

        mDate = view.findViewById(R.id.today_weather_date);
        mWeatherTemp = view.findViewById(R.id.today_weather_temp);
        mWeatherPressure = view.findViewById(R.id.today_weather_pressure);
        mWeatherHumidity = view.findViewById(R.id.today_weather_humidity);



        return view;
    }

    public void update(Weather weather) {
        WeatherList weatherList = weather.getList().get(0);

        if(weatherList == null) return;

        mDate.setText(weatherList.getDtTxt());

        double tempMin = weatherList.getMain().getTempMin();

        double tempMax = weatherList.getMain().getTempMax();

        mWeatherTemp.setText(String.format(Locale.US, "Temp min: %3.2f - max: %3.2f", tempMin, tempMax));

        mWeatherPressure.setText(String.valueOf(weatherList.getMain().getPressure()));

        mWeatherHumidity.setText(String.valueOf(weatherList.getMain().getHumidity()));
    }
}
