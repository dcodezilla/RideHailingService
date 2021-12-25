package com.shivam.RideHailingService.Services;

import com.mongodb.DB;
import com.shivam.RideHailingService.InMemoryDataBase.DriverDB;
import com.shivam.RideHailingService.InMemoryDataBase.TripsDB;
import com.shivam.RideHailingService.InMemoryDataBase.UserDB;
import com.shivam.RideHailingService.models.Driver;
import com.shivam.RideHailingService.models.Location;
import com.shivam.RideHailingService.models.Trip;
import com.shivam.RideHailingService.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DriverService {
    private DriverDB driverDB;
    private TripsDB tripsDB;

    public DriverService(DriverDB driverDB, TripsDB tripsDB) {
        this.driverDB = driverDB;
        this.tripsDB = tripsDB;
    }

    @RequestMapping(value = "/driverService/addDriverToTheSystem", method = RequestMethod.POST)
    public ResponseEntity addDriverToTheSystem(final String driverId, final String userName) {
        driverDB.addDriverToTheSystem(new Driver(driverId, userName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/driverService/notifyAvailability", method = RequestMethod.POST)
    public Boolean notifyAvailability() {
           //to-do : logic to notify driver's availability in system
        return true;
    }

    @RequestMapping(value = "/driverService/showRideHistoryForUser", method = RequestMethod.GET)
    public ResponseEntity showRideHistoryForDriver(final String driverId) {
        List<Trip> trips = tripsDB.tripHistoryOfDriver(driverDB.getDriver(driverId));
        return ResponseEntity.ok(trips);
    }
}
