package com.shivam.RideHailingService.Services;

import com.shivam.RideHailingService.InMemoryDataBase.CabDB;
import com.shivam.RideHailingService.InMemoryDataBase.DriverDB;
import com.shivam.RideHailingService.InMemoryDataBase.TripsDB;
import com.shivam.RideHailingService.InMemoryDataBase.UserDB;
import com.shivam.RideHailingService.models.Cab;
import com.shivam.RideHailingService.models.Driver;
import com.shivam.RideHailingService.models.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CabService {

    private CabDB cabDB;
    private DriverDB driverDB;
    private TripsDB tripsDB;

    public CabService() {

    }

    public CabService(CabDB cabDB, TripsDB tripsDB, DriverDB driverDB) {
        this.cabDB = cabDB;
        this.tripsDB = tripsDB;
        this.driverDB = driverDB;
    }

    public void addDriverToTheCab(final String cabId, final String driverId) {
        Driver driver = driverDB.getDriver(driverId);
        cabDB.setDriver(cabId, driver);
    }

    @RequestMapping(value = "/cabService/introduceNewCabInSystem", method = RequestMethod.POST)
    public ResponseEntity introduceNewCabInSystem(final String cabId, final String driverId) throws  Exception {
        Driver driver = driverDB.getDriver(driverId);
        cabDB.introduceNewCabInSystem(new Cab(cabId, driver));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/cabService/updateCabLocation", method = RequestMethod.POST)
    public ResponseEntity updateCabLocation(
            final String cabId, final Double newX, final Double newY) {

        cabDB.updateCabLocation(cabId, new Location(newX, newY));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/cabService/updateCabAvailability", method = RequestMethod.POST)
    public ResponseEntity updateCabAvailability(final String cabId, final Boolean newAvailability) {
        cabDB.updateCabAvailability(cabId, newAvailability);
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/cabService/endTrip", method = RequestMethod.POST)
    public ResponseEntity endTrip(final String cabId) {
        tripsDB.endTrip(cabDB.getCab(cabId));
        return ResponseEntity.ok("");
    }

}
