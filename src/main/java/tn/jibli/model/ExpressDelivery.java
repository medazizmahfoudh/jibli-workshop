package tn.jibli.model;

import tn.jibli.annotation.Experimental;

// extends: an ExpressDelivery is a Delivery with its own fee
@Experimental("Launching in Sfax first")
public class ExpressDelivery extends Delivery {

    @Override
    public double fee() {
        return 7.0;
    }
}
