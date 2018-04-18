package com.harri.harriinterviewtask.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.harri.harriinterviewtask.R;
import com.harri.harriinterviewtask.model.Country;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private Context mContext;

    private List<Country> mCountryList;

    private LayoutInflater mLayoutInflater;

    private OnCountryClickListener mOnCountryClickListener;

    private static final String COUNTRY_FLAG_URL = "http://www.geognos.com/api/en/countries/flag/";

    public interface OnCountryClickListener {
        void onCountryClick(Country country);
    }

    public CountryAdapter(Context context, List<Country> countryList, OnCountryClickListener onCountryClickListener) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
        mCountryList = countryList;
        mOnCountryClickListener = onCountryClickListener;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.country_item, parent, false);
        CountryViewHolder holder = new CountryViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country country = mCountryList.get(position);
        holder.setCountryData(country);
    }

    @Override
    public int getItemCount() {
        return mCountryList.size();
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {

        private ImageView mCountryFlag;

        private TextView mCountryName;

        private TextView mCountryRegion;

        public CountryViewHolder(View itemView) {
            super(itemView);

            mCountryFlag = itemView.findViewById(R.id.country_flag);
            mCountryName = itemView.findViewById(R.id.country_name);
            mCountryRegion = itemView.findViewById(R.id.country_region);

        }

        public void setCountryData(final Country country) {
            Glide.with(mContext).load(COUNTRY_FLAG_URL + country.getAlpha2Code() + ".png")
                    .into(mCountryFlag);

            mCountryName.setText(country.getName());
            mCountryRegion.setText(country.getRegion());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnCountryClickListener != null) {
                        mOnCountryClickListener.onCountryClick(country);
                    }
                }
            });

        }
    }

    public void setOnCountryClickListener(OnCountryClickListener onCountryClickListener) {
        mOnCountryClickListener = onCountryClickListener;
    }

    public OnCountryClickListener getOnCountryClickListener() {
        return mOnCountryClickListener;
    }

}