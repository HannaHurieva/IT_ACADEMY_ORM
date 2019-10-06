package itAcademy.ORM.sql.exceptions;

@SuppressWarnings("serial")
public class DuplicateSubclauseException extends MappingException {
    public DuplicateSubclauseException(String t) {
        super("The following subclause is already existing on this query, cannot duplicate: %s", t);
    }
}
