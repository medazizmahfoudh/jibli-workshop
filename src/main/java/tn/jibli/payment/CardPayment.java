package tn.jibli.payment;

public class CardPayment implements Payment {

    public String pay(double amount) {
        return "Paid " + amount + " TND by card";
    }
}
