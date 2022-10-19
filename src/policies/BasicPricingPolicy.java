package policies;

import model.Location;

public class BasicPricingPolicy implements IPricingPolicy {

    public static final Double BASIC_PRICE_PER_KM = 15.0;

    @Override
    public Double getPriceForTrip(Location source, Location destination) {
        return distanceBetweenPoints(source, destination) * BASIC_PRICE_PER_KM;
    }

    public Double distanceBetweenPoints(Location source, Location destination) {
        return Math.sqrt( Math.pow(source.getX() - destination.getX(), 2) + Math.pow(source.getY() - destination.getY(), 2) );
    }
}
