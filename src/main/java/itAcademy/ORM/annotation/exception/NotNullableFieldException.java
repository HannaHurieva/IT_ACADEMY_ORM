package itAcademy.ORM.annotation.exception;

@SuppressWarnings("serial")
public class NotNullableFieldException extends MappingException {

    public NotNullableFieldException(String type, String field) {
        super("Trying to save instance with a null value on a @NotNull (not nullable) field: `%s` (%s)", field, type);
    }

}
