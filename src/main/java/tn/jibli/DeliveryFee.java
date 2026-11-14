package tn.jibli;

public class DeliveryFee {

    public static double fee(double subtotal, String mode) {
        switch (mode) {
            case "pickup":
                return 0.0;
            case "express":
                return 7.0;
            case "standard":
                if (subtotal >= 40.0) {
                    return 0.0;
                } else {
                    return 4.0;
                }
            default:
                throw new IllegalArgumentException("Unknown delivery mode: " + mode);
        }
    }
}
