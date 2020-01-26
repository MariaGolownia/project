package by.javatr.bicrent.action.impl;
import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.entity.Location;
import by.javatr.bicrent.entity.UserInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MainPageCommand extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        Location selectedLocation = (Location) session.getAttribute("selectedLocation");
        if (selectedLocation != null) {
            String selectedLocationInformation = selectedLocation.getCountry() + ", " +
                    selectedLocation.getCity() + ", " + selectedLocation.withoutCountryAndCityToString();
            request.setAttribute("locationName", selectedLocationInformation);
        }

        UserInfo selectedUserInfo = (UserInfo) session.getAttribute("selectedUserInfo");
        if (selectedUserInfo != null) {
            String selectedUserInformation = selectedUserInfo.getSurname() + ", " +
                    selectedUserInfo.getName() + ", " + selectedUserInfo.getPassportIdentificationNumber();
            request.setAttribute("userName", selectedUserInformation);
        }

        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main_page.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            LOGGER.error("ServletException from MainPageCommand =" + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException from MainPageCommand =" + e.getMessage());
        }
    }
}
