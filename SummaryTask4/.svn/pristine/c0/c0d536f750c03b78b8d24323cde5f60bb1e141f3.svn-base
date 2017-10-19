package ua.nure.mykytenko.SummaryTask4.util;

import java.util.Collections;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.exception.Messages;
import ua.nure.mykytenko.SummaryTask4.constants.Path;
import ua.nure.mykytenko.SummaryTask4.exception.AppException;

/**
 * Class creates ResourceBundle object for config properties 
 * 
 * @author A.Mykytenko
 */
public final class PropertyContainer {

	private static final Logger LOG = Logger.getLogger(PropertyContainer.class);

	private static ResourceBundle bundle;

	static {
		bundle = ResourceBundle.getBundle(Path.SQL_RES);

		LOG.debug(Collections.list(bundle.getKeys()));
	}

	public static String get(String key) throws AppException {
		try {
			return bundle.getString(key);
		} catch (MissingResourceException e) {
			LOG.error(String.format(Messages.ERR_RESOURCE_NOT_FOUND,
					e.getMessage()));
			throw new AppException(String
					.format(Messages.ERR_RESOURCE_NOT_FOUND, e.getMessage()), e);
		}
	}
}
