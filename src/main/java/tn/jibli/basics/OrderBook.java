package tn.jibli.basics;

import java.util.List;
import tn.jibli.model.Order;
import tn.jibli.model.Restaurant;

public class OrderBook {

    // TODO(step-18): a List<Order> for the orders and a Map<Long, Restaurant> for the restaurants

    public void add(Order order) {
        // TODO(step-18)
        throw new UnsupportedOperationException("TODO(step-18)");
    }

    public int size() {
        // TODO(step-18)
        throw new UnsupportedOperationException("TODO(step-18)");
    }

    public List<Order> orders() {
        // TODO(step-18): a list the caller cannot modify
        throw new UnsupportedOperationException("TODO(step-18)");
    }

    public double revenue() {
        // TODO(step-18): sum of the totals
        throw new UnsupportedOperationException("TODO(step-18)");
    }

    public List<Order> ordersFrom(String restaurantName) {
        // TODO(step-18)
        throw new UnsupportedOperationException("TODO(step-18)");
    }

    public void addRestaurant(Restaurant restaurant) {
        // TODO(step-18): the id is the key
        throw new UnsupportedOperationException("TODO(step-18)");
    }

    public Restaurant findRestaurant(long id) {
        // TODO(step-18): null when unknown
        throw new UnsupportedOperationException("TODO(step-18)");
    }
}
