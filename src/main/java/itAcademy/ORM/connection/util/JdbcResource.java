package itAcademy.ORM.connection.util;

import java.util.Map;
import java.util.HashMap;

public class JdbcResource {

    public static final String JDBC_DRIVER = "jdbc.driverClassName";

    public static final String JDBC_URL = "jdbc.url";

    public static final String JDBC_USERNAME = "jdbc.username";

    public static final String JDBC_PASSWORD = "jdbc.password";

    private Map<String, Object> attributeMap = new HashMap<String, Object>();

    public void setAttribute(String key, Object value) {
        attributeMap.put(key, value);
    }

    public Object getAttribute(String key) {
        return attributeMap.get(key);
    }

    public void clear() {
        attributeMap.clear();
    }

}
