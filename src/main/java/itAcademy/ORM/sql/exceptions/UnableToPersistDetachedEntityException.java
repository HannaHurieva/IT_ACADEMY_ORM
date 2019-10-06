package itAcademy.ORM.sql.exceptions;

@SuppressWarnings("serial")
public class UnableToPersistDetachedEntityException extends MappingException {

    public UnableToPersistDetachedEntityException(String type){
        super("Unable to persist detached (non-persistent) entity `%s`. Save the entity with insert() or update() before using it on other queries.", type);
    }
}
