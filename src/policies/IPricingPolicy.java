package policies;

import model.Location;

public interface IPricingPolicy {
    Double getPriceForTrip(Location source, Location destination);
}
