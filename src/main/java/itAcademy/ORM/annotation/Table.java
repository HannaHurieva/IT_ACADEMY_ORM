package itAcademy.ORM.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE})
@Retention(RUNTIME)

public @interface Table {
    /**
     * name of the targeted table.
     */
    String appliesTo();

    /**
     * Indexes.
     */
    Index[] indexes() default {};

    /**
     * Defines the Foreign Key name of a secondary table pointing back to the primary table.
     */
    ForeignKey foreignKey() default @ForeignKey(name = "");
}
