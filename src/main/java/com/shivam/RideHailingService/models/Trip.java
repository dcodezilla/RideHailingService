package com.shivam.RideHailingService.models;

import lombok.NonNull;

public class Trip {
    private User user;
    private Cab cab;
    private TripStatus tripStatus;
    private Double price;
    private Location source;
    private Location destination;

    @Override
    public String toString() {
        return "Trip{" +
                "user=" + user +
                ", cab=" + cab +
                ", tripStatus=" + tripStatus +
                ", price=" + price +
                ", source=" + source +
                ", destination=" + destination +
                '}';
    }

    public Trip(
            @NonNull final User user,
            @NonNull final Cab cab,
            @NonNull final Double price,
            @NonNull final Location source,
            @NonNull final Location destination) {
        this.user = user;
        this.cab = cab;
        this.price = price;
        this.source = source;
        this.destination = destination;
        this.tripStatus = TripStatus.ONGOING;
    }

    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public Double getPrice() {
        return price;
    }

    public void endTrip() {
        this.tripStatus = TripStatus.FINISHED;
    }
}
