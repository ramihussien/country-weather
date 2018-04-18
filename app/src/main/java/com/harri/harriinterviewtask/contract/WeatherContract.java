package com.harri.harriinterviewtask.contract;

import android.support.v4.view.ViewPager;

import com.harri.harriinterviewtask.base.BasePresenter;
import com.harri.harriinterviewtask.base.BaseView;
import com.harri.harriinterviewtask.model.Country;
import com.harri.harriinterviewtask.model.Weather;

import java.util.List;

public interface WeatherContract {

    interface View extends BaseView<WeatherContract.Presenter> {
        void setWeatherData(Weather weather);
    }

    interface Presenter extends BasePresenter {
        void getWeatherDetails(double lat, double lon, String appId);
    }
}
