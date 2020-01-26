package by.javatr.bicrent.dao;
import by.javatr.bicrent.entity.Location;
import by.javatr.bicrent.entity.en_um.City;
import by.javatr.bicrent.entity.en_um.Country;

import java.sql.SQLException;
import java.util.List;

public interface LocationDao extends Dao<Location> {

    List<Location> readByCompanyId(Integer search) throws SQLException;

    List<Location> readByCountry(String search) throws SQLException;

    List<Location> readByCity(String search) throws SQLException;

    List<String> readByCountryAndCity(String country, String city) throws SQLException;

    List<Location> readByCountryAndCity(Country country, City city) throws SQLException;

}
