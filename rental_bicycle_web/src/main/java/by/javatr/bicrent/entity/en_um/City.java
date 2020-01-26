package by.javatr.bicrent.entity.en_um;
import by.javatr.bicrent.entity.PersistentException;

import java.util.ArrayList;
import java.util.List;

public enum City {
    MINSK("Minsk"),
    GRODNO("Grodno"),
    LIDA("Lida"),
    POZNAN("Poznan"),
    BIALYSTOK ("Bialystok"),
    KRAKOV ("Krakov"),
    VILNIUSE ("Vilnius"),
    KAUNAS("Kaunas"),
    TRAKAJ("Trakai");

    private static final Integer START_INDEX_BELARUS_CITIES = 0;
    private static final Integer FINISH_INDEX_BELARUS_CITIES = 2;
    private static final Integer START_INDEX_POLAND_CITIES = 3;
    private static final Integer FINISH_INDEX_POLAND_CITIES = 5;
    private static final Integer START_INDEX_LITHUANIA_CITIES = 6;
    private static final Integer FINISH_INDEX_LITHUANIA_CITIES = 8;
    private String name;

    private City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static City getById(Integer id) {
        return City.values()[id];
    }

    public static List<City> getCitiesByCountry(Country country) {
        List<City>cityList = new ArrayList<>();
        if (country!=null) {
                if (country.equals(Country.BELARUS)) {
                    for (int i = START_INDEX_BELARUS_CITIES; i < FINISH_INDEX_BELARUS_CITIES +1; i++) {
                        cityList.add(City.getById(i));
                    }
                } else if (country.equals(Country.POLAND)) {
                    for (int i = START_INDEX_POLAND_CITIES; i < FINISH_INDEX_POLAND_CITIES +1; i++) {
                        cityList.add(City.getById(i));
                    }
                } else if (country.equals(Country.LITHUANIA)) {
                        for (int i = START_INDEX_LITHUANIA_CITIES; i < FINISH_INDEX_LITHUANIA_CITIES +1; i++) {
                            cityList.add(City.getById(i));
                        }
                } else {
                    try {
                        throw new PersistentException("Please contact the developer to extend the software application" +
                                " to the level of your region!");
                    } catch (PersistentException e) {
                        e.printStackTrace();
                    }
                }
            }
        else {
            try {
                throw new PersistentException("The option of the country doesn't matter!");
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        }
        return cityList;
    }


    public static List<String> getCitiesStrByCountry(Country country) {
        List<String>cityList = new ArrayList<>();
        if (country!=null) {
            if (country.equals(Country.BELARUS)) {
                for (int i = START_INDEX_BELARUS_CITIES; i < FINISH_INDEX_BELARUS_CITIES +1; i++) {
                    cityList.add(City.getById(i).getName());
                }
            } else if (country.equals(Country.POLAND)) {
                for (int i = START_INDEX_POLAND_CITIES; i < FINISH_INDEX_POLAND_CITIES +1; i++) {
                    cityList.add(City.getById(i).getName());
                }
            } else if (country.equals(Country.LITHUANIA)) {
                for (int i = START_INDEX_LITHUANIA_CITIES; i < FINISH_INDEX_LITHUANIA_CITIES +1; i++) {
                    cityList.add(City.getById(i).getName());
                }
            } else {
                try {
                    throw new PersistentException("Please contact the developer to extend the software application" +
                            " to the level of your region!");
                } catch (PersistentException e) {
                    e.printStackTrace();
                }
            }
        }
        else {
            try {
                throw new PersistentException("The option of the country doesn't matter!");
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        }
        return cityList;
    }

    public static City getCity(String str) {
        City city = null;
        for (int i = 0; i < City.values().length; i++) {
            String cityFromUnum = City.values()[i].toString();
            if (cityFromUnum.equals(str.toUpperCase())) {
                city = City.getById(i);
                break;
            }
        }
        return city;
    }
}
