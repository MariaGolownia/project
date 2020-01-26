package by.javatr.bicrent.action.impl.selected_user_page;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.VirtualCard;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.impl.VirtualCardServiceImpl;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebServlet("/GetUserCardBalance/*")
public class ActionShowBalance extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // Используем Gson для преобразования массива в структурированный string
        FactoryService factoryService = FactoryService.getInstance();
        VirtualCard virtualCard = new VirtualCard();
        VirtualCardServiceImpl virtualCardService = factoryService.get(DaoSql.VirtualCardDao);
        String json = "";
        String cardIdStr = request.getParameter("userCardId").trim();
        
        if (cardIdStr == null || "".equals(cardIdStr)) {
            json = "None";
        } else {
            virtualCard = virtualCardService.read(Integer.valueOf(cardIdStr));
            HttpSession session = request.getSession();
            VirtualCard virtualCardSession = (VirtualCard) session.getAttribute("selectedCard");
            if (virtualCardSession!=null)
                session.removeAttribute("selectedCard");
            session.setAttribute("selectedCard", virtualCard);
            json = new Gson().toJson(virtualCard);
        }
        response.setContentType("text/plain");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        doGet(request, response);
    }

}
