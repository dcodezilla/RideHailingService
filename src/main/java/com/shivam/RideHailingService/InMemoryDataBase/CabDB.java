package com.shivam.RideHailingService.InMemoryDataBase;

import com.shivam.RideHailingService.models.Cab;
import com.shivam.RideHailingService.models.Driver;
import com.shivam.RideHailingService.models.Location;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabDB {
    Map<String, Cab> cabs = new HashMap<>();

    public void introduceNewCabInSystem(@NonNull Cab newCab) throws Exception {
        if (cabs.containsKey(newCab.getId())) {
            System.out.println("Car already exists in system!");
            throw new Exception("Test Exception");

        }

        cabs.put(newCab.getId(), newCab);
    }

    public Cab getCab(@NonNull final String cabId) {
        if (!cabs.containsKey(cabId)) {
            System.out.println("No cab was found with CabId" + cabId);
            return null;
        }
        return cabs.get(cabId);
    }

    public void updateCabLocation(@NonNull final String cabId, @NonNull final Location newLocation) {
        if (!cabs.containsKey(cabId)) {
            System.out.println("No cab was found with CabId" + cabId);
            return;
        }
        cabs.get(cabId).setCurrentLocation(newLocation);
    }

    public void updateCabAvailability(
            @NonNull final String cabId, @NonNull final Boolean newAvailability) {
        if (!cabs.containsKey(cabId)) {
            System.out.println("No cab was found with CabId" + cabId);
            return;
        }
        cabs.get(cabId).setAvailable(newAvailability);
    }

    public List<Cab> getNearbyAvailableCabs(@NonNull final Location source, @NonNull final Double distance) {
        List<Cab> result = new ArrayList<>();
        for (Cab cab : cabs.values()) {
            if (cab.getAvailable()
                    && cab.getCurrentLocation().getDistance(source) <= distance
                    && cab.getCurrentTrip() == null) {
                result.add(cab);
            }
        }
        return result;
    }

    public void setDriver(@NonNull final String cabId, @NonNull final Driver driver) {
        if (!cabs.containsKey(cabId)) {
            System.out.println("No cab was found with CabId" + cabId);
            return;
        }

        if(getDriver(cabId) != null) {
            System.out.println("Driver already present in the cab, it will be updated now with: " + driver.getName());
        }
        cabs.get(cabId).setDriver(driver);
    }

    public Driver getDriver(@NonNull final String cabId) {
        if (!cabs.containsKey(cabId)) {
            System.out.println("No cab was found with CabId" + cabId);
            return null;
        }

        return cabs.get(cabId).getDriver();
    }
}
