package ua.nure.mykytenko.SummaryTask4.web.command.train;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.CarriageService;
import ua.nure.mykytenko.SummaryTask4.db.entity.Carriage;
import ua.nure.mykytenko.SummaryTask4.db.bean.CarriageListBean;
import ua.nure.mykytenko.SummaryTask4.db.bean.TrainBean;

public class GetFreeSeatsCommand implements Command {

	private static final Logger LOG = Logger.getLogger(GetFreeSeatsCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String routeIdStr = req.getParameter("routeId");
		long routeId = Long.valueOf(routeIdStr);

		@SuppressWarnings("unchecked")
		List<TrainBean> trainInfoBeans = (List<TrainBean>) req.getSession().getAttribute(Constants.TRAIN_INFO_BEANS);
		TrainBean temp = new TrainBean();
		temp.setRouteId(routeId);

		LOG.debug("beans ==> " + trainInfoBeans);

		TrainBean bean = trainInfoBeans.get(trainInfoBeans.indexOf(temp));
		List<Carriage> carriages = getCarriageList(req, bean);
		bean.setCarriages(carriages);

		req.setAttribute(Constants.CARRIAGE_LIST, new CarriageListBean(carriages));
		req.setAttribute(Constants.ROUTE_ID, routeId);

		return Path.FREE_SEATS_VIEW;
	}

	private List<Carriage> getCarriageList(HttpServletRequest req, TrainBean bean) {
		CarriageService carriageService = (CarriageService) req.getServletContext()
				.getAttribute(Constants.CARRIAGE_SERVICE);
		List<Carriage> carriages = carriageService.getAllByTrainIdRouteItems(bean.getTrainId(), bean.getRouteId(), 
				bean.getRouteItemIdFrom(), bean.getRouteItemIdTo());

		LOG.debug("carriages found ==> " + carriages);
		return carriages;
	}

}
