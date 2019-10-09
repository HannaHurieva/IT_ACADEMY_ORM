package itAcademy.ORM.connection.transaction;

import java.sql.Connection;

public class BaseTransactionFactory implements TransactionFactory {

    public BaseTransactionFactory(Connection connection, boolean autoCommit) {
    }

    @Override
    public BaseTransaction create(Connection connection, boolean autoCommit) {
        return new BaseTransaction(connection, autoCommit);
    }

}
