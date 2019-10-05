package itAcademy.ORM.transaction;

import java.sql.Connection;

public class BaseTransactionFactory implements itAcademy.ORM.transaction.TransactionFactory {

    public BaseTransactionFactory(Connection connection, boolean autoCommit) {
    }

    @Override
    public itAcademy.ORM.transaction.BaseTransaction create(Connection connection, boolean autoCommit) {
        return new itAcademy.ORM.transaction.BaseTransaction(connection, autoCommit);
    }

}
