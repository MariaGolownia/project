package by.javatr.bicrent.action.validator;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class contains basic client-side validation methods for users.
 */

public class UserDataValidator {
    private static final String NAME_SURNAME_REGEX = "[A-Za-zА-Яа-я]{3,255}";
    // Matches any letter or number; equivalent [a-zA-Z0-9_]
    private static final String LOGIN_REGEX = "(?=.*?[a-z]).{3,255}(?=.*?[0-9]){0,255}";
    private static final String PASSWORD_REGEX = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,255}$";
    private static final String EMAIL_REGEX = "[A-Z0-9a-z._]+@[A-Za-z0-9]+\\.[A-Za-z]{2,64}";
    private static final String PHONE_NUMBER_REGEX = "\\d{11}+$";
    private static final int PHONE_DIGIT_COUNT = 11;
    //Regular expression that takes into account the number of days in a month and the leap year
    private static final String DATE_REGEX = "^(?:(?:31(\\/|-|\\.)(?:0?[13578]|1[02]))\\1|(?:(?:29|30)(\\/|-|\\.)(?:0?[1,3-9]|1[0-2])\\2))(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:29(\\/|-|\\.)0?2\\3(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|^(?:0?[1-9]|1\\d|2[0-8])(\\/|-|\\.)(?:(?:0?[1-9])|(?:1[0-2]))\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
    private static final int USER_AGE_MIN = 18;
    private static final int PASSPORT_AGE_MIN = 10;

    private static final Pattern NAME_SURNAME_PATTERN = Pattern.compile(NAME_SURNAME_REGEX);
    private static final Pattern LOGIN_PATTERN = Pattern.compile(LOGIN_REGEX);
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
    private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_NUMBER_REGEX);
    private static final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);

    private UserDataValidator() {
    }

    /**
     * Checks the name / surname matches the required format.
     *
     * @param name name / surname  to be checked
     * @throws IncorrectDataException if the name / surname  is <code>null</code>,
     *                                or does not represent a String of letters
     *                                without any numbers in length from 3 to 255 symbols.
     */
    public static void checkStr(String name, Boolean ifReduiredToFill) throws IncorrectDataException {
        if (ifReduiredToFill == true || name != null) {
            Matcher matcher = NAME_SURNAME_PATTERN.matcher(name);
            if (!matcher.matches()) {
                throw new IncorrectDataException("Name: " + name + " is incorrect");
            }
        }
    }

    /**
     * Checks the login matches the required format.
     *
     * @param login login to be checked
     * @throws IncorrectDataException if the login is <code>null</code>,
     *                                or does not represent a String of letters
     *                                in length from 3 to 255 symbols.
     */
    public static void checkLogin(String login) throws IncorrectDataException {
        Matcher matcher = LOGIN_PATTERN.matcher(login);
        if (!matcher.matches()) {
            throw new IncorrectDataException("Login: " + login + " is incorrect");
        }
    }

    /**
     * Checks the password matches the required format.
     *
     * @param password password to be checked
     * @throws IncorrectDataException if the password is <code>null</code>,
     *                                or does not represent a String following the requirements:
     *                                <br>8 - 255 characters long
     *                                <br>at least 1 uppercase letter
     *                                <br>at least 1 lowercase letter
     *                                <br>at least 1 digit
     *                                <br>at least 1 special symbol: <code>#?!@$%^&;*-</code>
     */
    public static void checkPassword(String password) throws IncorrectDataException {
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        if (!matcher.matches()) {
            throw new IncorrectDataException("Password: " + password + " is incorrect");
        }
    }

    /**
     * Checks the email matches the required format.
     *
     * @param email email to be checked
     * @throws IncorrectDataException if the email is <code>null</code>,
     *                                or does not represent a String following the requirements:
     *                                <br>alphanumeric line that may also contain dots or underscores
     *                                <br>followed by <code>@</code>
     *                                <br>followed by alphanumeric line
     *                                <br>followed by <code>.</code>
     *                                <br>followed by 2 - 64 alphabetical characters
     */
    public static void checkEmail(String email) throws IncorrectDataException {
        if (email != null) {
            Matcher matcher = EMAIL_PATTERN.matcher(email);
            if (!matcher.matches()) {
                throw new IncorrectDataException("Email: " + email + " is incorrect");
            }
        }
    }

    /**
     * Checks the phone matches the required format.
     *
     * @param phone to be checked
     * @throws IncorrectDataException if the email is <code>null</code>,
     *                                or does not represent a String following the requirements:
     *                                <br>alphanumeric line that may also contain dots or underscores
     *                                <br>followed by <code>@</code>
     *                                <br>followed by alphanumeric line
     *                                <br>followed by <code>.</code>
     *                                <br>followed by 2 - 64 alphabetical characters
     */
    public static void checkPhone(String phone, Boolean ifReduiredToFill) throws IncorrectDataException {
        if (ifReduiredToFill == true || phone != null) {
            if (phone.length() != PHONE_DIGIT_COUNT) {
                throw new IncorrectDataException("Phone: " + phone + " is incorrect!" +
                        "Not the correct number of digits in the phone number! Start with a numner." +
                        " For example: '8029...'.");
            } else {
                Matcher matcher = PHONE_PATTERN.matcher(phone);
                if (!matcher.matches()) {
                    throw new IncorrectDataException("Phone: " + phone + " is incorrect");
                }
            }
        }
    }

    /**
     * Checks the date matches the required format.
     *
     * @param date to be checked
     * @throws IncorrectDataException if the date is <code>null</code>,
     *                                or does not represent a String of format
     *                                XX.XX.XXXX or XX/XX/XXXX or XX-XX-XXXX.
     *                                Accounting for the number of days in a month and leap year
     */
    public static void checkFormateDate(String date) throws IncorrectDataException {
        Matcher matcher = DATE_PATTERN.matcher(date);
        if (!matcher.matches()) {
            throw new IncorrectDataException("Date: " + date + " is incorrect");
        }
    }
    // Leap check not via regular expression
    // code1
//    public static boolean isLeapYear(int year) {
//        if (year % 4 != 0) {
//            return false;
//        } else if (year % 400 == 0) {
//            return true;
//        } else if (year % 100 == 0) {
//            return false;
//        } else {
//            return true;
//        }
//    }
    //  code 2
//    public static boolean isLeapYear(int year) {
//        Calendar cal = Calendar.getInstance();
//        cal.set(Calendar.YEAR, year);
//        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
//    }

    /**
     * Checks the date matches the required format.
     *
     * @param date to be checked
     * @throws IncorrectDataException if the date is later than current date
     */
    public static void checkDateInPast(String date) throws IncorrectDataException {
        Integer day = Integer.valueOf(date.substring(0, 2));
        Integer month = Integer.valueOf(date.substring(3, 5));
        Integer year = Integer.valueOf(date.substring(6));
        Calendar userDate = Calendar.getInstance();
        userDate.set(year, month, day);
        Calendar curDate = new GregorianCalendar();
        if (userDate.after(curDate)) {
            throw new IncorrectDataException("Date cannot be later than the current date!");
        }
    }

    /**
     * Check the date for: the user corresponds to the required age in years
     *
     * @param date to be checked the minimum level
     * @throws IncorrectDataException if user under @param USER_AGE_MIN of age
     */
    public static void checkMinDateBirth(String date) throws IncorrectDataException {
        Integer userYear = Integer.valueOf(date.substring(6));
        Calendar curDate = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        curDate.setTime(new java.util.Date());
        Integer curYear = curDate.get(java.util.Calendar.YEAR);
        if ((curYear - userYear) < USER_AGE_MIN) {
            throw new IncorrectDataException("The user's age cannot be less than " + USER_AGE_MIN + "!");
        }
    }

    /**
     * Check the date for: the user's passport corresponds to the required age in years
     *
     * @param date to be checked the minimum level
     * @throws IncorrectDataException if passport under @param PASSPORT_AGE_MIN of age
     */
    public static void checkMinDatePassport(String date) throws IncorrectDataException {
        Integer passportYear = Integer.valueOf(date.substring(6, 10));
        Calendar curDate = Calendar.getInstance(TimeZone.getDefault(), Locale.getDefault());
        curDate.setTime(new java.util.Date());
        Integer curYear = curDate.get(java.util.Calendar.YEAR);
        if ((curYear - passportYear) > PASSPORT_AGE_MIN) {
            throw new IncorrectDataException("The validity of the passport exceeds " + PASSPORT_AGE_MIN + "!");
        }
    }

}
