package tn.jibli.model;

// extends: an ExpressDelivery is a Delivery with its own fee
public class ExpressDelivery extends Delivery {

    public double fee() {
        return 7.0;
    }
}
