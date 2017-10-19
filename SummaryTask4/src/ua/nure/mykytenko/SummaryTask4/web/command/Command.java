package ua.nure.mykytenko.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.mykytenko.SummaryTask4.web.command.CommandContainer;
import ua.nure.mykytenko.SummaryTask4.exception.AppException;

/**
 * Main interface for the Command pattern implementation.
 * 
 * @author A.Mykytenko
 * @see CommandContainer
 */
public interface Command {

	/**
	 * Execution method for command.
	 * 
	 * @return address, where user should be redirected
	 * @throws IOException,
	 *             ServletException, AppException
	 */
	String execute(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException, AppException;
}
