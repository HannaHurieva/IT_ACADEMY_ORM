package itAcademy.ORM.annotations;


import itAcademy.ORM.mapping.Table;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
public @interface OneToOne {

    String fieldName();

    String tableName();

    String mappedBy() default "";

    boolean autoIncremental() default false;
}
