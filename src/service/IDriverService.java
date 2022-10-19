package service;

import dto.DriverRequest;
import enums.DriverStatus;
import exception.NoRideFoundException;
import model.Driver;
import model.Location;

import java.util.List;
import java.util.Map;

public interface IDriverService {
    boolean addNewDriver(DriverRequest driverRequest);
    boolean updateDriverStatus(String driverId, DriverStatus status);
    boolean updateDriverLocation(String id, Location location);
    Driver getDriverById(String id);

    List<Driver> getAvailableDrivers(Location source) throws NoRideFoundException;
    Map<String, Double> getAllDriverIncome();

    public boolean addTripIncomeForDriver(String driverId, Double income);

}
