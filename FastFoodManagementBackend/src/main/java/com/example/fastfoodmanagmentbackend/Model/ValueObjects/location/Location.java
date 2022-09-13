package com.example.fastfoodmanagmentbackend.Model.ValueObjects.location;

import com.example.fastfoodmanagmentbackend.Model.base.ValueObject;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Location implements ValueObject {
    private final String longitude;
    private final String latitude;
    private final String city;

    protected Location() {
        this.longitude = "";
        this.latitude = "";
        this.city = "";
    }

    public Location(String longitude, String latitude, String city) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
    }

    public static Location valueOf(String longitude, String latitude, String city) {
        return new Location(longitude, latitude, city);
    }
}
