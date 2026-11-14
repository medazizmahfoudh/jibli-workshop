package tn.jibli;

public class WalletPayment implements Payment {

    public String pay(double amount) {
        return "Paid " + amount + " TND from wallet";
    }
}
