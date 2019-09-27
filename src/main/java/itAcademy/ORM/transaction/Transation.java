package itAcademy.ORM.transaction;

import java.sql.Connection;

public interface Transation {

    public Connection open() throws TransactionException;

    public void commit() throws TransactionException;

    public void rollback() throws TransactionException;

    public void close() throws TransactionException;
}
