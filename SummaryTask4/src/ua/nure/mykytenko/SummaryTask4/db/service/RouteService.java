package ua.nure.mykytenko.SummaryTask4.db.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.db.DBUtil;
import ua.nure.mykytenko.SummaryTask4.db.entity.Route;
import ua.nure.mykytenko.SummaryTask4.exception.DBException;
import ua.nure.mykytenko.SummaryTask4.util.PropertyContainer;

/**
 * Methods to manage route entity
 * 
 * @author A.Mykytenko
 */
public class RouteService {

	private static final Logger LOG = Logger.getLogger(RouteService.class);

	/**
	 * Returns routes for the given date and stations.
	 * 
	 * @return List of routes entities.
	 */
	public List<Route> getAllByStationsAndDate(long stationFromId,
			long stationToId, java.sql.Date date) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Route> routes = null;

		try {
			ps = conn.prepareStatement(PropertyContainer
					.get(Constants.GET_ROUTE_BY_STATIONS_AND_DATE));
			int k = 1;
			ps.setLong(k++, stationFromId);
			ps.setLong(k++, stationToId);
			ps.setDate(k++, date);

			LOG.debug("executing ==> " + ps);

			rs = ps.executeQuery();

			routes = new ArrayList<>();
			while (rs.next()) {
				routes.add(extract(rs));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return routes;
	}

	private Route extract(ResultSet rs) throws SQLException {
		Route route = new Route();
		route.setId(rs.getInt("id"));
		route.setDate(rs.getDate("date"));
		route.setTrainId(rs.getLong("train_id"));

		return route;
	}

	/**
	 * Creates route entity
	 * 
	 * @param item
	 *            Route to create
	 */
	public boolean create(Route item) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Constants.SQL_INSERT_ROUTE),
					Statement.RETURN_GENERATED_KEYS);
			int k = 1;
			ps.setDate(k++, item.getDate());
			ps.setLong(k++, item.getTrainId());

			result = ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (rs.next()) {
				item.setId(rs.getInt(1));
			}

			conn.commit();
		} catch (SQLException e) {
			DBUtil.rollback(conn);
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}

		return result != 0;
	}

	/**
	 * Deletes route entity
	 * 
	 * @param id
	 *            Id of the route to delete
	 */
	public boolean delete(long id) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int result = 0;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Constants.SQL_DELETE_ROUTE));
			ps.setLong(1, id);

			result = ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			DBUtil.rollback(conn);
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, null);
		}
		return result != 0;
	}

	/**
	 * Returns routes for the specified date period .
	 * 
	 * @return List of routes entities.
	 */
	public List<Route> getAllByDates(Date from, Date to) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Route> routes = null;

		try {
			ps = conn.prepareStatement(
					PropertyContainer.get(Constants.SQL_GET_ALL_ROUTES_BY_DATES));
			int k = 1;
			ps.setDate(k++, from);
			ps.setDate(k++, to);

			rs = ps.executeQuery();
			LOG.debug(String.format("Executing %s ", ps));

			routes = new ArrayList<>();
			while (rs.next()) {
				routes.add(extract(rs));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return routes;
	}
}
