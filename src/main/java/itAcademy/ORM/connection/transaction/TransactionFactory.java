package itAcademy.ORM.connection.transaction;

import java.sql.Connection;

public interface TransactionFactory {

    public BaseTransaction create(Connection connection, boolean autoCommit);

}
