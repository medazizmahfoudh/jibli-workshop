package tn.jibli.exception;

// unchecked: callers are not forced to catch it, but they can
public class RestaurantClosedException extends RuntimeException {

    public RestaurantClosedException(String restaurantName) {
        super("Restaurant closed: " + restaurantName);
    }
}
