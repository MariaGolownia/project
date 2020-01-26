package by.javatr.bicrent.entity;
import by.javatr.bicrent.entity.en_um.Currency;
import by.javatr.bicrent.entity.en_um.TimeUnit;

import java.math.BigDecimal;

public class Price extends Entity {
    private Currency currency;
    private TimeUnit unitTime;
    private BigDecimal rate;
    private  Integer bookMaxTimeInMin;
    private BigDecimal bookRate;

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public TimeUnit getUnitTime() {
        return unitTime;
    }

    public void setUnitTime(TimeUnit unitTime) {
        this.unitTime = unitTime;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Integer getBookMaxTimeInMin() {
        return bookMaxTimeInMin;
    }

    public void setBookMaxTimeInMin(Integer bookMaxTimeInMin) {
        this.bookMaxTimeInMin = bookMaxTimeInMin;
    }

    public BigDecimal getBookRate() {
        return bookRate;
    }

    public void setBookRate(BigDecimal bookRate) {
        this.bookRate = bookRate;
    }

    @Override
    public String toString() {
        return "Price{" +
                "currency=" + currency +
                ", unitTime=" + unitTime +
                ", rate=" + rate +
                ", bookMaxTimeInMin=" + bookMaxTimeInMin +
                ", bookRate=" + bookRate +
                '}';
    }
}
