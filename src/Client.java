import dto.DriverRequest;
import dto.UserRequest;
import enums.DriverStatus;
import exception.NoRideFoundException;
import model.Driver;
import model.Location;
import model.User;
import service.DriverService;
import service.TripService;
import service.UserService;

import java.util.List;
import java.util.Map;

public class Client {

    public static void main(String[] args) throws NoRideFoundException {

        //mock userRequest to create user
        UserRequest userRequest = mockRequest();

        //mock driverRequest to create driver
        DriverRequest driver = new DriverRequest();
        driver.setId("1");
        driver.setName("lewis hamilton");
        driver.setEmail("lewis@gmail");
        driver.setVehicleBrand("Hyundai");
        driver.setVehicleModel("verna");
        driver.setVehicleNumber("HP33B8077");
        driver.setStatus(DriverStatus.AVAILABLE);
        driver.setyCoordinate(5);
        driver.setxCoordinate(6);

        //mock driverRequest to create driver
        DriverRequest driver2 = new DriverRequest();
        driver2.setId("2");
        driver2.setName("Max Verstappan");
        driver2.setEmail("max@gmail");
        driver2.setVehicleBrand("Audi");
        driver2.setVehicleModel("R8");
        driver2.setVehicleNumber("HP33B8977");
        driver2.setStatus(DriverStatus.AVAILABLE);
        driver2.setyCoordinate(5);
        driver2.setxCoordinate(6);

        //mock driverRequest to create driver
        // this driver is out of range for user and won't be available
        DriverRequest driver3 = new DriverRequest();
        driver3.setId("3");
        driver3.setName("George Russell");
        driver3.setEmail("geaorge@gmail");
        driver3.setVehicleBrand("Mahindra");
        driver3.setVehicleModel("Thar");
        driver3.setVehicleNumber("HP32B8127");
        driver3.setStatus(DriverStatus.AVAILABLE);
        driver3.setyCoordinate(20);
        driver3.setxCoordinate(15);

        //Initializing Services
        UserService userService = new UserService();
        DriverService driverService = new DriverService();
        TripService tripService = new TripService();

        //Creating user and drivers
        userService.addUser(userRequest);
        driverService.addNewDriver(driver);
        driverService.addNewDriver(driver2);
        driverService.addNewDriver(driver3);

        //Fetching the created user by id
        User user = userService.getUserById("1");
        System.out.println("User name: " + user.getName());

        System.out.println("***********************************************************");

        //method returns drivers who are available and closed than 5 unit to the user location
        List<Driver> availableDrivers = driverService.getAvailableDrivers(user.getLocation());

        //printing the available drivers
        for(Driver nearByDriver: availableDrivers) {
            System.out.println("Available driver: " + nearByDriver.getName());
        }

        System.out.println("***********************************************************");
        //user selects driver with id 1
        String tripId = tripService.chooseDriver(user.getId(), availableDrivers.get(1).getId(), user.getLocation(), new Location(20,32));
        tripService.completeTrip(tripId);

        //trip price is calculated and displayed
        Double tripPrice = tripService.calculateTripPrice(tripId);
        System.out.println("Total cost of Trip = " +tripPrice);

        System.out.println("***********************************************************");
        //incomes of all the drivers is fetched and displayed here
        Map<String, Double> driverIncomes = driverService.getAllDriverIncome();
        for(String driverId: driverIncomes.keySet()) {
            System.out.println(driverService.getDriverById(driverId).getName() + " has earned: " + driverIncomes.get(driverId));
        }
    }

    public static UserRequest mockRequest() {
        UserRequest userRequest1 = new UserRequest();
        userRequest1.setEmail("ashok@gmail");
        userRequest1.setPhoneNumber("9846748833");
        userRequest1.setName("Ashok");
        userRequest1.setxLocation(4);
        userRequest1.setyLocation(5);
        // mocking the id of user
        userRequest1.setId("1");
        return userRequest1;
    }


}
