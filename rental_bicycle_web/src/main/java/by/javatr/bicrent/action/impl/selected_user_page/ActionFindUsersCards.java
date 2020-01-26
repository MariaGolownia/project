package by.javatr.bicrent.action.impl.selected_user_page;


import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.VirtualCard;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.ServiceException;
import by.javatr.bicrent.service.impl.VirtualCardServiceImpl;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GetUserCards/*")
public class ActionFindUsersCards extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // Используем Gson для преобразования массива в структурированный string
        FactoryService factoryService = FactoryService.getInstance();
        List<VirtualCard> virtualCards = new ArrayList<>();
        VirtualCardServiceImpl virtualCardService = factoryService.get(DaoSql.VirtualCardDao);
        String json = "";
        String numberPasportStr = request.getParameter("userDocId").trim();
        if (numberPasportStr == null || "".equals(numberPasportStr)) {
        } else {
            try {
                virtualCards = virtualCardService.findByUserPassportId(numberPasportStr);

                if (virtualCards.size() > 0) {
                    json = new Gson().toJson(virtualCards);
                }
            } catch (ServiceException e) {
                e.printStackTrace();
            }
        }
        response.setContentType("text/plain");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        doGet(request, response);
    }
}