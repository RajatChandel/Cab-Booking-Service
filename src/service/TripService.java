package service;

import enums.TripStatus;
import model.Driver;
import model.Location;
import model.Trip;
import model.User;
import policies.*;
import repository.TripRepository;

import java.util.List;
import java.util.Map;

public class TripService implements ITripsService {

    private DriverService driverService;
    private UserService userService;
    private TripRepository tripRepository;

    private IPricingPolicy pricingPolicy;
    private IDriverIncomePolicy driverIncomePolicy;

    public TripService() {
        driverService = new DriverService();
        userService = new UserService();
        tripRepository = TripRepository.getTripRepository();
        pricingPolicy = new BasicPricingPolicy();
        driverIncomePolicy = new BasicIncomePolicy();
    }

    @Override
    public String createTrip(Driver driver, User user, Location source, Location destination) {
        Trip trip = new Trip();
        //mocked trip id
        trip.setId("1");

        trip.setDriver(driver);
        trip.setUser(user);
        trip.setSource(source);
        trip.setDestination(destination);
        trip.setStatus(TripStatus.CREATED);

        tripRepository.createTrip(trip);
        return trip.getId();

    }

    @Override
    public Double calculateTripPrice(String tripId) {
        Trip trip = tripRepository.getTripById(tripId);
        Double price = pricingPolicy.getPriceForTrip(trip.getSource(), trip.getDestination());
        trip.setPrice(price);
        driverService.addTripIncomeForDriver(trip.getDriver().getId(), driverIncomePolicy.calculateDriverIncome(price));
        return price;
    }

    @Override
    public boolean changeTripStatus(String tripId, TripStatus tripStatus) {
        return tripRepository.changeTripStatus(tripId, tripStatus);
    }

    @Override
    public String chooseDriver(String userId, String driverId, Location source, Location destination) {
        User user = userService.getUserById(userId);
        Driver driver = driverService.getDriverById(driverId);
        return createTrip(driver, user, source, destination);
    }


    @Override
    public boolean startTrip(String tripId) {
        return changeTripStatus(tripId, TripStatus.IN_PROGRESS);
    }

    @Override
    public boolean completeTrip(String tripId) {
        return changeTripStatus(tripId, TripStatus.COMPLETED);
    }



}
