package by.javatr.bicrent.action.impl.admin.order_page;

import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.action.validator.DataValidator;
import by.javatr.bicrent.action.validator.IncorrectDataException;
import by.javatr.bicrent.action.validator.UserDataValidator;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.Bicycle;
import by.javatr.bicrent.entity.Location;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.UserInfo;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.service.ServiceException;
import by.javatr.bicrent.service.impl.BicycleServiceImpl;
import by.javatr.bicrent.service.impl.LocationServiceImpl;
import by.javatr.bicrent.service.impl.OrderServiceImpl;
import by.javatr.bicrent.service.impl.UserInfoServiceImpl;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// if mode = 1 => work/return with location
// if mode = 2 => work/return with user
// if mode = 3 => work/return with list of bicycles
// if mode = 4 => work/return with date
public class FindOrder extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public FindOrder() {
        allowedRoles.add(Role.ADMIN);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = null;
        String returnJson = null;
        Integer orderId = null;
        String orderIdStr = request.getParameter("orderId");
        String modeStr = request.getParameter("mode");
        Boolean flagValidationSuccess = true;

        try {
            DataValidator.checkIfNumber(orderIdStr);
            orderId = Integer.valueOf(orderIdStr);
        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
        }

        if (flagValidationSuccess) {
            OrderServiceImpl userOrderService = factoryService.get(DaoSql.OrderDao);
            if (modeStr.equals("1")) {
                Integer locationId = userOrderService.read(orderId).getStartLocationId();
                LocationServiceImpl locationService = factoryService.get(DaoSql.LocationDao);
                Location location = locationService.findById(locationId);

                returnJson = new Gson().toJson(location);
            } else if (modeStr.equals("2")) {
                UserInfo user = null;
                Integer userId = userOrderService.read(orderId).getUserId();
                UserInfoServiceImpl userInfoService = factoryService.get(DaoSql.UserInfoDao);
                try {
                    user = userInfoService.findByIdentity(userId);

                    System.out.println(user.toString());
                    returnJson = new Gson().toJson(user);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }

            } else if (modeStr.equals("3")) {
                List<Bicycle> bicycleList = new ArrayList<>();
                List<Integer> bicycleListId = userOrderService.read(orderId).getBicyclesId();
                BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
                for (int i = 0; i < bicycleListId.size(); i++) {
                    Bicycle bicycle = bicycleService.findById(bicycleListId.get(i));
                    bicycleList.add(bicycle);
                }

                System.out.println(bicycleList.toString());
                returnJson = new Gson().toJson(bicycleList);

            } else if (modeStr.equals("4")) {
                List<String> dateListStr = new ArrayList<>();
                LocalDateTime dateTimeStart = userOrderService.read(orderId).getStartTime();
                LocalDateTime dateTimeFinish = userOrderService.read(orderId).getFinishTime();
                dateListStr.add(dateTimeStart.toString());
                if (dateTimeFinish != null)
                    dateListStr.add(dateTimeFinish.toString());
                else dateListStr.add("");

                returnJson = new Gson().toJson(dateListStr);
            }
        }

        response.setContentType("text/plain");
        try {
            response.getWriter().write(returnJson);
        } catch (IOException e) {
            LOGGER.error("ServletException from FindOrder =" + e);
        }
    }
}
