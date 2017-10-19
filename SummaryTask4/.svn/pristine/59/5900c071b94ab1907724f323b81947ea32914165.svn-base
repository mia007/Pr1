package ua.nure.mykytenko.SummaryTask4.web.command.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.web.command.Command;
import ua.nure.mykytenko.SummaryTask4.db.service.UserService;
import ua.nure.mykytenko.SummaryTask4.exception.AppException;
import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.db.entity.Role;
import ua.nure.mykytenko.SummaryTask4.db.entity.User;
import ua.nure.mykytenko.SummaryTask4.util.DateUtil;
import ua.nure.mykytenko.SummaryTask4.util.Util;

public class SignUpCommand implements Command {

	private static final Logger LOG = Logger.getLogger(SignUpCommand.class);

	private static final String PASSWORD_REPEAT = "repPassword";

	private static final String FIRST_NAME = "firstName";

	private static final String LAST_NAME = "lastName";

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse res) {
		String email = req.getParameter(Constants.EMAIL);
		String password = req.getParameter(Constants.PASSWORD);
		String repPassword = req.getParameter(PASSWORD_REPEAT);
		String firstName = req.getParameter(FIRST_NAME);
		String lastName = req.getParameter(LAST_NAME);

		HttpSession session = req.getSession();
		session.setAttribute(Constants.EMAIL, email);
		session.setAttribute(FIRST_NAME, firstName);
		session.setAttribute(LAST_NAME, lastName);

		List<String> errors = new ArrayList<>();
		session.setAttribute(Constants.ERRORS, errors);

		// validate
		if (repPassword == null || !repPassword.equals(password)) {
			errors.add("message.passwords_not_equal");
		}

		User user = validate(email, repPassword, firstName, lastName, errors);
		if (!errors.isEmpty()) {

			return Path.SIGN_UP_VIEW_COMMAND;
		}
		String key = generateKey(user);
		session.setAttribute(Constants.KEY, key);
		session.setAttribute(Constants.USER_BEAN, user);

		User user2 = (User) session.getAttribute(Constants.USER_BEAN);
		UserService service = (UserService) req.getServletContext().getAttribute(Constants.USER_SERVICE);

		if (service.create(user2)) {
			LOG.info("User was successfully created");
		} else {
			LOG.error("User creation failed");
			throw new AppException("User creation failed");
		}

		return Path.LOGIN_VIEW_COMMAND;
	}

	private String generateKey(User user) {
		String input = String.format("%s%s", DateUtil.today().toString(),
				user.getEmail());
		return Util.hash(input);
	}

	private User validate(String email, String password, String firstName,
			String lastName, List<String> errors) {
		User user = new User();
		user.setEmail(email);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setRole(Role.USER);
		return user;
	}

}
