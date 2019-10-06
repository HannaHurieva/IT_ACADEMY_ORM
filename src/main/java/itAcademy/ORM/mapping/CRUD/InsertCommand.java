package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.mapping.exception.CRUDException;

public class InsertCommand implements Command {

    @Override
    public void execute(CRUDOperations crudOperations) throws CRUDException {
        try {
            crudOperations.insert();
        } catch (Exception e) {
            throw new CRUDException("Insert exception");
        }
    }
}
