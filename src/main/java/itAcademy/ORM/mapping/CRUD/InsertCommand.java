package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.reflection.ReflectionException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class InsertCommand implements Command {

    private setCRUDOperations crudOperations = new setCRUDOperations();

    @Override
    public void execute() throws IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException {
        crudOperations.insert();
    }
}
