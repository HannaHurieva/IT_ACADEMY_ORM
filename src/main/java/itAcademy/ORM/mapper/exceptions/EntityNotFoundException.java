package itAcademy.ORM.mapper.exceptions;

@SuppressWarnings("serial")
public class EntityNotFoundException extends MappingException {
    public EntityNotFoundException(String type){
        super("The entity type %s could not be found in MappingSession context. Register this @Entity to MappingSession first.", type);
    }
}
