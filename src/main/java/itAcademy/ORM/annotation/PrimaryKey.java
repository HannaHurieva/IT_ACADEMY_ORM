package itAcademy.ORM.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Indicates that the field is a primary key for the relation. If more than one
 * fields are annotated as primary key in the table, then all of them compose a
 * single primary key. e.g. In relation <code>R(A, B, C)</code> if
 * <code>A</code> and <code>B</code> are annotated with this, then primary key
 * is <code>(A, B)</code> for the relation.
 */
@Retention(RUNTIME)
@Target(FIELD) // works only on class fields
public @interface PrimaryKey {
    /**
     * Indicates that the column is an auto increment field so that it will be
     * generated upon schema construction. Make sure that this field is an
     * augmentable (numeric) type which your DBMS can handle if you set this to
     * <code>true</code>. It is <code>false</code> by default.
     */
    boolean autoIncrement() default false;
}
