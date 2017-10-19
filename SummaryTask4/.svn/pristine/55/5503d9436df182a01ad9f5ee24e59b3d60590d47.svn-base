package ua.nure.mykytenko.SummaryTask4.web.command.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;

public class LogoutCommand implements Command {

	private static final Logger LOG = Logger.getLogger(LogoutCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		LOG.info("Logged out");
		
		return Path.INDEX_VIEW;
	}

}
