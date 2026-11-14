package tn.jibli.basics;

import java.util.Optional;
import tn.jibli.annotation.Experimental;

public class AnnotationReader {

    public static Optional<String> experimentalNote(Class<?> type) {
        // reflection: ask the class for its annotation
        Experimental experimental = type.getAnnotation(Experimental.class);
        if (experimental == null) {
            return Optional.empty();
        }
        return Optional.of(experimental.value());
    }
}
