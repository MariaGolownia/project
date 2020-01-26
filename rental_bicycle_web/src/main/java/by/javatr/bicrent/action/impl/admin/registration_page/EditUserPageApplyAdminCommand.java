package by.javatr.bicrent.action.impl.admin.registration_page;
import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.DateConverter;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.UserInfo;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.entity.en_um.UserStatus;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.ServiceException;
import by.javatr.bicrent.service.impl.UserInfoServiceImpl;
import by.javatr.bicrent.service.impl.UserServiceImpl;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EditUserPageApplyAdminCommand extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USER_ROLE = "userRole";
    private static final String USER_LOGIN = "userLogin";
    private static final String USER_PASSWORD = "userPassword";
    private static final String SURNAME_USER = "userSurname";
    private static final String NAME_USER = "userName";
    private static final String SECOND_NAME_USER = "userSecondName";
    private static final String BIRTH_DATE_USER = "userBirthDate";
    private static final String COUNTRY_USER = "userCountry";
    private static final String PASSPORT_ISSUE_DATE_USER = "userPassportIssueDate";
    private static final String PASSPORT_ISSUING_AUTHORITY_USER = "userPassportIssuingAuthority";
    private static final String PASSPORT_IDENTIFICATION_NUMBER_USER = "userPassportIdentificationNumber";
    private static final String PASSPORT_SERIAL_NUMBER_USER = "userPassportSerialNumber";
    private static final String USER_PASSPORT_ADDRESS_REGISTRATION = "userPassportAddressRegistration";
    private static final String PASSPORT_ADDRESS_RESIDENCE_USER = "userPassportAddressResidence";
    private static final String PHONE_NUMBER_USER = "userPhoneNumber";
    private static final String SECOND_PHONE_NUMBER_USER = "userSecondPhoneNumber";
    private static final String EMAIL_USER = "userEmail";

    public EditUserPageApplyAdminCommand() {
        allowedRoles.add(Role.ADMIN);
    }
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        String message_error = "";
        RequestDispatcher dispatcher = null;
        Boolean sts = false;

            String userRole = request.getParameter(USER_ROLE);
            String userLogin = request.getParameter(USER_LOGIN);
            String userPassword = request.getParameter(USER_PASSWORD);
            String surnameUser = request.getParameter(SURNAME_USER);
            String nameUser = request.getParameter(NAME_USER);
            String secondNameUser = request.getParameter(SECOND_NAME_USER);
            String birthDateUser = request.getParameter(BIRTH_DATE_USER);
            String countryUser = request.getParameter(COUNTRY_USER);
            String passportIssueDateUser = request.getParameter(PASSPORT_ISSUE_DATE_USER);
            String passportIssuingAuthorityUser = request.getParameter(PASSPORT_ISSUING_AUTHORITY_USER);
            String passportIdentificationNumberUser = request.getParameter(PASSPORT_IDENTIFICATION_NUMBER_USER);
            String passportSerialNumberUser = request.getParameter(PASSPORT_SERIAL_NUMBER_USER);
            String userPassportAddressRegistration = request.getParameter(USER_PASSPORT_ADDRESS_REGISTRATION);
            String passportAddressResidenceUser = request.getParameter(PASSPORT_ADDRESS_RESIDENCE_USER);
            String phoneNumberUser = request.getParameter(PHONE_NUMBER_USER);
            String secondPhoneNumberUser = request.getParameter(SECOND_PHONE_NUMBER_USER);
            String emailUser = request.getParameter(EMAIL_USER);

            try {
            FactoryService factoryService = FactoryService.getInstance();
            UserInfoServiceImpl userInfoService = factoryService.get(DaoSql.UserInfoDao);
            UserInfo userInfoFromDB = new UserInfo();
            
            userInfoFromDB = userInfoService.findByIdNumberPassport(passportIdentificationNumberUser);
            UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
            Integer updateUserId = userInfoFromDB.getId();
            
            User userFromDB = userService.findByIdentity(updateUserId);

            User userNew = new User();
            userNew.setId(updateUserId);
            userNew.setLogin(userLogin);
            userPassword = userService.getHashCodePassword(userPassword);
            userNew.setPassword(userPassword);
            userNew.setUserStatus(UserStatus.ACTIVE);
            userNew.setRole(Role.USER);
            userService.update(userFromDB, userNew);

            UserInfo userInfoNew = new UserInfo();
            userInfoNew.setId(updateUserId);
            userInfoNew.setSurname(surnameUser);
            userInfoNew.setName(nameUser);
            userInfoNew.setBirthDate(DateConverter.converterDateFromString(birthDateUser));
            userInfoNew.setSecondName(secondNameUser);
            userInfoNew.setCountry(countryUser);
            userInfoNew.setPassportIssueDate(DateConverter.converterDateFromString(passportIssueDateUser));
            userInfoNew.setPassportSerialNumber(passportSerialNumberUser);
            userInfoNew.setPassportIdentificationNumber(passportIdentificationNumberUser);
            userInfoNew.setPassportIssuingAuthority(passportIssuingAuthorityUser);
            userInfoNew.setPassportAddressRegistration(userPassportAddressRegistration);
            userInfoNew.setPassportAddressResidence(passportAddressResidenceUser);
            userInfoNew.setPhoneNumber(Long.valueOf(phoneNumberUser));
            if (secondPhoneNumberUser != null && secondPhoneNumberUser != "")
                userInfoNew.setSecondPhoneNumber(Long.valueOf(secondPhoneNumberUser));
            userInfoNew.setEmail(emailUser);

            userInfoService.update(userInfoNew);

            sts = true;
        } catch (ServiceException e) {
            message_error = e.getMessage();
            e.printStackTrace();
        }

        if (sts) {
            request.setAttribute("msgString", "Success!");
            request.setAttribute("idValue", passportIdentificationNumberUser);

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/selected_user_page.jsp");
        }
        else {
            request.setAttribute("role_user", userRole);
            request.setAttribute("login_user", userLogin);
            request.setAttribute("surname_user", surnameUser);
            request.setAttribute("name_user", nameUser);
            request.setAttribute("second_name_user", secondNameUser);
            request.setAttribute("birth_date_user", birthDateUser);
            request.setAttribute("country_user", countryUser);
            request.setAttribute("pass_issue_date_user", passportIssueDateUser);
            request.setAttribute("pass_iss_authotity_user", passportIssuingAuthorityUser);
            request.setAttribute("pass_id_number_user", passportIdentificationNumberUser);
            request.setAttribute("pass_s_number_user", passportSerialNumberUser);
            request.setAttribute("address_reg_user", userPassportAddressRegistration);
            request.setAttribute("address_res_user", passportAddressResidenceUser);
            request.setAttribute("phone_number_user", phoneNumberUser);
            request.setAttribute("phone_number2_user", secondPhoneNumberUser);
            request.setAttribute("e_mail_user", emailUser);

            request.setAttribute("errorMessage", message_error);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/admin/registration_page_adm.jsp");
        }

        try {
            dispatcher.forward(request, response);
            // передача запроса/управления другому ресурсу на сервере;
        } catch (ServletException e) {
            LOGGER.error("ServletException =" + e);
        } catch (IOException e) {
            LOGGER.error("IOException =" + e);
        }
    }
}
