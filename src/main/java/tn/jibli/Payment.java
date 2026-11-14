package tn.jibli;

// a contract: anything that can pay must offer this method
public interface Payment {

    String pay(double amount);
}
