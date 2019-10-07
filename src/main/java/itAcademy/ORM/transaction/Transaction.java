package itAcademy.ORM.transaction;

import itAcademy.ORM.reflection.ReflectionException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface Transaction {

    Connection open() throws itAcademy.ORM.transaction.TransactionException;

    void commit() throws itAcademy.ORM.transaction.TransactionException;

    void rollback() throws itAcademy.ORM.transaction.TransactionException;

    void close() throws itAcademy.ORM.transaction.TransactionException;


    Object insert(Object object, String tableName) throws itAcademy.ORM.transaction.TransactionException, IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException;

    int delete(Object object, int wherePrimaryKeyValue, String tableName) throws itAcademy.ORM.transaction.TransactionException, IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException;

    Object update(Object object, int wherePrimaryKeyFieldValue, String tableName) throws itAcademy.ORM.transaction.TransactionException, IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException;

    ArrayList<Object> findAll(Object object) throws itAcademy.ORM.transaction.TransactionException, IllegalAccessException, SQLException, ReflectionException, NoSuchMethodException, InvocationTargetException, InstantiationException, IOException;
}
