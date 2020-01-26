package by.javatr.bicrent.entity.en_um;

public enum BicycleType {
    UNIVERSAL("universal"),
    HIGHWAY("highway"),
    HIGHLAND("highland");

    public final static int INDEX_TIME_UNIT_BY_DEFAULT = 0;

    private String name;

    private BicycleType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getIdentity() {
        return ordinal();
    }

    public static BicycleType getById(Integer id) {
        return BicycleType.values()[id];
    }

    public static BicycleType getBicycleType(String str) {
        BicycleType bicycleType = null;
        for (int i = 0; i < bicycleType.values().length; i++) {
            String bicycleTypeFromEnum = BicycleType.values()[i].toString();
            if (bicycleTypeFromEnum.equals(str.toUpperCase())) {
                bicycleType = BicycleType.getById(i);
            }
            else bicycleType = BicycleType.getById(INDEX_TIME_UNIT_BY_DEFAULT);
        }
        return bicycleType;
    }
}
