package itAcademy.ORM.annotation;

import itAcademy.ORM.annotation.util.LoadingPolicy;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD) // works only on class fields
public @interface OneToMany {

    /**
     * Optionally, to fill target instance's related field
     * with this value.
     */
    String targetBindingField() default "";

    /**
     * Default option is LAZY since it works pretty well
     * when {@link ManyToOne} is EAGER (default).
     *
     * @return loading policy of field
     */
    LoadingPolicy load() default LoadingPolicy.LAZY;
}
