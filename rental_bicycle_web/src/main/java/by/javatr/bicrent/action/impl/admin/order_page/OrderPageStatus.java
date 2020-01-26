package by.javatr.bicrent.action.impl.admin.order_page;

import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.entity.en_um.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderPageStatus extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public OrderPageStatus() {
        allowedRoles.add(Role.ADMIN);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {

        RequestDispatcher dispatcher = null;
        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/order_page_status.jsp");

        try {
            dispatcher.forward(request, response);
            // передача запроса/управления другому ресурсу на сервере;
        } catch (ServletException e) {
            LOGGER.error("ServletException from OrderPageStatus =" + e);
        } catch (IOException e) {
            LOGGER.error("IOException from OrderPageStatus =" + e);
        }
    }
}
