package tn.jibli.basics;

import tn.jibli.model.Order;

public class MemoryLab {

    public static Order copyOf(Order o) {
        // new puts a second object on the heap, with its own values
        return new Order(o.getRestaurant(), o.getProduct(), o.getTotal());
    }

    public static boolean sameObject(Order a, Order b) {
        // == on objects compares the references (the addresses)
        return a == b;
    }

    public static boolean sameValues(Order a, Order b) {
        // equals compares the characters of the strings
        return a.getRestaurant().equals(b.getRestaurant())
                && a.getProduct().equals(b.getProduct())
                && a.getTotal() == b.getTotal();
    }
}
