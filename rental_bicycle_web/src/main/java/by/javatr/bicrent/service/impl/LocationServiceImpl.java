package by.javatr.bicrent.service.impl;
import by.javatr.bicrent.entity.PersistentException;
import by.javatr.bicrent.dao.mysql.DaoException;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.dao.mysql.FactoryDaoSql;
import by.javatr.bicrent.dao.mysql.LocationDaoSql;
import by.javatr.bicrent.entity.Location;
import by.javatr.bicrent.entity.en_um.City;
import by.javatr.bicrent.entity.en_um.Country;
import by.javatr.bicrent.entity.valid.EntityValidation;
import by.javatr.bicrent.service.LocationService;
import by.javatr.bicrent.service.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocationServiceImpl extends Service implements LocationService {
    @Override
    public List<String> findByCountryAndCity(String country, String city) {
        LocationDaoSql dao;
        List<String> locationList = new ArrayList<>();
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.LocationDao);
            EntityValidation entityValidation = new EntityValidation();
            Country countryEn = Country.getCountry(country);
            City cityEn = City.getCity(city);
            if (entityValidation.ifCityBelongsToCountry(countryEn, cityEn)) {
                locationList = dao.readByCountryAndCity(country, city);
            }
            else try {
                throw  new PersistentException("\n" +
                        "It is necessary to clarify the country in which the selected city is located!");
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return locationList;
    }

    @Override
    public List<Location> findByCountryAndCity(Country country, City city) {
        LocationDaoSql dao;
        List<Location> locationList = new ArrayList<>();
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.LocationDao);
            EntityValidation entityValidation = new EntityValidation();
            if (entityValidation.ifCityBelongsToCountry(country, city)) {
                locationList = dao.readByCountryAndCity(country, city);
            }
            else try {
                throw  new PersistentException("\n" +
                        "It is necessary to clarify the country in which the selected city is located!");
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return locationList;
    }

    @Override
    public Location findById(Integer locationId) {
        LocationDaoSql dao;
        Location location = new Location();
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.LocationDao);
            EntityValidation entityValidation = new EntityValidation();
            location = dao.read(locationId);
            } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return location;
    }
}
