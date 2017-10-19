package ua.nure.mykytenko.SummaryTask4.web.command.train;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.exception.Messages;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.RouteService;
import ua.nure.mykytenko.SummaryTask4.db.service.StationService;
import ua.nure.mykytenko.SummaryTask4.db.service.TrainBeanService;
import ua.nure.mykytenko.SummaryTask4.db.entity.Route;
import ua.nure.mykytenko.SummaryTask4.db.entity.Station;
import ua.nure.mykytenko.SummaryTask4.db.bean.TrainBean;
import ua.nure.mykytenko.SummaryTask4.db.bean.TrainBeanComparator;
import ua.nure.mykytenko.SummaryTask4.util.DateUtil;

public class FindTrainsCommand implements Command {

	private static final Logger LOG = Logger.getLogger(FindTrainsCommand.class);

	public static final String ERR_STATION_NOT_FOUND = "Station %s was not found.";

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String stationFrom = req.getParameter(Constants.STATION_FROM);
		String stationTo = req.getParameter(Constants.STATION_TO);
		String date = req.getParameter(Constants.DATE);

		HttpSession session = req.getSession();
		ServletContext context = req.getServletContext();

		req.setAttribute(Constants.STATION_FROM, stationFrom);
		req.setAttribute(Constants.STATION_TO, stationTo);
		req.setAttribute(Constants.DATE, date);

		LOG.debug("date=" + date);

		if (date == null || date.isEmpty()) {
			req.setAttribute("dateError", Messages.ERR_DATE_INCORRECT);
			session.setAttribute(Constants.TRAIN_INFO_BEANS, null);
			return Path.INDEX_VIEW;
		}

		Date dateSql = DateUtil.extractDate(date, Constants.CLIENT_DATE_FORMAT);
		if (dateSql.before(DateUtil.today())) {
			req.setAttribute("dateError", Messages.ERR_DATE_INCORRECT);
			return Path.INDEX_VIEW;
		}

		LOG.debug(String.format("Looking for train from %s to %s, date: %s",
				stationFrom, stationTo, date));

		StationService stationService = (StationService) context
				.getAttribute(Constants.STATION_SERVICE);

		Map<String, Station> stations = stationService.getStations(stationFrom,
				stationTo);
		Station from = stations.get(stationFrom);
		Station to = stations.get(stationTo);

		if (hasErrors(req, from, to)) {
			session.setAttribute(Constants.TRAIN_INFO_BEANS, null);
			return Path.INDEX_VIEW;
		}

		session.setAttribute(Constants.STATION_FROM, from);
		session.setAttribute(Constants.STATION_TO, to);

		List<TrainBean> trainBeans = getTrainBeans(context, from, to, dateSql);
		LOG.info(String.format("Train beans found ==> %s", trainBeans));

		sortBeans(req, trainBeans);

		session.setAttribute(Constants.TRAIN_INFO_BEANS, trainBeans);

		return Path.INDEX_VIEW;
	}

	private void sortBeans(HttpServletRequest req, List<TrainBean> beans) {
		String orderby = req.getParameter("orderby");
		String order = req.getParameter("o");

		LOG.info(String.format("Sort by %s %s", orderby, order));
		Collections.sort(beans,
				TrainBeanComparator.getByName(orderby).getComparator(order));
	}

	private boolean hasErrors(HttpServletRequest req, Station from,
			Station to) {
		boolean error = false;
		if (from == null) {
			error = true;
			req.setAttribute("stationFromError", Messages.STATION_NOT_FOUND);
			LOG.error(Messages.STATION_NOT_FOUND);
		}

		if (to == null) {
			error = true;
			req.setAttribute("stationToError", Messages.STATION_NOT_FOUND);
			LOG.error(Messages.STATION_NOT_FOUND);
		}

		return error;
	}

	private List<TrainBean> getTrainBeans(ServletContext context, Station from,
			Station to, java.sql.Date date) {
		RouteService routeService = (RouteService) context
				.getAttribute(Constants.ROUTE_SERVICE);
		TrainBeanService trainBeanService = (TrainBeanService) context
				.getAttribute(Constants.TRAIN_BEAN_SERVICE);

		List<Route> routes = routeService.getAllByStationsAndDate(from.getId(),
				to.getId(), date);
		LOG.info(String.format("Routes found ==> %s", routes.toString()));

		List<TrainBean> trainBeans = new ArrayList<>();
		for (Route r : routes) {
			trainBeans.add(trainBeanService.getFullInfo(r.getTrainId(),
					from.getId(), to.getId(), r.getId()));
		}

		return trainBeans;
	}

}
