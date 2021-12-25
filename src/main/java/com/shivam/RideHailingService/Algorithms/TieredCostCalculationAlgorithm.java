package com.shivam.RideHailingService.Algorithms;

import com.shivam.RideHailingService.models.Location;

public class TieredCostCalculationAlgorithm implements CostCalculationAlgorithmContract {

    final Double MINIMUM_RIDE_PRICE = 50D;
    final Double FIRST_BAND_PRICE = 10D;
    final Double SECOND_BAND_PRICE = 8D;
    final Double LAST_BAND_PRICE = 6D;
    final Double FIRST_BAND_DISTANCE_LIMIT = 2D;
    final Double SECOND_BAND_DISTANCE_LIMIT = 5D;
    final Double SURGE_PRICE_FACTOR = 1.5D;


    @Override
    public Double calculatePriceForARide(Location source, Location destination, Boolean isSurgePricingApplicable) {
        Double price = MINIMUM_RIDE_PRICE;

        Double totalDistance = source.getDistance(destination);

        if(totalDistance > SECOND_BAND_DISTANCE_LIMIT) {
            price += (totalDistance - SECOND_BAND_DISTANCE_LIMIT)*LAST_BAND_PRICE;
            price += (SECOND_BAND_DISTANCE_LIMIT - FIRST_BAND_DISTANCE_LIMIT)*SECOND_BAND_PRICE;
            price += FIRST_BAND_DISTANCE_LIMIT*FIRST_BAND_PRICE;
        } else if(totalDistance > FIRST_BAND_DISTANCE_LIMIT) {
            price += (totalDistance - FIRST_BAND_DISTANCE_LIMIT)*SECOND_BAND_PRICE;
            price += FIRST_BAND_DISTANCE_LIMIT*FIRST_BAND_PRICE;
        } else {
            price += totalDistance*FIRST_BAND_PRICE;
        }

        if(isSurgePricingApplicable) {
            price *= SURGE_PRICE_FACTOR;
        }

        return price;
    }
}
