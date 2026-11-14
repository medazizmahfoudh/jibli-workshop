package tn.jibli;

public class Order {

    private String restaurant;
    private String product;
    private double total;

    String note;

    // no return type, same name as the class
    public Order(String restaurant, String product, double total) {
        // this.restaurant is the field, restaurant is the parameter
        this.restaurant = restaurant;
        this.product = product;
        this.total = total;
    }

    protected String audit() {
        return "audit: " + restaurant + "/" + product + "/" + total;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public String getProduct() {
        return product;
    }

    public double getTotal() {
        return total;
    }
}
