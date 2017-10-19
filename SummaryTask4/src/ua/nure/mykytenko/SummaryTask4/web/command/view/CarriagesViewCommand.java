package ua.nure.mykytenko.SummaryTask4.web.command.view;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.service.CarriageService;
import ua.nure.mykytenko.SummaryTask4.db.service.CarriageTypeService;
import ua.nure.mykytenko.SummaryTask4.db.entity.Carriage;
import ua.nure.mykytenko.SummaryTask4.db.entity.CarriageType;

public class CarriagesViewCommand implements Command {

	private static final Logger LOG = Logger.getLogger(CarriagesViewCommand.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String trainId = req.getParameter("trainId");

		ServletContext context = req.getServletContext();
		HttpSession session = req.getSession();

		if (trainId != null) {
			int id = Integer.valueOf(trainId);
			session.setAttribute(Constants.TRAIN_ID, id);

			CarriageService carriageService = (CarriageService) context.getAttribute(Constants.CARRIAGE_SERVICE);
			List<Carriage> carriages = carriageService.getAllByTrainId(id);
			LOG.info("Carriages obtained ==> " + carriages.toString());
			session.setAttribute("carriages", carriages);
		}

		CarriageTypeService service = (CarriageTypeService) context.getAttribute(Constants.CARRIAGE_TYPE_SERVICE);
		List<CarriageType> types = service.getAll();
		LOG.info(String.format("Carriage types obtained ==> %s", types));

		req.setAttribute("carTypes", types);

		return Path.CARRIAGES_VIEW;
	}

}
