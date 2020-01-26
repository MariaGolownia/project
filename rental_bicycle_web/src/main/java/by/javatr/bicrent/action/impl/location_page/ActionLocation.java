package by.javatr.bicrent.action.impl.location_page;

import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.Location;
import by.javatr.bicrent.entity.en_um.City;
import by.javatr.bicrent.entity.en_um.Country;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.impl.LocationServiceImpl;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/GetLocation/*")
public class ActionLocation extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Используем Gson для преобразования массива в структурированный string
        FactoryService factoryService = FactoryService.getInstance();
        List<Location> list = new ArrayList<>();
        String json = "";

        String cityName = request.getParameter("cityName").trim().toUpperCase();
        if (cityName == null || "".equals(cityName)) {
            json = "None";
        } else {
            LocationServiceImpl locationService = factoryService.get(DaoSql.LocationDao);
            String countryFromJsp = request.getParameter("countryName").trim();
            String cityFromJsp = request.getParameter("cityName").trim();
            list = locationService.findByCountryAndCity(Country.valueOf(countryFromJsp.toUpperCase()),
                    City.valueOf(cityFromJsp.toUpperCase()));
            json = new Gson().toJson(list);
        }

        response.setContentType("text/plain");
        response.getWriter().write(json);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
