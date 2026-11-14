package tn.jibli.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Step08AccessTest {

    @Test
    @DisplayName("the class is public")
    void classIsPublic() {
        assertThat(Modifier.isPublic(Order.class.getModifiers())).as("Order should be public").isTrue();
    }

    @Test
    @DisplayName("the data fields are private")
    void fieldsArePrivate() throws Exception {
        for (String name : new String[] {"restaurant", "product", "total"}) {
            int modifiers = Order.class.getDeclaredField(name).getModifiers();
            assertThat(Modifier.isPrivate(modifiers)).as("field " + name + " should be private").isTrue();
        }
    }

    @Test
    @DisplayName("note is package-private")
    void noteIsPackagePrivate() throws Exception {
        int modifiers = Order.class.getDeclaredField("note").getModifiers();
        boolean hasNoModifier = !Modifier.isPublic(modifiers) && !Modifier.isProtected(modifiers)
                && !Modifier.isPrivate(modifiers);
        assertThat(hasNoModifier).as("note should have no access modifier").isTrue();
    }

    @Test
    @DisplayName("audit is protected")
    void auditIsProtected() throws Exception {
        Method audit = Order.class.getDeclaredMethod("audit");
        assertThat(Modifier.isProtected(audit.getModifiers())).as("audit() should be protected").isTrue();
        assertThat(audit.getReturnType()).isEqualTo(String.class);
    }

    @Test
    @DisplayName("the getters are public")
    void gettersArePublic() throws Exception {
        assertThat(Modifier.isPublic(Order.class.getDeclaredMethod("getRestaurant").getModifiers())).isTrue();
        assertThat(Modifier.isPublic(Order.class.getDeclaredMethod("getProduct").getModifiers())).isTrue();
        assertThat(Modifier.isPublic(Order.class.getDeclaredMethod("getTotal").getModifiers())).isTrue();
    }
}
