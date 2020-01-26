package by.javatr.bicrent.dao.mysql;
import by.javatr.bicrent.dao.LocationDao;
import by.javatr.bicrent.entity.PersistentException;
import by.javatr.bicrent.dao.valid.ValidationException;
import by.javatr.bicrent.entity.*;
import by.javatr.bicrent.entity.en_um.City;
import by.javatr.bicrent.entity.en_um.Country;
import by.javatr.bicrent.entity.valid.EntityValidation;
import org.apache.logging.log4j.LogManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocationDaoSql extends BaseDaoSql implements LocationDao {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();
    private static final String SQL_LOCATION_INSERT =
            "INSERT INTO `location` " +
                    "(`location_name`, `location_company_id`, `location_country`, `location_city`,`location_street`," +
                    " `location_house`, `location_office`, `location_phone` , `location_secondPhone`,`location_photo`)" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_SELECT_LOCATION_BY_ID =
            "SELECT `location_name`, `location_company_id`, `location_country`, `location_city`, `location_street`, " +
                    "`location_house`, `location_office`, `location_phone` , `location_secondPhone`, `location_photo`" +
                    " FROM `location` WHERE `location_id` = ?";
    private static final String SQL_SELECT_LOCATIONS_BY_COMPANY_ID =
            "SELECT `location_id`,`location_name`, `location_company_id`, `location_country`, `location_city`," +
                    "`location_street`, `location_house`, `location_office`, `location_phone` , `location_secondPhone`," +
                    " `location_photo` FROM `location` WHERE `location_company_id` = ?";
    private static final String SQL_SELECT_LOCATIONS_BY_COUNTRY =
            "SELECT `location_id`,`location_name`, `location_company_id`, `location_city`," +
                    "`location_street`, `location_house`, `location_office`, `location_phone` , `location_secondPhone`," +
                    " `location_photo` FROM `location` WHERE `location_country` = ?";
    private static final String SQL_SELECT_LOCATIONS_BY_CITY=
            "SELECT `location_id`,`location_name`, `location_company_id`, `location_country`," +
                    "`location_street`, `location_house`, `location_office`, `location_phone` , `location_secondPhone`," +
                    " `location_photo` FROM `location` WHERE `location_city` = ?";
    private static final String SQL_SELECT_LOCATIONS_BY_CITY_AND_COUNTRY=
            "SELECT `location_id`,`location_name`, `location_company_id`," +
                    "`location_street`, `location_house`, `location_office`, `location_phone` , `location_secondPhone`," +
                    " `location_photo` FROM `location` WHERE `location_country` = ? AND `location_city` = ?";
    private static final String SQL_LOCATION_UPDATE =
            "UPDATE `location` SET `location_name` = ?, `location_company_id` = ?, `location_country` = ?," +
                    " `location_city` = ?, `location_street` = ?, `location_house` = ?, `location_office` = ?," +
                    " `location_phone` = ?, `location_secondPhone` =  ?, `location_photo` = ? WHERE `location_id` = ?";
    private static final String SQL_LOCATION_DELETE = "DELETE FROM `location` WHERE `location_id` = ?";

    public LocationDaoSql(Connection connection) {
        this.connection = connection;
    }

    protected LocationDaoSql() {
        super();
    }
    @Override
    public List<Location> readByCompanyId(Integer companyID) throws SQLException {
        List<Location> locationList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            statement = connection.prepareStatement(SQL_SELECT_LOCATIONS_BY_COMPANY_ID);
            statement.setInt(1, companyID);
            resultSet = statement.executeQuery();
            Location location = null;
            while(resultSet.next()) {
                location = new Location();
                location.setId(resultSet.getInt("location_id"));
                location.setName(resultSet.getString("location_name"));
                Company company = new Company();
                CompanyDaoSql companyDao = new CompanyDaoSql(connection);
                company = companyDao.read(resultSet.getInt("location_company_id"));
                location.setCompany(company);
                String countryStr = resultSet.getString("location_country");
                Country country = Country.getCountry(countryStr);
                String cityStr = resultSet.getString("location_city");
                City city = City.getCity(cityStr);
                EntityValidation entityValidation = new EntityValidation();
                if (entityValidation.ifCityBelongsToCountry(country, city)) {
                    location.setCountry(country);
                    location.setCity(city);
                }
                else try {
                    throw new PersistentException("Check the correctness of the country and city located in the country!");
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
                location.setStreet(resultSet.getString("location_street"));
                location.setHouse(resultSet.getString("location_house"));
                location.setOffice(resultSet.getString("location_office"));
                location.setPhone(resultSet.getLong("location_phone"));
                location.setSecondPhone(resultSet.getLong("location_secondPhone"));
                location.setPhoto(resultSet.getString("location_photo"));
                locationList.add(location);
            }
            try {
                resultSet.close();
            } catch(NullPointerException e) {}
            try {
                statement.close();
            } catch( NullPointerException e) {}
        return locationList;
    }

    @Override
    public List<Location> readByCountry(String country) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Location> locationList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_SELECT_LOCATIONS_BY_COUNTRY);
            statement.setString(1, country);
            resultSet = statement.executeQuery();
            Location location = null;
            while(resultSet.next()) {
                location = new Location();
                location.setId(resultSet.getInt("location_id"));
                location.setName(resultSet.getString("location_name"));
                Company company = new Company();
                CompanyDaoSql companyDao = new CompanyDaoSql(connection);
                company = companyDao.read(resultSet.getInt("location_company_id"));
                location.setCompany(company);
                Country countryC = Country.getCountry(country);
                String cityStr = resultSet.getString("location_city");
                City city = City.getCity(cityStr);
                EntityValidation entityValidation = new EntityValidation();
                if (entityValidation.ifCityBelongsToCountry(countryC, city)) {
                    location.setCountry(countryC);
                    location.setCity(city);
                }
                else try {
                    throw new PersistentException("Check the correctness of the country and city located in the country!");
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
                location.setStreet(resultSet.getString("location_street"));
                location.setHouse(resultSet.getString("location_house"));
                location.setOffice(resultSet.getString("location_office"));
                location.setPhone(resultSet.getLong("location_phone"));
                location.setSecondPhone(resultSet.getLong("location_secondPhone"));
                location.setPhoto(resultSet.getString("location_photo"));
                locationList.add(location);
            }

        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
        return locationList;
    }

    @Override
    public List<Location> readByCity(String city) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            statement = connection.prepareStatement(SQL_SELECT_LOCATIONS_BY_CITY);
            statement.setString(1, city);
            resultSet = statement.executeQuery();
            Location location = null;
            List<Location> locationList = new ArrayList<>();
            while(resultSet.next()) {
                location = new Location();
                location.setId(resultSet.getInt("location_id"));
                location.setName(resultSet.getString("location_name"));
                Company company = new Company();
                CompanyDaoSql companyDao = new CompanyDaoSql(connection);
                company = companyDao.read(resultSet.getInt("location_company_id"));
                location.setCompany(company);
                String countryStr = resultSet.getString("location_country");
                Country country = Country.getCountry(countryStr);
                EntityValidation entityValidation = new EntityValidation();
                City cityC = City.valueOf(city.toUpperCase());
                if (entityValidation.ifCityBelongsToCountry(country, cityC)) {
                    location.setCountry(country);
                    location.setCity(cityC);
                }
                else try {
                    throw new PersistentException("Check the correctness of the country and city located in the country!");
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
                location.setStreet(resultSet.getString("location_street"));
                location.setHouse(resultSet.getString("location_house"));
                location.setOffice(resultSet.getString("location_office"));
                location.setPhone(resultSet.getLong("location_phone"));
                location.setSecondPhone(resultSet.getLong("location_secondPhone"));
                location.setPhoto(resultSet.getString("location_photo"));
                locationList.add(location);
            }
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        return locationList;
    }

    @Override
    public List<String> readByCountryAndCity(String country, String city) throws SQLException {
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            List<String> locationList = new ArrayList<>();
                statement = connection.prepareStatement(SQL_SELECT_LOCATIONS_BY_CITY_AND_COUNTRY);
                statement.setString(1, country);
                statement.setString(2, city);
                resultSet = statement.executeQuery();
                Location location = null;
                while(resultSet.next()) {
                    location = new Location();
                    location.setId(resultSet.getInt("location_id"));
                    location.setName(resultSet.getString("location_name"));
                    Company company = new Company();
                    CompanyDaoSql companyDao = new CompanyDaoSql(connection);
                    company = companyDao.read(resultSet.getInt("location_company_id"));
                    location.setCompany(company);
                    Country countryEn = Country.getCountry(country);
                    City cityEn = City.getCity(city);
                    location.setCountry(countryEn);
                    location.setCity(cityEn);
                    location.setStreet(resultSet.getString("location_street"));
                    location.setHouse(resultSet.getString("location_house"));
                    location.setOffice(resultSet.getString("location_office"));
                    location.setPhone(resultSet.getLong("location_phone"));
                    location.setSecondPhone(resultSet.getLong("location_secondPhone"));
                    location.setPhoto(resultSet.getString("location_photo"));
                    locationList.add(location.withoutCountryAndCityToString());
                }
            return locationList;
    }

    @Override
    public List<Location> readByCountryAndCity(Country country, City city) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Location> locationList = new ArrayList<>();
        try {
            statement = connection.prepareStatement(SQL_SELECT_LOCATIONS_BY_CITY_AND_COUNTRY);
            statement.setString(1, country.getName());
            statement.setString(2, city.getName());
            resultSet = statement.executeQuery();
            Location location = null;

            while(resultSet.next()) {
                location = new Location();
                location.setId(resultSet.getInt("location_id"));
                location.setName(resultSet.getString("location_name"));
                Company company = new Company();
                CompanyDaoSql companyDao = new CompanyDaoSql(connection);
                company = companyDao.read(resultSet.getInt("location_company_id"));
                location.setCompany(company);
                Country countryEn = country;
                City cityEn = city;
                location.setCountry(countryEn);
                location.setCity(cityEn);
                location.setStreet(resultSet.getString("location_street"));
                location.setHouse(resultSet.getString("location_house"));
                location.setOffice(resultSet.getString("location_office"));
                location.setPhone(resultSet.getLong("location_phone"));
                location.setSecondPhone(resultSet.getLong("location_secondPhone"));
                location.setPhoto(resultSet.getString("location_photo"));
                locationList.add(location);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return locationList;
    }

    @Override
    public Integer create(Location location) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer idOfLocation = null;
        try {
                statement = connection.prepareStatement(SQL_LOCATION_INSERT, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, location.getName());
            Integer companyNumber = location.getCompany().getAccountNumberOfPayer();
            CompanyDaoSql companyDao = new CompanyDaoSql(connection);
            Company company = new Company();
            company = companyDao.readByAccountNumberOfPayer(companyNumber);
             if (!company.equals(null)) {
                    statement.setInt(2, company.getId());
                    Country country = location.getCountry();
                    statement.setString(3, country.getName());
                    City city = location.getCity();
                    EntityValidation entityValidation = new EntityValidation();
                    if (entityValidation.ifCityBelongsToCountry(country, city)) {
                        statement.setString(4, city.getName());
                        statement.setString(5, location.getStreet());
                        statement.setString(6, location.getHouse());
                        statement.setString(7, location.getOffice());
                        statement.setLong(8, location.getPhone());
                        statement.setLong(9, location.getSecondPhone());
                        statement.setString(10, location.getPhoto());
                        statement.executeUpdate();
                        resultSet = statement.getGeneratedKeys();
                        if (resultSet.next()) {
                            idOfLocation = resultSet.getInt(1);
                        } else {
                            logger.error("There is no autoincremented index after trying to add record into table `users`");
                            throw new PersistentException();
                        }
                    }
                    else try {
                        throw new PersistentException("Check the correctness of the country and city located in the country!");
                    } catch (PersistentException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        throw new ValidationException("This company does not exist in the database! You must first enter the company information!");
                    } catch (ValidationException e) {
                        e.printStackTrace();
                    }
                }
        } catch(PersistentException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        }
        return idOfLocation;
    }

    @Override
    public Location read(Integer id) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            statement = connection.prepareStatement(SQL_SELECT_LOCATION_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Location location = null;
            if(resultSet.next()) {
                location = new Location();
                location.setId(id);
                location.setName(resultSet.getString("location_name"));
                Company company = new Company();
                CompanyDaoSql companyDao = new CompanyDaoSql(connection);
                company = companyDao.read(resultSet.getInt("location_company_id"));
                location.setCompany(company);
                String countryStr = resultSet.getString("location_country");
                Country country = Country.getCountry(countryStr);
                String cityStr = resultSet.getString("location_city");
                City city = City.getCity(cityStr);
                EntityValidation entityValidation = new EntityValidation();
                if (entityValidation.ifCityBelongsToCountry(country, city)) {
                    location.setCountry(country);
                    location.setCity(city);
                }
                else try {
                    throw new PersistentException("Check the correctness of the country and city located in the country!");
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
                location.setStreet(resultSet.getString("location_street"));
                location.setHouse(resultSet.getString("location_house"));
                location.setOffice(resultSet.getString("location_office"));
                location.setPhone(resultSet.getLong("location_phone"));
                location.setSecondPhone(resultSet.getLong("location_secondPhone"));
                location.setPhoto(resultSet.getString("location_photo"));
            }
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        return location;
    }

    public Location read(Integer id, Connection connection) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
            statement = connection.prepareStatement(SQL_SELECT_LOCATION_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Location location = null;
            if(resultSet.next()) {
                location = new Location();
                location.setId(id);
                location.setName(resultSet.getString("location_name"));
                Company company = new Company();
                CompanyDaoSql companyDao = new CompanyDaoSql(connection);
                company = companyDao.read(resultSet.getInt("location_company_id"));
                location.setCompany(company);
                String countryStr = resultSet.getString("location_country");
                Country country = Country.getCountry(countryStr);
                String cityStr = resultSet.getString("location_city");
                City city = City.getCity(cityStr);
                EntityValidation entityValidation = new EntityValidation();
                if (entityValidation.ifCityBelongsToCountry(country, city)) {
                    location.setCountry(country);
                    location.setCity(city);
                }
                else try {
                    throw new PersistentException("Check the correctness of the country and city located in the country!");
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
                location.setStreet(resultSet.getString("location_street"));
                location.setHouse(resultSet.getString("location_house"));
                location.setOffice(resultSet.getString("location_office"));
                location.setPhone(resultSet.getLong("location_phone"));
                location.setSecondPhone(resultSet.getLong("location_secondPhone"));
                location.setPhoto(resultSet.getString("location_photo"));
            }
            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}
        return location;
    }


    @Override
    public void update(Location location) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Integer idOfLocation = null;
            statement = connection.prepareStatement(SQL_LOCATION_UPDATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, location.getName());
            Integer companyNumber = location.getCompany().getAccountNumberOfPayer();
            CompanyDaoSql companyDao = new CompanyDaoSql(connection);
            Company company = new Company();
            company = companyDao.readByAccountNumberOfPayer(companyNumber);
            if (!company.equals(null)) {
                statement.setInt(2, company.getId());
                Country country = location.getCountry();
                statement.setString(3, country.getName());
                City city = location.getCity();
                EntityValidation entityValidation = new EntityValidation();
                if (entityValidation.ifCityBelongsToCountry(country, city)) {
                    statement.setString(4, city.getName());
                    statement.setString(5, location.getStreet());
                    statement.setString(6, location.getHouse());
                    statement.setString(7, location.getOffice());
                    statement.setLong(8, location.getPhone());
                    statement.setLong(9, location.getSecondPhone());
                    statement.setString(10, location.getPhoto());
                    statement.setInt(11, location.getId());
                    statement.executeUpdate();
                    resultSet = statement.getGeneratedKeys();
                }
                else try {
                    throw new PersistentException("Check the correctness of the country and city located in the country!");
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    throw new ValidationException("This company does not exist in the database! You must first enter the company information!");
                } catch (ValidationException e) {
                    e.printStackTrace();
                }
            }

            try {
                resultSet.close();
            } catch(SQLException | NullPointerException e) {}
            try {
                statement.close();
            } catch(SQLException | NullPointerException e) {}

    }

    @Override
    public void delete(Integer id) throws PersistentException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(SQL_LOCATION_DELETE);
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
