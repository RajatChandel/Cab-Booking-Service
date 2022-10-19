package service;

import dto.DriverRequest;
import enums.DriverStatus;
import exception.NoRideFoundException;
import model.Contact;
import model.Driver;
import model.Location;
import model.Vehicle;
import policies.DriverFilterPolicy;
import policies.IDriverFilterPolicy;
import repository.DriverRepository;

import java.util.List;
import java.util.Map;

public class DriverService implements IDriverService {

    private DriverRepository driverRepository;
    private IDriverFilterPolicy driverFilterPolicy;

    public DriverService() {
        driverRepository = DriverRepository.getDriverRepository();
        driverFilterPolicy = new DriverFilterPolicy();
    }

    @Override
    public boolean addNewDriver(DriverRequest driverRequest) {
        Driver driver = new Driver();

        driver.setId(driverRequest.getId());
        driver.setStatus(driverRequest.getStatus());
        driver.setName(driverRequest.getName());

        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(driverRequest.getVehicleBrand());
        vehicle.setRegistrationNumber(driverRequest.getVehicleNumber());
        vehicle.setModel(driverRequest.getVehicleModel());

        Contact contact = new Contact();
        contact.setEmail(driverRequest.getEmail());
        contact.setPhoneNumber(driverRequest.getPhoneNumber());

        Location location = new Location(driverRequest.getxCoordinate(), driverRequest.getyCoordinate());
        driver.setLocation(location);
        driver.setContact(contact);
        driver.setVehicle(vehicle);

        driverRepository.addNewDriver(driver);
        driverRepository.addDriverToIncomeMap(driver.getId());
        return true;
    }

    @Override
    public boolean updateDriverStatus(String driverId, DriverStatus status) {
        return driverRepository.updateStatus(driverId, status);

    }

    @Override
    public boolean updateDriverLocation(String id, Location location) {
        return driverRepository.updateDriverLocation(id, location);
    }

    @Override
    public Driver getDriverById(String id) {
        return driverRepository.getDriverById(id);
    }

    @Override
    public List<Driver> getAvailableDrivers(Location source) throws NoRideFoundException {
        List<Driver> drivers = driverRepository.getAllDrivers();
        List<Driver> availableDrivers = driverFilterPolicy.getEligibleDrivers(drivers, source);
        if(availableDrivers == null || availableDrivers.isEmpty()) {
            throw new NoRideFoundException("No ride found");
        }
        return availableDrivers;
    }

    @Override
    public Map<String, Double> getAllDriverIncome() {
        return driverRepository.getAllDriverIncome();
    }

    @Override
    public boolean addTripIncomeForDriver(String driverId, Double income) {
        driverRepository.addTripIncomeForDriver(driverId, income);
        return false;
    }


}
