package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step17PackagesTest {

    private void assertExists(String className) {
        try {
            Class.forName(className);
        } catch (ClassNotFoundException e) {
            throw new AssertionError(className + " was not found, is it in the right package?");
        }
    }

    @Test
    @DisplayName("tn.jibli.basics holds the small exercises")
    void basicsPackage() {
        for (String name : new String[] {"Greeter", "Basics", "Calc", "DeliveryFee", "Cart", "Receipt",
                "MemoryLab", "Recursion"}) {
            assertExists("tn.jibli.basics." + name);
        }
    }

    @Test
    @DisplayName("tn.jibli.model holds the order and the deliveries")
    void modelPackage() {
        for (String name : new String[] {"Order", "Delivery", "ExpressDelivery", "PickupDelivery"}) {
            assertExists("tn.jibli.model." + name);
        }
    }

    @Test
    @DisplayName("tn.jibli.payment holds the payments and the checkout")
    void paymentPackage() {
        for (String name : new String[] {"Payment", "CardPayment", "CashPayment", "WalletPayment", "Checkout"}) {
            assertExists("tn.jibli.payment." + name);
        }
    }

    @Test
    @DisplayName("Main stays in tn.jibli")
    void mainStays() {
        assertExists("tn.jibli.Main");
        assertThat(Main.class.getPackageName()).isEqualTo("tn.jibli");
    }
}
