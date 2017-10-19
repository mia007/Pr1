package ua.nure.mykytenko.SummaryTask4.web.command.admin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.StationService;
import ua.nure.mykytenko.SummaryTask4.db.service.TrainService;
import ua.nure.mykytenko.SummaryTask4.db.entity.RouteItem;
import ua.nure.mykytenko.SummaryTask4.db.entity.Station;
import ua.nure.mykytenko.SummaryTask4.db.entity.Train;
import ua.nure.mykytenko.SummaryTask4.util.DateUtil;

public class AddTrainCommand implements Command {

	private static final Logger LOG = Logger.getLogger(AddTrainCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String trainTag = req.getParameter("trainTag");
		String price = req.getParameter("trainPrice");

		ServletContext context = req.getServletContext();

		Train train = new Train();
		train.setTag(trainTag);
		train.setPrice(BigDecimal.valueOf(Double.valueOf(price)));

		train.setRouteItems(processRouteItems(req, train.getId()));

		TrainService trainService = (TrainService) context
				.getAttribute(Constants.TRAIN_SERVICE);
		trainService.create(train);

		return Path.TRAIN_VIEW_COMMAND;
	}

	private List<RouteItem> processRouteItems(HttpServletRequest req,
			int trainId) {
		String[] stations = req.getParameterValues("stationSelect");
		String[] arrTime = req.getParameterValues("arrTime");
		String[] depTime = req.getParameterValues("depTime");

		List<RouteItem> routeItems = new ArrayList<>();

		RouteItem item = extractRouteItem(req.getServletContext(), null,
				depTime[0], stations[0], 0, trainId);
		routeItems.add(item);
		
		for (int i = 1; i < stations.length - 1; i++) {
			item = extractRouteItem(req.getServletContext(), arrTime[i],
					depTime[i], stations[i], i, trainId);
			LOG.debug(item);

			routeItems.add(item);
		}

		item = extractRouteItem(req.getServletContext(),
				arrTime[stations.length - 1], null,
				stations[stations.length - 1], stations.length - 1, trainId);
		routeItems.add(item);
		
		return routeItems;
	}

	private RouteItem extractRouteItem(ServletContext context, String arrTime,
			String depTime, String stationName, int ordinal, int trainId) {
		StationService stationService = (StationService) context
				.getAttribute(Constants.STATION_SERVICE);
		RouteItem item = new RouteItem();
		Date date = DateUtil.extractDate(arrTime, Constants.CLIENT_TIME_FORMAT);
		LOG.debug("date ==> " + date);
		item.setArrivalTime(date);
		date = DateUtil.extractDate(depTime, Constants.CLIENT_TIME_FORMAT);
		item.setDepartureTime(date);

		Station station = stationService.getByName(stationName);

		item.setOrdinal(ordinal);
		item.setStationId(station.getId());
		item.setTrainId(trainId);

		return item;
	}

}
