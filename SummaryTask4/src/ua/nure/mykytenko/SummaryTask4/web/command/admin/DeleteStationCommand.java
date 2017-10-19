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
import ua.nure.mykytenko.SummaryTask4.db.service.StationService;
import ua.nure.mykytenko.SummaryTask4.exception.DBException;

public class DeleteStationCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(DeleteStationCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String stationIdStr = req.getParameter("stationId");
		long stationId = Long.valueOf(stationIdStr);

		StationService service = (StationService) req.getServletContext()
				.getAttribute(Constants.STATION_SERVICE);

		List<String> errors = new ArrayList<>();
		boolean result = false;
		try {
			result = service.delete(stationId);
			
			if (!result) {
				errors.add(Messages.SERVER_ERROR);
				LOG.error(String.format("Error while deleting station id=%d",
						stationId));
			}
		} catch (DBException e) {
			errors.add(Messages.SERVER_ERROR);
			LOG.error(String.format("DBException while deleting station id=%d",
					stationId));
		}
		req.getSession().setAttribute(Constants.ERRORS, errors);

		return Path.STATIONS_VIEW_COMMAND;
	}

}
