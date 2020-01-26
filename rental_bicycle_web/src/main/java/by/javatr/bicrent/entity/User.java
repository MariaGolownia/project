package by.javatr.bicrent.entity;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.entity.en_um.UserStatus;

import java.util.ArrayList;
import java.util.List;

public class User extends Entity {
    private String login;
    private String password;
    private Role role;
    private UserStatus userStatus;
    private List<VirtualCard> virtualCardList = new ArrayList<>();
    private List<Order> rentList = new ArrayList<>();

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<VirtualCard> getVirtualCardList() {
        return virtualCardList;
    }

    public void setVirtualCardList(List<VirtualCard> virtualCardList) {
        this.virtualCardList = virtualCardList;
    }

    public List<Order> getRentList() {
        return rentList;
    }

    public void setRentList(List<Order> rentList) {
        this.rentList = rentList;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", userStatus=" + userStatus +
                ", virtualCardList=" + virtualCardList +
                ", rentList=" + rentList +
                "}\n";
    }
}
