package itAcademy.ORM.annotation;

import itAcademy.ORM.annotation.util.LoadingPolicy;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ManyToMany {

    /**
     * @return custom name for join table or empty string (do auto-binding)
     */
    public String joinTable() default "";

    /**
     * Default option is LAZY.
     *
     * @return loading policy of field
     */
    LoadingPolicy load() default LoadingPolicy.LAZY;
}
