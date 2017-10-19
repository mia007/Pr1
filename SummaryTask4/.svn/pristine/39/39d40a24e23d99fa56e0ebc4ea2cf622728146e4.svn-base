package ua.nure.mykytenko.SummaryTask4.web.command.view;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.RouteService;
import ua.nure.mykytenko.SummaryTask4.db.service.TrainBeanService;
import ua.nure.mykytenko.SummaryTask4.util.DateUtil;
import ua.nure.mykytenko.SummaryTask4.db.entity.Route;
import ua.nure.mykytenko.SummaryTask4.db.bean.TrainBean;

public class RouteViewCommand implements Command {

	private static final Logger LOG = Logger.getLogger(RouteViewCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		TrainBeanService service = (TrainBeanService) req.getServletContext()
				.getAttribute(Constants.TRAIN_BEAN_SERVICE);
		List<TrainBean> beans = service.getAll();

		LOG.info(String.format("Trains found ==> %s", beans));

		HttpSession session = req.getSession();
		session.setAttribute("trainBeans", beans);

		String dateFrom = req.getParameter(Constants.DATE_FROM);
		String dateTo = req.getParameter(Constants.DATE_TO);
		if (dateFrom == null) {
			dateFrom = (String) session.getAttribute(Constants.DATE_FROM);
		}
		if (dateTo == null) {
			dateTo = (String) session.getAttribute(Constants.DATE_TO);
		}

		if (dateFrom != null && dateTo != null) {
			session.setAttribute(Constants.DATE_FROM, dateFrom);
			session.setAttribute(Constants.DATE_TO, dateTo);
			getRoute(req.getServletContext(), session, beans, dateFrom, dateTo);
		}

		return Path.ROUTE_VIEW;
	}

	private void getRoute(ServletContext context, HttpSession session, List<TrainBean> trainBeans, String dateFrom,
			String dateTo) {
		RouteService routeService = (RouteService) context.getAttribute(Constants.ROUTE_SERVICE);
		Date from = DateUtil.extractDate(dateFrom, Constants.CLIENT_DATE_FORMAT);
		Date to = DateUtil.extractDate(dateTo, Constants.CLIENT_DATE_FORMAT);
		List<Route> routes = routeService.getAllByDates(from, to);

		LOG.info("Routes obtained ==> " + routes);

		List<TrainBean> trainBeans2 = new ArrayList<>();
		for (Route item : routes) {
			trainBeans2.add(extract(trainBeans, item));
		}

		session.setAttribute("routes", trainBeans2);
		session.setAttribute(Constants.ROUTE_ADD_MES, null);
		session.setAttribute(Constants.ROUTE_ADD_ERR, null);
	}

	private TrainBean extract(List<TrainBean> beans, Route route) {
		TrainBean bean = new TrainBean();
		bean.setTrainId(route.getTrainId());
		bean.setRouteId(route.getId());
		bean.setDepDate(route.getDate());

		TrainBean att = getByTrainId(beans, route.getTrainId());
		bean.setTrainTag(att.getTrainTag());
		bean.setStationFrom(att.getStationFrom());
		bean.setStationTo(att.getStationTo());

		return bean;
	}

	private TrainBean getByTrainId(List<TrainBean> beans, long trainId) {
		for (TrainBean bean : beans) {
			if (bean.getTrainId() == trainId) {
				return bean;
			}
		}
		return null;
	}

}
