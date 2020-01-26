package by.javatr.bicrent.action.impl.payment_page;
import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.*;
import by.javatr.bicrent.entity.en_um.Currency;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.service.ServiceException;
import by.javatr.bicrent.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaymentPageCommand extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public PaymentPageCommand() {
        allowedRoles.add(Role.ADMIN);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = null;
        String orderID=request.getParameter("orderid");
        String locationID =request.getParameter("locationid");

        OrderServiceImpl orderService = factoryService.get(DaoSql.OrderDao);
        Order order = orderService.read(Integer.valueOf(orderID));

        try {
        BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
        List<Integer>bicyclesIdList = order.getBicyclesId();
        List<Bicycle>bicycleList = bicycleService.findById(bicyclesIdList);
        bicycleService.changeLocation(bicycleList, Integer.valueOf(locationID.trim()));

        UserInfoServiceImpl userService = factoryService.get(DaoSql.UserInfoDao);
        UserInfo userInfo = null;

            userInfo = userService.findByIdentity(order.getUserId());

        List<VirtualCard>virtualCards = new ArrayList<>();
        VirtualCardServiceImpl virtualCardService = factoryService.get(DaoSql.VirtualCardDao);
        virtualCards = virtualCardService.findByUserId(userInfo.getId());

        request.setAttribute("IdPassportUser", userInfo.getPassportIdentificationNumber());
        request.setAttribute("idPayment", orderID);
        request.setAttribute("cards", virtualCards);
        request.setAttribute("bicycles", bicycleList);

        request.setAttribute("cardBalance", virtualCards.get(0).getBalance());
        request.setAttribute("cardCurrency", virtualCards.get(0).getCurrency());
//        request.setAttribute("orderDuration", orderService.calcDuration(order.getStartTime(),
//                order.getFinishTime()));

        Integer countDurationInMin = orderService.calcDurationInMin(order.getStartTime(),
                order.getFinishTime());
               request.setAttribute("orderDuration", countDurationInMin);

        PriceServiceImpl priceService = factoryService.get(DaoSql.PriceDao);
        Currency currency = priceService.read(bicycleList.get(0).getPriceId()).getCurrency();
        request.setAttribute("orderCurrency", currency.toString());

        BigDecimal ammountForPay = orderService.calcAmmountForPay(bicyclesIdList, countDurationInMin);
        request.setAttribute("orderAmount", ammountForPay);


        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/payment_page.jsp");
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            LOGGER.error("ServletException from PaymentPageCommand =" + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException from PaymentPageCommand =" + e.getMessage());
        }
    }
}
