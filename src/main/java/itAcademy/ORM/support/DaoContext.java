package itAcademy.ORM.support;

import javax.sql.DataSource;

public interface DaoContext {

    public void setDataSource(DataSource dataSource);

    public DataSource getDataSource();

}
