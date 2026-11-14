package tn.jibli.basics;

import tn.jibli.exception.RestaurantClosedException;
import tn.jibli.model.Order;
import tn.jibli.model.Restaurant;

public class OrderPlacer {

    public static Order placeOrder(Restaurant restaurant, String product, double total) {
        if (!restaurant.isOpen()) {
            throw new RestaurantClosedException(restaurant.getName());
        }
        return new Order(restaurant.getName(), product, total);
    }

    public static String tryPlace(Restaurant restaurant, String product, double total) {
        try {
            placeOrder(restaurant, product, total);
            return "Order accepted";
        } catch (RestaurantClosedException e) {
            return "Restaurant closed";
        }
    }
}
