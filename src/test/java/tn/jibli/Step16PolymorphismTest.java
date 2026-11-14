package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step16PolymorphismTest {

    @Test
    @DisplayName("pay works with any Payment")
    void payWithAnyPayment() {
        assertThat(Checkout.pay(new CardPayment(), 25.0)).isEqualTo("Paid 25.0 TND by card");
        assertThat(Checkout.pay(new CashPayment(), 25.0)).isEqualTo("Paid 25.0 TND in cash");
    }

    @Test
    @DisplayName("WalletPayment returns its receipt")
    void wallet() {
        assertThat(new WalletPayment().pay(25.0)).isEqualTo("Paid 25.0 TND from wallet");
    }

    @Test
    @DisplayName("payAll handles a mixed list and keeps the order")
    void payAll() {
        List<Payment> payments = List.of(new CardPayment(), new CashPayment(), new WalletPayment());
        assertThat(Checkout.payAll(payments, 25.0)).as("receipts").containsExactly(
                "Paid 25.0 TND by card",
                "Paid 25.0 TND in cash",
                "Paid 25.0 TND from wallet");
    }

    @Test
    @DisplayName("totalFees adds the fee of each kind of delivery")
    void totalFees() {
        List<Delivery> deliveries = List.of(new Delivery(), new ExpressDelivery(), new PickupDelivery());
        assertThat(Checkout.totalFees(deliveries)).as("4.0 + 7.0 + 0.0").isEqualTo(11.0);
        assertThat(Checkout.totalFees(List.of())).isEqualTo(0.0);
    }
}
