package itAcademy.ORM.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction {
    private Connection connection;
    private Class idClass = null;

    public Transaction(Connection c, Class id) throws SQLException {
        idClass = id;
        connection = c;
        c.setAutoCommit(false);

    }

    public void commit() throws SQLException {
        try {
            connection.commit();
        } finally {
            try {
                connection.setAutoCommit(false);
            } finally {
                connection.close();
            }
        }
    }

    public void rollback() throws SQLException {
        try {
            connection.rollback();
        } finally {
            try {
                connection.setAutoCommit(false);
            } finally {
                connection.close();
            }
        }
    }
}
