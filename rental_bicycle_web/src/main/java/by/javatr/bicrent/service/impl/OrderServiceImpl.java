package by.javatr.bicrent.service.impl;
import by.javatr.bicrent.dao.OrderDao;
import by.javatr.bicrent.dao.UserInfoDao;
import by.javatr.bicrent.dao.mysql.*;
import by.javatr.bicrent.entity.*;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.OrderService;
import by.javatr.bicrent.service.Service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;

public class OrderServiceImpl extends Service implements OrderService {
    private final static Integer COUNT_MIN_IN_HOUR = 60;
    private final static Integer COUNT_HOUR_IN_DAY = 24;
    private final static Integer COUNT_DAY_IN_YEAR = 360;

    @Override
    public Integer getLastOrderId() {
        Integer numberLastId = 0;
        OrderDao dao = null;
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.OrderDao);
            numberLastId = dao.getLastId();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (DaoException e) {
            e.printStackTrace();
        }
        return numberLastId;
    }

    @Override
    public Integer createOrder(Order order) {
        Integer orderId = 0;
        OrderDao dao = null;
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.OrderDao);
            LocalDateTime localDateTimeStart = LocalDateTime.now();
            order.setStartTime(localDateTimeStart);
            orderId = dao.create(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch (DaoException e) {
            e.printStackTrace();
        }
        return orderId;

    }

    @Override
    public Order readStartOrder(Integer orderId) {
        OrderDao dao = null;
        Order order = new Order();
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.OrderDao);
            order = dao.readStartOrder(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public Integer createOrder(Integer idUser, List<Integer> idBicycles, Integer idLocation) {
        Integer orderId = 0;
        Order order = new Order();
        User user = new User();
        try {
            UserDaoSql userDaoSql =  FactoryDaoSql.getInstance().get(DaoSql.UserDao);
            user = userDaoSql.read(idUser);
            order.setUserId(idUser);
            order.setBicyclesId(idBicycles);
            LocalDateTime localDateTimeStart = LocalDateTime.now();
            order.setStartTime(localDateTimeStart);
            order.setStartLocationId(idLocation);
            orderId = createOrder(order);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return orderId;
    }

    @Override
    public Integer createOrder(String idPasswordUser, List<Integer> idBicycles, String idLocation) {
        Integer orderId = 0;
        Order order = new Order();
        UserInfo userInfo = new UserInfo();
        User user = new User();
        try {
            UserInfoDao userInfoDaoSql =  FactoryDaoSql.getInstance().get(DaoSql.UserInfoDao);
            userInfo = userInfoDaoSql.readByPassportId(idPasswordUser);
            order.setUserId(userInfo.getId());
            order.setBicyclesId(idBicycles);
            LocalDateTime localDateTimeStart = LocalDateTime.now();
            order.setStartTime(localDateTimeStart);
            order.setStartLocationId(Integer.valueOf(idLocation));
            orderId = createOrder(order);
        } catch (DaoException e) {
            e.printStackTrace();
    } catch (SQLException e) {
        e.printStackTrace();
    }
        return orderId;
    }

    @Override
    public Order read(Integer orderId) {
        OrderDao dao = null;
        Order order = new Order();
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.OrderDao);
            order = dao.read(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public String setFinishTime(Integer idOrder) {
        String finishTimeStr = "";
        try {
            OrderDaoSql orderDaoSql =  FactoryDaoSql.getInstance().get(DaoSql.OrderDao);
            finishTimeStr = orderDaoSql.selectFinishTime(idOrder);
        } catch (DaoException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return finishTimeStr;
    }

    @Override
    public LocalDateTime calcDuration(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeFinish) {
        Period p = Period.between(localDateTimeStart.toLocalDate(), localDateTimeFinish.toLocalDate());
        LocalDateTime localDateTimeDiff = LocalDateTime.of(p.getYears(),
                p.getMonths(), p.getDays(), localDateTimeFinish.getHour()-localDateTimeStart.getHour(),
                localDateTimeFinish.getMinute() - localDateTimeStart.getMinute());
        return localDateTimeDiff;
    }

    @Override
    public Integer calcDurationInMin(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeFinish) {
        Period p = Period.between(localDateTimeStart.toLocalDate(), localDateTimeFinish.toLocalDate());
        Integer minutes = localDateTimeFinish.getMinute() - localDateTimeStart.getMinute();
        minutes += p.getYears()*COUNT_DAY_IN_YEAR*COUNT_HOUR_IN_DAY*COUNT_MIN_IN_HOUR;
        minutes += p.getDays()*COUNT_HOUR_IN_DAY*COUNT_MIN_IN_HOUR;
        minutes += (localDateTimeFinish.getHour()-localDateTimeStart.getHour())*COUNT_MIN_IN_HOUR;
        return minutes;
    }

    @Override
    public Map<Integer, BigDecimal> calcAmmountForPay(List<Integer> idBicycles) {
//        List<Bicycle> bicycleList = new ArrayList<>();
//        try {
//            BicycleServiceImpl bicycleService =  FactoryDaoSql.getInstance().get(DaoSql.BicycleDao);
//            bicycleList = bicycleService.findById(idBicycles);
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }
        PriceServiceImpl priceService = FactoryService.getInstance().get(DaoSql.PriceDao);
        Price price = new Price();
        BigDecimal countMin = new BigDecimal(0);
        Map<Integer, BigDecimal> countBicMin = new HashMap<>();
        for (int i = 0; i < idBicycles.size(); i++) {
            price = priceService.read(idBicycles.get(i));
            countBicMin.put(idBicycles.get(i), price.getRate());
        }
        return countBicMin;
    }

    @Override
    public List<Integer> getBicyclesIdByOrder(Integer orderId) {
        List <Integer> bicylesId = new ArrayList<>();
        OrderDao dao = null;
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.OrderDao);
            bicylesId = dao.getBicyclesByOrderId(orderId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return bicylesId;
    }


    public BigDecimal calcAmmountForPay(List<Integer> idBicycles, Integer minutes) {
        List<Bicycle> bicycleList = new ArrayList<>();
        FactoryService factoryService = FactoryService.getInstance();
            BicycleServiceImpl bicycleService =  factoryService.get(DaoSql.BicycleDao);
            bicycleList = bicycleService.findById(idBicycles);
        PriceServiceImpl priceService = FactoryService.getInstance().get(DaoSql.PriceDao);
        Price price = new Price();
        BigDecimal countMin = new BigDecimal(0);
        BigDecimal countMinTemp = new BigDecimal(0);
        Map<Integer, BigDecimal> countBicMin = new HashMap<>();
        for (int i = 0; i < bicycleList.size(); i++) {
            price = priceService.read(bicycleList.get(i).getPriceId());
            if (minutes != 0)
            countMinTemp = price.getRate().multiply(BigDecimal.valueOf(minutes));
            countMin = countMin.add(countMinTemp);
        }
        return countMin;
    }
}
