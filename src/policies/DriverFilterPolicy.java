package policies;

import enums.DriverStatus;
import model.Driver;
import model.Location;

import java.util.ArrayList;
import java.util.List;

public class DriverFilterPolicy implements IDriverFilterPolicy {
    public static final Integer MAX_DISTANCE = 5;

    // A circle equation is used to find nearby drivers, all drivers less than or equal to 5 unit distance ie who are inside the circle will be returned
    @Override
    public List<Driver> getEligibleDrivers(List<Driver> allDrivers, Location userLocation) {
        List<Driver> eligibleDrivers = new ArrayList<>();
        for (Driver driver : allDrivers) {
            if (driver.getStatus().equals(DriverStatus.AVAILABLE)) {
                double dist = Math.abs(Math.pow((driver.getLocation().getX() - userLocation.getX()), 2) - Math.pow((driver.getLocation().getY() - userLocation.getY()), 2));
                if (dist <= Math.pow(MAX_DISTANCE, 2)) {
                    eligibleDrivers.add(driver);
                }
            }
        }
        return eligibleDrivers;
    }
}
