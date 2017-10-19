package ua.nure.mykytenko.SummaryTask4.web.command.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;

public class IndexViewCommand implements Command {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		req.getSession().setAttribute(Constants.TRAIN_INFO_BEANS, null);
		return Path.INDEX_VIEW;
	}

}
