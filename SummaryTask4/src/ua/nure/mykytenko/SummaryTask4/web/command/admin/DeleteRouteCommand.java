package ua.nure.mykytenko.SummaryTask4.web.command.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.exception.Messages;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.RouteService;
import ua.nure.mykytenko.SummaryTask4.exception.DBException;

public class DeleteRouteCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(DeleteRouteCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String routeIdStr = req.getParameter("routeId");
		long routeId = Long.valueOf(routeIdStr);

		RouteService service = (RouteService) req.getServletContext()
				.getAttribute(Constants.ROUTE_SERVICE);

		List<String> errors = new ArrayList<>();
		boolean result = false;
		try {
			result = service.delete(routeId);

			if (!result) {
				errors.add(Messages.SERVER_ERROR);
				LOG.error(String.format("Error while deleting route id=%d",
						routeId));
			} else {
				LOG.info(String.format("Successfully deleted route id=%d",
						routeId));
			}
		} catch (DBException e) {
			errors.add(Messages.SERVER_ERROR);
			LOG.error(String.format("DBException while deleting route id=%d",
					routeId));
		}
		req.getSession().setAttribute(Constants.ERRORS, errors);

		return Path.ROUTE_VIEW_COMMAND;
	}

}
