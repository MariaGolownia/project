package by.javatr.bicrent.dao.mysql;

import by.javatr.bicrent.dao.PaymentDao;
import by.javatr.bicrent.entity.Payment;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
//TODO: to realize
public class PaymentDaoSql extends BaseDaoSql implements PaymentDao {

    public PaymentDaoSql(Connection connection) {
        this.connection = connection;
    }

    protected PaymentDaoSql() {
        super();
    }

    @Override
    public List<Payment> readByDate(Date search) throws SQLException {
        return null;
    }

    @Override
    public List<Payment> readByIfPaid(Boolean search) throws SQLException {
        return null;
    }

    @Override
    public Integer create(Payment entity) throws SQLException {
        return null;
    }

    @Override
    public Payment read(Integer identity) throws SQLException {
        return null;
    }

    @Override
    public void update(Payment entity) throws SQLException {

    }

    @Override
    public void delete(Integer identity) throws SQLException {

    }
}
