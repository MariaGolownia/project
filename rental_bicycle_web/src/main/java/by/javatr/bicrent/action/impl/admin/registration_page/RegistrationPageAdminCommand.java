package by.javatr.bicrent.action.impl.admin.registration_page;
import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.DateConverter;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.UserInfo;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.entity.en_um.UserStatus;
import by.javatr.bicrent.service.ServiceException;
import by.javatr.bicrent.service.impl.UserInfoServiceImpl;
import by.javatr.bicrent.service.impl.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationPageAdminCommand extends BaseCommand {
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

    public RegistrationPageAdminCommand() {
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
            UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
            User userNew = new User();
            userNew.setLogin(userLogin);
            userPassword = userService.getHashCodePassword(userPassword);
            userNew.setPassword(userPassword);
            userNew.setUserStatus(UserStatus.ACTIVE);
            userNew.setRole(Role.USER);

            Integer id = userService.save(userNew);
            UserInfoServiceImpl userInfoService = factoryService.get(DaoSql.UserInfoDao);
            UserInfo userInfo = new UserInfo();
            userInfo.setId(id);
            userInfo.setSurname(surnameUser);
            userInfo.setName(nameUser);
            userInfo.setBirthDate(DateConverter.converterDateFromString(birthDateUser));

            userInfo.setSecondName(secondNameUser);
            userInfo.setCountry(countryUser);
            userInfo.setPassportIssueDate(DateConverter.converterDateFromString(passportIssueDateUser));
            userInfo.setPassportSerialNumber(passportSerialNumberUser);
            userInfo.setPassportIdentificationNumber(passportIdentificationNumberUser);
            userInfo.setPassportIssuingAuthority(passportIssuingAuthorityUser);
            userInfo.setPassportAddressRegistration(userPassportAddressRegistration);
            userInfo.setPassportAddressResidence(passportAddressResidenceUser);
            userInfo.setPhoneNumber(Long.valueOf(phoneNumberUser));
            if (secondPhoneNumberUser != null && secondPhoneNumberUser != "")
                userInfo.setSecondPhoneNumber(Long.valueOf(secondPhoneNumberUser));
            userInfo.setEmail(emailUser);

            userInfoService.save(userInfo);

            sts = true;
        } catch (ServiceException e) {
            message_error = e.getMessage();
            e.printStackTrace();
        }

        if (sts)
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/selected_user_page.jsp");
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
            // transfer request / control to another resource on the server;
        } catch (ServletException e) {
            LOGGER.error("ServletException from RegistrationPageAdminCommand =" + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException from RegistrationPageAdminCommand =" + e.getMessage());
        }
    }
}
