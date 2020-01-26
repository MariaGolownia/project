package by.javatr.bicrent.action.impl.location_page;

import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.Bicycle;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.impl.BicycleServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.misc.BASE64Encoder;

import java.sql.Blob;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/GetBicycleImg/*")
public class ActionBicycleImg extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    // Используем Gson для преобразования массива в структурированный string
    FactoryService factoryService = FactoryService.getInstance();
    Bicycle bicycle = new Bicycle();
    String imageString = null;
    byte[] bdata=null;
    Blob blobImg=null;

    String bicycleIdStr = request.getParameter("bicycleId").trim();
    if (bicycleIdStr == null || "".equals(bicycleIdStr)) {
            imageString = "None";
    } else {
        Integer bicycleIdStrId = Integer.valueOf(bicycleIdStr);
        BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
        bicycle = bicycleService.findById(bicycleIdStrId);
        blobImg = bicycle.getPhoto();
        bdata = new byte[0];
        try {
            bdata = blobImg.getBytes(1, (int) blobImg.length());
            //String bicycleImg = new String(bdata);

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(bdata);
        } catch (SQLException e) {
            LOGGER.error("SQLException from ActionBicycleImg =" + e.getMessage());
        }
    }
        response.setContentType("blob");
        try {
            response.getWriter().write(imageString);
        //response.getOutputStream().write(imageString);
    } catch (IOException e) {
            LOGGER.error("IOException from ActionBicycleImg =" + e.getMessage());
    }
}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        doGet(request, response);
    }
}
