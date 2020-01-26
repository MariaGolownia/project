package by.javatr.bicrent.dao;
import by.javatr.bicrent.entity.Company;

import java.sql.SQLException;


public interface CompanyDao extends Dao<Company> {

    Company readByAccountNumberOfPayer(Integer search) throws SQLException;
}
