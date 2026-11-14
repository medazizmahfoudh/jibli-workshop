package tn.jibli.payment;

public class CashPayment implements Payment {

    public String pay(double amount) {
        return "Paid " + amount + " TND in cash";
    }
}
