package by.javatr.bicrent.action.impl.registration_page;
import by.javatr.bicrent.action.BaseCommand;
import by.javatr.bicrent.action.validator.IncorrectDataException;
import by.javatr.bicrent.action.validator.UserDataValidator;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.DateConverter;
import by.javatr.bicrent.entity.User;
import by.javatr.bicrent.entity.UserInfo;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.entity.en_um.UserStatus;
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

public class RegistrationPageCommand extends BaseCommand {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String USER_LOGIN = "userLogin";
    private static final String USER_PASSWORD = "userPassword";
    private static final String USER_PASSWORD_REP = "userPasswordRep";
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

    private static final String messageSuccessRegistration = "Registration is successful!";
    private static final Boolean IF_REQUIRED_FILL = true;
    private static final Boolean IF_NOT_REQUIRED_FILL = false;

    public RegistrationPageCommand() {
        allowedRoles.add(Role.ADMIN);
        allowedRoles.add(Role.USER);
        allowedRoles.add(Role.GUEST);
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) {
        Boolean flagValidationSuccess = true;
        RequestDispatcher dispatcherSuccess = null;
        RequestDispatcher dispatcher = null;
        Boolean sts = false;
        String messageCheckUserPassword = null;
        String userLogin = request.getParameter(USER_LOGIN);
        String userPassword = request.getParameter(USER_PASSWORD);
        String userPasswordRep = request.getParameter(USER_PASSWORD_REP);
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
            UserDataValidator.checkLogin(userLogin);
        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
            String messageCheckUserLogin = "Please, check your login!";
            request.setAttribute("messageCheckUserLogin", messageCheckUserLogin);
        }

        try {
            UserDataValidator.checkStr(surnameUser, IF_REQUIRED_FILL);
        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
            String messageCheckUserSurname = "Please, check your surname!";
            request.setAttribute("messageCheckUserSurname", messageCheckUserSurname);
        }

        try {
            UserDataValidator.checkStr(nameUser, IF_REQUIRED_FILL);
        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
            String messageCheckUserName = "Please, check your name!";
            request.setAttribute("messageCheckUserName", messageCheckUserName);
        }

        try {
            UserDataValidator.checkPassword(userPassword);
        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
            messageCheckUserPassword = "Please, check your password! " +
                    "The password is empty or does not represent a string following the requirements: " +
                    "1) 8 - 255 characters long," +
                    "2) at least 1 uppercase letter," +
                    "3) at least 1 lowercase letter," +
                    "4) at least 1 digit," +
                    "5) at least 1 special symbol: #?!@$%^&;*-.";
            request.setAttribute("messageCheckUserPassword", messageCheckUserPassword);
        }

        try {
            System.out.println(userPassword);
            System.out.println(userPasswordRep);
            if (messageCheckUserPassword == null && !userPassword.equals(userPasswordRep)) {
                throw new IncorrectDataException("Passwords do not match!");
            }

        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
            String messageCheckUserPasswordRep = "Passwords do not match!";
            request.setAttribute("messageCheckUserPasswordRep", messageCheckUserPasswordRep);
        }


        try {
            UserDataValidator.checkStr(secondNameUser, IF_NOT_REQUIRED_FILL);
        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
            String messageCheckSecName = "Please, check your second name!";
            request.setAttribute("messageCheckSecName", messageCheckSecName);
        }
//-----------------------
        try {
            UserDataValidator.checkStr(countryUser, IF_NOT_REQUIRED_FILL);
        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
            String messageCheckCountry = "Please, check your country!";
            request.setAttribute("messageCheckCountry", messageCheckCountry);
        }


        try {
            UserDataValidator.checkFormateDate(birthDateUser);
            UserDataValidator.checkDateInPast(birthDateUser);
            UserDataValidator.checkMinDateBirth(birthDateUser);
        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
            String messageCheckBirthDate = "Please, check your birth date! Format:" +
                    " 'XX.XX.XXXX' or 'XX/XX/XXXX' or 'XX-XX-XXXX' and age authority.";
            request.setAttribute("messageCheckBirthDate", messageCheckBirthDate);
        }

        try {
            UserDataValidator.checkFormateDate(passportIssueDateUser);
            UserDataValidator.checkDateInPast(passportIssueDateUser);
            UserDataValidator.checkMinDatePassport(passportIssueDateUser);
        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
            String messageCheckPassportDate = "Please, check your passport issue date! Format:" +
                    " 'XX.XX.XXXX' or 'XX/XX/XXXX' or 'XX-XX-XXXX' and passport's authority.";
            request.setAttribute("messageCheckPassportDate", messageCheckPassportDate);
        }

        UserInfoServiceImpl userServiceInfo = factoryService.get(DaoSql.UserInfoDao);
        try {
            UserInfo userInfo = userServiceInfo.findByIdNumberPassport(passportIdentificationNumberUser);
            if (userInfo.getPassportIdentificationNumber() != null)
                throw new ServiceException();
        } catch (ServiceException e) {
            flagValidationSuccess = false;
            String messageCheckIdPassport = "Entered passport identification number is exist in system!";
            request.setAttribute("messageCheckIdPassport", messageCheckIdPassport);
        }

        try {
            UserDataValidator.checkPhone(phoneNumberUser, IF_REQUIRED_FILL);
        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
            String messageCheckUserPhone = "Please, check your phone! Start with a number."
                    + " For example: '8029'";
            request.setAttribute("messageCheckUserPhone", messageCheckUserPhone);
        }

        try {
            UserDataValidator.checkPhone(secondPhoneNumberUser, IF_NOT_REQUIRED_FILL);
        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
            String messageCheckUserSecPhone = "Please, check your phone! Start with a number."
                    + " For example: '8029'";
            request.setAttribute("messageCheckUserSecPhone", messageCheckUserSecPhone);
        }

        try {
            UserDataValidator.checkStr(countryUser, IF_NOT_REQUIRED_FILL);
        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
            String messageCheckUserCountry = "Please, check your name!";
            request.setAttribute("messageCheckUserCountry", messageCheckUserCountry);
        }

        try {
            UserDataValidator.checkEmail(emailUser);
        } catch (IncorrectDataException e) {
            flagValidationSuccess = false;
            String messageCheckEmail = "Please, check your email!";
            request.setAttribute("messageCheckEmail", messageCheckEmail);
        }


        if (flagValidationSuccess) {
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
                e.printStackTrace();
            }

            if (sts) {
                request.setAttribute("messageSuccessRegistration", messageSuccessRegistration);
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/authorization_page.jsp");
            } else
                dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error_page.jsp");

        } else {
            request.setAttribute(USER_LOGIN, userLogin);
            request.setAttribute(USER_PASSWORD, userPassword);
            request.setAttribute(SURNAME_USER, surnameUser);
            request.setAttribute(NAME_USER, nameUser);
            request.setAttribute(SECOND_NAME_USER, secondNameUser);
            request.setAttribute(BIRTH_DATE_USER, birthDateUser);
            request.setAttribute(COUNTRY_USER, countryUser);
            request.setAttribute(PASSPORT_ISSUE_DATE_USER, passportIssueDateUser);
            request.setAttribute(PASSPORT_ISSUING_AUTHORITY_USER, passportIssuingAuthorityUser);
            request.setAttribute(PASSPORT_IDENTIFICATION_NUMBER_USER, passportIdentificationNumberUser);
            request.setAttribute(PASSPORT_SERIAL_NUMBER_USER, passportSerialNumberUser);
            request.setAttribute(USER_PASSPORT_ADDRESS_REGISTRATION, userPassportAddressRegistration);
            request.setAttribute(PASSPORT_ADDRESS_RESIDENCE_USER, passportAddressResidenceUser);
            request.setAttribute(PHONE_NUMBER_USER, phoneNumberUser);
            request.setAttribute(SECOND_PHONE_NUMBER_USER, secondPhoneNumberUser);
            request.setAttribute(EMAIL_USER, emailUser);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/registration_page.jsp");
        }

        try {
            dispatcher.forward(request, response);
            // transfer request / control to another resource on the server;
        } catch (ServletException e) {
            LOGGER.error("ServletException from RegistrationPageCommand =" + e.getMessage());
        } catch (IOException e) {
            LOGGER.error("IOException from RegistrationPageCommand =" + e.getMessage());
        }
    }
}
