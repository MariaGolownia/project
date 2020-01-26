package by.javatr.bicrent.action.impl.admin.registration_page;
import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.DateConverter;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.UserInfo;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.ServiceException;
import by.javatr.bicrent.service.impl.UserInfoServiceImpl;
import by.javatr.bicrent.service.impl.UserServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditUserPageAdminCommand extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PASSPORTID_USER_PARAM = "userPassportIdentificationNumber";

    public EditUserPageAdminCommand() {
        allowedRoles.add(Role.ADMIN);
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = null;
        String idPassportUser = request.getParameter(PASSPORTID_USER_PARAM);
        FactoryService factoryService = FactoryService.getInstance();
        UserInfoServiceImpl userInfoService = factoryService.get(DaoSql.UserInfoDao);
        UserInfo userInfoFromDB = new UserInfo();

        try {
            if (idPassportUser != null) {
                userInfoFromDB = userInfoService.findByIdNumberPassport(idPassportUser);
                Integer userInfoId = userInfoFromDB.getId();
                if (userInfoFromDB!=null && userInfoId!=null) {
                    UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
                    User userFromDB = userService.findByIdentity(userInfoId);
                    request.setAttribute("role_user", userFromDB.getRole());
                    request.setAttribute("login_user", userFromDB.getLogin());
                    request.setAttribute("surname_user", userInfoFromDB.getSurname());
                    request.setAttribute("name_user", userInfoFromDB.getName());
                    request.setAttribute("second_name_user", userInfoFromDB.getSecondName());
                    request.setAttribute("birth_date_user", DateConverter.converterStringFromDate(userInfoFromDB.getBirthDate()));
                    request.setAttribute("country_user", userInfoFromDB.getCountry());
                    request.setAttribute("pass_issue_date_user", DateConverter.converterStringFromDate(userInfoFromDB.getPassportIssueDate()));
                    request.setAttribute("pass_iss_authotity_user", userInfoFromDB.getPassportIssuingAuthority());
                    request.setAttribute("pass_id_number_user", userInfoFromDB.getPassportIdentificationNumber());
                    request.setAttribute("pass_s_number_user", userInfoFromDB.getPassportSerialNumber());
                    request.setAttribute("address_reg_user", userInfoFromDB.getPassportAddressRegistration());
                    request.setAttribute("address_res_user", userInfoFromDB.getPassportAddressResidence());
                    request.setAttribute("phone_number_user", userInfoFromDB.getPhoneNumber());
                    request.setAttribute("phone_number2_user", userInfoFromDB.getSecondPhoneNumber());
                    request.setAttribute("e_mail_user", userInfoFromDB.getEmail());
                    dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/edit_user_page.jsp");
                }
                else {
                    request.setAttribute("messageUserIsNotExist", "need to show error-message");
                    dispatcher =  request.getRequestDispatcher("/WEB-INF/jsp/selected_user_page.jsp");
                }
            }
            else {
                dispatcher =  request.getRequestDispatcher("/WEB-INF/jsp/selected_user_page.jsp");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        try {
            dispatcher.forward(request, response);
            // передача запроса/управления другому ресурсу на сервере;
            // transfer request / control to another resource on the server;
        } catch (ServletException e) {
            LOGGER.error("ServletException from ServletException =" + e);

        } catch (IOException e) {
            LOGGER.error("IOException from IOException =" + e);
        }
    }
}
