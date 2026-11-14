package tn.jibli.payment;

public class CashPayment implements Payment {

    @Override
    public String pay(double amount) {
        return "Paid " + amount + " TND in cash";
    }
}
