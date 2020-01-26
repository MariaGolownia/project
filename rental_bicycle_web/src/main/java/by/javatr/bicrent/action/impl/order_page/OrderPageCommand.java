package by.javatr.bicrent.action.impl.order_page;

import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.Bicycle;
import by.javatr.bicrent.entity.Location;
import by.javatr.bicrent.entity.UserInfo;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.bic_sort.BicycleComparator;
import by.javatr.bicrent.service.impl.BicycleServiceImpl;
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

public class OrderPageCommand extends BaseCommand {

    public OrderPageCommand() {
        allowedRoles.add(Role.ADMIN);
    }

    private static final Logger LOGGER = LogManager.getLogger();
    private static final Boolean BOOLEAN_FREE_BICYCLE = true;
    private static final int RECORDS_PER_PAGE = 5;
    private static final String SORT_PARAMETER_MODEL_BICYCLE = "model";
    private static final String SORT_PARAMETER_COUNTRY_BICYCLE = "country";
    private static final String SORT_PARAMETER_YEAR_BICYCLE = "year";
    private static final String SORT_PARAMETER_RATE_BICYCLE = "rate";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        FactoryService factoryService = FactoryService.getInstance();
        RequestDispatcher dispatcher = null;
        HttpSession session = request.getSession();
        int page = 1;

        Location selectedLocation = (Location) session.getAttribute("selectedLocation");
        if (selectedLocation != null) {
            if(request.getParameter("page") != null)
                page = Integer.parseInt(request.getParameter("page"));

            Integer selectedLocationId = selectedLocation.getId();
            String selectedLocationInformation = selectedLocation.getCountry() + ", " +
                    selectedLocation.getCity() + ", " + selectedLocation.withoutCountryAndCityToString();

            request.setAttribute("selectLocation", selectedLocationId);
            request.setAttribute("selectAddress", selectedLocationInformation);

            BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
            List<Bicycle>listBicycleTmp = new ArrayList<>();
            listBicycleTmp = bicycleService.findByCurrentLocationWithPriceAndFreedom(selectedLocationId, BOOLEAN_FREE_BICYCLE);
            List<Bicycle> listBicycle = new ArrayList<>();

            //----------------------------------------------------------------------------------------------
            // BICYCLE'S SORT - STEP 1 - checking type_of_sort that is in session
            //----------------------------------------------------------------------------------------------
            Object sortParameterFromSession = session.getAttribute("sort_parameter");
            String sortParameterFromRequest = request.getParameter("sort_parameter");
            if (sortParameterFromRequest == null && sortParameterFromSession == null) {
                sortParameterFromRequest = SORT_PARAMETER_YEAR_BICYCLE;
            }
            else if (sortParameterFromRequest == null && sortParameterFromSession != null) {
                sortParameterFromRequest = sortParameterFromSession.toString();
            }
            else {
                page = 1;
                session.setAttribute("sort_parameter", sortParameterFromRequest);
            }
            //----------------------------------------------------------------------------------------------
            // BICYCLE'S SORT - STEP 2 - checking sort_parameter from jsp
            //----------------------------------------------------------------------------------------------

                if (sortParameterFromRequest.equals(SORT_PARAMETER_MODEL_BICYCLE)) {
                    listBicycleTmp = bicycleService.sortBy(listBicycleTmp, new BicycleComparator.SortBicycleByModel().comparatorSpecified());
                } else if (sortParameterFromRequest.equals(SORT_PARAMETER_COUNTRY_BICYCLE)) {
                    listBicycleTmp = bicycleService.sortBy(listBicycleTmp, new BicycleComparator.SortBicycleByCountry().comparatorSpecified());
                } else if (sortParameterFromRequest.equals(SORT_PARAMETER_YEAR_BICYCLE)) {
                    listBicycleTmp = bicycleService.sortBy(listBicycleTmp, new BicycleComparator.SortBicycleByYear().comparatorSpecified());
                } else if (sortParameterFromRequest.equals(SORT_PARAMETER_RATE_BICYCLE)) {
                    listBicycleTmp = bicycleService.sortBy(listBicycleTmp, new BicycleComparator.SortBicycleByRate().comparatorSpecified());
                } else {
                    listBicycleTmp = bicycleService.sortBy(listBicycleTmp, new BicycleComparator.SortBicycleByYear().comparatorSpecified());
                }

            //----------------------------------------------------------------------------------------------

            int noOfRecords = listBicycleTmp.size();
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);

            for (int i=0;i<listBicycleTmp.size();i++)
            {
                if (i>=(page-1)*RECORDS_PER_PAGE && i<(page-1)*RECORDS_PER_PAGE+RECORDS_PER_PAGE)
                {
                    listBicycle.add(listBicycleTmp.get(i));
                }
            }

            request.setAttribute("bicyclesList", listBicycle);
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", page);
        }

        UserInfo selectedUserInfo = (UserInfo) session.getAttribute("selectedUserInfo");
        if (selectedUserInfo != null) {
            request.setAttribute("selectIdPassport", selectedUserInfo.getPassportIdentificationNumber());
            request.setAttribute("selectUser", selectedUserInfo.getSurname()+ ' ' + selectedUserInfo.getName());
        }

        dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/order_page.jsp");

        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            LOGGER.error("ServletException from OrderPageCommand =" + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException from OrderPageCommand =" + e.getMessage());
        }
    }
}
