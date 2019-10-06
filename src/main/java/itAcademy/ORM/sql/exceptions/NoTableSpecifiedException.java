package itAcademy.ORM.sql.exceptions;

@SuppressWarnings("serial")
public class NoTableSpecifiedException extends MappingException {
    public NoTableSpecifiedException() {
        super("You should specify a table before preparing this query.");
    }
}
