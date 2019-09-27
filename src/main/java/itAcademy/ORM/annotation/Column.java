package itAcademy.ORM.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Use of this annotation is not mandatory but
 * required when specifying custom dynamic data type
 * or a custom name for the column.
 */
@Retention(RUNTIME)
@Target(FIELD) // works only on class fields
public @interface Column {
    String columnName();
}
