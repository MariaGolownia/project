package by.javatr.bicrent.entity;

import by.javatr.bicrent.entity.en_um.City;
import by.javatr.bicrent.entity.en_um.Country;

public class Location extends Entity {
    private String name;
    private Company company;
    private Country country;
    private City city;
    private String street;
    private String house;
    private String office;
    public Long phone;
    public Long secondPhone;
    public String photo;

    public Location(Company company) {
        this.company = company;
    }

    public Location() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Long getSecondPhone() {
        return secondPhone;
    }

    public void setSecondPhone(Long secondPhone) {
        this.secondPhone = secondPhone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                ", company=" + company +
                ", country=" + country +
                ", city=" + city +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                ", office='" + office + '\'' +
                ", phone=" + phone +
                ", secondPhone=" + secondPhone +
                ", photo='" + photo + '\'' +
                '}';
    }

    public String withoutCountryAndCityToString() {
        return (street + ", house: " + house + ", office: " + office);
    }


}


