package by.javatr.bicrent.dao;
import by.javatr.bicrent.entity.Entity;
import by.javatr.bicrent.entity.PersistentException;

import java.sql.SQLException;

public interface Dao<Type extends Entity > {

    Integer create(Type entity) throws SQLException;

    Type read(Integer identity) throws  SQLException;

    void update(Type entity) throws  SQLException;

    void delete(Integer identity) throws PersistentException, SQLException;
}
