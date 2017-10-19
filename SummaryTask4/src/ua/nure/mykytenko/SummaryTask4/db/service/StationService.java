package ua.nure.mykytenko.SummaryTask4.db.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;

import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.db.DBUtil;
import ua.nure.mykytenko.SummaryTask4.db.entity.Station;
import ua.nure.mykytenko.SummaryTask4.exception.DBException;
import ua.nure.mykytenko.SummaryTask4.util.PropertyContainer;

/**
 * Methods to manage station entity
 * 
 * @author A.Mykytenko
 */
public class StationService {

	private static final Logger LOG = Logger.getLogger(StationService.class);

	/**
	 * Creates station entity
	 * 
	 * @param item
	 *            Station to create
	 */
	public boolean create(Station item) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result = 0;

		try {
			ps = conn.prepareStatement(PropertyContainer.get(Constants.SQL_CREATE_STATION),
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, item.getName());

			result = ps.executeUpdate();

			rs = ps.getGeneratedKeys();
			if (!rs.next()) {
				throw new DBException("A key for station was not generated");
			}
			item.setId(rs.getInt(1));

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
	 * Updates station entity
	 * 
	 * @param item
	 *            Station to update
	 */
	public boolean update(Station item) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int result = 0;

		try {
			ps = conn.prepareStatement(PropertyContainer.get(Constants.SQL_UPDATE_STATION));

			int k = 1;
			ps.setString(k++, item.getName());
			ps.setLong(k++, item.getId());

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
	 * Deletes station entity
	 * 
	 * @param id
	 *            Id of the station to delete
	 */
	public boolean delete(long id) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		int result = 0;

		try {
			ps = conn.prepareStatement(PropertyContainer.get(Constants.SQL_DELETE_STATION));
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
	 * Returns stations satisfied given filter or all stations.
	 * 
	 * @return List of Station entities.
	 */
	public List<Station> getAll(String filter) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Station> stations = new ArrayList<>();
		String query = (filter != null) ? PropertyContainer.get(Constants.GET_ALL_STATIONS_LIKE)
				: PropertyContainer.get(Constants.GET_ALL_STATIONS);

		try {
			if (filter != null) {
				query = query.replace("?", filter);
			}
			ps = conn.prepareStatement(query);
			LOG.debug(String.format("Executing statement %s", ps.toString()));

			rs = ps.executeQuery();
			while (rs.next()) {
				stations.add(extractStation(rs));
			}
		} catch (SQLException e) {
			DBUtil.rollback(conn);
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return stations;
	}

	/**
	 * Returns station with the given name.
	 * 
	 * @return Station entity.
	 */
	public Station getByName(String name) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Station station = null;
		final String query = PropertyContainer.get(Constants.GET_STATION_BY_NAME);

		try {
			ps = conn.prepareStatement(query);
			ps.setString(1, name);

			rs = ps.executeQuery();
			if (rs.next()) {
				station = extractStation(rs);
			}
		} catch (SQLException e) {
			DBUtil.rollback(conn);
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		return station;
	}

	/**
	 * Returns stations by the given ids.
	 * 
	 * @return List of Station entities.
	 */
	public List<Station> getByRouteItems(long routeItemId1, long routeItemId2) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Station> stations = null;

		try {
			ps = conn.prepareStatement(PropertyContainer.get(Constants.GET_STATIONS_BY_ROUTE_ITEMS));
			int k = 1;
			ps.setLong(k++, routeItemId1);
			ps.setLong(k++, routeItemId2);

			rs = ps.executeQuery();
			stations = new ArrayList<>();
			while (rs.next()) {
				stations.add(extractStation(rs));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}
		if (stations.size() != 2) {
			throw new DBException("Unexpected result set.");
		}
		return stations;
	}

	public Map<String, Station> getStations(String stationFrom, String stationTo) {
		Map<String, Station> stations = new HashMap<>();
		stations.put(stationFrom, getByName(stationFrom));
		stations.put(stationTo, getByName(stationTo));
		return stations;
	}

	/**
	 * Extracts a Station bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a station bean will be extracted.
	 * @return Station bean
	 */
	private Station extractStation(ResultSet rs) throws SQLException {
		Station s = new Station();
		s.setId(rs.getInt("id"));
		s.setName(rs.getString("name"));
		return s;
	}

}
