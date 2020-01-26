package by.javatr.bicrent.service.impl;

import by.javatr.bicrent.dao.UserInfoDao;
import by.javatr.bicrent.dao.mysql.DaoException;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.dao.mysql.FactoryDaoSql;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.UserInfo;
import by.javatr.bicrent.service.Service;
import by.javatr.bicrent.service.ServiceException;
import by.javatr.bicrent.service.UserInfoService;

import java.sql.SQLException;
import java.util.List;

public class UserInfoServiceImpl  extends Service implements UserInfoService {
    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public UserInfo findByIdNumberPassport(String idPassport) throws ServiceException {
        UserInfo userInfo = new UserInfo();
        try {
            UserInfoDao dao = FactoryDaoSql.getInstance().get(DaoSql.UserInfoDao);
            userInfo = dao.readByPassportId(idPassport);
        } catch (DaoException e) {
            e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return userInfo;
    }

    @Override
    public UserInfo findByIdentity(Integer identity) throws ServiceException{
        UserInfo userInfo = new UserInfo();
        try {
            UserInfoDao dao = FactoryDaoSql.getInstance().get(DaoSql.UserInfoDao);
            userInfo = dao.read(identity);
        } catch (DaoException e) {
            e.printStackTrace();
        }
         catch (SQLException e) {
        e.printStackTrace();
    }
        return userInfo;
    }

    @Override
    public void save(UserInfo userInfo) throws ServiceException {
        UserInfoDao dao = null;
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.UserInfoDao);
            dao.create(userInfo);
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(UserInfo userInfoUpdate) throws ServiceException{
        UserInfoDao userInfoDao = null;
            try {
                userInfoDao = FactoryDaoSql.getInstance().get(DaoSql.UserInfoDao);
                userInfoDao.update(userInfoUpdate);
            }
            catch (SQLException ex) {
                ex.printStackTrace();
            }
            catch (DaoException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void delete(Integer identity) throws ServiceException {

    }
}
