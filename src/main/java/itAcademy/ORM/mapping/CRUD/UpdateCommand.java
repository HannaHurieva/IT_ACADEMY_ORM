package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.mapping.exception.CRUDException;

public class UpdateCommand implements Command {

    @Override
    public void execute(CRUDOperations crudOperations) throws CRUDException {
        try {
            crudOperations.update();
        } catch (Exception e) {
            throw new CRUDException("Update exception");
        }
    }
}
