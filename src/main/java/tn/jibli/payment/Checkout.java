package tn.jibli.payment;

import java.util.ArrayList;
import java.util.List;
import tn.jibli.model.Delivery;

public class Checkout {

    public static String pay(Payment payment, double amount) {
        // we only know it is a Payment, the real class decides what happens
        return payment.pay(amount);
    }

    public static List<String> payAll(List<Payment> payments, double amount) {
        List<String> receipts = new ArrayList<>();
        for (Payment payment : payments) {
            receipts.add(payment.pay(amount));
        }
        return receipts;
    }

    public static double totalFees(List<Delivery> deliveries) {
        double total = 0;
        for (Delivery delivery : deliveries) {
            total = total + delivery.fee();
        }
        return total;
    }
}
