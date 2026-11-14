package tn.jibli.payment;

// a contract: anything that can pay must offer this method
public interface Payment {

    String pay(double amount);
}
