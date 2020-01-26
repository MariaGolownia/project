package by.javatr.bicrent.dao.mysql;
import by.javatr.bicrent.dao.Dao;
import by.javatr.bicrent.dao.pool.ConnectionSQL;
import org.apache.logging.log4j.LogManager;

import java.sql.Connection;
import java.sql.SQLException;

final public class FactoryDaoSql {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    private static final FactoryDaoSql instance = new FactoryDaoSql();
    private Connection connection;

    public static FactoryDaoSql getInstance(){
        return instance;
    }

    private FactoryDaoSql(){
            if (this.connection == null) {
                ConnectionSQL connectionSQL = new ConnectionSQL();
                connection = connectionSQL.getConnectionToDB();
            }
    }


    public <Type extends Dao<?>> Type get (DaoSql entityDao) throws DaoException  {
        switch (entityDao) {
            case BicycleDao:
                return (Type) new BicycleDaoSql(connection);
            case CompanyDao:
                return (Type)new CompanyDaoSql(connection);
            case LocationDao:
                return (Type)new LocationDaoSql(connection);
            case PriceDao:
                return (Type)new PriceDaoSql(connection);
            case OrderDao:
                return (Type)new OrderDaoSql(connection);
            case UserDao:
                return (Type)new UserDaoSql(connection);
            case UserInfoDao:
                return (Type)new UserInfoDaoSql(connection);
            case VirtualCardDao:
                return (Type)new VirtualCardDaoSql(connection);
            default:
                throw new DaoException("Check the existence of the desired entity! " + entityDao);
        }
    }

    public void closeConnection () {
        try {
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
