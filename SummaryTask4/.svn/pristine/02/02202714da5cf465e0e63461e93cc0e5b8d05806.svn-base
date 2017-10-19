package ua.nure.mykytenko.SummaryTask4.web.command.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.TicketService;
import ua.nure.mykytenko.SummaryTask4.db.bean.TicketBean;

public class ViewTicketsCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(StationViewCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {

		TicketService ticketService = (TicketService) req.getServletContext()
				.getAttribute(Constants.TICKET_SERVICE);
		List<TicketBean> beans = ticketService
				.getTicketNum();
		
		req.setAttribute(Constants.TICKET_BEAN, beans);
		
		return Path.TICKET_VIEW;
	}

}
