package by.javatr.bicrent.action.impl.authorization_page;

import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.controller.version.VersionClass;
import by.javatr.bicrent.controller.web_data.Attributes;
import by.javatr.bicrent.entity.en_um.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.applet.Main;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class AuthorizationPageCommand extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public AuthorizationPageCommand() {
        allowedRoles.add(Role.ADMIN);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
//Интерфейс RequestDispatcher используется для работы с дополнительными ресурсами,
//к которым относятся другой сервлет, страница JSP или документ HTML.
//Данный интерфейс используется для внутренней коммуникации между сервлетами в одном контексте.
//Доступ к RequestDispatcher можно получить с помощью метода getRequestDispatcher(String url)
//интерфейса ServletContext.
// The RequestDispatcher interface is used to work with additional resources,
// which include another servlet, a JSP page, or an HTML document.
// This interface is used for internal communication between servlets in the same context.
// RequestDispatcher can be accessed using the getRequestDispatcher(String url) method)
// ServletContext interface.

        Date date;
        String todayAsString = "";
        String version = getClass().getPackage().getImplementationVersion();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/authorization_page.jsp");
        try {
            //------------------
            date = null;
            try {
                date = new Date(new File(getClass().getClassLoader().getResource(getClass().getCanonicalName().replace('.', '/') + ".class").toURI()).lastModified());
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            //-------------------
            HttpSession session = request.getSession();
            session.removeAttribute(Attributes.USER_LOGIN);
            if (session.getAttribute("date_build") == "" || session.getAttribute("date_build") == null) {
                String pattern = "yyyy/MM/dd HH:mm:ss";

        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date according to the chosen pattern
                DateFormat df = new SimpleDateFormat(pattern);

        // Get the today date using Calendar object.
                Date today = Calendar.getInstance().getTime();
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
                todayAsString = df.format(today);
                session.setAttribute("date_build", todayAsString);
            }
            VersionClass vc = new VersionClass();

            request.setAttribute("date_build", todayAsString);
            request.setAttribute("version_build", "v." + vc.getVer());
            dispatcher.forward(request, response);
            // передача запроса/управления другому ресурсу на сервере;

        } catch (ServletException e) {
            LOGGER.error("ServletException from AuthorizationPageCommand =" + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException from AuthorizationPageCommand =" + e.getMessage());
        }
    }
}
