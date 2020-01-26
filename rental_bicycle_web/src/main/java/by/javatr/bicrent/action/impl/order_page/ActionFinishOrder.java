package by.javatr.bicrent.action.impl.order_page;

import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.Order;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.impl.BicycleServiceImpl;
import by.javatr.bicrent.service.impl.OrderServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/finishOrder/*")
public class ActionFinishOrder extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    public static final Boolean FREE_STATUS_TRUE = true;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Integer orderId = Integer.valueOf(request.getParameter("idOrder"));
        String finishTimeStr = "";
        FactoryService factoryService = FactoryService.getInstance();
        OrderServiceImpl userOrderService = factoryService.get(DaoSql.OrderDao);
        String json = "";
        finishTimeStr = userOrderService.setFinishTime(orderId);
        response.setContentType("text/plain");

        List<Integer> bicycleIdList = new ArrayList<>();
        Order order = userOrderService.read(orderId);
        bicycleIdList = order.getBicyclesId();
//     Обращение для поиска велосипедов по idOrder
        BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
        bicycleService.changeFreeStatus(bicycleIdList, FREE_STATUS_TRUE);

        try {
            response.getWriter().write(finishTimeStr);
        } catch (IOException e) {
            LOGGER.error("IOException from ActionFinishOrder =" + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}
