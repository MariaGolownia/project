package by.javatr.bicrent.dao;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.UserInfo;

import java.sql.SQLException;
import java.util.List;

public interface UserInfoDao extends Dao<UserInfo> {

    UserInfo readByUser(User search) throws SQLException;

    List<UserInfo> readByCountry(String search) throws SQLException;

    UserInfo readByPassportId(String search) throws SQLException;
}
