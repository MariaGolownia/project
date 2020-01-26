package by.javatr.bicrent.action.impl.authorization_page;
import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.controller.web_data.Attributes;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthorizationPageUserSubmitCommand extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public AuthorizationPageUserSubmitCommand() {
        allowedRoles.add(Role.ADMIN);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.error("AuthorizationPageUserSubmitCommand");
        RequestDispatcher dispatcherSuccess = null;
        String userLogin = request.getParameter(Attributes.USER_LOGIN);
        String userPassword = request.getParameter(Attributes.USER_PASSWORD);
        UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
        userPassword = userService.getHashCodePassword(userPassword);
        User userByLoginAndPassword = userService.findByLoginAndPassword(userLogin, userPassword);
        HttpSession session = request.getSession();
        Role userRole=null;
        if (userByLoginAndPassword != null || session.getAttribute(Attributes.USER_LOGIN)!=null) {
            if (session.getAttribute(Attributes.USER_LOGIN)!=null)
                userRole=(Role)session.getAttribute(Attributes.USER_ROLE);
            else
                userRole=userByLoginAndPassword.getRole();

            if (session.getAttribute(Attributes.USER_LOGIN)==null) {
                session.setAttribute(Attributes.USER_LOGIN, userLogin);
                session.setAttribute(Attributes.USER_ROLE, userRole);
            }

            if (userRole.equals(Role.ADMIN)) {
                dispatcherSuccess = request.getRequestDispatcher("/WEB-INF/jsp/main_page.jsp");
            }
            else {
                dispatcherSuccess = request.getRequestDispatcher("/WEB-INF/jsp/location_page.jsp");
            }
        } else {
            //User userByLogin = userService.findByLogin(userLogin);
            request.setAttribute("loginUser", userLogin);
            request.setAttribute("loginErr", "Please check your password is correct!");
            dispatcherSuccess = request.getRequestDispatcher("/WEB-INF/jsp/authorization_page.jsp");

        }
        try {
            dispatcherSuccess.forward(request, response);
        } catch (ServletException e) {
            LOGGER.error("ServletException from AuthorizationPageUserSubmitCommand =" + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException from AuthorizationPageUserSubmitCommand =" + e.getMessage());
        }
    }
}
