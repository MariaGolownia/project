package by.javatr.bicrent.service;
import by.javatr.bicrent.entity.Bicycle;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public interface BicycleService {

    List<Bicycle> findByCurrentLocation(Integer locationId);

    List<Bicycle> findByCurrentLocationWithPriceAndFreedom(Integer locationId, Boolean ifFree);

    List<Bicycle> findByFreeStatus(Integer idLocation, Boolean ifFree);

    Bicycle findById(Integer bicycleId);

    List<Bicycle> findById(List <Integer> bicyclesId);

    List<Bicycle> findAll();

    Bicycle readLastBicycle();

    void changeFreeStatus (List <Integer> bicyclesId, Boolean freeStatus);

    List<Bicycle> sortBy (List<Bicycle> bicycles, Comparator<Bicycle> bicycleComparator);

    Integer save (Bicycle bicycleNew);

    void update (Bicycle bicycle);

    void changeLocation (List<Bicycle> bicycleList, Integer locationId);

}
