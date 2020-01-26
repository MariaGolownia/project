package by.javatr.bicrent.entity;
import by.javatr.bicrent.entity.en_um.BicycleType;
import by.javatr.bicrent.entity.en_um.Currency;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Bicycle extends Entity {
    private String model;
    private BicycleType bicycleType;
    private Short productionYear;
    private String producer;
    private Location currentLocation;
    private Blob photo;
    private String photoBlobStr;
    private Boolean ifNotBooked;
    private Boolean ifFree;
    private Integer priceId;
    private BigDecimal rate;
    private Currency currency;
    private List<Order> rentList = new ArrayList<>();

    public String getPhotoBlobStr() {
        return photoBlobStr;
    }

    public void setPhotoBlobStr(String photoBlobStr) {
        this.photoBlobStr = photoBlobStr;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public BicycleType getBicycleType() {
        return bicycleType;
    }

    public void setBicycleType(BicycleType bicycleType) {
        this.bicycleType = bicycleType;
    }

    public Short getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Short productionYear) {
        this.productionYear = productionYear;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public List<Order> getRentList() {
        return rentList;
    }

    public void setRentList(List<Order> rentList) {
        this.rentList = rentList;
    }

    public Boolean getIfNotBooked() {
        return ifNotBooked;
    }

    public Integer getIfNotBookedInt() {
        return ifNotBooked == true ? 1 : 0;
    }

    public void setIfNotBooked(Boolean ifNotBooked) {
        this.ifNotBooked = ifNotBooked;
    }

    public Integer getPriceId() {
        return priceId;
    }

    public void setPriceId(Integer priceId) {
        this.priceId = priceId;
    }

    public void setIfNotBooked(Integer ifNotBooked) {
        if (ifNotBooked == 1) {
        this.ifNotBooked = true;}
        else this.ifNotBooked = false;
    }

    public Boolean getIfFree() {
        return ifFree;
    }

    public Integer getIfFreeInt() {
        return ifFree == true ? 1 : 0;
    }

    public void setIfFree(Boolean ifFree) {
        this.ifFree = ifFree;
    }

    public void setIfFree(Integer ifFree) {
        if (ifFree == 1) {
            this.ifFree = true;}
        else this.ifFree = false;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setCurrency(String currency) {
        this.currency = Currency.getCurrency(currency);
    }





    @Override
    public String toString() {
        return "Bicycle{" +
                "id='" + super.toString() + '\'' +
                "model='" + model + '\'' +
                ", bicycleType=" + bicycleType +
                ", productionYear=" + productionYear +
                ", producer='" + producer + '\'' +
                ", currentLocation=" + currentLocation +
                ", photo=" + photo +
                ", photoBlobStr='" + photoBlobStr + '\'' +
                ", ifNotBooked=" + ifNotBooked +
                ", ifFree=" + ifFree +
                ", priceId=" + priceId +
                ", rate=" + rate +
                ", currency=" + currency +
                ", rentList=" + rentList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Bicycle)) return false;
        Bicycle bicycle = (Bicycle) o;
        return Objects.equals(getModel(), bicycle.getModel()) &&
                getBicycleType() == bicycle.getBicycleType() &&
                Objects.equals(getProductionYear(), bicycle.getProductionYear()) &&
                Objects.equals(getProducer(), bicycle.getProducer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getModel(), getBicycleType(), getProductionYear(), getProducer(), getCurrentLocation(), getPhoto(), getPriceId(), getIfNotBooked(), getIfFree(), getRentList());
    }
}
