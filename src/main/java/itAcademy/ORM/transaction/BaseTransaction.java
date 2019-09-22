package itAcademy.ORM.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public class BaseTransaction implements Transation {

    protected Connection connection;

    public BaseTransaction(Connection connection) {
        this.connection = connection;
    }

    public BaseTransaction(Connection connection, boolean autoCommit) {
        this.connection = connection;
        try {
            if (connection.getAutoCommit() != autoCommit) {
                connection.setAutoCommit(autoCommit);
            }
        } catch (SQLException e) {
            throw new TransactionException("Set auto commit error: " + e.getMessage(), e);
        }
    }

    @Override
    public Connection open() throws TransactionException {
        return connection;
    }

    @Override
    public void commit() throws TransactionException {
        try {
            if (!connection.getAutoCommit()) {
                connection.commit();
            }
        } catch (SQLException e) {
            new TransactionException("Commit error: " + e.getMessage(), e);
        }
    }

    @Override
    public void rollback() throws TransactionException {
        try {
            if (!connection.getAutoCommit()) {
                connection.rollback();
            }
        } catch (SQLException e) {
            throw new TransactionException("Rollback error: " + e.getMessage(), e);
        }
    }

    @Override
    public void close() throws TransactionException {
        try {
            if (!connection.getAutoCommit()) {
                connection.setAutoCommit(true);
            }
            if (connection != null && !connection.isClosed())
                connection.close();
        } catch (SQLException e) {
            throw new TransactionException("Error closing SQL connection: " + e.getMessage(), e);
        }
    }
}
