package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.mapping.exception.CRUDException;

public class DeleteCommand implements Command {

    @Override
    public void execute(CRUDOperations crudOperations) throws CRUDException {
        try {
            crudOperations.delete();
        } catch (Exception e) {
            throw new CRUDException("Delete exception");
        }
    }
}
