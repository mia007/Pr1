package ua.nure.mykytenko.SummaryTask4.web.command.train;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.entity.Carriage;
import ua.nure.mykytenko.SummaryTask4.db.entity.Station;
import ua.nure.mykytenko.SummaryTask4.db.bean.TicketOrderBean;
import ua.nure.mykytenko.SummaryTask4.db.bean.TrainBean;

public class OrderTicketViewCommand implements Command {

	private static final Logger LOG = Logger.getLogger(OrderTicketViewCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String carriageIdStr = req.getParameter("carriageId");
		String seatNumStr = req.getParameter("seatNum");

		long carriageId = Long.valueOf(carriageIdStr);
		int seatNum = Integer.valueOf(seatNumStr);
		long routeId = (long) req.getSession().getAttribute(Constants.ROUTE_ID);

		HttpSession session = req.getSession();

		String redirect = String.format("%s?%s", req.getServletPath(), req.getQueryString());
		LOG.info("redirect ==> " + redirect);
		session.setAttribute(Constants.REDIRECT, redirect);

		TrainBean trainBean = getTrainBean(session, routeId);
		Carriage carriage = getCarriage(trainBean, carriageId);

		Station from = (Station) session.getAttribute(Constants.STATION_FROM);
		Station to = (Station) session.getAttribute(Constants.STATION_TO);

		TicketOrderBean ticketBean = new TicketOrderBean();
		ticketBean.setSeatNum(seatNum);
		ticketBean.setCarriage(carriage);
		ticketBean.setTrainBean(trainBean);
		ticketBean.setStationFrom(from);
		ticketBean.setStationTo(to);

		session.setAttribute(Constants.TICKET_ORDER_BEAN, ticketBean);

		return Path.ORDER_TICKET_VIEW;
	}

	private TrainBean getTrainBean(HttpSession session, long routeId) {
		@SuppressWarnings("unchecked")
		List<TrainBean> trainBeans = (List<TrainBean>) session.getAttribute(Constants.TRAIN_INFO_BEANS);
		TrainBean trainBean = new TrainBean();
		trainBean.setRouteId(routeId);
		trainBean = trainBeans.get(trainBeans.indexOf(trainBean));

		LOG.info("Train bean ==> " + trainBean);

		return trainBean;
	}

	private Carriage getCarriage(TrainBean trainBean, long carriageId) {
		Carriage carriage = new Carriage();
		carriage.setId(carriageId);
		carriage = trainBean.getCarriages().get(trainBean.getCarriages().indexOf(carriage));

		LOG.info("Carriage ==> " + carriage);

		return carriage;

	}
}
