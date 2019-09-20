package itAcademy.ORM.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD})
@Retention(RUNTIME)

public @interface Index {
    /**
     * The index name.
     */
    String name();

    /**
     * The column(s) that are indexed.
     */
    String[] columnNames() default {};
}
