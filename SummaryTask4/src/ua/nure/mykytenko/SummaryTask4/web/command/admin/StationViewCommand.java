package ua.nure.mykytenko.SummaryTask4.web.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.StationService;
import ua.nure.mykytenko.SummaryTask4.db.entity.Station;

public class StationViewCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(StationViewCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		StationService stationService = (StationService) req.getServletContext()
				.getAttribute(Constants.STATION_SERVICE);
		
		List<Station> stations = stationService.getAll(null);
		LOG.info(String.format("Stations obtained ==> %s", stations.toString()));
		
		req.setAttribute("stations", stations);
		return Path.STATIONS_VIEW;
	}

}
