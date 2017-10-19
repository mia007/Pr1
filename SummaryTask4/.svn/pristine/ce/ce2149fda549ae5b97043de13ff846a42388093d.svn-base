package ua.nure.mykytenko.SummaryTask4.web.command.train;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.RouteBeanService;

public class ShowRouteInfoCommand implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String trainIdStr = req.getParameter("trainId");
		long trainId = Long.valueOf(trainIdStr);

		RouteBeanService service = (RouteBeanService) req.getServletContext()
				.getAttribute(Constants.ROUTE_INFO_SERVICE);
		req.setAttribute(Constants.ROUTE_INFO_BEANS,
				service.getAllByTrainId(trainId));

		return Path.ROUTE_INFO_VIEW;
	}

}
