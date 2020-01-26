package by.javatr.bicrent.service.impl;

import by.javatr.bicrent.dao.VirtualCardDao;
import by.javatr.bicrent.dao.mysql.DaoException;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.dao.mysql.FactoryDaoSql;
import by.javatr.bicrent.entity.UserInfo;
import by.javatr.bicrent.entity.VirtualCard;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.Service;
import by.javatr.bicrent.service.ServiceException;
import by.javatr.bicrent.service.VirtualCardService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VirtualCardServiceImpl extends Service implements VirtualCardService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Integer save(VirtualCard virtualCard) throws ServiceException {
        VirtualCardDao dao = null;
        Integer virtualCardId = null;
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.VirtualCardDao);
            virtualCardId = dao.create(virtualCard);

        } catch (DaoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return virtualCardId;
    }

    @Override
    public List<VirtualCard> findByUserId(Integer userId) {
        List<VirtualCard> virtualCards = new ArrayList<>();
        try {
            VirtualCardDao virtualCardDao = FactoryDaoSql.getInstance().get(DaoSql.VirtualCardDao);
            virtualCards = virtualCardDao.readByUserId(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return virtualCards;
    }

    @Override
    public List<VirtualCard> findByUserPassportId(String userPassportId) throws ServiceException {
        List<VirtualCard> virtualCards = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        Integer userId = null;
        try {
            UserInfoServiceImpl userInfoDao = FactoryService.getInstance().get(DaoSql.UserInfoDao);
            userInfo = userInfoDao.findByIdNumberPassport(userPassportId);
            userId = userInfo.getId();
            if (userId != null) {
                VirtualCardDao virtualCardDao = FactoryDaoSql.getInstance().get(DaoSql.VirtualCardDao);
                virtualCards = virtualCardDao.readByUserId(userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return virtualCards;
    }

    @Override
    public BigDecimal readBalance(String cardId) {
        BigDecimal balance = new BigDecimal(0.00);
        VirtualCard virtualCard = new VirtualCard();
        Integer userId = null;
        VirtualCardDao virtualCardService = FactoryService.getInstance().get(DaoSql.VirtualCardDao);
        try {
            virtualCard = virtualCardService.read(Integer.valueOf(cardId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        balance = virtualCard.getBalance();
        return balance;
    }

    @Override
    public VirtualCard read(Integer id) {
        VirtualCard virtualCard = new VirtualCard();
        try {
            VirtualCardDao virtualCardDao = FactoryDaoSql.getInstance().get(DaoSql.VirtualCardDao);
            virtualCard = virtualCardDao.read(id);

        } catch (DaoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return virtualCard;
    }

    @Override
    public Boolean topUp(Integer cardId, BigDecimal ammount) {
        Boolean result = false;
        VirtualCardServiceImpl virtualCardService = new VirtualCardServiceImpl();
        VirtualCard virtualCard = virtualCardService.read(cardId);
        BigDecimal currentBalance = virtualCard.getBalance();
        currentBalance = currentBalance.add(ammount);
        virtualCard.setBalance(currentBalance);
        try {
            VirtualCardDao virtualCardDao = FactoryDaoSql.getInstance().get(DaoSql.VirtualCardDao);
            virtualCardDao.update(virtualCard);
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return result;
    }

}
