package com.shivam.junittest;

import com.shivam.RideHailingService.models.DiscountVoucher;
import com.shivam.RideHailingService.models.Trip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PricingAndDiscountTest extends UnitTests{
    @Test
    public void testPricingForRideWithoutApplyingDiscountVoucher() throws Exception {

        initEntities();

        updateCabLocations();

        //user query for cabs
        Trip trip = userService.bookTheRide("u1", 0D, 0D, 10D, 10D);
        Double price = trip.getPrice();
        //System.out.println(price);
        Assertions.assertEquals(148.8528137423857D, price);
    }

    @Test
    public void testPricingForRideAfterApplyingDiscountVoucher() throws Exception {

        initEntities();

        updateCabLocations();

        //user query for cabs
        Trip trip = userService.bookTheRide("u1", 0D, 0D, 10D, 10D);
        Double price = trip.getPrice();
        Double discount = manageDiscountVoucher(price, discountVoucher);
        price = price - discount;
        //System.out.println(price);
        Assertions.assertEquals(133.96753236814715, price);
    }


    Double manageDiscountVoucher(Double price, DiscountVoucher discountVoucher) {
        Double discount = 0D;

        discount = Math.min((price * discountVoucher.getPercentage()) / 100, discountVoucher.getMaxValue());
        return discount;
    }
}
