package by.javatr.bicrent.service;

import by.javatr.bicrent.entity.Order;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderService {

    Integer getLastOrderId();

    Integer createOrder(Order order);

    Order readStartOrder(Integer orderId);

    Integer createOrder(Integer idUser, List<Integer> idBicycles, Integer idLocation);

    Integer createOrder(String idPasswordUser, List<Integer> bicycles, String idLocation) throws SQLException;

    Order read(Integer identity);

    String setFinishTime (Integer idOrder);

    LocalDateTime calcDuration (LocalDateTime localDateTimeStart, LocalDateTime localDateTimeFinish);

    Integer calcDurationInMin(LocalDateTime localDateTimeStart, LocalDateTime localDateTimeFinish);

    BigDecimal calcAmmountForPay (List<Integer> idBicycles, Integer minutes);

    Map<Integer, BigDecimal> calcAmmountForPay(List<Integer> idBicycles);

    List <Integer> getBicyclesIdByOrder (Integer orderId);
}
