package itAcademy.ORM.connection.connectionpool;

import itAcademy.ORM.connection.util.ObjectUtils;
import itAcademy.ORM.connection.util.ResourceLoader;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.Properties;

import static itAcademy.ORM.connection.util.JdbcResource.*;

public class DBCPDataSourceFactory implements DataSourceFactory {

    private static final String DEFAULT_PROPERTIES_FILE = "jdbc.properties";

    /**
     * The initial number of connections that are created when the pool is started.
     */
    private static final String DBCP_INITIAL_SIZE = "dbcp.initialSize";

    /**
     * The maximum number of active connections that can be allocated from this pool at the same time,
     * or non-positive for no limit.
     */
    private static final String DBCP_MAX_ACTIVE = "dbcp.maxActive";

    /**
     * The maximum number of connections that can remain idle in the pool,
     * without extra ones being released, or negative for no limit.
     */
    private static final String DBCP_MAX_IDLE = "dbcp.maxIdle";

    /**
     * The minimum number of active connections that can remain idle in the pool,
     * without extra ones being created, or 0 to create none.
     */
    private static final String DBCP_MIN_IDLE = "dbcp.minIdle";

    /**
     * The maximum number of open statements that can be allocated from the statement pool at the same time,
     * or non-positive for no limit.
     */
    private static final String DBCP_MAX_OPEN_PREPARED_STATEMENTS = "dbcp.maxOpenPreparedStatements";

    /**
     * The maximum number of milliseconds that the pool will wait (when there are no available connections)
     * for a connection to be returned before throwing an exception, or -1 to wait indefinitely.
     */
    private static final String DBCP_MAX_WAIT = "dbcp.maxWait";

    private DataSource dataSource;

    public DBCPDataSourceFactory(Properties properties) {
        try {
            this.dataSource = initialize(properties);
        } catch (Exception e) {
            throw new RuntimeException("Initializing " + DBCPDataSourceFactory.class.getName() + " fail: " + e.getMessage(), e);
        }
    }

    public DBCPDataSourceFactory() {
        String resourceName = DEFAULT_PROPERTIES_FILE;
        try {
            Properties properties = ResourceLoader.loadProperties(resourceName);
            this.dataSource = initialize(properties);
        } catch (Exception e) {
            throw new RuntimeException("Initializing " + DBCPDataSourceFactory.class.getName() + " fail: " + e.getMessage(), e);
        }
    }

    @Override
    public DataSource getDataSource() {
        return dataSource;
    }

    private BasicDataSource initialize(Properties properties) {
        ObjectUtils.notNull(properties, "properties");
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(properties.getProperty(JDBC_DRIVER));
        basicDataSource.setUrl(properties.getProperty(JDBC_URL));
        basicDataSource.setUsername(properties.getProperty(JDBC_USERNAME));
        basicDataSource.setPassword(properties.getProperty(JDBC_PASSWORD));

        if (properties.containsKey(DBCP_INITIAL_SIZE)) {
            basicDataSource.setInitialSize(Integer.parseInt(properties.getProperty(DBCP_INITIAL_SIZE)));
        }
        if (properties.containsKey(DBCP_MAX_ACTIVE)) {
            basicDataSource.setMaxActive(Integer.parseInt(properties.getProperty(DBCP_MAX_ACTIVE)));
        }
        if (properties.containsKey(DBCP_MAX_IDLE)) {
            basicDataSource.setMaxIdle(Integer.parseInt(properties.getProperty(DBCP_MAX_IDLE)));
        }
        if (properties.containsKey(DBCP_MIN_IDLE)) {
            basicDataSource.setMinIdle(Integer.parseInt(properties.getProperty(DBCP_MIN_IDLE)));
        }
        if (properties.containsKey(DBCP_MAX_OPEN_PREPARED_STATEMENTS)) {
            basicDataSource.setMaxOpenPreparedStatements(Integer.parseInt(properties.getProperty(DBCP_MAX_OPEN_PREPARED_STATEMENTS)));
        }
        if (properties.containsKey(DBCP_MAX_WAIT)) {
            basicDataSource.setMaxWait(Long.parseLong(properties.getProperty(DBCP_MAX_WAIT)));
        }

        return basicDataSource;
    }
}
