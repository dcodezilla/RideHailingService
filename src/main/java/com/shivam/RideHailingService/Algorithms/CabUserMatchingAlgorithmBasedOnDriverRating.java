package com.shivam.RideHailingService.Algorithms;

import com.shivam.RideHailingService.models.Cab;
import com.shivam.RideHailingService.models.Location;
import com.shivam.RideHailingService.models.User;

import java.util.List;
import java.util.TreeMap;

public class CabUserMatchingAlgorithmBasedOnDriverRating implements CabUserMatchingAlgorithms {

    @Override
    public Cab findSuitableCabForUser(User user, List<Cab> availableNearbyCabs, Location source, Location destination) {
        //business logic
        TreeMap<Double, Cab> driverRatingCabMap = new TreeMap<>();

        for(Cab cab: availableNearbyCabs) {
            driverRatingCabMap.put(cab.getDriver().getRating(), cab);
        }
        return driverRatingCabMap.lastEntry().getValue();
    }
}
