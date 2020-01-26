package by.javatr.bicrent.entity.valid;

import by.javatr.bicrent.entity.en_um.City;
import by.javatr.bicrent.entity.en_um.Country;

import java.util.ArrayList;
import java.util.List;

public class EntityValidation {

    // Валидация принадлежности города к конкретной стране
    public Boolean ifCityBelongsToCountry (Country country, City city) {
        List<City> listCity = new ArrayList<>();
        listCity = City.getCitiesByCountry(country);
        Boolean ifCityBelongs = false;
        for (int i = 0; i < listCity.size(); i ++) {
         if (listCity.get(i).toString().toUpperCase().contains(city.getName().toUpperCase()))   {
             ifCityBelongs = true;
         }
        }
       return ifCityBelongs;
    }
}
