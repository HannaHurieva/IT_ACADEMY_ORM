package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.reflection.ReflectionException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface Command {
//todo create your own runtime exception and wrap others with it
    void execute(CRUDOperations crudOperations) throws IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException, IOException, InstantiationException;

}
