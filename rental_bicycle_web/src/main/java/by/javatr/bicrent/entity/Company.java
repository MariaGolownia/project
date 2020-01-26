package by.javatr.bicrent.entity;

import java.util.Objects;

public class Company extends Entity {
    private String name;
    private Integer accountNumberOfPayer;

    public Company(String s) {
        this.name = s;
    }

    public Company() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAccountNumberOfPayer() {
        return accountNumberOfPayer;
    }

    public void setAccountNumberOfPayer(Integer accountNumberOfPayer) {
        this.accountNumberOfPayer = accountNumberOfPayer;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", accountNumberOfPayer='" + accountNumberOfPayer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (this==null || o==null) return false;
        if (!(o instanceof Company)) return false;
        if (!super.equals(o)) return false;
        Company company = (Company) o;
        return Objects.equals(getName(), company.getName()) &&
                Objects.equals(getAccountNumberOfPayer(), company.getAccountNumberOfPayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getAccountNumberOfPayer());
    }
}
