package itAcademy.ORM.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD, TYPE})
@Retention(RUNTIME)
//@Deprecated
public @interface ForeignKey {

    String fieldName();

    String inverseName() default "";
}
