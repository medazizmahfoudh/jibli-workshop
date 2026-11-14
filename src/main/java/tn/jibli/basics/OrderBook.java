package tn.jibli.basics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import tn.jibli.model.Order;
import tn.jibli.model.Restaurant;

public class OrderBook {

    // declare the interface type, create a concrete class
    private final List<Order> orders = new ArrayList<>();
    private final Map<Long, Restaurant> restaurants = new HashMap<>();

    public void add(Order order) {
        orders.add(order);
    }

    public int size() {
        return orders.size();
    }

    public List<Order> orders() {
        // a read-only view, so callers cannot change our list
        return Collections.unmodifiableList(orders);
    }

    public double revenue() {
        double sum = 0;
        for (Order order : orders) {
            sum += order.getTotal();
        }
        return sum;
    }

    public List<Order> ordersFrom(String restaurantName) {
        List<Order> result = new ArrayList<>();
        for (Order order : orders) {
            if (order.getRestaurant().equals(restaurantName)) {
                result.add(order);
            }
        }
        return result;
    }

    public void addRestaurant(Restaurant restaurant) {
        restaurants.put(restaurant.getId(), restaurant);
    }

    public Restaurant findRestaurant(long id) {
        // get returns null when the key is missing
        return restaurants.get(id);
    }
}
