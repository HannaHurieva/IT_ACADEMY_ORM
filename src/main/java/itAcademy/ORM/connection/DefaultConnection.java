package itAcademy.ORM.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static itAcademy.ORM.connection.util.JdbcResource.*;

public class DefaultConnection {
    /**
     * Gets a <code>Connection</code> object through JDBC driver.
     *
     * @return New <code>Connection</code>.
     */
    public Connection getConnection() {
        try {
            Class.forName(JDBC_DRIVER);
            return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Closes an opened <code>Connection</code>.
     *
     * @param connection
     */
    public void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
