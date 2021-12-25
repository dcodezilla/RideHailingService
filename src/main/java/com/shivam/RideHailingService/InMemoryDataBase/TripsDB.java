package com.shivam.RideHailingService.InMemoryDataBase;

import com.shivam.RideHailingService.Algorithms.CabUserMatchingAlgorithms;
import com.shivam.RideHailingService.Algorithms.CostCalculationAlgorithmContract;
import com.shivam.RideHailingService.models.*;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TripsDB {
    public static final Double NEARBY_DISTANCE = 5D;
    private Map<String, List<Trip>> trips = new HashMap<>();

    private CabDB cabDB;
    private UserDB userDB;
    private DriverDB driverDB;
    private CabUserMatchingAlgorithms cabUserMatchingAlgorithms;
    private CostCalculationAlgorithmContract costCalculationAlgorithmContract;

    public TripsDB(
            CabDB cabDB,
            UserDB userDB,
            DriverDB driverDB,
            CabUserMatchingAlgorithms cabUserMatchingAlgorithms,
            CostCalculationAlgorithmContract costCalculationAlgorithmContract) {
        this.cabDB = cabDB;
        this.userDB = userDB;
        this.driverDB = driverDB;
        this.cabUserMatchingAlgorithms = cabUserMatchingAlgorithms;
        this.costCalculationAlgorithmContract = costCalculationAlgorithmContract;
    }

    public Trip createNewTrip(
            @NonNull final User user,
            @NonNull final Location source,
            @NonNull final Location destination) {
        final List<Cab> nearByAvailableCabs =
                cabDB.getNearbyAvailableCabs(source, NEARBY_DISTANCE);

        final Cab selectedCab =
                cabUserMatchingAlgorithms.findSuitableCabForUser(user, nearByAvailableCabs, source, destination);
        if (selectedCab == null) {
            System.out.println("Could not find any cab in your area, please try again in some time!");
            return null;
        }

        final Double price = costCalculationAlgorithmContract.calculatePriceForARide(source, destination, false);
        final Trip newTrip = new Trip(user, selectedCab, price, source, destination);

        //update trip for user
        if (!trips.containsKey(user.getId())) {
            trips.put(user.getId(), new ArrayList<>());
        }
        trips.get(user.getId()).add(newTrip);

        //update trip for driver
        if (!trips.containsKey(selectedCab.getDriver().getId())) {
            trips.put(selectedCab.getDriver().getId(), new ArrayList<>());
        }
        trips.get(selectedCab.getDriver().getId()).add(newTrip);

        //mark unavailable so that no other user can book the same cab
        selectedCab.setAvailable(false);
        selectedCab.setCurrentTrip(newTrip);

        return newTrip;
    }

    public List<Trip> tripHistory(@NonNull final User user) {
        return trips.get(user.getId());
    }

    public List<Trip> tripHistoryOfDriver(@NonNull final Driver driver) {
        return trips.get(driver.getId());
    }

    public void endTrip(@NonNull final Cab cab) {
        if (cab.getCurrentTrip() == null) {
            System.out.println("No ongoing trip was found for given cab!");
            return;
        }

        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
        cab.setAvailable(true);
    }
}
