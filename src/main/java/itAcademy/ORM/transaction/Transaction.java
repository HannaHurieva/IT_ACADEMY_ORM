package itAcademy.ORM.transaction;

import itAcademy.ORM.connection.ConnectionToDB;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Transaction {
    private static Logger LOG = Logger.getLogger(Transaction.class.getName());

    private Connection connection;
    private TransactionStatus status;

    public Transaction(Connection c) throws SQLException {
        connection = c;
        status = TransactionStatus.ACTIVE;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void commit() throws SQLException {
        try {
            connection.commit();
            status = TransactionStatus.COMMITTED;
            LOG.log(Level.INFO,"The transaction has been completed successfully");
            System.out.println(status);
        } finally {
            try {
                connection.setAutoCommit(false);
            } finally {
                connection.close();
            }
        }
    }

    public void rollback() throws SQLException {
        TransactionStatus status = getStatus();
        if (status == TransactionStatus.ROLLED_BACK || status == TransactionStatus.NOT_ACTIVE) {
            // Allow rollback() calls on completed transactions, just no-op.
            LOG.log(Level.WARNING,"rollback() called on an inactive transaction");
            return;
        }

        if (!status.canRollback()) {
            throw new TransactionException("Cannot rollback transaction in current status [" + status.name() + "]");
        }

        try {
            connection.rollback();
            status = TransactionStatus.ROLLED_BACK;
            LOG.log(Level.INFO,"The transaction has been rolled back");
            System.out.println(status);
        } finally {
            try {
                connection.setAutoCommit(false);
            } finally {
                connection.close();
            }
        }
    }

    // Активизация соединения с СУБД, если оно не активно.
    void reopen() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = ConnectionToDB.getConnection();
        }
    }

    // Закрытие
    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
            LOG.log(Level.INFO,"The connection has been closed.");
        } catch (SQLException e) {
            System.out.println("Error closing SQL connection!");
        }
    }
}
