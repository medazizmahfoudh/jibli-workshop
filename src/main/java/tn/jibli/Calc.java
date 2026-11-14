package tn.jibli;

public class Calc {

    public static double total(double subtotal, double fee) {
        return subtotal + fee;
    }

    public static double applyDiscount(double subtotal, int percent) {
        // subtotal is a double, so the whole expression stays in double
        return subtotal - subtotal * percent / 100;
    }

    public static int dinars(double amount) {
        // a cast cuts the decimals, it does not round
        return (int) amount;
    }

    public static double averageItems(int totalItems, int orders) {
        // cast one side first, otherwise 7 / 2 is 3
        return (double) totalItems / orders;
    }

    public static boolean isLoyaltyOrder(int orderNumber) {
        // % is the remainder of the division
        return orderNumber % 5 == 0;
    }
}
