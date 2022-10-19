package repository;

import enums.TripStatus;
import model.Driver;
import model.Trip;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TripRepository implements ITripRepository {

    private static final Object lock = new Object();
    private static TripRepository tripRepository = null;
    private Map<String, Trip> tripMap;

    private TripRepository() {
        tripMap = new ConcurrentHashMap<>();
    }

    public static TripRepository getTripRepository() {
        synchronized (lock) {
            if (tripRepository == null) {
                tripRepository = new TripRepository();
            }
        }
        return tripRepository;
    }

    @Override
    public boolean createTrip(Trip trip) {
        try{
            tripMap.put(trip.getId(), trip);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Trip getTripById(String id) {
        return tripMap.get(id);
    }

    @Override
    public boolean changeTripStatus(String id, TripStatus tripStatus) {
        Trip trip = tripMap.get(id);
        trip.setStatus(tripStatus);
        return true;
    }


}
