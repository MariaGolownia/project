package by.javatr.bicrent.dao;
import by.javatr.bicrent.entity.Payment;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface PaymentDao extends Dao<Payment> {

    List<Payment> readByDate(Date search) throws SQLException;

    List<Payment> readByIfPaid(Boolean search) throws SQLException;
}
