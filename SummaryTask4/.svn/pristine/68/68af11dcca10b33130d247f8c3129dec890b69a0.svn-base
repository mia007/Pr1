package ua.nure.mykytenko.SummaryTask4.web.listener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import ua.nure.mykytenko.SummaryTask4.exception.Messages;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.*;

public class ContextListener implements ServletContextListener {
	private static final Logger LOG = Logger.getLogger(ContextListener.class);
	
	private String dbms;

	public void contextDestroyed(ServletContextEvent event) {
		log("Servlet context destruction starts");
		log("Servlet context destruction finished");
	}

	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		initLog4J(context);
		initParams(context);
		initContainers();
		configureServices(context);
	}

	/**
	 * Initializes log4j framework.
	 * 
	 * @param servletContext
	 */
	private void initLog4J(ServletContext servletContext) {
		log("Log4J initialization started");
		try {
			PropertyConfigurator.configure(servletContext.getRealPath(Path.LOG4J_CONFIG));
			LOG.debug("Log4j has been initialized");
		} catch (Exception ex) {
			LOG.error(Messages.ERR_CANNOT_CONFIGURE_LOG4J, ex);
		}
		log("Log4J initialization finished");
	}

	/**
	 * Initializes CommandContainer.
	 * 
	 * @param servletContext
	 */
	private void initContainers() {

		// initialize commands container
		// just load class to JVM
		try {
			Class.forName(
					"ua.nure.mykytenko.SummaryTask4.web.command.CommandContainer");
			Class.forName(
					"ua.nure.mykytenko.SummaryTask4.util.PropertyContainer");
			Class.forName("ua.nure.mykytenko.SummaryTask4.db.DBUtil");
		} catch (ClassNotFoundException e) {
			LOG.error(Messages.ERR_CONTAINERS_NOT_INITIALIZED);
		}
		LOG.debug("Containers initialized successfully");
	}
	
	private void initParams(ServletContext context) {
		context.setAttribute(Constants.BUNDLE_BASENAME,
				context.getInitParameter(Constants.BUNDLE_BASENAME));
		String[] locales = context.getInitParameter(Constants.LOCALE_LIST)
				.split("\\s+");
		context.setAttribute(Constants.LOCALE_LIST, Arrays.asList(locales));

		List<Locale> localesL = new ArrayList<>();
		for (String l : locales) {
			localesL.add(new Locale(l));
		}
		LOG.info(
				String.format("Locales supported ==> %s", localesL.toString()));
		context.setAttribute(Constants.LOCALE_LIST_LOCALE, localesL);

		dbms = context.getInitParameter("dbms");
		LOG.info(String.format("Application is using %s DBMS", dbms));

		LOG.info("Init params loaded");
	}
	
	private void configureServices(ServletContext context) {
		
		LOG.debug("configuring services");

		context.setAttribute(Constants.USER_SERVICE,
				new UserService());
		LOG.info(String.format(Messages.SERVICE_INITIALIZED, "User"));

		context.setAttribute(Constants.STATION_SERVICE,
				new StationService());
		LOG.info(String.format(Messages.SERVICE_INITIALIZED, "Station"));

		context.setAttribute(Constants.ROUTE_SERVICE,
				new RouteService());
		LOG.info(String.format(Messages.SERVICE_INITIALIZED, "Route"));

		context.setAttribute(Constants.ROUTE_ITEM_SERVICE,
				new RouteItemService());
		LOG.info(String.format(Messages.SERVICE_INITIALIZED, "RouteItem"));

		context.setAttribute(Constants.TRAIN_BEAN_SERVICE, new TrainBeanService());
		LOG.info(String.format(Messages.SERVICE_INITIALIZED, "TrainInfo"));

		context.setAttribute(Constants.ROUTE_INFO_SERVICE,
				new RouteBeanService());
		LOG.info(String.format(Messages.SERVICE_INITIALIZED, "RouteInfo"));

		context.setAttribute(Constants.CARRIAGE_SERVICE,
				new CarriageService());
		LOG.info(String.format(Messages.SERVICE_INITIALIZED, "Carriage"));

		context.setAttribute(Constants.TICKET_SERVICE,
				new TicketService());
		LOG.info(String.format(Messages.SERVICE_INITIALIZED, "Ticket"));

		context.setAttribute(Constants.TRAIN_SERVICE,
				new TrainService());
		LOG.info(String.format(Messages.SERVICE_INITIALIZED, "Train"));

		context.setAttribute(Constants.CARRIAGE_TYPE_SERVICE,
				new CarriageTypeService());
		LOG.info(String.format(Messages.SERVICE_INITIALIZED, "CarriageType"));
	}

	
	private void log(String msg) {
		System.out.println("[ContextListener] " + msg);
	}
}
