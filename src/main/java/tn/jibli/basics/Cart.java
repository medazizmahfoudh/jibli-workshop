package tn.jibli.basics;

public class Cart {

    public static double sum(double[] prices) {
        double total = 0;
        for (int i = 0; i < prices.length; i++) {
            total = total + prices[i];
        }
        return total;
    }

    public static double mostExpensive(double[] prices) {
        double max = 0.0;
        // enhanced for: no index, we just visit each element
        for (double price : prices) {
            if (price > max) {
                max = price;
            }
        }
        return max;
    }

    public static int indexOf(String[] names, String wanted) {
        int i = 0;
        while (i < names.length) {
            if (names[i].equals(wanted)) {
                return i;
            }
            i++;
        }
        return -1;
    }
}
