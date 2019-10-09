package itAcademy.ORM.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OneToOne {
    String fieldName();
    String toTable();
    String toTableFieldName();
    boolean autoIncremental() default false;
}
