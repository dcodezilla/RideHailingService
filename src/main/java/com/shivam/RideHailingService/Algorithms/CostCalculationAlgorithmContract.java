package com.shivam.RideHailingService.Algorithms;

import com.shivam.RideHailingService.models.Location;

public interface CostCalculationAlgorithmContract {
        public Double calculatePriceForARide(Location source, Location destination, Boolean isSurgePricingApplicable);
}
