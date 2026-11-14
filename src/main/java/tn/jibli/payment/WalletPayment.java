package tn.jibli.payment;

public class WalletPayment implements Payment {

    @Override
    public String pay(double amount) {
        return "Paid " + amount + " TND from wallet";
    }
}
