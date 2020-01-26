package by.javatr.bicrent.service;

import by.javatr.bicrent.entity.PersistentException;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.UserInfo;

import java.util.List;

public interface UserInfoService {

    List<User> findAll() throws PersistentException;

    UserInfo findByIdNumberPassport(String idPassport) throws ServiceException;

    UserInfo findByIdentity(Integer identity) throws ServiceException;

    void save(UserInfo userInfo) throws ServiceException;

    void update(UserInfo userInfoUpdate) throws ServiceException;

    void delete(Integer identity) throws ServiceException;

}
