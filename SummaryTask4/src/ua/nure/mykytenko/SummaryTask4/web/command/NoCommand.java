package ua.nure.mykytenko.SummaryTask4.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Path;

/**
 * This command is called when the requested command is not found. It
 * redirects user to error page.
 * 
 * @author A.Mykytenko
 */
public class NoCommand implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		return Path.ERROR_404_VIEW_COMMAND;
	}
}
