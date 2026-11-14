package tn.jibli.payment;

public class CardPayment implements Payment {

    @Override
    public String pay(double amount) {
        return "Paid " + amount + " TND by card";
    }
}
