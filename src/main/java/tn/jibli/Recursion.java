package tn.jibli;

public class Recursion {

    public static int sumTo(int n) {
        if (n <= 0) {
            return 0;   // the stopping case
        }
        return n + sumTo(n - 1);
    }

    public static boolean overflows() {
        try {
            forever();
            return false;
        } catch (StackOverflowError e) {
            // each call added a frame on the stack until there was no room left
            return true;
        }
    }

    private static void forever() {
        forever();
    }
}
