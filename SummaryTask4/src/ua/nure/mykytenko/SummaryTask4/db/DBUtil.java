package ua.nure.mykytenko.SummaryTask4.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.exception.DBException;
import ua.nure.mykytenko.SummaryTask4.exception.Messages;

/**
 * Util methods for work with database
 * 
 * @author A.Mykytenko
 */
public final class DBUtil {

	private static final Logger LOG = Logger.getLogger(DBUtil.class);

	private static DataSource ds;

	static {
		try {
			LOG.debug("Loading data source");
			Context context = (Context) new InitialContext().lookup("java:/comp/env");
			ds = (DataSource) context.lookup("jdbc/SummaryTask4");
		} catch (NamingException e) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_DATASOURCE);
			 throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATASOURCE, e);
		}
	}

	/**
	 * Closes Connection, Statement, Resultset
	 * 
	 * @param con
	 *            Connection
	 * @param st
	 *            Statement
	 * @param rs
	 *            ResultSet
	 * 
	 * @throws DBException
	 */
	public static void close(Connection conn, Statement s, ResultSet rs) throws DBException {
		try {
			if (rs != null) {
				rs.close();
			}
			if (s != null) {
				s.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_CLOSE_RESOURCES);
			throw new DBException(Messages.ERR_CANNOT_CLOSE_RESOURCES, e);
		}
	}

	/**
	 * Rollbacks a connection.
	 * 
	 * @param conn
	 *            Connection to be rollbacked.
	 * @throws DBException
	 */
	public static void rollback(Connection conn) throws DBException {
		try {
			conn.rollback();
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_ROLLBACK);
			throw new DBException(Messages.ERR_CANNOT_ROLLBACK, e);
		}
	}

	/**
	 * Returns a DB connection from the Pool Connections.
	 * 
	 * @return DB connection.
	 * @throws DBException
	 */
	public static Connection getConnection() throws DBException {
		Connection conn = null;
		try {
			conn = (Connection) ds.getConnection();
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, e);
		}
		return conn;
	}

	public static boolean isBatchSuccessful(int[] results) {
		for (int r : results) {
			if (r <= 0) {
				return false;
			}
		}
		return true;
	}
}
