package by.javatr.bicrent.entity;
import by.javatr.bicrent.entity.en_um.Currency;
import java.math.BigDecimal;

public class VirtualCard extends Entity {
    private String name;
    private User user;
    private BigDecimal balance;
    private Currency currency;

    public VirtualCard(String name, User user) {
        this.name = name;
        this.user = user;
    }

    public VirtualCard() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "VirtualCard{" +
                "name='" + name + '\'' +
                ", user=" + user +
                ", balance=" + balance +
                ", currency=" + currency +
                "}\n";
    }
}
