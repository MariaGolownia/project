package by.javatr.bicrent.dao;
import by.javatr.bicrent.entity.en_um.Currency;
import by.javatr.bicrent.entity.Price;

import java.sql.SQLException;
import java.util.List;

public interface PriceDao extends Dao<Price> {

    List<Price> readByCurrency(Currency currency) throws SQLException;

}
