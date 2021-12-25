package com.shivam.RideHailingService.Services;

import com.shivam.RideHailingService.InMemoryDataBase.TripsDB;
import com.shivam.RideHailingService.InMemoryDataBase.UserDB;
import com.shivam.RideHailingService.models.Location;
import com.shivam.RideHailingService.models.Trip;
import com.shivam.RideHailingService.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserService {
    private UserDB userDB;
    private TripsDB tripsDB;

    public UserService(UserDB userDB, TripsDB tripsDB) {
        this.userDB = userDB;
        this.tripsDB = tripsDB;
    }

    @RequestMapping(value = "/userService/addUserToTheSystem", method = RequestMethod.POST)
    public ResponseEntity addUserToTheSystem(final String userId, final String userName) {
        userDB.addUserToTheSystem(new User(userId, userName));
        return ResponseEntity.ok("");
    }

    @RequestMapping(value = "/userService/bookTheRide", method = RequestMethod.POST)
    public Trip bookTheRide(
            final String userId,
            final Double sourceX,
            final Double sourceY,
            final Double destX,
            final Double destY) {

        Trip trip = tripsDB.createNewTrip(
                userDB.getUser(userId),
                new Location(sourceX, sourceY),
                new Location(destX, destY));

        return trip;
    }

    @RequestMapping(value = "/userService/showRideHistoryForUser", method = RequestMethod.GET)
    public StringBuilder showRideHistoryForUser(final String userId) {
        List<Trip> trips = tripsDB.tripHistory(userDB.getUser(userId));
        StringBuilder response = new StringBuilder();
        for(Trip trip: trips) {
            response.append(trip.toString());
            //System.out.println(trip.toString());
        }
        return response;
    }
}
