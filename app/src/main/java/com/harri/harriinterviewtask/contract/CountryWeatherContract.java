package com.harri.harriinterviewtask.contract;

import android.support.v4.view.ViewPager;

import com.harri.harriinterviewtask.base.BasePresenter;
import com.harri.harriinterviewtask.base.BaseView;
import com.harri.harriinterviewtask.model.Country;

import java.util.List;

public interface CountryWeatherContract {

    interface View extends BaseView<Presenter> {
        void showLoading();

        void hideLoading();

        void showAllCountries();

        void initCountryRecyclerView(List<Country> countryList);

        void setupWeatherViewPager(ViewPager viewPager);
    }

    interface Presenter extends BasePresenter {
        void getAllCountries();
    }
}
