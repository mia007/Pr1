package ua.nure.mykytenko.SummaryTask4.web.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.util.PropertyContainer;

@WebListener
public class SessionListener implements HttpSessionListener {

	private static final Logger LOG = Logger.getLogger(SessionListener.class);

	public void sessionCreated(HttpSessionEvent se) {
		int interval = Integer
				.valueOf(PropertyContainer.get(Constants.SESSION_MAX_INTERVAL));
		se.getSession().setMaxInactiveInterval(interval);
		
		LOG.info(String.format("Session maximum inactive interval set to %d",
				interval));
	}

	public void sessionDestroyed(HttpSessionEvent se) {
	}

}
