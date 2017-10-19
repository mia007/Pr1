package ua.nure.mykytenko.SummaryTask4.web.command;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.user.*;
import ua.nure.mykytenko.SummaryTask4.web.command.train.*;
import ua.nure.mykytenko.SummaryTask4.web.command.admin.*;
import ua.nure.mykytenko.SummaryTask4.web.command.view.*;
/**
 * Container for commands.
 * 
 * @author A.Mykytenko
 */
public final class CommandContainer {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	private static Map<String, Command> commands = new HashMap<>();

	static {
		commands.put("login", new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("signUp", new SignUpCommand());

		commands.put("findTrains", new FindTrainsCommand());
		commands.put("showRouteInfo", new ShowRouteInfoCommand());
		commands.put("getFreeSeats", new GetFreeSeatsCommand());
		commands.put("orderTicketView", new OrderTicketViewCommand());
		commands.put("orderTicket", new OrderTicketCommand());

	commands.put("loginView", new LoginViewCommand());
		commands.put("signUpView", new SignUpViewCommand());
		commands.put("userProfileView", new UserProfileViewCommand());
		commands.put("indexView", new IndexViewCommand());
		commands.put("paymentSuccessfulView", new PaymentSuccessViewCommand());
		commands.put("error404View", new ErrorViewCommand());

		commands.put("adminView", new AdminViewCommand());
		commands.put("ticketNumView", new ViewTicketsCommand());
		commands.put("stationView", new StationViewCommand());
		commands.put("routeView", new RouteViewCommand());
		commands.put("trainView", new TrainViewCommand());
		commands.put("carriagesView", new CarriagesViewCommand());

		commands.put("deleteStation", new DeleteStationCommand());
		commands.put("deleteTrain", new DeleteTrainCommand());
		commands.put("addStation", new AddStationCommand());
		commands.put("getRoutes", new GetRoutesCommand());
		commands.put("addTrain", new AddTrainCommand());
		commands.put("deleteRoute", new DeleteRouteCommand());
		commands.put("addCarriages", new AddCarriagesCommand());
		commands.put("addRoute", new AddRouteCommand());

		commands.put("getStations", new GetStationsCommand());
		commands.put("noCommand", new NoCommand());

		LOG.debug("Command container was successfully initialized");
		LOG.trace("Number of commands --> " + commands.size());
	}

	/**
	 *  Returns command object with the given name.
	 * 
	 * @param commandName
	 *            Name of the command.
	 * @return Command object.
	 */
	public static Command get(String commandName) {
		if (commandName == null || !commands.containsKey(commandName)) {
			LOG.info("Command not found, name --> " + commandName);
			return commands.get("noCommand");
		}

		return commands.get(commandName);
	}
}
