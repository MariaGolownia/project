package by.javatr.bicrent.action.impl.order_page;

import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.Order;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.impl.BicycleServiceImpl;
import by.javatr.bicrent.service.impl.OrderServiceImpl;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@WebServlet("/startOrder/*")
public class ActionStartOrder extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
public static final Boolean FREE_STATUS_FALSE = false;

    public ActionStartOrder() {
        allowedRoles.add(Role.ADMIN);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String userIdPassword = request.getParameter("idOfPassport");
        String locationID = request.getParameter("idLocation");
        List<Integer> bicycleIdList = new ArrayList<>();
        Integer orderId;
        String[] id = request.getParameter("idVal").split(",");
        for (int i = 0; i < id.length; i++) {
            System.out.println('|'+id[i]+'|');
            if (!id[i].trim().equals(""))
                bicycleIdList.add(Integer.valueOf(id[i]));
        }
        //bicycleIdList = request.getParameter("idVal");
        FactoryService factoryService = FactoryService.getInstance();
        BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
        bicycleService.changeFreeStatus(bicycleIdList, FREE_STATUS_FALSE);

        OrderServiceImpl userOrderService = factoryService.get(DaoSql.OrderDao);
        String json = "";
        orderId = userOrderService.createOrder(userIdPassword, bicycleIdList, locationID);

        Order order = new Order();
        order = userOrderService.readStartOrder(orderId);

        HttpSession session = request.getSession();
        session.setAttribute("newOrderId", Integer.toString(orderId));
        json = new Gson().toJson(order);

        response.setContentType("text/plain");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            LOGGER.error("IOException from ActionStartOrder =" + e.getMessage());
        }
    }
}
