package com.shivam.junittest;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.shivam.RideHailingService.Algorithms.CabUserMatchingAlgorithmBasedOnDistance;
import com.shivam.RideHailingService.Algorithms.CabUserMatchingAlgorithms;
import com.shivam.RideHailingService.Algorithms.CostCalculationAlgorithmContract;
import com.shivam.RideHailingService.Algorithms.TieredCostCalculationAlgorithm;
import com.shivam.RideHailingService.InMemoryDataBase.CabDB;
import com.shivam.RideHailingService.InMemoryDataBase.DriverDB;
import com.shivam.RideHailingService.InMemoryDataBase.TripsDB;
import com.shivam.RideHailingService.InMemoryDataBase.UserDB;
import com.shivam.RideHailingService.Services.CabService;
import com.shivam.RideHailingService.Services.DiscountVoucherService;
import com.shivam.RideHailingService.Services.DriverService;
import com.shivam.RideHailingService.Services.UserService;
import com.shivam.RideHailingService.models.Cab;
import com.shivam.RideHailingService.models.DiscountVoucher;
import com.shivam.RideHailingService.models.Driver;
import com.shivam.RideHailingService.models.Trip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class UnitTests {
    CabService cabService;
    UserService userService;
    DriverService driverService;
    DiscountVoucherService discountVoucherService;
    DiscountVoucher discountVoucher;


    @BeforeEach
    public void init() {
        CabDB cabDB = new CabDB();
        UserDB userDB = new UserDB();
        DriverDB driverDB = new DriverDB();
        CabUserMatchingAlgorithms cabUserMatchingAlgorithms = new CabUserMatchingAlgorithmBasedOnDistance();
        CostCalculationAlgorithmContract costCalculationAlgorithmContract = new TieredCostCalculationAlgorithm();

        TripsDB tripsDB = new TripsDB(cabDB, userDB, driverDB, cabUserMatchingAlgorithms, costCalculationAlgorithmContract);


        cabService = new CabService(cabDB, tripsDB, driverDB);
        userService = new UserService(userDB, tripsDB);
        driverService = new DriverService(driverDB, tripsDB);
        discountVoucherService = new DiscountVoucherService();

        discountVoucher = createDiscountVoucher();
    }


    @Test
    public void testGeneralRideBookingFlow() throws Exception{

        initEntities();

        updateCabLocations();

        //user query for cabs
        userService.bookTheRide("u1", 0D, 0D, 10D, 10D);
        userService.bookTheRide("u2", 1D,1D,2D,2D);

        //current allocation
        System.out.println("Current cab allocation to users:");
        System.out.println("User1: " + userService.showRideHistoryForUser("u1"));
        System.out.println("User2: " + userService.showRideHistoryForUser("u2"));

    }

    @Test
    public void testEndOngoingRideFeature() throws Exception{

        initEntities();

        updateCabLocations();

        //user query for cabs
        userService.bookTheRide("u1", 0D, 0D, 10D, 10D);
        userService.bookTheRide("u2", 1D,1D,2D,2D);

        cabService.endTrip("c1");
        //current allocation
        System.out.println("Current cab allocation to users:");
        System.out.println("User1: " + userService.showRideHistoryForUser("u1"));
        System.out.println("User2: " + userService.showRideHistoryForUser("u2"));

        //Ride History of drivers
        //current allocation
        System.out.println("Current ride history of drivers:");
        System.out.println("Driver1: " + driverService.showRideHistoryForDriver("d1").getBody());
        System.out.println("Driver4: " + driverService.showRideHistoryForDriver("d4").getBody());
    }


    protected void initEntities() throws Exception {
        initUsers();
        initDrivers();
        initCabs();
    }

    protected void updateCabLocations() {
        //update cabs' location in the system
        cabService.updateCabLocation("c1", 0D, 0D);
        cabService.updateCabLocation("c2", 10D, 0D);
        cabService.updateCabLocation("c3", 0D, 9D);
        cabService.updateCabLocation("c4", 1D, 1D);
        cabService.updateCabLocation("c5", 2D, 2D);

    }

    protected void initUsers() {
        //add users to system
        userService.addUserToTheSystem("u1", "User1");
        userService.addUserToTheSystem("u2", "User2");
        userService.addUserToTheSystem("u3", "User3");
        userService.addUserToTheSystem("u4", "User4");
        userService.addUserToTheSystem("u5", "User5");
    }
    protected void initDrivers() {
        driverService.addDriverToTheSystem("d1", "driver1");
        driverService.addDriverToTheSystem("d2", "driver2");
        driverService.addDriverToTheSystem("d3", "driver3");
        driverService.addDriverToTheSystem("d4", "driver4");
        driverService.addDriverToTheSystem("d5", "driver5");
    }

    protected void initCabs() throws Exception {
        //add cabs to system
        cabService.introduceNewCabInSystem("c1","d1");
        cabService.introduceNewCabInSystem("c2","d2");
        cabService.introduceNewCabInSystem("c3","d3");
        cabService.introduceNewCabInSystem("c4","d4");
        cabService.introduceNewCabInSystem("c5","d5");
    }
    protected void addDriversToTheCabs() {
        cabService.addDriverToTheCab("c1","d1");
        cabService.addDriverToTheCab("c2","d2");
        cabService.addDriverToTheCab("c3","d3");
        cabService.addDriverToTheCab("c4","d4");
        cabService.addDriverToTheCab("c5","d5");

    }

    protected DiscountVoucher createDiscountVoucher() {
        Double maxDiscount = 50D;
        Double percentageOff = 10D;
        DiscountVoucher discountVoucher = discountVoucherService.generateDiscountVoucher(maxDiscount, percentageOff);
        return discountVoucher;
    }
}
