package com.shivam.RideHailingService.Algorithms;

import com.shivam.RideHailingService.models.Cab;
import com.shivam.RideHailingService.models.Location;
import com.shivam.RideHailingService.models.User;

import java.util.List;

public class CabUserMatchingAlgorithmBasedOnDistance implements CabUserMatchingAlgorithms {

    @Override
    public Cab findSuitableCabForUser(User user, List<Cab> availableNearbyCabs, Location source, Location destination) {
        if(availableNearbyCabs.size() == 0) {
            System.out.println("No cabs were found in your location, please try after some time!");
            return null;
        }

        //Default implementation to get the first available car in the list
        return availableNearbyCabs.get(0);
    }
}
