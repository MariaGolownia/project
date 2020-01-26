package by.javatr.bicrent.action.impl.selected_user_page;

import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.VirtualCard;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.impl.VirtualCardServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class GetTopUpBalance extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        // Используем Gson для преобразования массива в структурированный string
        FactoryService factoryService = FactoryService.getInstance();
        VirtualCardServiceImpl virtualCardService = factoryService.get(DaoSql.VirtualCardDao);
        String json = "";
        String cardIdStr = request.getParameter("userCardId").trim();
        String cardAmmountStr = request.getParameter("userCardAmmount").trim();

        if (cardIdStr == null || "".equals(cardIdStr)) {
            json = "None";
        } else {
            virtualCardService.topUp(Integer.valueOf(cardIdStr), new BigDecimal(cardAmmountStr));
            VirtualCard virtualCard = virtualCardService.read(Integer.valueOf(cardIdStr));
            json = virtualCard.getBalance().toString();
        }
        response.setContentType("text/plain");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
