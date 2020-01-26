package by.javatr.bicrent.action.impl.location_page;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.Bicycle;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.impl.BicycleServiceImpl;
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

@WebServlet("/GetFreeBicycles/*")
public class ActionBicycle extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Boolean BOOLEAN_FREE_BICYCLE = true;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)  {
        // Используем Gson для преобразования массива в структурированный string
        FactoryService factoryService = FactoryService.getInstance();
        List<Bicycle> list = new ArrayList<>();
        String json = "";

        String locationIdStr = request.getParameter("locationId").trim();

        if (locationIdStr == null || "".equals(locationIdStr)) {
            json = "None";
        } else {
            Integer locationId = Integer.valueOf(locationIdStr);
            BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
            list = bicycleService.findByFreeStatus(locationId, BOOLEAN_FREE_BICYCLE);
            json = new Gson().toJson(list);
        }

        response.setContentType("text/plain");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            LOGGER.error("IOException from ActionBicycle =" + e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
