package by.javatr.bicrent.service.impl;

import by.javatr.bicrent.dao.PriceDao;
import by.javatr.bicrent.dao.mysql.DaoException;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.dao.mysql.FactoryDaoSql;
import by.javatr.bicrent.entity.Price;
import by.javatr.bicrent.service.PriceService;
import by.javatr.bicrent.service.Service;

import java.sql.SQLException;

public class PriceServiceImpl extends Service implements PriceService {
    @Override
    public Price read(Integer identity) {
        PriceDao dao = null;
        Price price = new Price();
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.PriceDao);
            price = dao.read(identity);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return price;
    }
}
