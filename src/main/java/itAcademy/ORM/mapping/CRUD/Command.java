package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.mapping.exception.CRUDException;

public interface Command {

    void execute(CRUDOperations crudOperations) throws CRUDException;

}
