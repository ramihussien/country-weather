package com.harri.harriinterviewtask.ui.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.harri.harriinterviewtask.R;
import com.harri.harriinterviewtask.contract.WeatherContract;
import com.harri.harriinterviewtask.interfaces.CountryObservable;
import com.harri.harriinterviewtask.interfaces.CountryObserver;
import com.harri.harriinterviewtask.model.Country;
import com.harri.harriinterviewtask.model.Weather;
import com.harri.harriinterviewtask.presenter.WeatherPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment implements CountryObserver, WeatherContract.View {


    private static final String TAG = "WeatherFragment";

    private WeatherContract.Presenter mPresenter;


    public WeatherFragment() {
        // Required empty public constructor
    }

    public static WeatherFragment newInstance() {
        return new WeatherFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG, getResources().getString(R.string.fragment_created));

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather, container, false);

        // Create the weather presenter
        new WeatherPresenter(this);

        if(getActivity() != null && getActivity() instanceof CountryObservable) {
            ((CountryObservable) getActivity()).registerObserver(WeatherFragment.this);
        }

        return view;
    }

    @Override
    public void setPresenter(WeatherContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void update(Country country) {
        double lat = country.getLatlng()[0];
        double lon = country.getLatlng()[1];
        String appId = getResources().getString(R.string.weather_app_id);

        mPresenter.getWeatherDetails(lat, lon, appId);
    }

    @Override
    public void setWeatherData(Weather weather) {

    }
}
