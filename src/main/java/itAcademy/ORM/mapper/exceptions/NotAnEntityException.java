package itAcademy.ORM.mapper.exceptions;

@SuppressWarnings("serial")
public class NotAnEntityException extends MappingException {

    public NotAnEntityException(String name){
        super("The following class is not eligible to be an entity. Use @Entity annotation: %s", name);
    }
}
