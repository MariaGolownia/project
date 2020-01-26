package by.javatr.bicrent.action;
import by.javatr.bicrent.action.impl.*;
import by.javatr.bicrent.action.impl.admin.order_page.FindOrder;
import by.javatr.bicrent.action.impl.admin.order_page.OrderPageStatus;
import by.javatr.bicrent.action.impl.admin.registration_page.EditUserPageAdminCommand;
import by.javatr.bicrent.action.impl.admin.registration_page.EditUserPageApplyAdminCommand;
import by.javatr.bicrent.action.impl.admin.registration_page.RegistrationPageAdminCommand;
import by.javatr.bicrent.action.impl.admin.selected_user_page.AddNewCardAdminCommand;
import by.javatr.bicrent.action.impl.admin.selected_user_page.GetCurrencyAdmin;
import by.javatr.bicrent.action.impl.authorization_page.AuthorizationPageCommand;
import by.javatr.bicrent.action.impl.authorization_page.AuthorizationPageUserSubmitCommand;
import by.javatr.bicrent.action.impl.locale.SetLocale;
import by.javatr.bicrent.action.impl.location_page.LocationPageCommand;
import by.javatr.bicrent.action.impl.order_page.ActionStartOrder;
import by.javatr.bicrent.action.impl.order_page.OrderPageCommand;
import by.javatr.bicrent.action.impl.payment_page.DoMainPay;
import by.javatr.bicrent.action.impl.payment_page.PaymentPageCommand;
import by.javatr.bicrent.action.impl.registration_page.RegistrationCommand;
import by.javatr.bicrent.action.impl.registration_page.RegistrationPageCommand;
import by.javatr.bicrent.action.impl.selected_user_page.GetTopUpBalance;
import by.javatr.bicrent.action.impl.selected_user_page.UserPageCommand;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandProvider {

    private static final CommandProvider instance = new CommandProvider();
    private Map<CommandName, Command> commands = new ConcurrentHashMap<>();
    private Command command;

    private CommandProvider() {
        //User's commands
        this.commands.put(CommandName.SET_LOCALE, new SetLocale());
        this.commands.put(CommandName.AUTHORIZATION_PAGE, new AuthorizationPageCommand());
        this.commands.put(CommandName.AUTHORIZATION_PAGE_USER_SUBMIT, new AuthorizationPageUserSubmitCommand());
        this.commands.put(CommandName.REGISTER_COMMAND, new RegistrationCommand());
        this.commands.put(CommandName.REGISTRATION_PAGE, new RegistrationPageCommand());
        this.commands.put(CommandName.LOCATION_PAGE, new LocationPageCommand());
        this.commands.put(CommandName.USER_PAGE, new UserPageCommand());
        this.commands.put(CommandName.MAIN_PAGE, new MainPageCommand());
        this.commands.put(CommandName.ORDER_PAGE, new OrderPageCommand());
        this.commands.put(CommandName.PAYMENT_PAGE, new PaymentPageCommand());
        this.commands.put(CommandName.START_ORDER, new ActionStartOrder());

        //Admin's commands
        this.commands.put(CommandName.REGISTRATION_PAGE_ADMIN, new RegistrationPageAdminCommand());
        this.commands.put(CommandName.EDIT_USER_PAGE_ADMIN, new EditUserPageAdminCommand());
        this.commands.put(CommandName.EDIT_USER_APPLY_COMMAND_ADMIN, new EditUserPageApplyAdminCommand());
        this.commands.put(CommandName.ADD_NEW_CARD_ADMIN, new AddNewCardAdminCommand());
        this.commands.put(CommandName.GET_CURRENCY_ADMIN, new GetCurrencyAdmin());
        this.commands.put(CommandName.USER_PAGEUP_BALANCE, new GetTopUpBalance());
        this.commands.put(CommandName.DO_MAIN_PAY, new DoMainPay());
        this.commands.put(CommandName.FIND_ORDER, new FindOrder());
        this.commands.put(CommandName.ORDER_PAGE_STATUS, new OrderPageStatus());


    }

    public static CommandProvider getInstance() {
        return instance;
    }

    public Command getCommand(final String cName) {
        CommandName commandName = CommandName.valueOf(cName.toUpperCase());
        Command result = commands.get(commandName);
        return result;
    }

}
