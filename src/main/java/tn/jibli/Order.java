package tn.jibli;

public class Order {

    private String restaurant;
    private String product;
    private double total;

    String note;

    public Order(String restaurant, String product, double total) {
        // go through the setters so the same checks run for new objects too
        setRestaurant(restaurant);
        setProduct(product);
        setTotal(total);
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
