package by.javatr.bicrent.action.impl.payment_page;

import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.VirtualCard;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.impl.VirtualCardServiceImpl;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

public class DoMainPay extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Boolean result = false;
        VirtualCardServiceImpl virtualCardService = factoryService.get(DaoSql.VirtualCardDao);
        String json = "";
        String cardIdStr = request.getParameter("userCardId").trim();
        String cardAmmountStr = request.getParameter("ammountForPay").trim();

        if (cardIdStr == null || "".equals(cardIdStr)) {
            json = result.toString();
        } else {
            BigDecimal ammountBigDecimal = new BigDecimal(cardAmmountStr).multiply(new BigDecimal(-1));
            result = virtualCardService.topUp(Integer.valueOf(cardIdStr), ammountBigDecimal);

            json = result.toString();
        }
        response.setContentType("text/plain");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            LOGGER.error("IOException from OrderPageCommand =" + e.getMessage());
        }
    }
}
