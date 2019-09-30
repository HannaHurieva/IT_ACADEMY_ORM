package itAcademy.ORM.mapping.CRUD;

import itAcademy.ORM.reflection.ReflectionException;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface Command {
    void execute() throws IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException;

}
