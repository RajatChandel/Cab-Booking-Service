package repository;

import enums.DriverStatus;
import model.Driver;
import model.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DriverRepository implements IDriverRepository {

    public static final Double STARTING_INCOME_OF_DRIVER = 0.0;

    private static final Object lock = new Object();
    private Map<String, Double> driverIncomeMap;
    private Map<String, Driver> driverMap;
    private static DriverRepository driverRepository = null;

    private DriverRepository() {
        driverMap = new ConcurrentHashMap<>();
        driverIncomeMap = new ConcurrentHashMap<>();
    }

    public static DriverRepository getDriverRepository() {
        synchronized (lock) {
            if (driverRepository == null) {
                driverRepository = new DriverRepository();
            }
        }
        return driverRepository;
    }

    @Override
    public boolean addNewDriver(Driver driver) {
        try {
            driverMap.put(driver.getId(), driver);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean updateDriverLocation(String id, Location location) {
        try {
            Driver driver = driverMap.get(id);
            driver.setLocation(location);
            driverMap.put(driver.getId(), driver);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean updateStatus(String id, DriverStatus status) {
        try {
            Driver driver = driverMap.get(id);
            driver.setStatus(status);
            driverMap.put(driver.getId(), driver);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    @Override
    public List<Driver> findAvailableDrivers(Location userLocation) {
        return null;
    }

    @Override
    public Driver getDriverById(String id) {
        return driverMap.get(id);
    }

    @Override
    public List<Driver> getAllDrivers() {
        return new ArrayList(driverMap.values());
    }

    @Override
    public boolean addTripIncomeForDriver(String driverId, Double income) {
        driverIncomeMap.put(driverId, driverIncomeMap.getOrDefault(driverId, 0.0) + income);
        return true;
    }

    @Override
    public Map<String, Double> getAllDriverIncome() {
        return driverIncomeMap;
    }

    public void addDriverToIncomeMap(String driverId) {
        driverIncomeMap.putIfAbsent(driverId, STARTING_INCOME_OF_DRIVER);
    }


}
