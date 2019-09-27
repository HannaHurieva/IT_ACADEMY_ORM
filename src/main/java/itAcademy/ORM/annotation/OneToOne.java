package itAcademy.ORM.annotation;

import itAcademy.ORM.annotation.util.LoadingPolicy;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Place this annotation on columns to create relationship of 1:1 cardinality
 * between entities.
 */
@Retention(RUNTIME)
@Target(FIELD) // works only on class fields
public @interface OneToOne {
    String targetBindingField() default "";
    LoadingPolicy load() default LoadingPolicy.EAGER;
}
