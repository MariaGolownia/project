package by.javatr.bicrent.service.valid;

import by.javatr.bicrent.entity.PersistentException;
import by.javatr.bicrent.dao.UserDao;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.dao.mysql.DaoException;
import by.javatr.bicrent.dao.mysql.FactoryDaoSql;
import by.javatr.bicrent.entity.User;

import java.sql.SQLException;

public class UserServiceValidation {

    public Boolean ifUserExists (User user) {
        Boolean ifUserExists = false;
        User userValid = null;
        UserDao userDao = null;
        try {
            userDao = FactoryDaoSql.getInstance().get(DaoSql.UserDao);
            userValid = userDao.read(user.getLogin(), user.getPassword());
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (userValid != null) {
            ifUserExists = true;
        }
        return ifUserExists;
    }



    public Boolean ifUserLoginExists (String login) {
        Boolean ifUserExists = false;
        User userValid = null;
        UserDao userDao = null;
        try {
            userDao = FactoryDaoSql.getInstance().get(DaoSql.UserDao);
            userValid = userDao.read(login);
        } catch (PersistentException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (userValid != null) {
            ifUserExists = true;
        }
        return ifUserExists;
    }
}
