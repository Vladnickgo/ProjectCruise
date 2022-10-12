package com.vladnickgo.Project.context;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.command.home.ViewCruiseCommand;
import com.vladnickgo.Project.controller.command.home.header.*;
import com.vladnickgo.Project.controller.command.user.*;
import com.vladnickgo.Project.controller.dto.CruiseDto;
import com.vladnickgo.Project.controller.dto.UserDto;
import com.vladnickgo.Project.dao.CruiseDao;
import com.vladnickgo.Project.dao.UserDao;
import com.vladnickgo.Project.dao.entity.Cruise;
import com.vladnickgo.Project.dao.entity.User;
import com.vladnickgo.Project.dao.impl.CruiseDaoImpl;
import com.vladnickgo.Project.dao.impl.UserDaoImpl;
import com.vladnickgo.Project.service.CruiseService;
import com.vladnickgo.Project.service.UserService;
import com.vladnickgo.Project.service.impl.CruiseServiceImpl;
import com.vladnickgo.Project.service.impl.UserServiceImpl;
import com.vladnickgo.Project.service.mapper.CruiseMapper;
import com.vladnickgo.Project.service.mapper.Mapper;
import com.vladnickgo.Project.service.mapper.UserMapper;
import com.vladnickgo.Project.service.util.PageService;
import com.vladnickgo.Project.service.util.PageServiceImpl;
import com.vladnickgo.Project.service.util.PasswordEncryptionService;
import com.vladnickgo.Project.validator.CruiseValidator;
import com.vladnickgo.Project.validator.UserValidator;
import com.vladnickgo.Project.validator.Validator;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ApplicationContextInjector {

    private static final HikariConnectionPool HIKARI_CONNECTION_POOL = new HikariConnectionPool("bd-config");

    private static final UserDao USER_DAO = new UserDaoImpl(HIKARI_CONNECTION_POOL);

    private static final CruiseDao CRUISE_DAO = new CruiseDaoImpl(HIKARI_CONNECTION_POOL);

    private static final Mapper<UserDto, User> USER_MAPPER = new UserMapper();

    public static final Mapper<CruiseDto, Cruise> CRUISE_MAPPER = new CruiseMapper();

    private static final UserValidator USER_VALIDATOR = new UserValidator();

    private static final Validator<CruiseDto> CRUISE_VALIDATOR = new CruiseValidator();

    private static final PasswordEncryptionService PASSWORD_ENCRYPTION_SERVICE = new PasswordEncryptionService();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_MAPPER, USER_VALIDATOR, PASSWORD_ENCRYPTION_SERVICE);

    private static final CruiseService CRUISE_SERVICE = new CruiseServiceImpl(CRUISE_DAO, CRUISE_MAPPER, CRUISE_VALIDATOR);

    private static final PageService PAGE_SERVICE = new PageServiceImpl();

    private static final Command REGISTER_COMMAND = new RegisterCommand();

    private static final Command LOGIN_COMMAND = new LoginCommand();

    private static final Command LOGOUT_COMMAND = new LogoutCommand();

    private static final Command SHOW_USER_PROFILE_COMMAND = new ShowUserProfileCommand();

    private static final Command SHOW_ADMIN_PROFILE_COMMAND = new ShowAdminProfileCommand();

    private static final Command DEFAULT_COMMAND = new DefaultCommand();

    private static final Command HOME_COMMAND = new HomePageCommand();

    private static final Command ABOUT_COMMAND = new AboutPageCommand();

    private static final Command CONTACTS_COMMAND = new ContactsPageCommand();

    private static final Command LOGIN_PAGE_COMMAND = new LoginPageCommand();

    private static final Command REGISTER_PAGE_COMMAND = new RegisterPageCommand();

    private static final Command SHOW_CRUISES_COMMAND = new ShowCruisesCommand();

    private static final Command SHOW_CRUISES_PAGE_COMMAND = new ShowCruisesPageCommand();

    private static final Command SUCCESS_REGISTER_COMMAND = new SuccessRegisterCommand();

    private static final Command UNSUCCESSFUL_REGISTER_COMMAND = new UnsuccessfulRegisterCommand();

    private static final Command MY_ORDERS_PAGE_COMMAND = new MyOrdersPageCommand();

    private static final Command VIEW_CRUISES_COMMAND = new ViewCruiseCommand();

    private static final Map<String, Command> USER_COMMAND_NAME_TO_COMMAND = initUserCommand();

    private static final Map<String, Command> HOME_COMMAND_NAME_TO_COMMAND = initHomeCommand();

    private static ApplicationContextInjector applicationContextInjector;

    private ApplicationContextInjector() {

    }

    private static Map<String, Command> initUserCommand() {
        Map<String, Command> userCommandNameToCommand = new HashMap<>();
        userCommandNameToCommand.put("loginPage", LOGIN_PAGE_COMMAND);
        userCommandNameToCommand.put("login", LOGIN_COMMAND);
        userCommandNameToCommand.put("registerPage", REGISTER_PAGE_COMMAND);
        userCommandNameToCommand.put("register", REGISTER_COMMAND);
        userCommandNameToCommand.put("successRegister", SUCCESS_REGISTER_COMMAND);
        userCommandNameToCommand.put("unsuccessfulRegister", UNSUCCESSFUL_REGISTER_COMMAND);
        userCommandNameToCommand.put("logout", LOGOUT_COMMAND);
        userCommandNameToCommand.put("showUserProfile", SHOW_USER_PROFILE_COMMAND);
        userCommandNameToCommand.put("showAdminProfile", SHOW_ADMIN_PROFILE_COMMAND);
        userCommandNameToCommand.put("myOrdersPage", MY_ORDERS_PAGE_COMMAND);
        userCommandNameToCommand.put("defaultCommand", DEFAULT_COMMAND);
        return Collections.unmodifiableMap(userCommandNameToCommand);
    }

    private static Map<String, Command> initHomeCommand() {
        Map<String, Command> homeCommandNameToCommand = new HashMap<>();
        homeCommandNameToCommand.put("homePage", HOME_COMMAND);
        homeCommandNameToCommand.put("aboutPage", ABOUT_COMMAND);
        homeCommandNameToCommand.put("contactsPage", CONTACTS_COMMAND);
        homeCommandNameToCommand.put("showCruises", SHOW_CRUISES_COMMAND);
        homeCommandNameToCommand.put("showCruisesPageCommand", SHOW_CRUISES_PAGE_COMMAND);
        homeCommandNameToCommand.put("viewCruiseCommand", VIEW_CRUISES_COMMAND);

        homeCommandNameToCommand.put("defaultCommand", DEFAULT_COMMAND);

        return Collections.unmodifiableMap(homeCommandNameToCommand);
    }

    public static ApplicationContextInjector getInstance() {
        if (applicationContextInjector == null) {
            synchronized (ApplicationContextInjector.class) {
                if (applicationContextInjector == null) {
                    applicationContextInjector = new ApplicationContextInjector();
                }
            }
        }
        return applicationContextInjector;
    }

    public UserService getUserService() {
        return USER_SERVICE;
    }

    public CruiseService getCruiseService() {
        return CRUISE_SERVICE;
    }

    public PageService getPageService() {
        return PAGE_SERVICE;
    }

    public Map<String, Command> getHomeCommandNameToCommand() {
        return HOME_COMMAND_NAME_TO_COMMAND;
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }

}