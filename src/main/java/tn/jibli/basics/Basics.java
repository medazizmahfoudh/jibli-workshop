package tn.jibli.basics;

public class Basics {

    public static double subtotal(int quantity, double unitPrice) {
        // int * double is promoted to double
        return quantity * unitPrice;
    }

    public static String describeOrder(String product, int quantity, double unitPrice) {
        double sub = subtotal(quantity, unitPrice);
        return quantity + " x " + product + " = " + sub + " TND";
    }
}
