package tn.jibli.basics;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tn.jibli.model.Delivery;
import tn.jibli.model.ExpressDelivery;

class Step20AnnotationsTest {

    @Test
    @DisplayName("Experimental is an annotation kept at runtime, for types only")
    void annotationDeclaration() throws Exception {
        Class<?> type = Class.forName("tn.jibli.annotation.Experimental");
        assertThat(type.isAnnotation()).as("Experimental should be declared with @interface").isTrue();

        Retention retention = type.getAnnotation(Retention.class);
        assertThat(retention).as("@Retention is missing").isNotNull();
        assertThat(retention.value()).as("retention policy").isEqualTo(RetentionPolicy.RUNTIME);

        Target target = type.getAnnotation(Target.class);
        assertThat(target).as("@Target is missing").isNotNull();
        assertThat(target.value()).as("targets").containsExactly(ElementType.TYPE);
    }

    @Test
    @DisplayName("value() defaults to an empty text")
    void defaultValue() throws Exception {
        Class<?> type = Class.forName("tn.jibli.annotation.Experimental");
        assertThat(type.getDeclaredMethod("value").getDefaultValue()).isEqualTo("");
    }

    @Test
    @DisplayName("the reader finds the note on ExpressDelivery")
    void noteOnExpress() {
        assertThat(AnnotationReader.experimentalNote(ExpressDelivery.class))
                .isEqualTo(Optional.of("Launching in Sfax first"));
    }

    @Test
    @DisplayName("the reader finds nothing on a class without the annotation")
    void noNoteOnDelivery() {
        assertThat(AnnotationReader.experimentalNote(Delivery.class)).isEmpty();
    }
}
