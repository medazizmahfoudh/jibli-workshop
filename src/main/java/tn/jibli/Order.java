package tn.jibli;

public class Order {

    // static: one copy for the whole class. final: it can never change.
    public static final double DELIVERY_FEE = 4.0;

    private static int createdCount = 0;

    private String restaurant;
    private String product;
    private double total;

    String note;

    public Order(String restaurant, String product, double total) {
        setRestaurant(restaurant);
        setProduct(product);
        setTotal(total);
        // only reached when the values were accepted
        createdCount++;
    }

    public static int getCreatedCount() {
        return createdCount;
    }

    // used by tests to start from zero
    static void resetCounter() {
        createdCount = 0;
    }

    public double totalWithFee() {
        return total + DELIVERY_FEE;
    }

    protected String audit() {
        return "audit: " + restaurant + "/" + product + "/" + total;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(String restaurant) {
        if (restaurant == null || restaurant.isBlank()) {
            throw new IllegalArgumentException("restaurant must not be blank");
        }
        this.restaurant = restaurant;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        if (product == null || product.isBlank()) {
            throw new IllegalArgumentException("product must not be blank");
        }
        this.product = product;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        if (total < 0) {
            throw new IllegalArgumentException("total must not be negative");
        }
        this.total = total;
    }
}
