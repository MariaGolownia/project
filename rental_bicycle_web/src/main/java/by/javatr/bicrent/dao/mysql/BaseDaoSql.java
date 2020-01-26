package by.javatr.bicrent.dao.mysql;
import java.sql.Connection;

public class BaseDaoSql {
    protected Connection connection;

    public BaseDaoSql(Connection connection) {
        this.connection = connection;
    }

    protected BaseDaoSql() {
    }
}
