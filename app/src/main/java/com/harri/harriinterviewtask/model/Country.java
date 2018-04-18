package com.harri.harriinterviewtask.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Country {

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("alpha2Code")
    @Expose
    private String alpha2Code;

    @SerializedName("alpha3Code")
    @Expose
    private String alpha3Code;

    @SerializedName("capital")
    @Expose
    private String capital;

    @SerializedName("region")
    @Expose
    private String region;

    @SerializedName("population")
    @Expose
    private Integer population;

    @SerializedName("latlng")
    @Expose
    private double[] latlng;


    /**
     * No args constructor for use in serialization
     *
     */
    public Country() {
    }

    /**
     *
     * @param alpha3Code
     * @param alpha2Code
     * @param capital
     * @param name
     * @param population
     */
    public Country(String name, String alpha2Code, String alpha3Code, String capital, Integer population, double[] latlng) {
        super();
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.capital = capital;
        this.region = region;
        this.population = population;
        this.latlng = latlng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }


    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public double[] getLatlng() {
        return latlng;
    }

    public void setLatlng(double[] latlng) {
        this.latlng = latlng;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("name", name)
                .append("alpha2Code", alpha2Code)
                .append("alpha3Code", alpha3Code)
                .append("capital", capital)
                .append("region", region)
                .append("population", population)
                .append("latlng", latlng)
                .toString();
    }

}
