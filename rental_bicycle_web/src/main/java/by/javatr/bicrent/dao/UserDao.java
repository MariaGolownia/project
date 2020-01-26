package by.javatr.bicrent.dao;
import by.javatr.bicrent.entity.PersistentException;
import by.javatr.bicrent.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends Dao<User> {

    User read(String login, String password) throws PersistentException, SQLException;

    List<User> read() throws PersistentException, SQLException;

    User read(String login) throws PersistentException, SQLException;


}
