package tn.jibli;

public class Order {

    // private: only code inside Order can touch these directly
    private String restaurant;
    private String product;
    private double total;

    // package-private on purpose, to compare it with the other levels
    String note;

    // protected: this class, its package and its subclasses
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
