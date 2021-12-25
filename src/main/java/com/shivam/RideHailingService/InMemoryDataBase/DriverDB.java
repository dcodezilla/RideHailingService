package com.shivam.RideHailingService.InMemoryDataBase;

import com.shivam.RideHailingService.models.Driver;
import com.shivam.RideHailingService.models.User;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class DriverDB {
    Map<String, Driver> drivers = new HashMap<>();

    public void addDriverToTheSystem(@NonNull final Driver driver) {
        if (drivers.containsKey(driver.getId())) {
            System.out.println("Driver already exists in the system!");
            return;
        }

        drivers.put(driver.getId(), driver);
    }

    public Driver getDriver(@NonNull final String driverId) {
        if (!drivers.containsKey(driverId)) {
            System.out.println("Driver not found in the system!");
            return null;
        }
        return drivers.get(driverId);
    }
}
