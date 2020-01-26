package by.javatr.bicrent.action.impl.admin.selected_user_page;

import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.entity.en_um.Currency;
import by.javatr.bicrent.entity.en_um.Role;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class GetCurrencyAdmin extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();

    public GetCurrencyAdmin() {
        allowedRoles.add(Role.ADMIN);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String json = "";
        List<Currency> currencyList = new ArrayList<Currency>(Arrays.asList(Currency.values()));

        json = new Gson().toJson(currencyList);

        response.setContentType("text/plain");
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            LOGGER.error("IOException from GetCurrencyAdmin =" + e.getMessage());
        }
    }
}
