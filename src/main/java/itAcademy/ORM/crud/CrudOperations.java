package itAcademy.ORM.crud;

import itAcademy.ORM.connection.transaction.TransactionException;
import itAcademy.ORM.reflection.ReflectionException;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudOperations {

    Object insert(Object object) throws Exception;

    int delete(Object object) throws  Exception;

    Object update(Object object) throws TransactionException, IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException;

    ArrayList<Object> findAll(Class classObject) throws Exception;
}
