package ua.nure.mykytenko.SummaryTask4.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.RouteService;
import ua.nure.mykytenko.SummaryTask4.db.entity.Route;
import ua.nure.mykytenko.SummaryTask4.util.DateUtil;

public class AddRouteCommand implements Command {

	private static final Logger LOG = Logger.getLogger(AddRouteCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String trainIdStr = req.getParameter("trainSelect");
		String date = req.getParameter("date");

		int trainId = Integer.valueOf(trainIdStr);

		HttpSession session = req.getSession();

		RouteService service = (RouteService) req.getServletContext()
				.getAttribute(Constants.ROUTE_SERVICE);
		Route route = new Route();
		route.setTrainId(trainId);
		route.setDate(DateUtil.extractDate(date, Constants.CLIENT_DATE_FORMAT));

		if (service.create(route)) {
			session.setAttribute(Constants.ROUTE_ADD_MES, "message.route_added");
			session.setAttribute(Constants.ROUTE_ADD_ERR, null);
			LOG.info(String.format("New route added ==> %s", route.toString()));
		} else {
			session.setAttribute(Constants.ROUTE_ADD_ERR,
					"message.route_add_error");
			session.setAttribute(Constants.ROUTE_ADD_MES, null);
			LOG.error("Failed to create route");
		}
		return Path.ROUTE_VIEW_COMMAND;
	}

}
