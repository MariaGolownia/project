package by.javatr.bicrent.dao.pool;
import by.javatr.bicrent.entity.PersistentException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionSQL {
    public static final int MAX_CONNECTIONS_SIZE = 100;
    private static final String SERVER_NAME = "localhost";
    private static final String MY_DATA_BASE = "rental_bicycle_web";
    private static final String URL = "jdbc:mysql://" + SERVER_NAME + "/" + MY_DATA_BASE + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static final String USER_SQL_NAME = "root";
    private static final String USER_SQL_PASSWORD = "admin";

    public Connection getConnectionToDB () {
        // Регистрация драйвера
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connectionPool.init("java.sql.Driver",
                    URL, USER_SQL_NAME, USER_SQL_PASSWORD,
                    1,MAX_CONNECTIONS_SIZE, 10000);
            connection = connectionPool.getConnection();
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void connectToDB () {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection connection=null;
        try {
            connectionPool.init("java.sql.Driver",
                    URL, USER_SQL_NAME, USER_SQL_PASSWORD,
                    1,MAX_CONNECTIONS_SIZE, 10000);
            connection = connectionPool.getConnection();
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

}
