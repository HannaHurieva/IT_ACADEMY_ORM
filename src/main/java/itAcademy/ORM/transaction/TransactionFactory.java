package itAcademy.ORM.transaction;

import java.sql.Connection;

public interface TransactionFactory {

    public itAcademy.ORM.transaction.BaseTransaction create(Connection connection, boolean autoCommit);

}
