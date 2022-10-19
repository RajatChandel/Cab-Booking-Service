package policies;

import model.Driver;
import model.Location;

import java.util.List;

public interface IDriverFilterPolicy {
    List<Driver> getEligibleDrivers(List<Driver> allDrivers, Location userLocation);
}
