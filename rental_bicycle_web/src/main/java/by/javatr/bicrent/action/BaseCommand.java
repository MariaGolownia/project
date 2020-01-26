package by.javatr.bicrent.action;
import by.javatr.bicrent.entity.en_um.Role;
import by.javatr.bicrent.service.FactoryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;

public abstract class BaseCommand implements Command {
    protected  FactoryService factoryService = FactoryService.getInstance();


    /**
     * Every command has a set of user roles that are allowed
     * to ask for it’s execution.
     * <p>Empty set means any role is allowed.
     */
    protected Set<Role> allowedRoles;

    public BaseCommand() {
        allowedRoles = EnumSet.noneOf(Role.class);
    }

    public Set<Role> getAllowedRoles() {
        return allowedRoles;
    }

}

