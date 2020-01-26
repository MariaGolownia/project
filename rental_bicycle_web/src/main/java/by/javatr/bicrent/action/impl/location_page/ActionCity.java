package by.javatr.bicrent.action.impl.location_page;

import by.javatr.bicrent.entity.en_um.City;
import by.javatr.bicrent.entity.en_um.Country;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/GetCity/*")
public class ActionCity extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       // Используем Gson для преобразования массива в структурированный string
        List<String> list = new ArrayList<>();
        String json="";

        String countryName = request.getParameter("countryName").trim().toUpperCase();
        if(countryName == null || "".equals(countryName)){
            json = "None";
        }
        else {
            list = City.getCitiesStrByCountry(Country.valueOf(countryName));
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
