package itAcademy.ORM.connection;

import itAcademy.ORM.connection.util.ObjectUtils;
import itAcademy.ORM.connection.util.ResourceLoader;
import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.util.Properties;

import static itAcademy.ORM.connection.util.JdbcResource.*;

public class BaseDataSourceFactory implements DataSourceFactory {

    private static final String DEFAULE_PROPERTIES_FILE = "jdbc.properties";

    private DataSource dataSource;

    public BaseDataSourceFactory(Properties properties) {
        try {
            this.dataSource = initialize(properties);
        } catch (Exception e) {
            throw new RuntimeException("Initializing " + BaseDataSourceFactory.class.getName() + " fail: " + e.getMessage(), e);
        }
    }

    public BaseDataSourceFactory() {
        String resourceName = DEFAULE_PROPERTIES_FILE;
        try {
            Properties properties = ResourceLoader.loadProperties(resourceName);
            this.dataSource = initialize(properties);
        } catch (Exception e) {
            throw new RuntimeException("Initializing " + BaseDataSourceFactory.class.getName() + " fail: " + e.getMessage(), e);
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
        return basicDataSource;
    }
}
