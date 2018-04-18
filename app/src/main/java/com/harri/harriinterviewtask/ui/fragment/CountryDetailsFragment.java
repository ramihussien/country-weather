package com.harri.harriinterviewtask.ui.fragment;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.harri.harriinterviewtask.R;
import com.harri.harriinterviewtask.interfaces.CountryObservable;
import com.harri.harriinterviewtask.interfaces.CountryObserver;
import com.harri.harriinterviewtask.model.Country;

/**
 * A simple {@link Fragment} subclass.
 */
public class CountryDetailsFragment extends Fragment implements CountryObserver {


    private static final String COUNTRY_FLAG_URL = "http://www.geognos.com/api/en/countries/flag/";

    private static final String TAG = "CountryDetailsFragment";

    private TextView mCountryName;

    private TextView mCountryRegion;

    private TextView mCountryPopulation;

    private TextView mCountryCapital;

    private ImageView mCountryFlag;

    public CountryDetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_country_details, container, false);

        Log.d(TAG, getResources().getString(R.string.fragment_created));

        mCountryName = view.findViewById(R.id.country_detail_name);
        mCountryRegion = view.findViewById(R.id.country_detail_region);
        mCountryCapital = view.findViewById(R.id.country_detail_capital);
        mCountryPopulation = view.findViewById(R.id.country_detail_population);

        mCountryFlag = view.findViewById(R.id.country_detail_flag);


        if(getActivity() != null && getActivity() instanceof CountryObservable) {
            ((CountryObservable) getActivity()).registerObserver(CountryDetailsFragment.this);
        }

        return view;
    }

    @Override
    public void update(Country country) {
        mCountryName.setText(country.getName());
        mCountryRegion.setText(country.getRegion());
        mCountryCapital.setText(country.getCapital());
        mCountryPopulation.setText(String.valueOf(country.getPopulation()));

        if(getContext() != null) {
            Glide.with(getContext())
                    .load(COUNTRY_FLAG_URL + country.getAlpha2Code() + ".png")
                    .into(mCountryFlag);
        }

    }
}
