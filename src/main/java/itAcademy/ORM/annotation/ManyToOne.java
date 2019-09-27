package itAcademy.ORM.annotation;

import itAcademy.ORM.annotation.util.LoadingPolicy;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Place this annotation on columns to create relationship of *:1 cardinality
 * between entities. It should have type <code>List<?></code>
 */
@Retention(RUNTIME)
@Target(FIELD) // works only on class fields
public @interface ManyToOne {

    /**
     * Loading policy of this field. Lazy loading is NOT recommended because it
     * remains <code>null</code> until it is not loaded manually.
     *
     * @see LoadingPolicy
     */
    LoadingPolicy load() default LoadingPolicy.EAGER;
}
