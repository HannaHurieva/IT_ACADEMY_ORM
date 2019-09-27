package itAcademy.ORM.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Ensures that this column is not null while saving
 * throws {@link itAcademy.ORM.annotation.exception.NotNullableFieldException} before saving
 * if this column of the instance is null on insert and
 * update operations.
 */
 @Retention(RUNTIME)
@Target(FIELD) // works only on class fields
public @interface NotNull {
}
