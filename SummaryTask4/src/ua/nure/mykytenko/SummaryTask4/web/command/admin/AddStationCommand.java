package ua.nure.mykytenko.SummaryTask4.web.command.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.StationService;
import ua.nure.mykytenko.SummaryTask4.db.entity.Station;

public class AddStationCommand implements Command {

	private static final Logger LOG = Logger.getLogger(AddStationCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("stationName");

		LOG.debug("In add station");

		Station s = new Station();
		s.setName(name);


		LOG.info(String.format("creating station ==> %s	", s.toString()));

		StationService service = (StationService) req.getServletContext()
				.getAttribute(Constants.STATION_SERVICE);
		int status = service.create(s) ? HttpServletResponse.SC_OK
				: HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
		res.setStatus(status);

		return Path.STATIONS_VIEW_COMMAND;
	}

}
