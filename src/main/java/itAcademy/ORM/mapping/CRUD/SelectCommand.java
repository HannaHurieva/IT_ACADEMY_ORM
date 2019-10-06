package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.mapping.exception.CRUDException;

public class SelectCommand implements Command {

    public void execute(CRUDOperations crudOperations) throws CRUDException {
        try {
            crudOperations.selectAll();
        } catch (Exception e) {
            throw new CRUDException("Select exception");
        }
    }
}
