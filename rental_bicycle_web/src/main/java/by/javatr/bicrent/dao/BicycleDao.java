package by.javatr.bicrent.dao;
import by.javatr.bicrent.entity.Bicycle;
import by.javatr.bicrent.entity.Location;

import java.sql.SQLException;
import java.util.List;

public interface BicycleDao extends Dao<Bicycle> {

    List<Bicycle> readByCurrentLocation(Location search) throws SQLException;

    List<Bicycle> readByCurrentLocation(Integer idLocation) throws SQLException;

    List<Bicycle> readByCurrentLocationWithPriceAndFreedom(Integer idLocation, Boolean ifFree) throws SQLException;

    List<Bicycle> readByCurrentLocationAndFreedom(Integer idLocation, Boolean ifFree) throws SQLException;

    public List<Bicycle> readAll() throws SQLException;

    public Bicycle readByLastId() throws SQLException;

    void changeFreeStatus(Integer idBicycle, Boolean ifFree) throws SQLException;
}
