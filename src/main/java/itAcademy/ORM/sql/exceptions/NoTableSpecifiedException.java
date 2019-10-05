package itAcademy.ORM.sql.exceptions;

import itAcademy.ORM.mapper.exceptions.MappingException;

public class NoTableSpecifiedException extends MappingException {
    public NoTableSpecifiedException() {
        super("You should specify a table before preparing this query.");
    }
}
