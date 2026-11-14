package tn.jibli;

public class Order {

    // TODO(step-13): this must be a constant, shared by all orders, with the value 4.0
    public static double DELIVERY_FEE = 0.0;

    // TODO(step-13): the number of orders created so far, shared by all orders
    private static int createdCount = 0;

    private String restaurant;
    private String product;
    private double total;

    String note;

    public Order(String restaurant, String product, double total) {
        setRestaurant(restaurant);
        setProduct(product);
        setTotal(total);
        // TODO(step-13): count this order
    }

    public static int getCreatedCount() {
        // TODO(step-13)
        throw new UnsupportedOperationException("TODO(step-13)");
    }

    static void resetCounter() {
        // TODO(step-13)
        throw new UnsupportedOperationException("TODO(step-13)");
    }

    public double totalWithFee() {
        // TODO(step-13): total plus the delivery fee
        throw new UnsupportedOperationException("TODO(step-13)");
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
