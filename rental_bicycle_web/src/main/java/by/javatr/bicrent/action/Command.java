package by.javatr.bicrent.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Command {
    /**
     * Executes the command, then forwards/redirects user to the final destination.
     * <br>The URL is encapsulated in Destination object.
     *
     * @param request  user request object
     * @param response user response object
     */
    public void execute(HttpServletRequest request, HttpServletResponse response);
}
