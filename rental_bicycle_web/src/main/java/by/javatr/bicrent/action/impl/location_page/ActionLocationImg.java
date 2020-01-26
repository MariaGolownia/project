package by.javatr.bicrent.action.impl.location_page;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.Location;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.impl.LocationServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/GetLocationImg/*")
public class ActionLocationImg extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // Используем Gson для преобразования массива в структурированный string
        FactoryService factoryService = FactoryService.getInstance();
        Location location = new Location();
        String locationImg = "";

        String locationIdStr = request.getParameter("locationId").trim();
        if (locationIdStr == null || "".equals(locationIdStr)) {
            locationImg = "None";
        } else {
            Integer locationId = Integer.valueOf(locationIdStr);
            LocationServiceImpl locationService = factoryService.get(DaoSql.LocationDao);
            location = locationService.findById(locationId);
            HttpSession session = request.getSession();
            if ((Location)session.getAttribute("selectedLocation") != null)
                session.removeAttribute("selectedLocation");
            session.setAttribute("selectedLocation", location);
            locationImg = location.getPhoto();
        }

        response.setContentType("text/plain");
        try {
            response.getWriter().write(locationImg);
        } catch (IOException e) {
            LOGGER.error("IOException from ActionLocationImg =" + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        doGet(request, response);
    }
}
