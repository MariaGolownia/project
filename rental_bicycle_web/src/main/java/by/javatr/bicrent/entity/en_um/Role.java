package by.javatr.bicrent.entity.en_um;

public enum Role {
    ADMIN("admin"),
    USER("user"),
    GUEST("Guest");

    private String name;

    private Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return ordinal();
    }

    public static Role getById(Integer id) {
        return Role.values()[id];
    }
}
