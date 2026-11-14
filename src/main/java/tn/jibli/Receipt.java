package tn.jibli;

public class Receipt {

    public static double withTip(double total, double tip) {
        return total + tip;
    }

    public static void tryToChange(double total) {
        // total is a copy of the caller's value, so this changes nothing outside
        total = 0;
    }

    public static void applyDiscountInPlace(double[] prices, int percent) {
        // the array parameter points to the caller's array, so the elements really change
        for (int i = 0; i < prices.length; i++) {
            prices[i] = prices[i] * (100 - percent) / 100.0;
        }
    }
}
