package ua.nure.mykytenko.SummaryTask4.web.command.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.exception.Messages;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.TrainService;
import ua.nure.mykytenko.SummaryTask4.exception.DBException;

public class DeleteTrainCommand implements Command {

	private static final Logger LOG = Logger
			.getLogger(DeleteTrainCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String trainIdStr = req.getParameter("trainId");
		long trainId = Long.valueOf(trainIdStr);

		TrainService service = (TrainService) req.getServletContext()
				.getAttribute(Constants.TRAIN_SERVICE);

		List<String> errors = new ArrayList<>();
		boolean result = false;
		try {
			result = service.delete(trainId);
			
			if (!result) {
				errors.add(Messages.SERVER_ERROR);
				LOG.error(String.format("Error while deleting train id=%d",
						trainId));
			}
		} catch (DBException e) {
			errors.add(Messages.SERVER_ERROR);
			LOG.error(String.format("DBException while deleting train id=%d",
					trainId));
		}
		req.getSession().setAttribute(Constants.ERRORS, errors);

		return Path.TRAIN_VIEW_COMMAND;
	}

}
