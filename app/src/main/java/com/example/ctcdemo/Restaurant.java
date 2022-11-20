package com.example.ctcdemo;

public class Restaurant {

    public String name;
    public String city;
    public Boolean hasHealthyOptions;
    public Boolean hasVeganOptions;
    public double latitude;
    public double longitude;
    public String ZIPCode;


    public Restaurant() {

    }

    public Restaurant(String name, String city, Boolean hasHealthyOptions, Boolean hasVeganOptions,
                      double latitude, double longitude, String ZIPCode) {

        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.city = city;
        this.hasHealthyOptions = hasHealthyOptions;
        this.hasVeganOptions = hasVeganOptions;
        this.ZIPCode = ZIPCode;

    }

}