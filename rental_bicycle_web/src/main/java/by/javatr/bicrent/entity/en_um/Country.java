package by.javatr.bicrent.entity.en_um;

public enum Country {
    BELARUS("Belarus"),
    POLAND("Poland"),
    LITHUANIA("Lithuania");

    private String name;

    private Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return ordinal();
    }

    public static Country getById(Integer id) {
        return Country.values()[id];
    }

    public static Country getCountry(String str) {
        Country country = null;
        for (int i = 0; i < Country.values().length; i++) {
            String countryFromEnum = Country.values()[i].toString();
            if (countryFromEnum.equals(str.toUpperCase())) {
                country = Country.getById(i);
            }
        }
        return country;
    }

    public static Boolean ifCountryInSystem(String str) {
        Boolean ifCountryInSystem = false;
        if (str != null) {
            Country country = null;
            for (int i = 0; i < Country.values().length; i++) {
                String countryFromEnum = Country.values()[i].toString();
                if (countryFromEnum.equals(str.toUpperCase())) {
                    ifCountryInSystem = true;
                }
            }
        }
        return ifCountryInSystem;
    }


}
