package by.javatr.bicrent.action.impl.selected_user_page;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.UserInfo;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.ServiceException;
import by.javatr.bicrent.service.impl.UserInfoServiceImpl;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/GetUser/*")
public class ActionFindUser extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        // Используем Gson для преобразования массива в структурированный string
        FactoryService factoryService = FactoryService.getInstance();
        UserInfoServiceImpl userInfoService = factoryService.get(DaoSql.UserInfoDao);
        UserInfo userInfo = new UserInfo();
        String json = "";
        try {
            String numberPasportStr = request.getParameter("userDocId").trim();
            if (numberPasportStr == null || "".equals(numberPasportStr)) {
            } else {

                userInfo = userInfoService.findByIdNumberPassport(numberPasportStr);

                HttpSession session = request.getSession();
                UserInfo userInfoSession = (UserInfo) session.getAttribute("selectedUserInfo");
                if (userInfoSession!=null)
                    session.removeAttribute("selectedUserInfo");
                session.setAttribute("selectedUserInfo", userInfo);
                json = new Gson().toJson(userInfo);
            }
            response.setContentType("text/plain");
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        doGet(request, response);
    }
}
