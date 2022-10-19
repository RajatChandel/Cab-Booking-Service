package repository;

import enums.TripStatus;
import model.Driver;
import model.Trip;

import java.util.List;
import java.util.Map;

public interface ITripRepository {
    boolean createTrip(Trip trip);
    Trip getTripById(String id);

    boolean changeTripStatus(String id, TripStatus tripStatus);

}
