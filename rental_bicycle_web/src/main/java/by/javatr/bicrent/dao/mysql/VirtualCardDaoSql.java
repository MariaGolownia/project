package by.javatr.bicrent.dao.mysql;
import by.javatr.bicrent.entity.PersistentException;
import by.javatr.bicrent.dao.VirtualCardDao;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.VirtualCard;
import by.javatr.bicrent.entity.en_um.Currency;
import org.apache.logging.log4j.LogManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VirtualCardDaoSql extends BaseDaoSql implements VirtualCardDao {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    private static final String SQL_SELECT_VIRTUAL_CARD_BY_ID =
            "SELECT `vitualrCard_name`, `vitualCard_user_id`, `vitualCard_balance`, `vitualCard_currency`" +
                    " FROM `vitualcard` WHERE `vitualCard_id` = ?";
    private static final String SQL_SELECT_VIRTUAL_CARD_BY_USER =
            "SELECT `vitualCard_id`,`vitualrCard_name`, `vitualCard_balance`, `vitualCard_currency`" +
                    " FROM `vitualcard` WHERE `vitualCard_user_id` = ?";
    private static final String SQL_VIRTUAL_CARD_UPDATE =
            "UPDATE `vitualcard` SET `vitualrCard_name` = ?, `vitualCard_balance` = ?" +
                    ", `vitualCard_currency` = ? WHERE `vitualCard_id` = ?";
    private static final String SQL_VIRTUAL_CARD_INSERT =
            "INSERT INTO `vitualcard` (`vitualrCard_name`, `vitualCard_user_id`, `vitualCard_balance`," +
                    " `vitualCard_currency`) VALUES (?, ?, ?, ?)";
    private static final String SQL_VIRTUAL_CARD_DELETE = "DELETE FROM `vitualcard` WHERE `vitualCard_id` = ?";

    public VirtualCardDaoSql(Connection connection) {
        this.connection = connection;
    }

    protected VirtualCardDaoSql() {
        super();
    }

    @Override
    public List<VirtualCard> readByUser(User user) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_VIRTUAL_CARD_BY_USER);
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();
            List<VirtualCard> virtualCards = new ArrayList<>();
            VirtualCard virtualCard = new VirtualCard();
            while (resultSet.next()) {
                virtualCard = new VirtualCard();
                virtualCard.setId(resultSet.getInt("vitualCard_id"));
                virtualCard.setName(resultSet.getString("vitualrCard_name"));
                virtualCard.setUser(user);
                virtualCard.setBalance(resultSet.getBigDecimal("vitualCard_balance"));
                virtualCard.setCurrency(Currency.getCurrency(resultSet.getString("vitualCard_currency")));
                virtualCards.add(virtualCard);
            }
            return virtualCards;
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public List<VirtualCard> readByUserId(Integer id) throws SQLException {
        List<VirtualCard> virtualCards = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_VIRTUAL_CARD_BY_USER);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            VirtualCard virtualCard = new VirtualCard();
            while (resultSet.next()) {
                virtualCard = new VirtualCard();
                virtualCard.setId(resultSet.getInt("vitualCard_id"));
                virtualCard.setName(resultSet.getString("vitualrCard_name"));
                UserDaoSql userDaoSql = null;
                userDaoSql = FactoryDaoSql.getInstance().get(DaoSql.UserDao);
                User user = userDaoSql.read(id);
                virtualCard.setUser(user);
                virtualCard.setBalance(resultSet.getBigDecimal("vitualCard_balance"));
                virtualCard.setCurrency(Currency.getCurrency(resultSet.getString("vitualCard_currency")));
                virtualCards.add(virtualCard);
            }
        } catch (DaoException e) {
            e.printStackTrace(); }
            try {
                resultSet.close();
            } catch (NullPointerException en) {
            }
            try {
                statement.close();
            } catch (NullPointerException ex) {
            }

            return virtualCards;
    }

    @Override
    public Integer create(VirtualCard virtualCard) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer idOfVirtualCard = null;
        try {
            statement = connection.prepareStatement(SQL_VIRTUAL_CARD_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, virtualCard.getName());
            statement.setInt(2, virtualCard.getUser().getId());
            statement.setBigDecimal(3, virtualCard.getBalance());
            statement.setString(4, virtualCard.getCurrency().getName());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                idOfVirtualCard = resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `users`");
                throw new PersistentException();
            }
        } catch(PersistentException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch(NullPointerException e) {}
            try {
                statement.close();
            } catch(NullPointerException e) {}
        }
        return idOfVirtualCard;
    }

    @Override
    public VirtualCard read(Integer id) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_SELECT_VIRTUAL_CARD_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            VirtualCard virtualCard = new VirtualCard();
            while(resultSet.next()) {
                virtualCard.setId(id);
                virtualCard.setName(resultSet.getString("vitualrCard_name"));
                Integer userID = resultSet.getInt("vitualCard_user_id");
                User user = new User();
                UserDaoSql userDao = new UserDaoSql(connection);
                user = userDao.read(userID);
                virtualCard.setUser(user);
                virtualCard.setBalance(resultSet.getBigDecimal("vitualCard_balance"));
                virtualCard.setCurrency(Currency.getCurrency(resultSet.getString("vitualCard_currency")));
            }
            return virtualCard;
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public void update(VirtualCard virtualCard) throws SQLException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_VIRTUAL_CARD_UPDATE);
            statement.setString(1, virtualCard.getName());
            statement.setBigDecimal(2, virtualCard.getBalance());
            statement.setString(3, virtualCard.getCurrency().getName());
            statement.setInt(4, virtualCard.getId());
            statement.executeUpdate();
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }

    @Override
    public void delete(Integer id) throws PersistentException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_VIRTUAL_CARD_DELETE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            throw new PersistentException(e);
        } finally {
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
    }
}
