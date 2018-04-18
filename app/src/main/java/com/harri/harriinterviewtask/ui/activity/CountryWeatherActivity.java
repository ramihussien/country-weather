package com.harri.harriinterviewtask.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.harri.harriinterviewtask.R;
import com.harri.harriinterviewtask.contract.CountryWeatherContract;
import com.harri.harriinterviewtask.interfaces.CountryObservable;
import com.harri.harriinterviewtask.interfaces.CountryObserver;
import com.harri.harriinterviewtask.model.Country;
import com.harri.harriinterviewtask.presenter.CountryWeatherPresenter;
import com.harri.harriinterviewtask.ui.adapter.CountryAdapter;
import com.harri.harriinterviewtask.ui.adapter.WeatherAdapter;
import com.harri.harriinterviewtask.ui.fragment.TodayWeatherFragment;
import com.harri.harriinterviewtask.ui.fragment.TomorrowWeatherFragment;

import java.util.ArrayList;
import java.util.List;

public class CountryWeatherActivity extends AppCompatActivity
        implements CountryAdapter.OnCountryClickListener, CountryWeatherContract.View, CountryObservable {


    private DrawerLayout mDrawerLayout;

    private RecyclerView mCountryRecyclerView;

    private CountryWeatherContract.Presenter mPresenter;

    private ViewPager mWeatherViewPager;

    private List<CountryObserver> mCountryObserverList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_weather);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle(getResources().getString(R.string.main_title));

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        mCountryRecyclerView = findViewById(R.id.countries_rv);

        // Create the country weather presenter
        new CountryWeatherPresenter(this);

        // Display all countries
        showAllCountries();

        mWeatherViewPager = findViewById(R.id.weather_viewpager);
        setupWeatherViewPager(mWeatherViewPager);

        TabLayout tabLayout = findViewById(R.id.weather_tabs);
        tabLayout.setupWithViewPager(mWeatherViewPager);

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.country_weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onCountryClick(Country country) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        notifyObservers(country);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showAllCountries() {
        mPresenter.getAllCountries();
    }

    @Override
    public void initCountryRecyclerView(List<Country> countryList) {
        CountryAdapter countryAdapter = new CountryAdapter(this, countryList , this);

        mCountryRecyclerView.setAdapter(countryAdapter);
        mCountryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCountryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mCountryRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void setupWeatherViewPager(ViewPager viewPager) {
        WeatherAdapter weatherAdapter = new WeatherAdapter(getSupportFragmentManager());

        weatherAdapter.addFragment(new TodayWeatherFragment(), getString(R.string.today));
        weatherAdapter.addFragment(new TomorrowWeatherFragment(), getString(R.string.tomorrow));

        viewPager.setAdapter(weatherAdapter);
    }

    @Override
    public void setPresenter(CountryWeatherContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void registerObserver(CountryObserver countryObserver) {
        mCountryObserverList.add(countryObserver);
    }

    @Override
    public void removeObserver(CountryObserver countryObserver) {
        mCountryObserverList.remove(countryObserver);
    }

    @Override
    public void notifyObservers(Country country) {

        for(CountryObserver countryObserver: mCountryObserverList) {
            countryObserver.update(country);
        }
    }
}
