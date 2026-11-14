package tn.jibli;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step15InterfacesTest {

    @Test
    @DisplayName("Payment is an interface")
    void paymentIsAnInterface() throws Exception {
        Class<?> payment = Class.forName(getClass().getPackageName() + ".Payment");
        assertThat(payment.isInterface()).as("Payment should be an interface").isTrue();
    }

    @Test
    @DisplayName("card and cash implement Payment")
    void implementationsAreAPayment() throws Exception {
        Class<?> payment = Class.forName(getClass().getPackageName() + ".Payment");
        assertThat(payment.isInstance(new CardPayment())).as("CardPayment implements Payment").isTrue();
        assertThat(payment.isInstance(new CashPayment())).as("CashPayment implements Payment").isTrue();
    }

    @Test
    @DisplayName("card payment returns its receipt")
    void card() {
        assertThat(new CardPayment().pay(25.0)).isEqualTo("Paid 25.0 TND by card");
        assertThat(new CardPayment().pay(12.5)).isEqualTo("Paid 12.5 TND by card");
    }

    @Test
    @DisplayName("cash payment returns its receipt")
    void cash() {
        assertThat(new CashPayment().pay(25.0)).isEqualTo("Paid 25.0 TND in cash");
    }
}
