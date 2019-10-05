package itAcademy.ORM.sql.exceptions;

import itAcademy.ORM.mapper.exceptions.MappingException;

@SuppressWarnings("serial")
public class DuplicateSubclauseException extends MappingException {
    public DuplicateSubclauseException(String t) {
        super("The following subclause is already existing on this query, cannot duplicate: %s", t);
    }
}
