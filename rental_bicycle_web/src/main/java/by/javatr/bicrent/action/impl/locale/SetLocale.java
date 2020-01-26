package by.javatr.bicrent.action.impl.locale;
import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.controller.web_data.Params;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetLocale extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String LANG_COOKIE_NAME = "lang";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String locale = request.getParameter(Params.LOCALE);
        
        Cookie localeCookie = new Cookie(LANG_COOKIE_NAME, locale);
        localeCookie.setMaxAge(Integer.MAX_VALUE);
        response.addCookie(localeCookie);
        //Заголовки HTTP (англ. HTTP Headers) — это строки в HTTP-сообщении, содержащие разделённую двоеточием пару имя-значение.
        //Refer - Полный или относительный URI ресурса, с которого клиент сделал текущий запрос.
        String url = request.getHeader("referer");
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            LOGGER.error("IOException from SetLocale =" + e.getMessage());
        }
    }
}
