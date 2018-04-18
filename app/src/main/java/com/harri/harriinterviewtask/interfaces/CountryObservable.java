package com.harri.harriinterviewtask.interfaces;

import com.harri.harriinterviewtask.model.Country;

public interface CountryObservable {

    void registerObserver(CountryObserver countryObserver);

    void removeObserver(CountryObserver countryObserver);

    void notifyObservers(Country country);
}

