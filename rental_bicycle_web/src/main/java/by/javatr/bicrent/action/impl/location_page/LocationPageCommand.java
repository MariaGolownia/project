package by.javatr.bicrent.action.impl.location_page;

import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.Location;
import by.javatr.bicrent.entity.en_um.City;
import by.javatr.bicrent.entity.en_um.Country;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.service.impl.LocationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LocationPageCommand extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public LocationPageCommand() {
        allowedRoles.add(Role.ADMIN);
        allowedRoles.add(Role.USER);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = null;
        List<String> listCity = new ArrayList<>();
        List<Location> listLocation = new ArrayList<>();

        HttpSession session = request.getSession();
        Location selectedLocation = (Location) session.getAttribute("selectedLocation");
        Role userRole = (Role) session.getAttribute("role");

        String locationImg = "";
        if (userRole != null && userRole.equals(Role.ADMIN)){
            request.setAttribute("isAdminTag", "1");
        }
        if (selectedLocation != null) {
            request.setAttribute("selectedCountry", selectedLocation.getCountry().getName());

            listCity = City.getCitiesStrByCountry(Country.valueOf(selectedLocation.getCountry().getName().toUpperCase()));
            request.setAttribute("selectedCity", selectedLocation.getCity().getName());
            request.setAttribute("citys", listCity);

            LocationServiceImpl locationService = factoryService.get(DaoSql.LocationDao);

            listLocation = locationService.findByCountryAndCity(Country.valueOf(selectedLocation.getCountry().getName().toUpperCase()),
                    City.valueOf(selectedLocation.getCity().getName().toUpperCase()));

            locationImg = selectedLocation.getPhoto();

            request.setAttribute("selectedLocation", selectedLocation.getStreet() + ", house: " + selectedLocation.getHouse() + ", office: " + selectedLocation.getOffice());
            request.setAttribute("selectedLocationId", selectedLocation.getId());
            request.setAttribute("locations", listLocation);
            request.setAttribute("selectedImg", "." + locationImg);
        }

        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/location_page.jsp");

        try {
            dispatcher.forward(request, response);
            // передача запроса/управления другому ресурсу на сервере;
        } catch (ServletException e) {
            LOGGER.error("ServletException from LocationPageCommand =" + e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.error("IOException from LocationPageCommand =" + e.getMessage());
        }
    }
}
