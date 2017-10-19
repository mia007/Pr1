package ua.nure.mykytenko.SummaryTask4.web.command.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Path;

public class ErrorViewCommand implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		return Path.ERROR_404_VIEW;
	}

}