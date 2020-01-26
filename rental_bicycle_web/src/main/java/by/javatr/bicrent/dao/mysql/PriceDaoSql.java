package by.javatr.bicrent.dao.mysql;
import by.javatr.bicrent.entity.PersistentException;
import by.javatr.bicrent.dao.PriceDao;
import by.javatr.bicrent.entity.Price;
import by.javatr.bicrent.entity.en_um.TimeUnit;
import by.javatr.bicrent.entity.en_um.Currency;
import org.apache.logging.log4j.LogManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PriceDaoSql extends BaseDaoSql implements PriceDao {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    private static final String SQL_PRICE_INSERT =
            "INSERT INTO `price` " +
                    "(`price_currency`, `price_timeUnit`, `price_rate`, `price_bookMaxTimeInMin`, `price_bookRate`)" +
                    " VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_PRICE_BY_ID =
            "SELECT `price_currency`, `price_timeUnit`, `price_rate`, `price_bookMaxTimeInMin`, `price_bookRate` " +
                    "FROM `price` WHERE `price_id` = ?";
    private static final String SQL_SELECT_ALL_PRICES_BY_CURRENCY =
            "SELECT `price_id`, `price_timeUnit`, `price_rate`, `price_bookMaxTimeInMin`, `price_bookRate` " +
                    "FROM `price` WHERE `price_currency` = ?";
    private static final String SQL_SELECT_ALL_PRICES =
            "SELECT `price_id`,`price_currency`, `price_timeUnit`, `price_rate`, `price_bookMaxTimeInMin`, `price_bookRate` " +
                    "FROM `price` ORDER BY `price_currency`";
    private static final String SQL_PRICE_UPDATE =
            "UPDATE `price` SET `price_currency` = ?, `price_timeUnit` = ?, `price_rate` = ?, `price_bookMaxTimeInMin` = ?," +
                    " `price_bookRate` = ? WHERE `price_id` = ?";
    private static final String SQL_PRICE_DELETE = "DELETE FROM `price` WHERE `price_id` = ?";

    public PriceDaoSql(Connection connection) {
        this.connection = connection;
    }

    protected PriceDaoSql() {
        super();
    }

    @Override
    public List<Price> readByCurrency(Currency currency) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            statement = connection.prepareStatement(SQL_SELECT_ALL_PRICES_BY_CURRENCY);
            Integer currencyInt = Integer.valueOf(currency.getName());
            statement.setInt(1, currencyInt);
            resultSet = statement.executeQuery();
            List<Price> prices = new ArrayList<>();
            Price price = null;
            while(resultSet.next()) {
                price = new Price();
                price.setId(resultSet.getInt("price_id"));
                price.setCurrency(currency);
                String timeUnitStr = resultSet.getString("price_timeUnit");
                TimeUnit timeUnit = TimeUnit.getTimeUnit(timeUnitStr);
                price.setUnitTime(timeUnit);
                price.setRate(resultSet.getBigDecimal("price_rate"));
                price.setBookMaxTimeInMin(resultSet.getInt("price_bookMaxTimeInMin"));
                price.setBookRate(resultSet.getBigDecimal("price_bookRate"));
                prices.add(price);
            }

            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
            return prices;
    }

    @Override
    public Integer create(Price price) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer idOfPrice = null;
                statement = connection.prepareStatement(SQL_PRICE_INSERT, Statement.RETURN_GENERATED_KEYS);
                statement.setInt(1, Integer.valueOf(price.getCurrency().getName()));
                statement.setString(2, price.getUnitTime().getName());
                statement.setBigDecimal(3, price.getRate());
                statement.setInt (4, price.getBookMaxTimeInMin());
                statement.setBigDecimal(5, price.getBookRate());
                statement.executeUpdate();
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    idOfPrice = resultSet.getInt(1);
                } else {
                    logger.error("There is no autoincremented index after trying to add record into table `users`");
                    try {
                        throw new PersistentException();
                    } catch (PersistentException e) {
                        e.printStackTrace();
                    }
                }

            try {
                resultSet.close();
            } catch(NullPointerException e) {}
            try {
                statement.close();
            } catch( NullPointerException e) {}
        return idOfPrice;
    }


    @Override
    public Price read(Integer id) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            statement = connection.prepareStatement(SQL_SELECT_PRICE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Price price = null;
            if(resultSet.next()) {
                price = new Price();
                price.setId(id);
                Integer currencyInt = resultSet.getInt("price_currency");
                Currency currency = Currency.getCurrency(Integer.toString(currencyInt));
                price.setCurrency(currency);
                String timeUnitStr = resultSet.getString("price_timeUnit");
                TimeUnit timeUnit = TimeUnit.getTimeUnit(timeUnitStr);
                price.setUnitTime(timeUnit);
                price.setRate(resultSet.getBigDecimal("price_rate"));
                price.setBookMaxTimeInMin(resultSet.getInt("price_bookMaxTimeInMin"));
                price.setBookRate(resultSet.getBigDecimal("price_bookRate"));
            }
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        return price;
    }

    public Price read(Integer id, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            statement = connection.prepareStatement(SQL_SELECT_PRICE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Price price = null;
            if(resultSet.next()) {
                price = new Price();
                price.setId(id);
                Integer currencyInt = resultSet.getInt("price_currency");
                Currency currency = Currency.getCurrency(Integer.toString(currencyInt));
                price.setCurrency(currency);
                String timeUnitStr = resultSet.getString("price_timeUnit");
                TimeUnit timeUnit = TimeUnit.getTimeUnit(timeUnitStr);
                price.setUnitTime(timeUnit);
                price.setRate(resultSet.getBigDecimal("price_rate"));
                price.setBookMaxTimeInMin(resultSet.getInt("price_bookMaxTimeInMin"));
                price.setBookRate(resultSet.getBigDecimal("price_bookRate"));
            }
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        return price;
    }

    public List<Price> read() throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            statement = connection.prepareStatement(SQL_SELECT_ALL_PRICES);
            resultSet = statement.executeQuery();
            List<Price> prices = new ArrayList<>();
            Price price = null;
            while(resultSet.next()) {
                price = new Price();
                price.setId(resultSet.getInt("price_id"));
                Integer currencyInt = resultSet.getInt("price_currency");
                Currency currency = Currency.getCurrency(Integer.toString(currencyInt));
                price.setCurrency(currency);
                String timeUnitStr = resultSet.getString("price_timeUnit");
                TimeUnit timeUnit = TimeUnit.getTimeUnit(timeUnitStr);
                price.setUnitTime(timeUnit);
                price.setRate(resultSet.getBigDecimal("price_rate"));
                price.setBookMaxTimeInMin(resultSet.getInt("price_bookMaxTimeInMin"));
                price.setBookRate(resultSet.getBigDecimal("price_bookRate"));
                prices.add(price);
            }
            try {
                resultSet.close();
            } catch(NullPointerException e) {}
            try {
                statement.close();
            } catch(NullPointerException e) {}
        return prices;
    }

    @Override
    public void update(Price price) throws SQLException {
        PreparedStatement statement = null;
            statement = connection.prepareStatement(SQL_PRICE_UPDATE);
            statement.setInt(1,Currency.getCodeOfCurrency(price.getCurrency()));
            statement.setString(2, price.getUnitTime().getName());
            statement.setBigDecimal(3, price.getRate());
            statement.setInt(4, price.getBookMaxTimeInMin());
            statement.setBigDecimal(5, price.getBookRate());
            statement.setInt(6, price.getId());
            statement.executeUpdate();
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
    }

    @Override
    public void delete(Integer id) throws SQLException {
        PreparedStatement statement = null;
            statement = connection.prepareStatement(SQL_PRICE_DELETE);
            statement.setInt(1, id);
            statement.executeUpdate();
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}

    }
}
