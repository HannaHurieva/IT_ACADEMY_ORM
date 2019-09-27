package itAcademy.ORM.support;

import itAcademy.ORM.connection.util.ObjectUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class DaoSupport implements DaoContext {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    public static Connection getConnection(DataSource dataSource) throws DataAccessException {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

    public static void close(Connection connection) throws DataAccessException {
        if (ObjectUtils.isNotNull(connection)) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DataAccessException(e);
            }
        }
    }

}
