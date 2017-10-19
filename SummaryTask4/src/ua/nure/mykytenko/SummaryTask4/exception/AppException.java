package ua.nure.mykytenko.SummaryTask4.exception;

/**
 * An exception that provides information on an application error.
 * 
 * @author A. Mykytenko
 * 
 */
public class AppException extends RuntimeException{

	private static final long serialVersionUID = -5317641404415251994L;

	public AppException() {
		super();
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public AppException(String message) {
		super(message);
	}

}
