package service;

import enums.TripStatus;
import model.Driver;
import model.Location;
import model.User;

import java.util.List;
import java.util.Map;

public interface ITripsService {
    String createTrip(Driver driver, User user, Location source, Location destination);
    Double calculateTripPrice(String tripId);

    boolean changeTripStatus(String tripId, TripStatus tripStatus);

    String chooseDriver(String userId, String driverId, Location source, Location destination);

    boolean startTrip(String id);

    Double completeTripAndDisplayPrice(String id);


}
