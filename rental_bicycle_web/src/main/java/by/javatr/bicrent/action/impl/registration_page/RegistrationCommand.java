package by.javatr.bicrent.action.impl.registration_page;
import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.entity.en_um.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationCommand extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USER_MODE = "mode";

    public RegistrationCommand() {
        allowedRoles.add(Role.ADMIN);
        allowedRoles.add(Role.USER);
        allowedRoles.add(Role.GUEST);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = null;
        String mode = request.getParameter(USER_MODE);

        if (mode != null && mode.toLowerCase().equals("admin")){
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/registration_page_adm.jsp");
        }
        else{
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration_page.jsp");
        }

        try {
            dispatcher.forward(request, response);
            //transfer request / control to another resource on the server;
        } catch (ServletException e) {
            LOGGER.error("ServletException from RegistrationCommand =" + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException from RegistrationCommand =" + e.getMessage());
        }
    }
}

