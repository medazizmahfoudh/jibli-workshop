package tn.jibli.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// RUNTIME: the label is still there when the program runs, so reflection can read it
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Experimental {

    String value() default "";
}
