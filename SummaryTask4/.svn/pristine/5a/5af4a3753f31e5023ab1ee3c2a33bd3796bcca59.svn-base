package ua.nure.mykytenko.SummaryTask4.web.command.user;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.TicketService;
import ua.nure.mykytenko.SummaryTask4.db.entity.User;
import ua.nure.mykytenko.SummaryTask4.db.bean.TicketOrderBean;

public class UserProfileViewCommand implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		User currentUser = (User) req.getSession()
				.getAttribute(Constants.CURRENT_USER);

		TicketService ticketService = (TicketService) req.getServletContext()
				.getAttribute(Constants.TICKET_SERVICE);
		List<TicketOrderBean> beans = ticketService
				.getAllByUserId(currentUser.getId());
		
		req.getSession().setAttribute(Constants.TICKETS, beans);
		
		return Path.PROFILE_VIEW;
	}

}
