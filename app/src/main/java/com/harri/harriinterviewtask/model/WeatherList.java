package com.harri.harriinterviewtask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherList {

    @SerializedName("main")
    @Expose
    private Main main;

    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    public WeatherList() {
    }

    public WeatherList(Main main, String dtTxt) {
        this.main = main;
        this.dtTxt = dtTxt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }
}
