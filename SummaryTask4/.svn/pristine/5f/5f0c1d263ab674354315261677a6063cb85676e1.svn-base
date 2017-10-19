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

import ua.nure.mykytenko.SummaryTask4.constants.Constants;
import ua.nure.mykytenko.SummaryTask4.db.DBUtil;
import ua.nure.mykytenko.SummaryTask4.db.entity.Carriage;
import ua.nure.mykytenko.SummaryTask4.db.entity.CarriageType;
import ua.nure.mykytenko.SummaryTask4.exception.DBException;
import ua.nure.mykytenko.SummaryTask4.util.PropertyContainer;

/**
 * Methods to manage carriage entity
 * 
 * @author A.Mykytenko
 */
public class CarriageService {

	private static final Logger LOG = Logger.getLogger(CarriageService.class);

	/**
	 * Returns carriages by train id and route items.
	 * 
	 * @return List of carriage entities.
	 */
	public List<Carriage> getAllByTrainIdRouteItems(long trainId, long routeId, long routeItemFrom, long routeItemTo) {

		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Carriage> carriages = null;

		try {
			ps = conn.prepareStatement(PropertyContainer.get(Constants.GET_CARRIAGE_INFO_BY_TRAIN_ID_ROUTE_ITEMS));
			int k = 1;
			ps.setLong(k++, routeItemFrom);
			ps.setLong(k++, routeItemTo);
			ps.setLong(k++, trainId);
			LOG.debug("executing " + ps);

			rs = ps.executeQuery();

			carriages = new ArrayList<>();

			while (rs.next()) {
				carriages.add(extract(conn, rs, routeId, routeItemFrom, routeItemTo, trainId));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}

		return carriages;
	}

	/**
	 * Extracts a carriage bean from the result set and returns carriage bean with seat map
	 * 
	 * @return Carriage object
	 */
	private Carriage extract(Connection conn, ResultSet rs, long routeId, long routeItemFrom, long routeItemTo,
			long trainId) throws SQLException {
		Carriage c = null;
		PreparedStatement ps = null;
		ResultSet rsSeats = null;
		try {
			c = extract(rs);

			ps = conn.prepareStatement(PropertyContainer.get(Constants.GET_TAKEN_SEATS_BY_CAR_ID_AND_ROUTE_ID));
			int k = 1;
			ps.setLong(k++, c.getId());
			ps.setLong(k++, routeId);
			ps.setLong(k++, trainId);
			ps.setLong(k++, routeItemTo);
			ps.setLong(k++, trainId);
			ps.setLong(k++, routeItemFrom);

			LOG.debug("seats taken ps " + ps);

			rsSeats = ps.executeQuery();

			c.setSeats(getFreeSeats(rsSeats));
			c.setSeatsTaken(c.getSeats().size());

		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new SQLException(e.getMessage(), e);
		} finally {
			DBUtil.close(null, ps, rsSeats);
		}
		return c;
	}

	/**
	 * Method creates carriages entities
	 * 
	 * @param carriages
	 *            List of carriages to create.
	 */
	public boolean createAll(List<Carriage> carriages) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean result = false;

		try {
			ps = conn.prepareStatement(PropertyContainer.get(Constants.SQL_CREATE_CARRIAGE));

			for (Carriage c : carriages) {
				int k = 1;
				ps.setString(k++, c.getTag());
				ps.setBigDecimal(k++, c.getPrice());
				ps.setInt(k++, c.getCarTypeId());
				ps.setInt(k++, c.getTrainId());
				LOG.debug("executing " + ps);
				ps.addBatch();
			}
			result = DBUtil.isBatchSuccessful(ps.executeBatch());

			conn.commit();
		} catch (SQLException e) {
			DBUtil.rollback(conn);
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}

		return result;
	}

	/**
	 * Returns carriages related to the  given train.
	 * 
	 * @param trainId
	 *            ID of the train 
	 * @return List of carriage entities.
	 */
	public List<Carriage> getAllByTrainId(long trainId) {
		Connection conn = DBUtil.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Carriage> carriages = null;

		try {
			ps = conn.prepareStatement(PropertyContainer.get(Constants.GET_CARRIAGE_INFO_BY_TRAIN_ID));
			ps.setLong(1, trainId);

			rs = ps.executeQuery();

			carriages = new ArrayList<>();

			while (rs.next()) {
				carriages.add(extract(rs));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage());
			throw new DBException(e.getMessage(), e);
		} finally {
			DBUtil.close(conn, ps, rs);
		}

		return carriages;
	}
	
	/**
	 * Returns the map of free seats extracted from ResultSet
	 * 
	 * @param rs
	 *            ResultSet from which a seat's number  will be extracted.
	 * @return Map of seats' numbers
	 */
	private Map<Integer, Boolean> getFreeSeats(ResultSet rs) throws SQLException {
		Map<Integer, Boolean> seatsMap = new HashMap<>();
		while (rs.next()) {
			seatsMap.put(rs.getInt(1), true);
		}
		return seatsMap;
	}
	
	/**
	 * Extracts a carriage bean and carriage type bean from the result set.
	 * 
	 * @param rs
	 *            Result set from which a carriage bean will be extracted.
	 * @return Carriage object
	 */
	private Carriage extract(ResultSet rs) throws SQLException {
		Carriage c = new Carriage();
		c.setId(rs.getInt("id"));
		c.setCarTypeId(rs.getInt("type_id"));
		c.setPrice(rs.getBigDecimal("price"));
		c.setTag(rs.getString("tag"));

		CarriageType ct = new CarriageType();
		ct.setId(rs.getInt("type_id"));
		ct.setName(rs.getString("name"));
		ct.setSeatNum(rs.getInt("seat_num"));

		c.setType(ct);

		return c;
	}
}