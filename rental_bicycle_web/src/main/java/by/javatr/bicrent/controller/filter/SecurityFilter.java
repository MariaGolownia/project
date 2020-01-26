package by.javatr.bicrent.controller.filter;

import by.javatr.bicrent.action.CommandName;
import by.javatr.bicrent.controller.web_data.*;
import by.javatr.bicrent.entity.en_um.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class SecurityFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        RequestDispatcher dispatcherSuccess = null;
        HashSet<Role> allowedRoles = new HashSet<Role>();
        allowedRoles.add(Role.ADMIN);

        if (req instanceof HttpServletRequest && resp instanceof HttpServletResponse) {
            HttpServletRequest httpReq = (HttpServletRequest) req;
            HttpServletResponse httpResp = (HttpServletResponse) resp;
            HttpSession session = httpReq.getSession();
            Object userLogin = session.getAttribute(Attributes.USER_LOGIN);
            String commandName = req.getParameter(Params.COMMAND_NAME);

            Role attributeRole = (Role) session.getAttribute(Attributes.USER_ROLE);
            Role userRole = (attributeRole != null) ? attributeRole : Role.GUEST;

            if (session != null) {
                if (userLogin != null ){
                    if (allowedRoles.contains(userRole) || commandName.equals(Params.AUTHORIZATION_PAGE) || commandName.equals(CommandParams.SET_LOCALE)) {
                        chain.doFilter(req, resp);
                    }
                    else {
                        dispatcherSuccess = req.getRequestDispatcher(Pages.ALLOWED_USER_PAGE);
                        dispatcherSuccess.forward(req, resp);
                    }
                }
                else if ((commandName != null && commandName.equals(CommandParams.SET_LOCALE))
                        || commandName.equals(Params.AUTHORIZATION_PAGE)
                        || commandName.equals(Params.AUTHORIZATION_PAGE_USER_SUBMIT)
                        || commandName.equals(Params.REGISTER_COMMAND)
                        || commandName.equals(Params.REGISTRATION_PAGE)) {

                    List<Enum> enumValues = Arrays.asList(CommandName.values());

                    List<String> enumNames = Stream.of(CommandName.values())
                            .map(Enum::name)
                            .collect(Collectors.toList());

                    if (!enumNames.contains(commandName.toUpperCase())) {
                        dispatcherSuccess = req.getRequestDispatcher(Pages.ERROR_404);
                        dispatcherSuccess.forward(req, resp);
                    } else {
                        chain.doFilter(req, resp);
                    }
                } else {
                    dispatcherSuccess = req.getRequestDispatcher(Pages.AUTHORIZATION_PAGE);
                    dispatcherSuccess.forward(req, resp);
                }
            } else {
                dispatcherSuccess = req.getRequestDispatcher(Pages.ERROR_500);
                dispatcherSuccess.forward(req, resp);
            }
        }
    }
}

