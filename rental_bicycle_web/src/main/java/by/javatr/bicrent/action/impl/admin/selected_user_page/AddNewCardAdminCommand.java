package by.javatr.bicrent.action.impl.admin.selected_user_page;

import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.UserInfo;
import by.javatr.bicrent.entity.VirtualCard;
import by.javatr.bicrent.entity.en_um.Currency;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.ServiceException;
import by.javatr.bicrent.service.impl.UserInfoServiceImpl;
import by.javatr.bicrent.service.impl.UserServiceImpl;
import by.javatr.bicrent.service.impl.VirtualCardServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;


public class AddNewCardAdminCommand extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public AddNewCardAdminCommand() {
        allowedRoles.add(Role.ADMIN);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        FactoryService factoryService = FactoryService.getInstance();
        VirtualCardServiceImpl virtualCardService = factoryService.get(DaoSql.VirtualCardDao);
        String json = "";
        Integer virtualCardId = null;
        String numberPasportStr = request.getParameter("userDocId").trim();
        String cardNameStr = request.getParameter("userCardName").trim();
        String cardAmmountStr = request.getParameter("userCardAmmount").trim();
        String cardCcyStr = request.getParameter("userCardCcy").trim();
        VirtualCard virtualCard = new VirtualCard();
        virtualCard.setName(cardNameStr);
        virtualCard.setBalance(BigDecimal.valueOf(Double.valueOf(cardAmmountStr)));

        virtualCard.setCurrency(Currency.getCurrency(Currency.getCodeOfCurrency(cardCcyStr)));
        UserInfo userInfo = new UserInfo();
        UserInfoServiceImpl userInfoService = factoryService.get(DaoSql.UserInfoDao);
        UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
        User user = new User();
        try {
            userInfo = userInfoService.findByIdNumberPassport(numberPasportStr);
            user = userService.findByIdentity(userInfo.getId());

        } catch (ServiceException e) {
            e.printStackTrace();
        }
        virtualCard.setUser(user);

        if (numberPasportStr == null || cardNameStr == null || "".equals(cardNameStr)) {
        } else {
            try {
              virtualCardId = virtualCardService.save(virtualCard);
            } catch (ServiceException e) {
                LOGGER.error("ServiceException from AddNewCardAdminCommand =" + e.getMessage());
            }


        }
        try {
            response.setContentType("text/plain");
            response.getWriter().write(virtualCardId.toString());

        } catch (IOException e) {
            LOGGER.error("IOException from AddNewCardAdminCommand =" + e.getMessage());
        }
    }
}
