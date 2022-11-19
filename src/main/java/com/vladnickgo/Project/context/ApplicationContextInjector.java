package com.vladnickgo.Project.context;

import com.vladnickgo.Project.connection.HikariConnectionPool;
import com.vladnickgo.Project.controller.command.Command;
import com.vladnickgo.Project.controller.command.home.*;
import com.vladnickgo.Project.controller.command.home.header.*;
import com.vladnickgo.Project.controller.command.user.*;
import com.vladnickgo.Project.controller.dto.*;
import com.vladnickgo.Project.dao.*;
import com.vladnickgo.Project.dao.entity.*;
import com.vladnickgo.Project.dao.impl.*;
import com.vladnickgo.Project.service.*;
import com.vladnickgo.Project.service.impl.*;
import com.vladnickgo.Project.service.mapper.*;
import com.vladnickgo.Project.validator.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ApplicationContextInjector {

    private static final HikariConnectionPool HIKARI_CONNECTION_POOL = new HikariConnectionPool("bd-config");

    private static final UserDao USER_DAO = new UserDaoImpl(HIKARI_CONNECTION_POOL);

    private static final CruiseDao CRUISE_DAO = new CruiseDaoImpl(HIKARI_CONNECTION_POOL);

    private static final CabinTypeDao CABIN_TYPE_DAO = new CabinTypeDaoImpl(HIKARI_CONNECTION_POOL);

    private static final CabinDao CABIN_DAO = new CabinDaoImpl(HIKARI_CONNECTION_POOL);

    private static final CabinStatusDao CABIN_STATUS_DAO = new CabinStatusDaoImpl(HIKARI_CONNECTION_POOL);

    private static final PaymentDao PAYMENT_DAO = new PaymentDaoImpl(HIKARI_CONNECTION_POOL);

    private static final OrderDao ORDER_DAO = new OrderDaoImpl(HIKARI_CONNECTION_POOL);

    private static final ShipDao SHIP_DAO = new ShipDaoImpl(HIKARI_CONNECTION_POOL);

    private static final RoutePointDao ROUTE_POINT_DAO = new RoutePointDaoImpl(HIKARI_CONNECTION_POOL);

    private static final RouteDao ROUTE_DAO = new RouteDaoImpl(HIKARI_CONNECTION_POOL);

    private static final PortDao PORT_DTO = new PortDaoImpl(HIKARI_CONNECTION_POOL);

    private static final Mapper<UserDto, User> USER_MAPPER = new UserMapper();
    public static final Mapper<CruiseResponseDto, Cruise> CRUISE_RESPONSE_MAPPER = new CruiseResponseMapper();

    private static final Mapper<CabinDto, Cabin> CABIN_MAPPER = new CabinMapper();

    private static final Mapper<RoutePointDto, RoutePoint> ROUTE_POINT_MAPPER = new RoutePointMapper();

    private static final Mapper<CabinTypeDto, CabinType> CABIN_TYPE_MAPPER = new CabinTypeMapper();

    private static final Mapper<CabinStatusDto, CabinStatus> CABIN_STATUS_MAPPER = new CabinStatusMapper();

    private static final Mapper<PaymentDto, Payment> PAYMENT_MAPPER = new PaymentMapper();

    private static final Mapper<ShipDto, Ship> SHIP_MAPPER = new ShipMapper();

    private static final Mapper<RouteDto, Route> ROUTE_MAPPER = new RouteMapper();

    private static final Mapper<PortDto, Port> PORT_MAPPER = new PortMapper();

    private static final Mapper<RoutePointRequestDto, RoutePoint> ROUTE_POINT_REQUEST_DTO_MAPPER = new RoutePointRequestMapper();

    private static final Mapper<CruiseDto, Cruise> CRUISE_MAPPER = new CruiseMapper();

    private static final UserValidator USER_VALIDATOR = new UserValidator();

    private static final Validator<CruiseDto> CRUISE_VALIDATOR = new CruiseValidator();

    private static final Validator<CabinDto> CABIN_VALIDATOR = new CabinValidator();

    private static final Validator<PaymentDocumentsDto> PAYMENT_DOCUMENTS_VALIDATOR = new PaymentDocumentsValidator();

    private static final Validator<PaymentDto> PAYMENT_VALIDATOR = new PaymentValidator();

    private static final Validator<ShipDto> SHIP_VALIDATOR = new ShipValidator();

    private static final Validator<RouteDto> ROUTE_VALIDATOR = new RouteValidator();

    private static final Validator<CabinTypeRequestDto> CABIN_TYPE_REQUEST_DTO_VALIDATOR = new CabinTypeRequestValidator();

    private static final Validator<RoutePointDto> ROUTE_POINT_VALIDATOR = new RoutePointValidator();

    private static final Validator<PortDto> PORT_VALIDATOR = new PortValidator();

    private static final Validator<RoutePointRequestDto> ROUTE_POINT_REQUEST_VALIDATOR = new RoutePointRequestValidator();

    private static final UserService USER_SERVICE = new UserServiceImpl(USER_DAO, USER_MAPPER, USER_VALIDATOR);

    private static final CruiseService CRUISE_SERVICE = new CruiseServiceImpl(CRUISE_DAO, ORDER_DAO, CRUISE_RESPONSE_MAPPER, CRUISE_MAPPER, CRUISE_VALIDATOR);

    private static final CabinTypeService CABIN_TYPE_SERVICE = new CabinTypeServiceImpl(CABIN_TYPE_DAO, CABIN_TYPE_MAPPER);

    private static final CabinService CABIN_SERVICE = new CabinServiceImpl(CABIN_DAO, CABIN_MAPPER, CABIN_VALIDATOR);

    private static final CabinStatusService CABIN_STATUS_SERVICE = new CabinStatusServiceImpl(CABIN_STATUS_DAO, CABIN_STATUS_MAPPER);

    private static final PaymentService PAYMENT_SERVICE = new PaymentServiceImpl(PAYMENT_DAO, PAYMENT_MAPPER, PAYMENT_VALIDATOR);

    private static final OrderService ORDER_SERVICE = new OrderServiceImpl(ORDER_DAO);

    private static final PaymentDocumentsService PAYMENT_DOCUMENTS_SERVICE = new PaymentDocumentsServiceImpl(PAYMENT_DOCUMENTS_VALIDATOR);

    private static final ShipService SHIP_SERVICE = new ShipServiceImpl(SHIP_DAO, SHIP_MAPPER, SHIP_VALIDATOR);

    private static final RouteService ROUTE_SERVICE = new RouteServiceImpl(ROUTE_DAO, ROUTE_MAPPER, ROUTE_VALIDATOR);

    private static final RoutePointService ROUTE_POINT_SERVICE = new RoutePointServiceImpl(ROUTE_POINT_DAO, ROUTE_POINT_MAPPER, ROUTE_POINT_REQUEST_DTO_MAPPER, ROUTE_POINT_REQUEST_VALIDATOR);

    private static final PortService PORT_SERVICE = new PortServiceImpl(PORT_DTO, PORT_MAPPER, PORT_VALIDATOR);

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

    private static final Command VIEW_CRUISES_COMMAND = new ViewCruisesCommand();

    private static final Command CREATE_CABIN_ORDER_COMMAND = new CreateCabinOrderCommand();

    private static final Command PAYMENT_CONFIRMATION_PAGE_COMMAND = new PaymentConfirmationPage();

    private static final Command PAYMENT_COMMAND = new PaymentCommand();

    private static final Command SUCCESS_PAYMENT_COMMAND = new SuccessPaymentCommand();

    private static final Command ORDER_HANDLER_PAGE_COMMAND = new OrderHandlerPageCommand();

    private static final Command CONFIRM_PAYMENT_COMMAND = new ConfirmPaymentCommand();

    private static final Command CANCEL_PAYMENT_COMMAND = new CancelPaymentCommand();

    private static final Command CONFIRM_PAYMENT_PAGE_COMMAND = new ConfirmPaymentPageCommand();

    private static final Command EDIT_LINERS_COMMAND = new EditShipsCommand();

    private static final Command EDIT_ROUTES_COMMAND = new EditRoutesCommand();

    private static final Command EDIT_CRUISES_COMMAND = new EditCruisesCommand();

    private static final Command ADD_SHIP_COMMAND = new AddShipCommand();

    private static final Command EDIT_SHIP_DATA_COMMAND = new EditShipDataCommand();

    private static final Command CONFIRM_CHANGE_SHIP_DATA_COMMAND = new ConfirmChangeShipDataCommand();

    private static final Command ADD_ROUTE_COMMAND = new AddRouteCommand();

    private static final Command EDIT_ROUTE_DATA_COMMAND = new EditRouteDataCommand();

    private static final Command ADD_ROUTE_POINT_COMMAND = new AddRoutePointCommand();

    private static final Command DELETE_ROUTE_POINT_COMMAND = new DeleteRoutePointCommand();

    private static final Command ADD_CRUISE_COMMAND = new AddCruiseCommand();

    private static final Command DELETE_SHIP_PAGE_COMMAND = new DeleteShipPageCommand();

    private static final Command DELETE_LINER_COMMAND = new DeleteLinerCommand();

    private static final Command BLOCK_CRUISE_COMMAND = new BlockCruiseCommand();

    private static final Command UNBLOCK_CRUISE_COMMAND = new UnblockCruiseCommand();

    private static final Command EDIT_PORTS_COMMAND = new EditPortsCommand();

    private static final Command ADD_PORT_COMMAND = new AddPortCommand();

    private static final Command UNSUCCESSFUL_PORT_ADDING_COMMAND = new UnsuccessfulPortAddingCommand();

    private static final Command SUCCESSFUL_PORT_ADDING_COMMAND = new SuccessfulPortAddingCommand();

    private static final Command ADMIN_STATISTIC_COMMAND = new AdminStatisticCommand();

    private static final Command DELETE_PORT_PAGE_COMMAND = new DeletePortPageCommand();

    private static final Command DELETE_PORT_COMMAND = new DeletePortCommand();

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
        userCommandNameToCommand.put("createCabinOrder", CREATE_CABIN_ORDER_COMMAND);
        userCommandNameToCommand.put("paymentConfirmationPageCommand", PAYMENT_CONFIRMATION_PAGE_COMMAND);
        userCommandNameToCommand.put("paymentCommand", PAYMENT_COMMAND);
        userCommandNameToCommand.put("successPaymentCommand", SUCCESS_PAYMENT_COMMAND);
        userCommandNameToCommand.put("orderHandlerPage", ORDER_HANDLER_PAGE_COMMAND);
        userCommandNameToCommand.put("confirmPayment", CONFIRM_PAYMENT_COMMAND);
        userCommandNameToCommand.put("cancelPayment", CANCEL_PAYMENT_COMMAND);
        userCommandNameToCommand.put("confirmPaymentPage", CONFIRM_PAYMENT_PAGE_COMMAND);
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
        homeCommandNameToCommand.put("editLinersCommand", EDIT_LINERS_COMMAND);
        homeCommandNameToCommand.put("editRoutesCommand", EDIT_ROUTES_COMMAND);
        homeCommandNameToCommand.put("editCruisesCommand", EDIT_CRUISES_COMMAND);
        homeCommandNameToCommand.put("addShipCommand", ADD_SHIP_COMMAND);
        homeCommandNameToCommand.put("editShipDataCommand", EDIT_SHIP_DATA_COMMAND);
        homeCommandNameToCommand.put("confirmChangeShipDataCommand", CONFIRM_CHANGE_SHIP_DATA_COMMAND);
        homeCommandNameToCommand.put("editRouteDataCommand", EDIT_ROUTE_DATA_COMMAND);
        homeCommandNameToCommand.put("addRouteCommand", ADD_ROUTE_COMMAND);
        homeCommandNameToCommand.put("addRoutePointCommand", ADD_ROUTE_POINT_COMMAND);
        homeCommandNameToCommand.put("deleteRoutePointCommand", DELETE_ROUTE_POINT_COMMAND);
        homeCommandNameToCommand.put("addCruiseCommand", ADD_CRUISE_COMMAND);
        homeCommandNameToCommand.put("deleteShipPageCommand", DELETE_SHIP_PAGE_COMMAND);
        homeCommandNameToCommand.put("deleteLinerCommand", DELETE_LINER_COMMAND);
        homeCommandNameToCommand.put("blockCruiseCommand", BLOCK_CRUISE_COMMAND);
        homeCommandNameToCommand.put("unblockCruiseCommand", UNBLOCK_CRUISE_COMMAND);
        homeCommandNameToCommand.put("editPortsCommand", EDIT_PORTS_COMMAND);
        homeCommandNameToCommand.put("addPortCommand", ADD_PORT_COMMAND);
        homeCommandNameToCommand.put("unsuccessfulPortAddingCommand", UNSUCCESSFUL_PORT_ADDING_COMMAND);
        homeCommandNameToCommand.put("successfulPortAddingCommand", SUCCESSFUL_PORT_ADDING_COMMAND);
        homeCommandNameToCommand.put("adminStatisticCommand", ADMIN_STATISTIC_COMMAND);
        homeCommandNameToCommand.put("deletePortPageCommand",DELETE_PORT_PAGE_COMMAND);
        homeCommandNameToCommand.put("deletePortCommand",DELETE_PORT_COMMAND);

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

    public CabinTypeService getCabinTypeService() {
        return CABIN_TYPE_SERVICE;
    }

    public CruiseService getCruiseService() {
        return CRUISE_SERVICE;
    }

    public CabinStatusService getCabinStatusService() {
        return CABIN_STATUS_SERVICE;
    }

    public ShipService getShipService() {
        return SHIP_SERVICE;
    }

    public Map<String, Command> getHomeCommandNameToCommand() {
        return HOME_COMMAND_NAME_TO_COMMAND;
    }

    public Map<String, Command> getUserCommands() {
        return USER_COMMAND_NAME_TO_COMMAND;
    }

    public CabinService getCabinService() {
        return CABIN_SERVICE;
    }

    public PaymentService getPaymentService() {
        return PAYMENT_SERVICE;
    }

    public OrderService getOrderService() {
        return ORDER_SERVICE;
    }

    public PaymentDocumentsService getPaymentDocumentsService() {
        return PAYMENT_DOCUMENTS_SERVICE;
    }

    public RouteService getRouteService() {
        return ROUTE_SERVICE;
    }

    public RoutePointService getRoutePontService() {
        return ROUTE_POINT_SERVICE;
    }

    public PortService getPortService() {
        return PORT_SERVICE;
    }
}