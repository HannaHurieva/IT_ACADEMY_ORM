package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.reflection.ReflectionException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class SelectCommand implements Command {

    @Override
    public void execute(CRUDOperations crudOperations) throws IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException, IOException, InstantiationException {
        crudOperations.selectAll();
    }
}
