package itAcademy.ORM.connection.support;

import javax.sql.DataSource;

public interface DaoContext {

    public void setDataSource(DataSource dataSource);

    public DataSource getDataSource();

}
