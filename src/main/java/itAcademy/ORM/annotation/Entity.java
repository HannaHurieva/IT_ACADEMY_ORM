package itAcademy.ORM.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Required to make a class persistable entity. Use this
 * on the classes that you want to make entities.
 */
@Retention(RUNTIME)
@Target(TYPE) // works only on class declarations
public @interface Entity {
    String table() default "";
}
