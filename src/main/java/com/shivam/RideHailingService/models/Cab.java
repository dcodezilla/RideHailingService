package com.shivam.RideHailingService.models;

import lombok.Getter;
import lombok.Setter;

public class Cab {
    String id;
    Driver driver;

    Trip currentTrip;
    Location currentLocation;
    Boolean isAvailable;

    CarTypes carTypes;


    public String getId() {
        return id;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public Trip getCurrentTrip() {
        return currentTrip;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentTrip(Trip currentTrip) {
        this.currentTrip = currentTrip;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public CarTypes getCarTypes() {
        return carTypes;
    }

    public void setCarTypes(CarTypes carTypes) {
        this.carTypes = carTypes;
    }

    public Cab(String id, Driver driver) {
        this.id = id;
        this.driver = driver;
        this.isAvailable = true;
    }

    @Override
    public String toString() {
        return "Cab{" +
                "id='" + id + '\'' +
                ", driver='" + driver + '\'' +
                ", currentLocation=" + currentLocation +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
