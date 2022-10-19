package repository;

import enums.DriverStatus;
import model.Driver;
import model.Location;

import java.util.List;
import java.util.Map;

public interface IDriverRepository {
    boolean addNewDriver(Driver driver);
    boolean updateDriverLocation(String id, Location location);
    boolean updateStatus(String id, DriverStatus status);
    List<Driver> findAvailableDrivers(Location userLocation);
    Driver getDriverById(String id);
    List<Driver> getAllDrivers();

    boolean addTripIncomeForDriver(String driverId, Double income);
    Map<String, Double> getAllDriverIncome();
}
