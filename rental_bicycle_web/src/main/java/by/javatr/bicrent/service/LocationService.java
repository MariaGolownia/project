package by.javatr.bicrent.service;
import by.javatr.bicrent.entity.PersistentException;
import by.javatr.bicrent.entity.Location;
import by.javatr.bicrent.entity.en_um.City;
import by.javatr.bicrent.entity.en_um.Country;

import java.util.List;

public interface LocationService {

    List<String> findByCountryAndCity (String country, String city) throws PersistentException;

    List<Location> findByCountryAndCity (Country country, City city) throws PersistentException;

    Location findById (Integer locationId) throws PersistentException;
}
