package com.shivam.RideHailingService.Algorithms;

import com.shivam.RideHailingService.models.Cab;
import com.shivam.RideHailingService.models.Location;
import com.shivam.RideHailingService.models.User;

import java.util.List;

public interface CabUserMatchingAlgorithms {
    Cab findSuitableCabForUser(User user, List<Cab> availableNearbyCabs, Location source, Location destination);
}
