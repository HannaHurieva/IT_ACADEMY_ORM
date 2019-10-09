package itAcademy.ORM;

import itAcademy.ORM.connection.connectionpool.DBCPDataSourceFactory;
import itAcademy.ORM.connection.support.DaoSupport;
import itAcademy.ORM.connection.transaction.BaseTransaction;

import java.sql.Connection;

public class Demo {

    public static void main(String[] args) {
        DBCPDataSourceFactory dataSource = new DBCPDataSourceFactory();
        Connection connection = DaoSupport.getConnection(dataSource.getDataSource());
        BaseTransaction transaction = new BaseTransaction(connection);
        transaction.open();
        System.out.println("Some operations will have to be here ...");
        transaction.commit();
        transaction.close();
    }
}
