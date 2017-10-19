package ua.nure.your_last_name.SummaryTask4.exception;

/**
 * An exception that provides information on a database access error.
 * 
 * @author D.Kolesnikov
 * 
 */
public class DBException extends AppException {


	public DBException() {
		super();
	}

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

}
