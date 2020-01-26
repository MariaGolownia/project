package by.javatr.bicrent.controller.web_data;

/**
 * Contains internal relative URls of every page that users can be forwarded to.
 * As the pages are located in the WEB-INF folder, only forward option will work.
 */
public class Pages {

    public static final String ERROR_403 = "/WEB-INF/jsp/errors/403.jsp";
    public static final String ERROR_404 = "/WEB-INF/jsp/errors/404.jsp";
    public static final String ERROR_500 = "/WEB-INF/jsp/errors/500.jsp";
    public static final String ERROR = "/WEB-INF/jsp/errors/error.jsp";

    public static final String AUTHORIZATION_PAGE = "/WEB-INF/jsp/authorization_page.jsp";
    public static final String ALLOWED_USER_PAGE = "/WEB-INF/jsp/location_page.jsp";


    private Pages() {
    }
}
