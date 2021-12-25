package com.shivam.RideHailingService.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;


public class Location {
    //making it immutable
    private Double x;
    private Double y;

    public Location(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Double getX() {
        return x;
    }

    public Double getY() {
        return y;
    }

    public Double getDistance(Location location2) {
        return sqrt( pow(this.x - location2.x, 2) + pow(this.y - location2.y, 2) );
    }
}
