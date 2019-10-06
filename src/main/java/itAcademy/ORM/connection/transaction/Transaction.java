package itAcademy.ORM.connection.transaction;

import java.sql.Connection;

public interface Transaction {

    Connection open() throws TransactionException;

    void commit() throws TransactionException;

    void rollback() throws TransactionException;

    void close() throws TransactionException;



}
