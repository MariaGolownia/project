package by.javatr.bicrent.entity;
import by.javatr.bicrent.entity.en_um.Country;

import java.time.LocalDate;

public class UserInfo extends Entity {
    private String surname;
    private String name;
    private String secondName;
    private LocalDate birthDate;
    private Country country;
    private LocalDate passportIssueDate;
    private String passportIssuingAuthority;
    private String passportIdentificationNumber;
    private String passportSerialNumber;
    private String passportAddressRegistration;
    private String passportAddressResidence;
    private Long phoneNumber;
    private Long secondPhoneNumber;
    private String email;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        System.out.println(country);
        if (country != null && Country.ifCountryInSystem(country.getName()))
            this.country = country;
    }

    public void setCountry(String country) {
        if (Country.ifCountryInSystem(country))
            this.country = Country.valueOf(country.toUpperCase());
    }

    public LocalDate getPassportIssueDate() {
        return passportIssueDate;
    }

    public void setPassportIssueDate(LocalDate passportIssueDate) {
        this.passportIssueDate = passportIssueDate;
    }

    public String getPassportIssuingAuthority() {
        return passportIssuingAuthority;
    }

    public void setPassportIssuingAuthority(String passportIssuingAuthority) {
        this.passportIssuingAuthority = passportIssuingAuthority;
    }

    public String getPassportIdentificationNumber() {
        return passportIdentificationNumber;
    }

    public void setPassportIdentificationNumber(String passportIdentificationNumber) {
        this.passportIdentificationNumber = passportIdentificationNumber;
    }

    public String getPassportSerialNumber() {
        return passportSerialNumber;
    }

    public void setPassportSerialNumber(String passportSerialNumber) {
        this.passportSerialNumber = passportSerialNumber;
    }

    public String getPassportAddressRegistration() {
        return passportAddressRegistration;
    }

    public void setPassportAddressRegistration(String passportAdressRegistration) {
        this.passportAddressRegistration = passportAdressRegistration;
    }

    public String getPassportAddressResidence() {
        return passportAddressResidence;
    }

    public void setPassportAddressResidence(String passportAdressResidence) {
        this.passportAddressResidence = passportAdressResidence;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getSecondPhoneNumber() {
        return secondPhoneNumber;
    }

    public void setSecondPhoneNumber(Long secondPhoneNumber) {
        this.secondPhoneNumber = secondPhoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", secondName='" + secondName + '\'' +
                ", birthDate=" + birthDate +
                ", country=" + country +
                ", passportIssueDate=" + passportIssueDate +
                ", passportIssuingAuthority='" + passportIssuingAuthority + '\'' +
                ", passportIdentificationNumber='" + passportIdentificationNumber + '\'' +
                ", passportSerialNumber='" + passportSerialNumber + '\'' +
                ", passportAddressRegistration='" + passportAddressRegistration + '\'' +
                ", passportAddressResidence='" + passportAddressResidence + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", secondPhoneNumber=" + secondPhoneNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
