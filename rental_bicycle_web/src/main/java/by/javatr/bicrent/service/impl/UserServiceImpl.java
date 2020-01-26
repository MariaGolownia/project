package by.javatr.bicrent.service.impl;
import by.javatr.bicrent.entity.PersistentException;
import by.javatr.bicrent.dao.UserDao;
import by.javatr.bicrent.dao.mysql.DaoException;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.dao.mysql.FactoryDaoSql;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.service.Service;
import by.javatr.bicrent.service.ServiceException;
import by.javatr.bicrent.service.UserService;
import by.javatr.bicrent.service.valid.UserServiceValidation;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl extends Service implements UserService {
    @Override
    public List<User> findAll() {
        UserDao dao = null;
        List<User> users = new ArrayList<>();
        try {
            try {
                dao = FactoryDaoSql.getInstance().get(DaoSql.UserDao);
                try {
                    users = dao.read();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public User findByIdentity(Integer id) {
        UserDao dao = null;
        User user = null;
            try {
                dao = FactoryDaoSql.getInstance().get(DaoSql.UserDao);
                try {
                    user = dao.read(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        catch (DaoException e) {
            e.printStackTrace();
        }
        return user;
    }


    @Override
    public User findByLogin(String login) {
        UserDao dao = null;
        User user = null;
        try {
            try {
                dao = FactoryDaoSql.getInstance().get(DaoSql.UserDao);
                try {
                    user = dao.read(login);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User findByLoginAndPassword(String login, String password) {
        UserDao dao = null;
        User user = null;
        try {
            try {
                dao = FactoryDaoSql.getInstance().get(DaoSql.UserDao);
                try {
                    user = dao.read(login, password);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (PersistentException e) {
                e.printStackTrace();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public Integer save(User user) throws ServiceException {
        UserDao dao = null;
        Integer id = null;
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.UserDao);
            try {
                id = dao.create(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return id;
    }

    @Override
    public void update(User user, User userUpdate) {
        UserDao dao = null;
        UserServiceValidation userServiceValidation = new UserServiceValidation();
        if (userServiceValidation.ifUserExists(user)) {

            try {
                UserDao userDao = FactoryDaoSql.getInstance().get(DaoSql.UserDao);
                userDao.update(userUpdate);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
             catch (DaoException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void delete(Integer id)  {
        UserDao userDao = null;
        try {
            userDao = FactoryDaoSql.getInstance().get(DaoSql.UserDao);
            userDao.delete(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        catch (PersistentException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getHashCodePassword(String passwordStr) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            String salt = "some_random_salt";
            String passWithSalt = passwordStr + salt;
            byte[] passBytes = passWithSalt.getBytes();
            byte[] passHash = sha256.digest(passBytes);
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< passHash.length ;i++) {
                sb.append(Integer.toString((passHash[i] & 0xff) + 0x100, 16).substring(1));
            }
            String generatedPassword = sb.toString();
            return generatedPassword;
        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        return null;
    }
}
