package itAcademy.ORM.mapper.exceptions;

@SuppressWarnings("serial")
public class FieldNotFoundException extends MappingException {
    public FieldNotFoundException(String type, String field){
        super("Could not find property (field) `%s` in %s.", field, type);
    }
}
