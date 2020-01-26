package by.javatr.bicrent.dao.mysql;
import by.javatr.bicrent.dao.BicycleDao;
import by.javatr.bicrent.entity.PersistentException;
import by.javatr.bicrent.entity.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import by.javatr.bicrent.entity.en_um.BicycleType;
import org.apache.logging.log4j.LogManager;
import sun.misc.BASE64Encoder;

public class BicycleDaoSql extends BaseDaoSql implements BicycleDao {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    private static final String SQL_BICYCLE_INSERT =
            "INSERT INTO `bicycle` (`bicycle_model`, `bicycle_type`, `bicycle_productionYear`, `bicycle_producer`, " +
                    "`bicycle_currentLocation_id`, `bicycle_price_id`, `bicycle_ifNotBooked`, `bicycle_ifFree`,`bicycle_photo`)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_BICYCLE_BY_ID_SELECT =
            "SELECT `bicycle_model`, `bicycle_type`, `bicycle_productionYear`, `bicycle_producer`," +
                    " `bicycle_currentLocation_id`, `bicycle_price_id`, `bicycle_ifNotBooked`, `bicycle_ifFree`, `bicycle_photo`" +
                    " FROM `bicycle` WHERE `bicycle_id` = ?";
    private static final String SQL_BICYCLE_ALL =
            "SELECT `bicycle_id`,`bicycle_model`, `bicycle_type`, `bicycle_productionYear`, `bicycle_producer`," +
                    "`bicycle_currentLocation_id`, `bicycle_price_id`, `bicycle_ifNotBooked`, `bicycle_ifFree`, `bicycle_photo`" +
                    " FROM `bicycle`";
    private static final String SQL_BICYCLE_BY_CURRENT_LOCATION =
            "SELECT `bicycle_id`,`bicycle_model`, `bicycle_type`, `bicycle_productionYear`, `bicycle_producer`," +
                    "`bicycle_price_id`, `bicycle_ifNotBooked`, `bicycle_ifFree`, `bicycle_photo`" +
                    " FROM `bicycle` WHERE `bicycle_currentLocation_id` = ?";
    private static final String SQL_BICYCLE_WITH_PRICE_BY_CURRENT_LOCATION_AND_FREEDOM =
            "SELECT `bicycle_id`,`bicycle_model`, `bicycle_type`, `bicycle_productionYear`, `bicycle_producer`," +
                    "`bicycle_price_id`, `bicycle_ifNotBooked`, `bicycle_ifFree`, `bicycle_photo`, `price_rate`, `price_currency`" +
                    " FROM `bicycle` INNER JOIN `price` on `price_id`=`bicycle_price_id` WHERE `bicycle_currentLocation_id` = ? AND `bicycle_ifFree` = ?";
    private static final String SQL_BICYCLE_BY_CURRENT_LOCATION_AND_FREEDOM =
            "SELECT `bicycle_id`,`bicycle_model`, `bicycle_type`, `bicycle_productionYear`, `bicycle_producer`," +
                    "`bicycle_price_id`, `bicycle_ifNotBooked`, `bicycle_photo`" +
                    " FROM `bicycle` WHERE `bicycle_currentLocation_id` = ? AND `bicycle_ifFree` = ?";
    private static final String SQL_BICYCLE_BY_LAST_ID =
            "SELECT `bicycle_id`,`bicycle_model`, `bicycle_type`, `bicycle_productionYear`, `bicycle_producer`," +
                    "`bicycle_currentLocation_id`, `bicycle_price_id`, `bicycle_ifNotBooked`, `bicycle_ifFree`, `bicycle_photo`" +
                    " FROM `bicycle` ORDER BY `bicycle_id` DESC LIMIT 1";
    private static final String SQL_BICYCLE_UPDATE =
            "UPDATE `bicycle` SET `bicycle_model`= ?, `bicycle_type`= ?, `bicycle_productionYear`= ?," +
                    " `bicycle_producer`= ?, `bicycle_currentLocation_id`= ?, `bicycle_price_id`= ?," +
                    " `bicycle_ifNotBooked`= ?, `bicycle_ifFree`= ?, `bicycle_photo`= ? WHERE `bicycle_id` = ?";
    private static final String SQL_BICYCLE_DELETE = "DELETE FROM `bicycle` WHERE `bicycle_id` = ?";
    private static final String SQL_BICYCLE_CHANGE_FREE_STATUS = "UPDATE `bicycle` SET `bicycle_ifFree`= ? WHERE `bicycle_id` = ?";

    protected BicycleDaoSql() {
        super();
    }

    public BicycleDaoSql(Connection connection) {
        super(connection);
    }

    @Override
    public List<Bicycle> readAll() throws SQLException {
        List<Bicycle> bicycles = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_BICYCLE_ALL);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Bicycle bicycle = new Bicycle();
                Integer bicycleID = resultSet.getInt("bicycle_id");
                bicycle.setId(bicycleID);
                bicycle.setModel(resultSet.getString("bicycle_model"));
                BicycleType bicycleType = BicycleType.getBicycleType(resultSet.getString("bicycle_type"));
                bicycle.setBicycleType(bicycleType);
                bicycle.setProductionYear(resultSet.getShort("bicycle_productionYear"));
                bicycle.setProducer(resultSet.getString("bicycle_producer"));
                Location locationBicycle = new Location();
                LocationDaoSql locationDao = FactoryDaoSql.getInstance().get(DaoSql.LocationDao);
                locationBicycle = locationDao.read(resultSet.getInt("bicycle_currentLocation_id"));
                bicycle.setCurrentLocation(locationBicycle);
                bicycle.setPhoto(resultSet.getBlob("bicycle_photo"));
                Price price = new Price();
                PriceDaoSql priceDao = FactoryDaoSql.getInstance().get(DaoSql.PriceDao);
                Integer bicycle_price_id = resultSet.getInt("bicycle_price_id");
                price = priceDao.read(bicycle_price_id==null ? null : bicycle_price_id);
                //price = priceDao.read(resultSet.getInt("bicycle_price_id"));
                bicycle.setPriceId(price.getId());
                bicycle.setIfNotBooked(resultSet.getBoolean("bicycle_ifNotBooked"));
                bicycle.setIfFree(resultSet.getBoolean("bicycle_ifFree"));
                bicycles.add(bicycle);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch(NullPointerException e) {}
            try {
                statement.close();
            } catch(NullPointerException e) {}
        }
        return bicycles;
    }

    @Override
    public Bicycle readByLastId() throws SQLException {
        Bicycle bicycle = new Bicycle();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_BICYCLE_BY_LAST_ID);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Integer bicycleID = resultSet.getInt("bicycle_id");
                bicycle.setId(bicycleID);
                bicycle.setModel(resultSet.getString("bicycle_model"));
                BicycleType bicycleType = BicycleType.getBicycleType(resultSet.getString("bicycle_type"));
                bicycle.setBicycleType(bicycleType);
                bicycle.setProductionYear(resultSet.getShort("bicycle_productionYear"));
                bicycle.setProducer(resultSet.getString("bicycle_producer"));
                Location locationBicycle = new Location();
                LocationDaoSql locationDao = FactoryDaoSql.getInstance().get(DaoSql.LocationDao);
                locationBicycle = locationDao.read(resultSet.getInt("bicycle_currentLocation_id"));
                bicycle.setCurrentLocation(locationBicycle);
                bicycle.setPhoto(resultSet.getBlob("bicycle_photo"));
                Price price = new Price();
                PriceDaoSql priceDao = FactoryDaoSql.getInstance().get(DaoSql.PriceDao);
                Integer bicycle_price_id = resultSet.getInt("bicycle_price_id");
                price = priceDao.read(bicycle_price_id == null ? null : bicycle_price_id);
                bicycle.setPriceId(price.getId());
                bicycle.setIfNotBooked(resultSet.getBoolean("bicycle_ifNotBooked"));
                bicycle.setIfFree(resultSet.getBoolean("bicycle_ifFree"));
            }
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch(NullPointerException e) {}
            try {
                statement.close();
            } catch(NullPointerException e) {}
        }
        return bicycle;
    }

    @Override
    public List<Bicycle> readByCurrentLocation(Integer idLocation) throws SQLException {
        List<Bicycle> bicycles = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_BICYCLE_BY_CURRENT_LOCATION);
            statement.setInt(1, idLocation);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Bicycle bicycle = new Bicycle();
                Integer bicycleID = resultSet.getInt("bicycle_id");
                bicycle.setId(bicycleID);
                bicycle.setModel(resultSet.getString("bicycle_model"));
                BicycleType bicycleType = BicycleType.getBicycleType(resultSet.getString("bicycle_type"));
                bicycle.setBicycleType(bicycleType);
                bicycle.setProductionYear(resultSet.getShort("bicycle_productionYear"));
                bicycle.setProducer(resultSet.getString("bicycle_producer"));
                Location locationBicycle = new Location();
                LocationDaoSql locationDao = FactoryDaoSql.getInstance().get(DaoSql.LocationDao);
                locationBicycle = locationDao.read(idLocation);
                bicycle.setCurrentLocation(locationBicycle);
                bicycle.setPhoto(resultSet.getBlob("bicycle_photo"));
                Price price = new Price();
                PriceDaoSql priceDao = FactoryDaoSql.getInstance().get(DaoSql.PriceDao);
                Integer bicycle_price_id = resultSet.getInt("bicycle_price_id");
                price = priceDao.read(bicycle_price_id==null ? null : bicycle_price_id);
                //price = priceDao.read(resultSet.getInt("bicycle_price_id"));
                bicycle.setPriceId(price.getId());
                bicycle.setIfNotBooked(resultSet.getBoolean("bicycle_ifNotBooked"));
                bicycle.setIfFree(resultSet.getBoolean("bicycle_ifFree"));
                bicycles.add(bicycle);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch(NullPointerException e) {}
            try {
                statement.close();
            } catch(NullPointerException e) {}
        }
        return bicycles;
    }

    @Override
    public List<Bicycle> readByCurrentLocationWithPriceAndFreedom(Integer idLocation, Boolean ifFree) throws SQLException {
        List<Bicycle> bicycles = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_BICYCLE_WITH_PRICE_BY_CURRENT_LOCATION_AND_FREEDOM);
            statement.setInt(1, idLocation);
            if (ifFree == true)
                statement.setInt(2, 1);
            else statement.setInt(2, 0);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Bicycle bicycle = new Bicycle();
                Integer bicycleID = resultSet.getInt("bicycle_id");
                bicycle.setId(bicycleID);
                bicycle.setModel(resultSet.getString("bicycle_model"));
                BicycleType bicycleType = BicycleType.getBicycleType(resultSet.getString("bicycle_type"));
                bicycle.setBicycleType(bicycleType);
                bicycle.setProductionYear(resultSet.getShort("bicycle_productionYear"));
                bicycle.setProducer(resultSet.getString("bicycle_producer"));
                Location locationBicycle = new Location();
                LocationDaoSql locationDao = FactoryDaoSql.getInstance().get(DaoSql.LocationDao);
                locationBicycle = locationDao.read(idLocation);
                bicycle.setCurrentLocation(locationBicycle);
                Blob blobImg = resultSet.getBlob("bicycle_photo");
                bicycle.setPhoto(blobImg);
//                Price price = new Price();
//                PriceDaoSql priceDao = new PriceDaoSql();
//                price = priceDao.read(resultSet.getInt("bicycle_price_id"));
//                bicycle.setPriceId(price.getId());
                bicycle.setPriceId(resultSet.getInt("bicycle_price_id"));
                bicycle.setIfNotBooked(resultSet.getBoolean("bicycle_ifNotBooked"));
                bicycle.setIfFree(resultSet.getBoolean("bicycle_ifFree"));
                bicycle.setRate(resultSet.getBigDecimal("price_rate"));
                bicycle.setCurrency(resultSet.getString("price_currency"));
                byte[] bdata = new byte[0];
                try {
                    bdata = blobImg.getBytes(1, (int) blobImg.length());
                    BASE64Encoder encoder = new BASE64Encoder();
                    bicycle.setPhotoBlobStr(encoder.encode(bdata));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                bicycles.add(bicycle);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch(NullPointerException e) {}
            try {
                statement.close();
            } catch(NullPointerException e) {}
        }
        return bicycles;
    }


    @Override
    public List<Bicycle> readByCurrentLocation(Location location) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Bicycle> bicycles = new ArrayList<>();
//            ConnectionSQL connectionSQL = new ConnectionSQL();
//            connection = connectionSQL.getConnectionToDB();
            statement = connection.prepareStatement(SQL_BICYCLE_BY_CURRENT_LOCATION);
            statement.setInt(1, location.getId());
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Bicycle bicycle = new Bicycle();
                Integer bicycleID = resultSet.getInt("bicycle_id");
                bicycle.setId(bicycleID);
                bicycle.setModel(resultSet.getString("bicycle_model"));
                BicycleType bicycleType = BicycleType.getBicycleType(resultSet.getString("bicycle_type"));
                bicycle.setBicycleType(bicycleType);
                bicycle.setProductionYear(resultSet.getShort("bicycle_productionYear"));
                bicycle.setProducer(resultSet.getString("bicycle_producer"));
                bicycle.setCurrentLocation(location);
                bicycle.setPhoto(resultSet.getBlob("bicycle_photo"));
                Price price = new Price();
                PriceDaoSql priceDao = new PriceDaoSql();
                price = priceDao.read(resultSet.getInt("bicycle_price_id"));
                bicycle.setPriceId(price.getId());
                bicycle.setIfNotBooked(resultSet.getBoolean("bicycle_ifNotBooked"));
                bicycle.setIfFree(resultSet.getBoolean("bicycle_ifFree"));
                bicycles.add(bicycle);
            }
            try {
                resultSet.close();
            } catch(NullPointerException e) {}
            try {
                statement.close();

            } catch(NullPointerException e) {}
        return bicycles;
    }


    @Override
    public List<Bicycle> readByCurrentLocationAndFreedom (Integer idLocation, Boolean ifFree) throws SQLException {
        List<Bicycle> bicycles = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(SQL_BICYCLE_BY_CURRENT_LOCATION_AND_FREEDOM);
            statement.setInt(1, idLocation);
            if (ifFree == true)
                statement.setInt(2, 1);
            else statement.setInt(2, 0);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Bicycle bicycle = new Bicycle();
                Integer bicycleID = resultSet.getInt("bicycle_id");
                bicycle.setId(bicycleID);
                bicycle.setModel(resultSet.getString("bicycle_model"));
                BicycleType bicycleType = BicycleType.getBicycleType(resultSet.getString("bicycle_type"));
                bicycle.setBicycleType(bicycleType);
                bicycle.setProductionYear(resultSet.getShort("bicycle_productionYear"));
                bicycle.setProducer(resultSet.getString("bicycle_producer"));
                Location locationBicycle = new Location();
                LocationDaoSql locationDao = FactoryDaoSql.getInstance().get(DaoSql.LocationDao);
                locationBicycle = locationDao.read(idLocation);
                bicycle.setCurrentLocation(locationBicycle);
                bicycle.setPhoto(resultSet.getBlob("bicycle_photo"));
                Price price = new Price();
                PriceDaoSql priceDao = new PriceDaoSql();
                price = priceDao.read(resultSet.getInt("bicycle_price_id"), connection);
                bicycle.setPriceId(price.getId());
                bicycle.setIfNotBooked(resultSet.getBoolean("bicycle_ifNotBooked"));
                bicycles.add(bicycle);
            }
        } catch (DaoException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch(NullPointerException e) {}
            try {
                statement.close();
            } catch(NullPointerException e) {}
        }
        return bicycles;
    }

    @Override
    public void changeFreeStatus(Integer idBicycle, Boolean ifFree) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer idOfLocation = null;
        statement = connection.prepareStatement(SQL_BICYCLE_CHANGE_FREE_STATUS);
        statement.setBoolean(1, ifFree);
        statement.setInt(2, idBicycle);
        statement.executeUpdate();
        try {
            statement.close();
        } catch(NullPointerException e) {}

    }

    @Override
    public Integer create(Bicycle bicycle) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer idOfLocation = null;
        try {
            statement = connection.prepareStatement(SQL_BICYCLE_INSERT, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, bicycle.getModel());
            statement.setString(2, bicycle.getBicycleType().getName());
            statement.setShort(3, bicycle.getProductionYear());
            statement.setString(4, bicycle.getProducer());
            // locationID
            Location location = new Location();
            LocationDaoSql locationDao = new LocationDaoSql(connection);
            Integer locationID = bicycle.getCurrentLocation().getId();
            if (locationID != null) {
                location = locationDao.read(locationID, connection);
                statement.setInt(5, location.getId());
            }
            else {
                statement.setInt(5, 1);
            }
            // priceID
            Price price = new Price();
            PriceDaoSql priceDao = new PriceDaoSql(connection);
            Integer priceID = bicycle.getPriceId();
            if (priceID != null) {
                price = priceDao.read(priceID, connection);
                statement.setInt(6, price.getId());
            }
            else {
                statement.setInt(6, 1);
            }
            statement.setInt(7, bicycle.getIfNotBookedInt());
            statement.setInt(8, bicycle.getIfFreeInt());
            statement.setInt(9, bicycle.getIfFreeInt());
            if (bicycle.getPhoto().length() > 3) {
                statement.setBlob(9, bicycle.getPhoto());
            }
            else {
                Bicycle bicycleD = new Bicycle();
                BicycleDaoSql bicycleDao = new BicycleDaoSql(connection);
                bicycleD = bicycleDao.read(1);
                statement.setBlob(8, bicycleD.getPhoto());
            }
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                idOfLocation = resultSet.getInt(1);
            } else {
                logger.error("There is no autoincremented index after trying to add record into table `users`");
                throw new PersistentException();
            }
        } catch(PersistentException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch( NullPointerException e) {}
            try {
                statement.close();
            } catch(NullPointerException e) {}
        }
        return idOfLocation;
    }

    @Override
    public Bicycle read(Integer id) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Bicycle bicycle = new Bicycle();
            statement = connection.prepareStatement(SQL_BICYCLE_BY_ID_SELECT);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if(resultSet.next()) {
                bicycle = new Bicycle();
                bicycle.setId(id);
                bicycle.setModel(resultSet.getString("bicycle_model"));
                BicycleType bicycleType = BicycleType.getBicycleType(resultSet.getString("bicycle_type"));
                bicycle.setBicycleType(bicycleType);
                bicycle.setProductionYear(resultSet.getShort("bicycle_productionYear"));
                bicycle.setProducer(resultSet.getString("bicycle_producer"));
                Location location = new Location();
                LocationDaoSql locationDao = new LocationDaoSql(connection);
                location = locationDao.read(resultSet.getInt("bicycle_currentLocation_id"), connection);
                bicycle.setCurrentLocation(location);
                bicycle.setPhoto(resultSet.getBlob("bicycle_photo"));
//                Price price = new Price();
//                PriceDaoSql priceDao = new PriceDaoSql(connection);
//                price = priceDao.read(resultSet.getInt("bicycle_price_id"), connection);
                bicycle.setPriceId(resultSet.getInt("bicycle_price_id"));
                bicycle.setIfNotBooked(resultSet.getBoolean("bicycle_ifNotBooked"));
                bicycle.setIfFree(resultSet.getBoolean("bicycle_ifFree"));
            }
            try {
                resultSet.close();
            } catch(NullPointerException e) {}
            try {
                statement.close();
            } catch(NullPointerException e) {}
        return bicycle;
    }

    @Override
    public void update(Bicycle bicycle) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer idOfLocation = null;
            statement = connection.prepareStatement(SQL_BICYCLE_UPDATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, bicycle.getModel());
            statement.setString(2, bicycle.getBicycleType().getName());
            statement.setShort(3, bicycle.getProductionYear());
            statement.setString(4, bicycle.getProducer());
            Location location = new Location();
            LocationDaoSql locationDao = new LocationDaoSql(connection);
            location = locationDao.read(bicycle.getCurrentLocation().getId(),connection);
            statement.setInt(5, location.getId());
            Integer priceId;
            PriceDaoSql priceDao = new PriceDaoSql(connection);
            priceId = priceDao.read(bicycle.getPriceId()).getId();
            statement.setInt(6, priceId);
            statement.setInt(7, bicycle.getIfNotBookedInt());
            statement.setInt(8, bicycle.getIfFreeInt());
            Integer companyNumber = location.getCompany().getAccountNumberOfPayer();
            statement.setBlob(9, bicycle.getPhoto());
            statement.setInt(10, bicycle.getId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            try {
                resultSet.close();
            } catch( NullPointerException e) {}
            try {
                statement.close();
            } catch(NullPointerException e) {}

    }

    @Override
    public void delete(Integer id) throws SQLException {
        PreparedStatement statement = null;
        statement = connection.prepareStatement(SQL_BICYCLE_DELETE);
        statement.setInt(1, id);
        statement.executeUpdate();
        try {
            statement.close();
        } catch(NullPointerException e) {}
    }
}
