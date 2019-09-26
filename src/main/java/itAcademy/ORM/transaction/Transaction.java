package itAcademy.ORM.transaction;

import itAcademy.ORM.reflection.ReflectionException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Transaction {

    Connection open() throws TransactionException;

    void commit() throws TransactionException;

    void rollback() throws TransactionException;

    void close() throws TransactionException;


    Object insert(Object object) throws TransactionException, IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException;

    int delete(Object object) throws TransactionException, IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException;

    Object update(Object object) throws TransactionException, IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException;

    ArrayList<Object> findAll(Class classObject) throws TransactionException, IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException, InstantiationException, IOException;
}
