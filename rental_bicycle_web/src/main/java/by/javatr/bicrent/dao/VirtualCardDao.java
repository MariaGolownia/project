package by.javatr.bicrent.dao;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.VirtualCard;

import java.sql.SQLException;
import java.util.List;

public interface VirtualCardDao extends Dao<VirtualCard> {

    List<VirtualCard> readByUser (User search) throws SQLException;

    List<VirtualCard>  readByUserId(Integer id) throws SQLException;

    VirtualCard read(Integer id) throws SQLException;

    void update(VirtualCard virtualCard) throws SQLException;
}
