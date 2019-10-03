package itAcademy.ORM.sql;

import itAcademy.ORM.reflection.ReflectionException;
import itAcademy.ORM.transaction.TransactionException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudOperations {

    Object insert(Object object) throws TransactionException, IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException, Exception;

    int delete(Object object) throws TransactionException, IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException, Exception;

    Object update(Object object) throws TransactionException, IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException;

    ArrayList<Object> findAll(Class classObject) throws TransactionException, IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException, InstantiationException, IOException, Exception;
}
