package itAcademy.ORM.sql.exceptions;

public class MappingException extends RuntimeException {
    public MappingException(String message, Object... parameters) {
        super(String.format(message, parameters));
    }
}
