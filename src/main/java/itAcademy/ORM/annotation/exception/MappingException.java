package itAcademy.ORM.annotation.exception;

public class MappingException extends RuntimeException {

    public MappingException(String message, Object... parameters){
        super(String.format(message, parameters));
    }

}
