package itAcademy.ORM.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target(FIELD)
public @interface ForeignKey {
    /**
     * Name of the foreign key.  Used in OneToMany, ManyToOne, and OneToOne
     * relationships.  Used for the owning side in ManyToMany relationships
     */
    String name();
}
